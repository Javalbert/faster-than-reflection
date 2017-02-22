/*******************************************************************************
 * Copyright 2017 Albert Shun-Dat Chan
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 *******************************************************************************/
package com.github.javalbert.reflection;

import static java.util.stream.Collectors.*;
import static org.objectweb.asm.Opcodes.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ClassAccessFactory<T> {
	@SuppressWarnings("rawtypes")
	private static final Map<Class, ClassAccess> CLASS_ACCESS_MAP = new WeakHashMap<>();
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassAccessFactory.class);

	private static final String MEMBER_TYPE_FIELD = "field";
	private static final String MEMBER_TYPE_PROPERTY = "property";
	
	public static <T> ClassAccess<T> get(Class<T> clazz) {
		if (ClassAccess.class.isAssignableFrom(clazz)) {
			throw new IllegalArgumentException("should not get class access recursively");
		} else if (Modifier.isInterface(clazz.getModifiers()) 
				|| Modifier.isAbstract(clazz.getModifiers())) {
			throw new IllegalArgumentException("class cannot be an interface or be abstract");
		}
		
		try {
			return getInstance(clazz);
		} catch (Exception e) {
			LOGGER.error("Error occurred while trying to get ClassAccess for " + clazz, e);
			throw new RuntimeException(e);
		}
	}
	
	private static Class<?> createClassAccessClass(Class<?> clazz)
			throws ClassNotFoundException {
		String className = getClassNameOfClassAccessFor(clazz);
		try {
			return AccessClassLoader.get(clazz).loadClass(className);
		} catch (ClassNotFoundException ignored) {}
		
		new ClassAccessFactory<>(clazz).buildClassAccessClass();
		return AccessClassLoader.get(clazz).loadClass(className);
	}
	
	private static String getClassNameOfClassAccessFor(Class<?> clazz) {
		return clazz.getName() + "$" + clazz.getSimpleName() + "ClassAccess"; // com.github.javalbert.reflection.test.Foo$FooClassAccess
	}
	
	@SuppressWarnings("unchecked")
	private static <T> ClassAccess<T> getInstance(Class<T> clazz)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ClassAccess<T> classAccess = CLASS_ACCESS_MAP.get(clazz);
		
		if (classAccess == null) {
			synchronized (CLASS_ACCESS_MAP) {
				classAccess = CLASS_ACCESS_MAP.get(clazz);
				
				if (classAccess == null) {
					Class<?> classAccessClass = createClassAccessClass(clazz);
					classAccess = (ClassAccess<T>)classAccessClass.newInstance();
					CLASS_ACCESS_MAP.put(clazz, classAccess);
				}
			}
		}
		return classAccess;
	}
	
	private static Label[] getTableSwitchLabelsForAccess(
			Label defaultCaseLabel,
			List<? extends MemberInfo> members) {
		List<Label> labels = new ArrayList<>();
		
		int currentMember = 0;
		int memberIndex = members.get(0).memberIndex;
		
		int hi = members.get(members.size() - 1).memberIndex;
		int lo = members.get(0).memberIndex;
		
		for (int i = lo; i <= hi; i++) {
			MemberInfo member = members.get(currentMember);
			
			if (memberIndex < member.memberIndex) {
				labels.add(defaultCaseLabel);
				memberIndex++;
			} else {
				labels.add(new Label());
				memberIndex = member.memberIndex + 1;
				currentMember++;
			}
		}
		return labels.stream().toArray(s -> new Label[s]);
	}
	
	private static boolean isDescriptorDoubleOrLong(String descriptor) {
		return descriptor.equals(Type.DOUBLE_TYPE.getDescriptor())
				|| descriptor.equals(Type.LONG_TYPE.getDescriptor());
	}
	
	private static Label[] newLabelArray(int size) {
		Label[] labels = new Label[size];
		for (int i = 0; i < size; i++) {
			labels[i] = new Label();
		}
		return labels;
	}
	
	private static void setAccessible(AccessibleObject object) {
		object.setAccessible(true);
	}
	
	/**
	 * Use {@link MethodVisitor#visitTableSwitchInsn(int, int, Label, Label...)}
	 * instead of {@link MethodVisitor#visitLookupSwitchInsn(Label, int[], Label[])}
	 * @return
	 */
	private static boolean useTableSwitch(List<? extends MemberInfo> members) {
		int hi = members.get(members.size() - 1).memberIndex;
		int lo = members.get(0).memberIndex;
		int nlabels = members.size();
		
		// CREDIT: http://stackoverflow.com/a/31032054
		// CREDIT: http://hg.openjdk.java.net/jdk8/jdk8/langtools/file/30db5e0aaf83/src/share/classes/com/sun/tools/javac/jvm/Gen.java#l1153
		// Determine whether to issue a tableswitch or a lookupswitch
        // instruction.
        long table_space_cost = 4 + ((long) hi - lo + 1); // words
        long table_time_cost = 3; // comparisons
        long lookup_space_cost = 3 + 2 * (long) nlabels;
        long lookup_time_cost = nlabels;
        return
            nlabels > 0 &&
            table_space_cost + 3 * table_time_cost <=
            lookup_space_cost + 3 * lookup_time_cost;
	}
	
	private static class AccessInfo {
		private static AccessInfo forPrimitive(String memberType, Type type) {
			String camelCaseClassName = WordUtils.capitalize(type.getClassName());
			String capitalizedMemberType = WordUtils.capitalize(memberType);
			
			return new AccessInfo(
					memberType,
					"get" + camelCaseClassName + capitalizedMemberType,
					"set" + camelCaseClassName + capitalizedMemberType,
					type.getClassName(),
					type.getDescriptor(),
					type.getOpcode(ILOAD),
					type.getOpcode(IRETURN));
		}
		
		private static AccessInfo forPrimitiveWrapper(String memberType, Class<?> clazz) {
			Class<?> primitiveClass = ClassUtils.wrapperToPrimitive(clazz);
			String camelCaseClassName = WordUtils.capitalize(primitiveClass.getName());
			String capitalizedMemberType = WordUtils.capitalize(memberType);
			
			return new AccessInfo(
					memberType,
					"getBoxed" + camelCaseClassName + capitalizedMemberType,
					"setBoxed" + camelCaseClassName + capitalizedMemberType,
					clazz.getName(),
					Type.getDescriptor(clazz),
					ALOAD,
					ARETURN);
		}
		
		private static AccessInfo forReferenceType(String memberType, Class<?> clazz) {
			String capitalizedMemberType = WordUtils.capitalize(memberType);
			
			return new AccessInfo(
					memberType,
					"get" + clazz.getSimpleName() + capitalizedMemberType,
					"set" + clazz.getSimpleName() + capitalizedMemberType,
					clazz.getName(),
					Type.getDescriptor(clazz),
					ALOAD,
					ARETURN);
		}
		
		private final String className;
		private final String descriptor;
		private final String getMethodName;
		private final int loadOpcode;
		private final String memberType;
		private final int returnOpcode;
		private final String setMethodName;
		
		private AccessInfo(
				String memberType,
				String getMethodName,
				String setMethodName,
				String className,
				String descriptor,
				int loadOpcode,
				int returnOpcode) {
			this.className = className;
			this.descriptor = descriptor;
			this.getMethodName = getMethodName;
			this.loadOpcode = loadOpcode;
			this.memberType = memberType;
			this.returnOpcode = returnOpcode;
			this.setMethodName = setMethodName;
		}
	}
	
	private static class FieldInfo extends MemberInfo {
		private final int getFieldOpcode;
		private final int setFieldOpcode;
		
		private FieldInfo(Field field, int fieldIndex) {
			super(
					field.getName(),
					fieldIndex,
					field.getType()
					);
			getFieldOpcode = (field.getModifiers() & Modifier.STATIC) == 0 ? GETFIELD : GETSTATIC;
			setFieldOpcode = (field.getModifiers() & Modifier.STATIC) == 0 ? PUTFIELD : PUTSTATIC;
		}
	}
	
	private static abstract class MemberInfo {
		protected final String descriptor;
		protected final String internalName;
		protected final int memberIndex;
		protected final String name;
		protected final Class<?> type;
		
		private MemberInfo(
				String name,
				int memberIndex,
				Class<?> type) {
			this.descriptor = Type.getDescriptor(type);
			this.internalName = Type.getInternalName(type);
			this.memberIndex = memberIndex;
			this.name = name;
			this.type = type;
		}
	}
	
	private static class PropertyInfo extends MemberInfo {
		private final String readMethodName;
		private final String writeMethodName;

		private PropertyInfo(PropertyDescriptor propertyDescriptor, int propertyIndex) {
			super(
					propertyDescriptor.getName(),
					propertyIndex,
					propertyDescriptor.getPropertyType());
			readMethodName = Optional.ofNullable(propertyDescriptor.getReadMethod())
					.map(prop -> prop.getName())
					.orElse(null);
			writeMethodName = Optional.ofNullable(propertyDescriptor.getWriteMethod())
					.map(prop -> prop.getName())
					.orElse(null);
		}
	}
	
	private static class StringCaseReturnIndex {
		private static int compareIndex(StringCaseReturnIndex a, StringCaseReturnIndex b) {
			return Integer.compare(a.index, b.index);
		}
		
		private static int compareHashCode(StringCaseReturnIndex a, StringCaseReturnIndex b) {
			return Integer.compare(a.hashCode, b.hashCode);
		}
		
		private final int hashCode;
		private final int index;
		private final String name;
		private final Label returnIndexLabel = new Label();
		
		private StringCaseReturnIndex(String name, int index) {
			hashCode = name.hashCode();
			this.index = index;
			this.name = name;
		}
	}

	private final List<PropertyInfo> accessorInfoList = new ArrayList<>();
	private String classAccessInternalName;
	private String classAccessTypeDescriptor;
	private String classTypeDescriptor;
	private final Class<T> clazz;
	private final ClassWriter cw;
	private final List<FieldInfo> fieldInfoList = new ArrayList<>();
	private final List<Field> fields;
	private String internalName;
	private final List<PropertyInfo> mutatorInfoList = new ArrayList<>();
	private MethodVisitor mv;
	private final List<PropertyDescriptor> propertyDescriptors;
	private final Map<String, List<PropertyInfo>> typeToAccessorsMap = new HashMap<>();
	private final Map<String, List<FieldInfo>> typeToFieldsMap = new HashMap<>();
	private final Map<String, List<PropertyInfo>> typeToMutatorsMap = new HashMap<>();
	
	private ClassAccessFactory(Class<T> clazz) {
		try {
			BeanInfo info = Introspector.getBeanInfo(clazz);
			propertyDescriptors = Collections.unmodifiableList(Arrays.stream(info.getPropertyDescriptors())
					.filter(prop -> !prop.getName().equals("class"))
					.collect(toList()));
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
		
		for (int i = 0; i < propertyDescriptors.size(); i++) {
			putIntoTypeToPropertyMaps(propertyDescriptors.get(i), i);
		}
		
		Field[] declaredFields = clazz.getDeclaredFields();
		List<Field> fields = new ArrayList<>();
		for (int i = 0; i < declaredFields.length; i++) {
			Field field = declaredFields[i];
			setAccessible(field);

			fields.add(field);
			putIntoTypeToFieldsMap(field, i);
		}
		this.fields = Collections.unmodifiableList(fields);
		
		this.clazz = clazz;
		cw = new ClassWriter(0);
	}
	
	private void buildClassAccessClass() {
		visitClass();
		visitDefaultConstructor();
		visitIndexMethod(MEMBER_TYPE_FIELD, getFieldIndexSwitchCases());
		visitFieldAccessMethods();
		visitIndexMethod(MEMBER_TYPE_PROPERTY, getPropertyIndexSwitchCases());
		visitPropertyAccessMethods();
		cw.visitEnd();
		AccessClassLoader.get(clazz).defineClass(getClassNameOfClassAccessFor(clazz), cw.toByteArray());
	}
	
	private List<StringCaseReturnIndex> getFieldIndexSwitchCases() {
		List<StringCaseReturnIndex> fieldIndexSwitchCases = new ArrayList<>();
		for (int i = 0; i < fields.size(); i++) {
			fieldIndexSwitchCases.add(new StringCaseReturnIndex(fields.get(i).getName(), i));
		}
		return fieldIndexSwitchCases;
	}
	
	private List<StringCaseReturnIndex> getPropertyIndexSwitchCases() {
		List<StringCaseReturnIndex> propertyIndexSwitchCases = new ArrayList<>();
		for (int i = 0; i < propertyDescriptors.size(); i++) {
			propertyIndexSwitchCases.add(new StringCaseReturnIndex(propertyDescriptors.get(i).getName(), i));
		}
		return propertyIndexSwitchCases;
	}
	
	private void putIntoTypeToFieldsMap(Field field, int fieldIndex) {
		String key = field.getType().getName();
		
		List<FieldInfo> fieldsOfType = typeToFieldsMap.get(key);
		if (fieldsOfType == null) {
			fieldsOfType = new ArrayList<>();
			typeToFieldsMap.put(key, fieldsOfType);
		}
		FieldInfo fieldInfo = new FieldInfo(field, fieldIndex);
		
		fieldsOfType.add(fieldInfo);
		fieldInfoList.add(fieldInfo);
	}
	
	private void putIntoTypeToPropertyMaps(PropertyDescriptor propertyDescriptor, int propertyIndex) {
		String key = propertyDescriptor.getPropertyType().getName();
		
		PropertyInfo propertyInfo = new PropertyInfo(propertyDescriptor, propertyIndex);
		
		if (propertyDescriptor.getReadMethod() != null) {
			List<PropertyInfo> accessorsOfType = typeToAccessorsMap.get(key);
			if (accessorsOfType == null) {
				accessorsOfType = new ArrayList<>();
				typeToAccessorsMap.put(key, accessorsOfType);
			}
			
			accessorsOfType.add(propertyInfo);
			accessorInfoList.add(propertyInfo);
		}
		
		if (propertyDescriptor.getWriteMethod() != null) {
			List<PropertyInfo> mutatorsOfType = typeToMutatorsMap.get(key);
			if (mutatorsOfType == null) {
				mutatorsOfType = new ArrayList<>();
				typeToMutatorsMap.put(key, mutatorsOfType);
			}
			
			mutatorsOfType.add(propertyInfo);
			mutatorInfoList.add(propertyInfo);
		}
	}
	
	private void visitAccessGetter(
			List<? extends MemberInfo> memberInfoList,
			AccessInfo accessInfo) {
		mv = cw.visitMethod(
				ACC_PUBLIC,
				accessInfo.getMethodName,
				"(" + classTypeDescriptor + "I)" + accessInfo.descriptor,
				null,
				null);
		mv.visitCode();
		Label firstLabel = new Label();
		mv.visitLabel(firstLabel);
		mv.visitVarInsn(ILOAD, 2);

		Label defaultCaseLabel = new Label();

		if (memberInfoList.isEmpty()) {
			mv.visitInsn(POP);
			mv.visitLabel(defaultCaseLabel);
			visitAccessGetterLastPart(accessInfo.memberType, firstLabel);
			return;
		}
		
		boolean useTableSwitch = useTableSwitch(memberInfoList);
		Label[] labels = useTableSwitch ? getTableSwitchLabelsForAccess(defaultCaseLabel, memberInfoList)
				: newLabelArray(memberInfoList.size());
		
		if (useTableSwitch) {
			mv.visitTableSwitchInsn(
					memberInfoList.get(0).memberIndex,
					memberInfoList.get(memberInfoList.size() - 1).memberIndex,
					defaultCaseLabel,
					labels
					);
		} else {
			mv.visitLookupSwitchInsn(
					defaultCaseLabel,
					memberInfoList.stream()
					.mapToInt(f -> f.memberIndex)
					.toArray(),
					labels);
		}
		
		for (int i = 0; i < memberInfoList.size(); i++) {
			MemberInfo memberInfo = memberInfoList.get(i);
			
			mv.visitLabel(labels[i]);
			mv.visitFrame(F_SAME, 0, null, 0, null);
			mv.visitVarInsn(ALOAD, 1);
			
			switch (accessInfo.memberType) {
				case MEMBER_TYPE_FIELD:
					mv.visitFieldInsn(((FieldInfo)memberInfo).getFieldOpcode, internalName, memberInfo.name, accessInfo.descriptor);
					break;
				case MEMBER_TYPE_PROPERTY:
					mv.visitMethodInsn(INVOKEVIRTUAL, internalName, ((PropertyInfo)memberInfo).readMethodName, "()" + accessInfo.descriptor, false);
					break;
			}
			
			mv.visitInsn(accessInfo.returnOpcode);
		}
		
		mv.visitLabel(defaultCaseLabel);
		mv.visitFrame(F_SAME, 0, null, 0, null);
		
		visitAccessGetterLastPart(accessInfo.memberType, firstLabel);
	}
	
	private void visitAccessGetterBridge(AccessInfo accessInfo) {
		visitAccessGetterBridge(
				accessInfo.getMethodName,
				accessInfo.descriptor,
				accessInfo.returnOpcode);
	}
	
	private void visitAccessGetterBridge(
			String methodName,
			String returnTypeDescriptor,
			int returnOpcode) {
		mv = cw.visitMethod(
				ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC,
				methodName,
				"(Ljava/lang/Object;I)" + returnTypeDescriptor,
				null,
				null);
		mv.visitCode();
		mv.visitLabel(new Label());
		mv.visitVarInsn(ALOAD, 0);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitTypeInsn(CHECKCAST, internalName);
		mv.visitVarInsn(ILOAD, 2);
		mv.visitMethodInsn(
				INVOKEVIRTUAL,
				classAccessInternalName,
				methodName,
				"(" + classTypeDescriptor + "I)" + returnTypeDescriptor,
				false);
		mv.visitInsn(returnOpcode);
		mv.visitMaxs(3, 3);
		mv.visitEnd();
	}
	
	private void visitAccessGetterLastPart(String memberType, Label firstLabel) {
		mv.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
		mv.visitInsn(DUP);
		mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
		mv.visitInsn(DUP);
		mv.visitLdcInsn("No " + memberType + " with index: ");
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V", false);
		mv.visitVarInsn(ILOAD, 2);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V", false);
		mv.visitInsn(ATHROW);
		Label lastLabel = new Label();
		mv.visitLabel(lastLabel);
		mv.visitLocalVariable("this", classAccessTypeDescriptor, null, firstLabel, lastLabel, 0);
		mv.visitLocalVariable("obj", classTypeDescriptor, null, firstLabel, lastLabel, 1);
		mv.visitLocalVariable(memberType + "Index", "I", null, firstLabel, lastLabel, 2);
		mv.visitMaxs(5, 3);
		mv.visitEnd();
	}
	
	private void visitClass() {
		internalName = Type.getInternalName(clazz); // com/github/javalbert/reflection/test/Foo
		String classAccessSimpleName = clazz.getSimpleName() + "ClassAccess"; // FooClassAccess
		classAccessInternalName = internalName + "$" + classAccessSimpleName; // com/github/javalbert/reflection/test/Foo$FooClassAccess
		
		cw.visit(
				V1_8,
				ACC_PUBLIC + ACC_SUPER,
				classAccessInternalName,
				internalName,
				"sun/reflect/MagicAccessorImpl"/*CREDIT: https://github.com/dimzon/reflectasm/blob/master/src/com/esotericsoftware/reflectasm/ClassAccess.java*/,
				new String[] { Type.getInternalName(ClassAccess.class) });
		cw.visitSource(clazz.getSimpleName() + ".java", null);
		cw.visitInnerClass(classAccessInternalName, internalName, classAccessSimpleName, ACC_PUBLIC + ACC_STATIC);
		
		classAccessTypeDescriptor = "L" + classAccessInternalName + ";";
		classTypeDescriptor = "L" + internalName + ";";
	}
	
	private void visitDefaultConstructor() {
		mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
		mv.visitInsn(RETURN);
		Label l1 = new Label();
		mv.visitLabel(l1);
		mv.visitLocalVariable("this", classAccessTypeDescriptor, null, l0, l1, 0);
		mv.visitMaxs(1, 1);
		mv.visitEnd();
	}
	
	private void visitFieldAccessMethods() {
		List<AccessInfo> fieldAccessInfoList = Collections.unmodifiableList(
				Arrays.asList(
						// Primitive types
						//
						AccessInfo.forPrimitive(MEMBER_TYPE_FIELD, Type.BOOLEAN_TYPE),
						AccessInfo.forPrimitive(MEMBER_TYPE_FIELD, Type.BYTE_TYPE),
						AccessInfo.forPrimitive(MEMBER_TYPE_FIELD, Type.CHAR_TYPE),
						AccessInfo.forPrimitive(MEMBER_TYPE_FIELD, Type.DOUBLE_TYPE),
						AccessInfo.forPrimitive(MEMBER_TYPE_FIELD, Type.FLOAT_TYPE),
						AccessInfo.forPrimitive(MEMBER_TYPE_FIELD, Type.INT_TYPE),
						AccessInfo.forPrimitive(MEMBER_TYPE_FIELD, Type.LONG_TYPE),
						AccessInfo.forPrimitive(MEMBER_TYPE_FIELD, Type.SHORT_TYPE),
						// Primitive wrapper types
						//
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_FIELD, Boolean.class),
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_FIELD, Byte.class),
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_FIELD, Character.class),
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_FIELD, Double.class),
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_FIELD, Float.class),
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_FIELD, Integer.class),
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_FIELD, Long.class),
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_FIELD, Short.class),
						// Common reference types
						//
						AccessInfo.forReferenceType(MEMBER_TYPE_FIELD, BigDecimal.class),
						AccessInfo.forReferenceType(MEMBER_TYPE_FIELD, Date.class),
						AccessInfo.forReferenceType(MEMBER_TYPE_FIELD, LocalDate.class),
						AccessInfo.forReferenceType(MEMBER_TYPE_FIELD, LocalDateTime.class),
						AccessInfo.forReferenceType(MEMBER_TYPE_FIELD, String.class)
						)
				);
		
		for (int i = 0; i < fieldAccessInfoList.size(); i++) {
			AccessInfo fieldAccessInfo = fieldAccessInfoList.get(i);

			visitAccessGetter(typeToFieldsMap.get(fieldAccessInfo.className), fieldAccessInfo);
			visitAccessGetterBridge(fieldAccessInfo);
			visitFieldAccessSetter(fieldAccessInfo);
			visitFieldAccessSetterBridge(fieldAccessInfo);
		}
		
		visitGeneralAccessGetter("getField", MEMBER_TYPE_FIELD, fieldInfoList);
		visitAccessGetterBridge("getField", "Ljava/lang/Object;", ARETURN);
		visitGeneralFieldAccessSetter();
		visitFieldAccessSetterBridge("setField", ALOAD, "Ljava/lang/Object;");
	}
	
	private void visitFieldAccessSetter(AccessInfo fieldAccessInfo) {
		mv = cw.visitMethod(
				ACC_PUBLIC,
				fieldAccessInfo.setMethodName,
				"(" + classTypeDescriptor + "I" + fieldAccessInfo.descriptor + ")V",
				null,
				null);
		mv.visitCode();
		Label firstLabel = new Label();
		mv.visitLabel(firstLabel);
		mv.visitVarInsn(ILOAD, 2);

		Label defaultCaseLabel = new Label();

		List<FieldInfo> fields = typeToFieldsMap.get(fieldAccessInfo.className);
		if (fields.isEmpty()) {
			mv.visitInsn(POP);
			mv.visitLabel(defaultCaseLabel);
			visitFieldAccessSetterLastPart(fieldAccessInfo.descriptor, firstLabel, null);
			return;
		}
		
		boolean useTableSwitch = useTableSwitch(fields);
		Label[] labels = useTableSwitch ? getTableSwitchLabelsForAccess(defaultCaseLabel, fields)
				: newLabelArray(fields.size());
		
		if (useTableSwitch) {
			mv.visitTableSwitchInsn(
					fields.get(0).memberIndex,
					fields.get(fields.size() - 1).memberIndex,
					defaultCaseLabel,
					labels
					);
		} else {
			mv.visitLookupSwitchInsn(
					defaultCaseLabel,
					fields.stream()
					.mapToInt(f -> f.memberIndex)
					.toArray(),
					labels);
		}
		
		Label breakLabel = new Label();
		
		for (int i = 0; i < fields.size(); i++) {
			FieldInfo field = fields.get(i);
			
			mv.visitLabel(labels[i]);
			mv.visitFrame(F_SAME, 0, null, 0, null);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitVarInsn(fieldAccessInfo.loadOpcode, 3);
			mv.visitFieldInsn(field.setFieldOpcode, internalName, field.name, fieldAccessInfo.descriptor);
			mv.visitLabel(new Label());
			mv.visitJumpInsn(GOTO, breakLabel);
		}
		
		mv.visitLabel(defaultCaseLabel);
		mv.visitFrame(F_SAME, 0, null, 0, null);
		
		visitFieldAccessSetterLastPart(fieldAccessInfo.descriptor, firstLabel, breakLabel);
	}
	
	private void visitFieldAccessSetterBridge(AccessInfo fieldAccessInfo) {
		visitFieldAccessSetterBridge(
				fieldAccessInfo.setMethodName,
				fieldAccessInfo.loadOpcode,
				fieldAccessInfo.descriptor);
	}
	
	private void visitFieldAccessSetterBridge(
			String methodName,
			int loadOpcode,
			String descriptor) {
		mv = cw.visitMethod(
				ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC,
				methodName,
				"(Ljava/lang/Object;I" + descriptor + ")V",
				null,
				null);
		mv.visitCode();
		mv.visitLabel(new Label());
		mv.visitVarInsn(ALOAD, 0);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitTypeInsn(CHECKCAST, internalName);
		mv.visitVarInsn(ILOAD, 2);
		mv.visitVarInsn(loadOpcode, 3);
		mv.visitMethodInsn(
				INVOKEVIRTUAL,
				classAccessInternalName,
				methodName,
				"(" + classTypeDescriptor + "I" + descriptor + ")V",
				false);
		mv.visitInsn(RETURN);
		if (isDescriptorDoubleOrLong(descriptor)) {
			mv.visitMaxs(5, 5);
		} else {
			mv.visitMaxs(4, 4);
		}
		mv.visitEnd();
	}
	
	private void visitFieldAccessSetterLastPart(
			String fieldDescriptor,
			Label firstLabel,
			Label breakLabel) {
		mv.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
		mv.visitInsn(DUP);
		mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
		mv.visitInsn(DUP);
		mv.visitLdcInsn("No field with index: ");
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V", false);
		mv.visitVarInsn(ILOAD, 2);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V", false);
		mv.visitInsn(ATHROW);
		
		mv.visitLabel(breakLabel);
		mv.visitFrame(F_SAME, 0, null, 0, null);
		mv.visitInsn(RETURN);
		
		Label lastLabel = new Label();
		mv.visitLabel(lastLabel);
		mv.visitLocalVariable("this", classAccessTypeDescriptor, null, firstLabel, lastLabel, 0);
		mv.visitLocalVariable("obj", classTypeDescriptor, null, firstLabel, lastLabel, 1);
		mv.visitLocalVariable("fieldIndex", "I", null, firstLabel, lastLabel, 2);
		mv.visitLocalVariable("x", fieldDescriptor, null, firstLabel, lastLabel, 3);
		mv.visitMaxs(5, isDescriptorDoubleOrLong(fieldDescriptor) ? 5 : 4);
		mv.visitEnd();
	}
	
	private void visitGeneralAccessGetter(
			String methodName,
			String memberType,
			List<? extends MemberInfo> memberInfoList) {
		mv = cw.visitMethod(
				ACC_PUBLIC,
				methodName,
				"(" + classTypeDescriptor + "I)Ljava/lang/Object;",
				null,
				null);
		mv.visitCode();
		Label firstLabel = new Label();
		mv.visitLabel(firstLabel);
		mv.visitVarInsn(ILOAD, 2);

		Label defaultCaseLabel = new Label();

		if (memberInfoList.isEmpty()) {
			mv.visitInsn(POP);
			mv.visitLabel(defaultCaseLabel);
			visitAccessGetterLastPart(memberType, firstLabel);
			return;
		}
		
		Label[] labels = getTableSwitchLabelsForAccess(defaultCaseLabel, memberInfoList);
		
		// Always use a table switch because there are no gaps between member indices
		mv.visitTableSwitchInsn(
				memberInfoList.get(0).memberIndex,
				memberInfoList.get(memberInfoList.size() - 1).memberIndex,
				defaultCaseLabel,
				labels
				);
		
		for (int i = 0; i < memberInfoList.size(); i++) {
			MemberInfo member = memberInfoList.get(i);
			
			mv.visitLabel(labels[i]);
			mv.visitFrame(F_SAME, 0, null, 0, null);
			mv.visitVarInsn(ALOAD, 1);
			
			switch (memberType) {
				case MEMBER_TYPE_FIELD:
					mv.visitFieldInsn(((FieldInfo)member).getFieldOpcode, internalName, member.name, member.descriptor);
					break;
				case MEMBER_TYPE_PROPERTY:
					mv.visitMethodInsn(INVOKEVIRTUAL, internalName, member.name, "()" + member.descriptor, false);
					break;
			}
			
			if (member.type.isPrimitive()) {
				Class<?> wrapperType = ClassUtils.primitiveToWrapper(member.type);
				mv.visitMethodInsn(
						INVOKESTATIC,
						Type.getInternalName(wrapperType),
						"valueOf", "(" + member.descriptor +")" + Type.getDescriptor(wrapperType),
						false);
			}
			
			mv.visitInsn(ARETURN);
		}
		
		mv.visitLabel(defaultCaseLabel);
		mv.visitFrame(F_SAME, 0, null, 0, null);
		
		visitAccessGetterLastPart(memberType, firstLabel);
	}
	
	private void visitGeneralFieldAccessSetter() {
		mv = cw.visitMethod(
				ACC_PUBLIC,
				"setField",
				"(" + classTypeDescriptor + "ILjava/lang/Object;)V",
				null,
				null);
		mv.visitCode();
		Label firstLabel = new Label();
		mv.visitLabel(firstLabel);
		mv.visitVarInsn(ILOAD, 2);

		Label defaultCaseLabel = new Label();

		List<FieldInfo> fields = fieldInfoList;
		if (fields.isEmpty()) {
			mv.visitInsn(POP);
			mv.visitLabel(defaultCaseLabel);
			visitFieldAccessSetterLastPart("Ljava/lang/Object;", firstLabel, null);
			return;
		}
		
		Label[] labels = getTableSwitchLabelsForAccess(defaultCaseLabel, fields);

		// Always use a table switch because there are no gaps between field indices
		mv.visitTableSwitchInsn(
				fields.get(0).memberIndex,
				fields.get(fields.size() - 1).memberIndex,
				defaultCaseLabel,
				labels
				);
		
		Label breakLabel = new Label();
		
		for (int i = 0; i < fields.size(); i++) {
			FieldInfo field = fields.get(i);
			
			mv.visitLabel(labels[i]);
			mv.visitFrame(F_SAME, 0, null, 0, null);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitVarInsn(ALOAD, 3);
			
			String internalNameOfCast = field.internalName;
			if (field.type.isPrimitive()) {
				Class<?> wrapperType = ClassUtils.primitiveToWrapper(field.type);
				internalNameOfCast = Type.getInternalName(wrapperType);
			}
			mv.visitTypeInsn(CHECKCAST, internalNameOfCast);
			
			if (field.type.isPrimitive()) {
				mv.visitMethodInsn(
						INVOKEVIRTUAL,
						internalNameOfCast,
						field.type.getName() + "Value",
						"()" + field.descriptor,
						false);
			}
			
			mv.visitFieldInsn(field.setFieldOpcode, internalName, field.name, field.descriptor);
			mv.visitLabel(new Label());
			mv.visitJumpInsn(GOTO, breakLabel);
		}
		
		mv.visitLabel(defaultCaseLabel);
		mv.visitFrame(F_SAME, 0, null, 0, null);
		
		visitFieldAccessSetterLastPart("Ljava/lang/Object;", firstLabel, breakLabel);
	}
	
	private void visitIndexMethod(
			String categoryOfStringCase,
			List<StringCaseReturnIndex> stringCaseReturnIndices) {
		mv = cw.visitMethod(ACC_PUBLIC, categoryOfStringCase + "Index", "(Ljava/lang/String;)I", null, null);
		mv.visitCode();
		final Label firstLabel = new Label();
		mv.visitLabel(firstLabel);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitInsn(DUP);
		mv.visitVarInsn(ASTORE, 2);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "hashCode", "()I", false);

		final Label defaultCaseLabel = new Label();
		
		if (fields.isEmpty()) {
			mv.visitInsn(POP);
			mv.visitLabel(defaultCaseLabel);
			visitIndexMethodLastPart(categoryOfStringCase, firstLabel);
			return;
		}
		
		Collections.sort(stringCaseReturnIndices, StringCaseReturnIndex::compareHashCode);
		
		int[] stringHashCodes = new int[stringCaseReturnIndices.size()];
		Label[] caseLabels = new Label[stringCaseReturnIndices.size()];
		for (int i = 0; i < stringCaseReturnIndices.size(); i++) {
			stringHashCodes[i] = stringCaseReturnIndices.get(i).hashCode;
			caseLabels[i] = new Label();
		}
		
		mv.visitLookupSwitchInsn(defaultCaseLabel, stringHashCodes, caseLabels);
		
		for (int i = 0; i < stringCaseReturnIndices.size(); i++) {
			StringCaseReturnIndex stringCaseReturnIndex = stringCaseReturnIndices.get(i);
			
			mv.visitLabel(caseLabels[i]);
			
			if (i > 0) {
				mv.visitFrame(F_SAME, 0, null, 0, null);
			} else {
				mv.visitFrame(F_APPEND,1, new Object[] {"java/lang/String"}, 0, null);
			}
			
			mv.visitVarInsn(ALOAD, 2);
			mv.visitLdcInsn(stringCaseReturnIndex.name);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
			mv.visitJumpInsn(IFNE, stringCaseReturnIndex.returnIndexLabel);
			mv.visitJumpInsn(GOTO, defaultCaseLabel);
		}

		Collections.sort(stringCaseReturnIndices, StringCaseReturnIndex::compareIndex);
		for (int i = 0; i < stringCaseReturnIndices.size(); i++) {
			StringCaseReturnIndex stringCaseReturnIndex = stringCaseReturnIndices.get(i);
			
			mv.visitLabel(stringCaseReturnIndex.returnIndexLabel);
			mv.visitFrame(F_SAME, 0, null, 0, null);
			
			if (stringCaseReturnIndex.index > 5) {
				mv.visitIntInsn(BIPUSH, stringCaseReturnIndex.index);
			} else {
				int opcode = NOP;
				switch (stringCaseReturnIndex.index) {
					case 0: opcode = ICONST_0; break;
					case 1: opcode = ICONST_1; break;
					case 2: opcode = ICONST_2; break;
					case 3: opcode = ICONST_3; break;
					case 4: opcode = ICONST_4; break;
					case 5: opcode = ICONST_5; break;
				}
				mv.visitInsn(opcode);
			}
			
			mv.visitInsn(IRETURN);
		}
		
		mv.visitLabel(defaultCaseLabel);
		mv.visitFrame(F_SAME, 0, null, 0, null);
		
		visitIndexMethodLastPart(categoryOfStringCase, firstLabel);
	}
	
	private void visitIndexMethodLastPart(String categoryOfStringCase, Label firstLabel) {
		mv.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
		mv.visitInsn(DUP);
		mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
		mv.visitInsn(DUP);
		mv.visitLdcInsn("No " + categoryOfStringCase + " with name: ");
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V", false);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V", false);
		mv.visitInsn(ATHROW);
		Label lastLabel = new Label();
		mv.visitLabel(lastLabel);
		mv.visitLocalVariable("this", classAccessTypeDescriptor, null, firstLabel, lastLabel, 0);
		mv.visitLocalVariable("name", "Ljava/lang/String;", null, firstLabel, lastLabel, 1);
		mv.visitMaxs(5, 3);
		mv.visitEnd();
	}
	
	private void visitPropertyAccessMethods() {
		List<AccessInfo> propertyAccessInfoList = Collections.unmodifiableList(
				Arrays.asList(
						// Primitive types
						//
						AccessInfo.forPrimitive(MEMBER_TYPE_PROPERTY, Type.BOOLEAN_TYPE),
						AccessInfo.forPrimitive(MEMBER_TYPE_PROPERTY, Type.BYTE_TYPE),
						AccessInfo.forPrimitive(MEMBER_TYPE_PROPERTY, Type.CHAR_TYPE),
						AccessInfo.forPrimitive(MEMBER_TYPE_PROPERTY, Type.DOUBLE_TYPE),
						AccessInfo.forPrimitive(MEMBER_TYPE_PROPERTY, Type.FLOAT_TYPE),
						AccessInfo.forPrimitive(MEMBER_TYPE_PROPERTY, Type.INT_TYPE),
						AccessInfo.forPrimitive(MEMBER_TYPE_PROPERTY, Type.LONG_TYPE),
						AccessInfo.forPrimitive(MEMBER_TYPE_PROPERTY, Type.SHORT_TYPE),
						// Primitive wrapper types
						//
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_PROPERTY, Boolean.class),
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_PROPERTY, Byte.class),
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_PROPERTY, Character.class),
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_PROPERTY, Double.class),
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_PROPERTY, Float.class),
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_PROPERTY, Integer.class),
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_PROPERTY, Long.class),
						AccessInfo.forPrimitiveWrapper(MEMBER_TYPE_PROPERTY, Short.class),
						// Common reference types
						//
						AccessInfo.forReferenceType(MEMBER_TYPE_PROPERTY, BigDecimal.class),
						AccessInfo.forReferenceType(MEMBER_TYPE_PROPERTY, Date.class),
						AccessInfo.forReferenceType(MEMBER_TYPE_PROPERTY, LocalDate.class),
						AccessInfo.forReferenceType(MEMBER_TYPE_PROPERTY, LocalDateTime.class),
						AccessInfo.forReferenceType(MEMBER_TYPE_PROPERTY, String.class)
						)
				);
		
		for (int i = 0; i < propertyAccessInfoList.size(); i++) {
			AccessInfo propertyAccessInfo = propertyAccessInfoList.get(i);

			visitAccessGetter(typeToAccessorsMap.get(propertyAccessInfo.className), propertyAccessInfo);
			visitAccessGetterBridge(propertyAccessInfo);
		}
	}
}
