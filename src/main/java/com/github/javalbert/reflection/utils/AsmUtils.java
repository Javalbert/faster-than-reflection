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
package com.github.javalbert.reflection.utils;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.MethodVisitor;

public final class AsmUtils {
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
