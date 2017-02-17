package com.github.javalbert.reflection;

import static java.util.stream.Collectors.*;
import static org.objectweb.asm.Opcodes.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	private static void setAccessible(AccessibleObject object) {
		object.setAccessible(true);
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

	private String classAccessInternalName;
	private String classAccessTypeDescriptor;
	private String classTypeDescriptor;
	private final Class<T> clazz;
	private final ClassWriter cw;
	private final List<Field> fields;
	private final Set<String> fieldTypeExistenceSet = new HashSet<>();
	private String internalName;
	private MethodVisitor mv;
	private final List<PropertyDescriptor> propertyDescriptors;
	
	private ClassAccessFactory(Class<T> clazz) {
		try {
			BeanInfo info = Introspector.getBeanInfo(clazz);
			propertyDescriptors = Collections.unmodifiableList(Arrays.asList(info.getPropertyDescriptors()));
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
		
		this.fields = Collections.unmodifiableList(Arrays.stream(clazz.getDeclaredFields())
				.peek(ClassAccessFactory::setAccessible)
				.peek(this::addFieldTypeExistence)
				.collect(toList())
				);
		
//		fields.stream().allMatch(f -> (f.getModifiers() & Modifier.FINAL) != 0);
		
		this.clazz = clazz;
		cw = new ClassWriter(0);
	}
	
	private void addFieldTypeExistence(Field field) {
		fieldTypeExistenceSet.add(field.getType().getName());
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
		mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "intField", "(Ljava/lang/Object;I)I", null, null);
		mv.visitCode();
		mv.visitLabel(new Label());
		mv.visitVarInsn(ALOAD, 0);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitTypeInsn(CHECKCAST, internalName);
		mv.visitVarInsn(ILOAD, 2);
		mv.visitMethodInsn(INVOKEVIRTUAL, classAccessInternalName, "intField", "(" + classTypeDescriptor + "I)I", false);
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
		mv = cw.visitMethod(ACC_PUBLIC, "intField", "(" + classTypeDescriptor + "I)I", null, null);
		mv.visitCode();
		Label firstLabel = new Label();
		mv.visitLabel(firstLabel);
		mv.visitVarInsn(ILOAD, 2);

		Label defaultCaseLabel = new Label();
		
		if (!fieldTypeExistenceSet.contains("int")) {
			mv.visitInsn(POP);
			mv.visitLabel(defaultCaseLabel);
			visitFieldAccessLastPart(firstLabel);
			return;
		}
		
		Label l1 = new Label();
		mv.visitTableSwitchInsn(0, 0, defaultCaseLabel, new Label[] { l1 });
		
		mv.visitLabel(l1);
		mv.visitFrame(F_SAME, 0, null, 0, null);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKESTATIC, internalName, "access$0", "(" + classTypeDescriptor + ")I", false);
		mv.visitInsn(IRETURN);
		
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
