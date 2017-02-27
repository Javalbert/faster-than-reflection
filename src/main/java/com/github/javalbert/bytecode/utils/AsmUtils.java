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
package com.github.javalbert.bytecode.utils;

import static org.objectweb.asm.Opcodes.*;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public final class AsmUtils {
	/**
	 * 
	 * @param defaultCaseLabel
	 * @param cases should be a sorted int array
	 * @return an array of {@link Label}s when calling {@link MethodVisitor#visitTableSwitchInsn(int, int, Label, Label...)}
	 */
	public static Label[] getTableSwitchLabels(
			Label defaultCaseLabel,
			int[] cases) {
		List<Label> labels = new ArrayList<>();
		
		int currentCase = 0;
		int caseValue = cases[0];
		
		int hi = cases[cases.length - 1];
		int lo = cases[0];
		
		for (int i = lo; i <= hi; i++) {
			int currentCaseValue = cases[currentCase];
			
			if (caseValue < currentCaseValue) {
				labels.add(defaultCaseLabel);
				caseValue++;
			} else {
				labels.add(new Label());
				caseValue = currentCaseValue + 1;
				currentCase++;
			}
		}
		return labels.stream().toArray(s -> new Label[s]);
	}
	
	/**
	 * Determines what <code>switch</code> Java instruction to use:
	 * <br>{@link MethodVisitor#visitTableSwitchInsn(int, int, Label, Label...)}
	 * <br>or
	 * <br>{@link MethodVisitor#visitLookupSwitchInsn(Label, int[], Label[])}
	 * @param cases should be a sorted int array
	 * @return
	 */
	public static boolean useTableSwitch(int[] cases) {
		int hi = cases[cases.length - 1];
		int lo = cases[0];
		int nlabels = cases.length;
		
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
	
	public static void visitDefaultConstructor(ClassWriter cw, String classTypeDescriptor) {
		MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
		mv.visitInsn(RETURN);
		Label l1 = new Label();
		mv.visitLabel(l1);
		mv.visitLocalVariable("this", classTypeDescriptor, null, l0, l1, 0);
		mv.visitMaxs(1, 1);
		mv.visitEnd();
	}
	
	/**
	 * 
	 * @param mv
	 * @param i the int value
	 */
	public static void visitZeroOperandInt(MethodVisitor mv, int i) {
		if (i > 5) {
			mv.visitIntInsn(BIPUSH, i);
		} else {
			int opcode = NOP;
			switch (i) {
				case 0: opcode = ICONST_0; break;
				case 1: opcode = ICONST_1; break;
				case 2: opcode = ICONST_2; break;
				case 3: opcode = ICONST_3; break;
				case 4: opcode = ICONST_4; break;
				case 5: opcode = ICONST_5; break;
			}
			mv.visitInsn(opcode);
		}
	}
	
	private AsmUtils() {}
}
