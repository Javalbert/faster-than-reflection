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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ClassAccessFactory<T> {
	@SuppressWarnings("rawtypes")
	private final static Map<Class, ClassAccess> CLASS_ACCESS_MAP = new HashMap<>();
	private final static Logger LOGGER = LoggerFactory.getLogger(ClassAccessFactory.class);
	
	public static <T> ClassAccess<T> get(Class<T> clazz) {
		if (ClassAccess.class.isAssignableFrom(clazz)) {
			throw new IllegalArgumentException("should not get class access recursively");
		}
		
		try {
			return getInstance(clazz);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Error occurred while trying to get ClassAccess for " + clazz, e);
			CLASS_ACCESS_MAP.remove(clazz);
			
			new ClassAccessFactory<>(clazz).buildClassAccessClass();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		
		try {
			return getInstance(clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String getClassNameOfClassAccessFor(Class<?> clazz) {
		return clazz.getName() + "$" + clazz.getSimpleName() + "ClassAccess"; // com.github.javalbert.reflection.test.Foo$FooClassAccess
	}
	
	@SuppressWarnings("unchecked")
	private static <T> ClassAccess<T> getInstance(Class<T> clazz)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String className = getClassNameOfClassAccessFor(clazz);
		Class<?> classAccessClass = AccessClassLoader.get(clazz).loadClass(className);
		
		ClassAccess<T> classAccess = CLASS_ACCESS_MAP.get(clazz);
		if (classAccess == null) {
			synchronized (CLASS_ACCESS_MAP) {
				classAccess = CLASS_ACCESS_MAP.get(clazz);
				if (classAccess == null) {
					classAccess = (ClassAccess<T>)classAccessClass.newInstance();
					CLASS_ACCESS_MAP.put(clazz, classAccess);
				}
			}
		}
		return classAccess;
	}
	
	private static Label[] getTableSwitchLabelsForFieldAccess(
			Label defaultCaseLabel,
			List<FieldInfo> fields) {
		int len = fields.get(fields.size() - 1).fieldIndex - fields.get(0).fieldIndex + fields.size();
		
		Label[] labels = new Label[len];
		
		int fieldIndex = -1;
		
		for (int i = 0, currentField = 0; i < labels.length; i++) {
			FieldInfo field = fields.get(currentField);
			
			if (field.fieldIndex < fieldIndex) {
				labels[i] = defaultCaseLabel;
				fieldIndex++;
			} else {
				labels[i] = new Label();
				fieldIndex = field.fieldIndex + 1;
				currentField++;
			}
		}
		return labels;
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
	private static boolean useTableSwitch(List<FieldInfo> fields) {
		int hi = fields.get(fields.size() - 1).fieldIndex;
		int lo = fields.get(0).fieldIndex;
		int nlabels = fields.size();
		
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
	
	private static class FieldIndexSwitchCase {
		private static int compareFieldIndexSwitchCaseByFieldIndex(FieldIndexSwitchCase a, FieldIndexSwitchCase b) {
			return Integer.compare(a.fieldIndex, b.fieldIndex);
		}
		
		private static int compareFieldIndexSwitchCaseByHashCode(FieldIndexSwitchCase a, FieldIndexSwitchCase b) {
			return Integer.compare(a.hashCode, b.hashCode);
		}
		
		private final Field field;
		private final int fieldIndex;
		private final int hashCode;
		private final Label returnFieldIndexLabel = new Label();
		
		private FieldIndexSwitchCase(Field field, int fieldIndex) {
			this.field = field;
			this.fieldIndex = fieldIndex;
			this.hashCode = field.getName().hashCode();
		}
	}
	
	private static class FieldInfo {
		private final int fieldIndex;
		private final boolean isFinal;
		
		private FieldInfo(Field field, int fieldIndex) {
			this.fieldIndex = fieldIndex;
			isFinal = (field.getModifiers() & Modifier.FINAL) != 0;
		}
	}

	private String classAccessInternalName;
	private String classAccessTypeDescriptor;
	private String classTypeDescriptor;
	private final Class<T> clazz;
	private final ClassWriter cw;
	private final List<Field> fields;
	private String internalName;
	private MethodVisitor mv;
	private final List<PropertyDescriptor> propertyDescriptors;
	private final Map<String, List<FieldInfo>> typeToFieldsMap = new HashMap<>();
	
	private ClassAccessFactory(Class<T> clazz) {
		try {
			BeanInfo info = Introspector.getBeanInfo(clazz);
			propertyDescriptors = Collections.unmodifiableList(Arrays.asList(info.getPropertyDescriptors()));
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
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
		visitFieldIndexMethod();
		visitClassAccessImplMethods();
		visitClassAccessInterfaceMethods();
		cw.visitEnd();
		AccessClassLoader.get(clazz).defineClass(getClassNameOfClassAccessFor(clazz), cw.toByteArray());
	}
	
	private List<FieldIndexSwitchCase> getFieldIndexSwitchCases() {
		List<FieldIndexSwitchCase> fieldIndexSwitchCases = new ArrayList<>();
		for (int i = 0; i < fields.size(); i++) {
			fieldIndexSwitchCases.add(new FieldIndexSwitchCase(fields.get(i), i));
		}
		return fieldIndexSwitchCases;
	}
	
	@SuppressWarnings("unchecked")
	private List<FieldInfo> getNonFinalFieldsForType(String typeName) {
		List<FieldInfo> fields = typeToFieldsMap.get(typeName);
		return fields != null ? fields.stream()
				.filter(f -> !f.isFinal)
				.collect(toList()) : Collections.EMPTY_LIST;
	}
	
	private void putIntoTypeToFieldsMap(Field field, int fieldIndex) {
		String key = field.getType().getName();
		
		List<FieldInfo> fields = typeToFieldsMap.get(key);
		if (fields == null) {
			fields = new ArrayList<>();
			typeToFieldsMap.put(key, fields);
		}
		fields.add(new FieldInfo(field, fieldIndex));
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
				"java/lang/Object",
				new String[] { Type.getInternalName(ClassAccess.class) });
		cw.visitSource(clazz.getSimpleName() + ".java", null);
		cw.visitInnerClass(classAccessInternalName, internalName, classAccessSimpleName, ACC_PUBLIC + ACC_STATIC);
		
		classAccessTypeDescriptor = "L" + classAccessInternalName + ";";
		classTypeDescriptor = "L" + internalName + ";";
	}
	
	private void visitClassAccessImplMethods() {
		visitFieldAccess();
	}
	
	private void visitClassAccessInterfaceMethods() {
		mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "getIntField", "(Ljava/lang/Object;I)I", null, null);
		mv.visitCode();
		mv.visitLabel(new Label());
		mv.visitVarInsn(ALOAD, 0);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitTypeInsn(CHECKCAST, internalName);
		mv.visitVarInsn(ILOAD, 2);
		mv.visitMethodInsn(INVOKEVIRTUAL, classAccessInternalName, "getIntField", "(" + classTypeDescriptor + "I)I", false);
		mv.visitInsn(IRETURN);
		mv.visitMaxs(3, 3);
		mv.visitEnd();
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
	
	private void visitFieldAccess() {
		mv = cw.visitMethod(ACC_PUBLIC, "getIntField", "(" + classTypeDescriptor + "I)I", null, null);
		mv.visitCode();
		Label firstLabel = new Label();
		mv.visitLabel(firstLabel);
		mv.visitVarInsn(ILOAD, 2);

		Label defaultCaseLabel = new Label();

		List<FieldInfo> fields = getNonFinalFieldsForType("int");
		if (fields.isEmpty()) {
			mv.visitInsn(POP);
			mv.visitLabel(defaultCaseLabel);
			visitFieldAccessLastPart(firstLabel);
			return;
		}
		
		boolean useTableSwitch = useTableSwitch(fields);
		Label[] labels = useTableSwitch ? getTableSwitchLabelsForFieldAccess(defaultCaseLabel, fields)
				: newLabelArray(fields.size());
		
		if (useTableSwitch) {
			mv.visitTableSwitchInsn(
					fields.get(0).fieldIndex,
					fields.get(fields.size() - 1).fieldIndex,
					defaultCaseLabel,
					labels
					);
		} else {
			mv.visitLookupSwitchInsn(
					defaultCaseLabel,
					fields.stream()
					.mapToInt(f -> f.fieldIndex)
					.toArray(),
					labels);
		}
		
		for (int i = 0; i < fields.size(); i++) {
			mv.visitLabel(labels[i]);
			mv.visitFrame(F_SAME, 0, null, 0, null);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitMethodInsn(INVOKESTATIC, internalName, "access$" + i, "(" + classTypeDescriptor + ")I", false);
			mv.visitInsn(IRETURN);
		}
		
		mv.visitLabel(defaultCaseLabel);
		mv.visitFrame(F_SAME, 0, null, 0, null);
		
		visitFieldAccessLastPart(firstLabel);
	}
	
	private void visitFieldAccessLastPart(Label firstLabel) {
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
		Label lastLabel = new Label();
		mv.visitLabel(lastLabel);
		mv.visitLocalVariable("this", classAccessTypeDescriptor, null, firstLabel, lastLabel, 0);
		mv.visitLocalVariable("object", classTypeDescriptor, null, firstLabel, lastLabel, 1);
		mv.visitLocalVariable("index", "I", null, firstLabel, lastLabel, 2);
		mv.visitMaxs(5, 3);
		mv.visitEnd();
	}
	
	private void visitFieldIndexMethod() {
		mv = cw.visitMethod(ACC_PUBLIC, "fieldIndex", "(Ljava/lang/String;)I", null, null);
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
			visitFieldIndexMethodLastPart(firstLabel);
			return;
		}
		
		List<FieldIndexSwitchCase> fieldIndexSwitchCases = getFieldIndexSwitchCases();
		Collections.sort(fieldIndexSwitchCases, FieldIndexSwitchCase::compareFieldIndexSwitchCaseByHashCode);
		
		int[] fieldNameHashCodes = new int[fields.size()];
		Label[] caseLabels = new Label[fields.size()];
		for (int i = 0; i < fieldIndexSwitchCases.size(); i++) {
			fieldNameHashCodes[i] = fieldIndexSwitchCases.get(i).hashCode;
			caseLabels[i] = new Label();
		}
		
		mv.visitLookupSwitchInsn(defaultCaseLabel, fieldNameHashCodes, caseLabels);
		
		for (int i = 0; i < fieldIndexSwitchCases.size(); i++) {
			FieldIndexSwitchCase fieldIndexSwitchCase = fieldIndexSwitchCases.get(i);
			
			mv.visitLabel(caseLabels[i]);
			
			if (i > 0) {
				mv.visitFrame(F_SAME, 0, null, 0, null);
			} else {
				mv.visitFrame(F_APPEND,1, new Object[] {"java/lang/String"}, 0, null);
			}
			
			mv.visitVarInsn(ALOAD, 2);
			mv.visitLdcInsn(fieldIndexSwitchCase.field.getName());
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
			mv.visitJumpInsn(IFNE, fieldIndexSwitchCase.returnFieldIndexLabel);
			mv.visitJumpInsn(GOTO, defaultCaseLabel);
		}

		Collections.sort(fieldIndexSwitchCases, FieldIndexSwitchCase::compareFieldIndexSwitchCaseByFieldIndex);
		for (int i = 0; i < fieldIndexSwitchCases.size(); i++) {
			FieldIndexSwitchCase fieldIndexSwitchCase = fieldIndexSwitchCases.get(i);
			
			mv.visitLabel(fieldIndexSwitchCase.returnFieldIndexLabel);
			mv.visitFrame(F_SAME, 0, null, 0, null);
			
			if (fieldIndexSwitchCase.fieldIndex > 5) {
				mv.visitIntInsn(BIPUSH, fieldIndexSwitchCase.fieldIndex);
			} else {
				int opcode = NOP;
				switch (fieldIndexSwitchCase.fieldIndex) {
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
		
		visitFieldIndexMethodLastPart(firstLabel);
	}
	
	private void visitFieldIndexMethodLastPart(Label firstLabel) {
		mv.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
		mv.visitInsn(DUP);
		mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
		mv.visitInsn(DUP);
		mv.visitLdcInsn("No field with name: ");
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
}
