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
package com.github.javalbert.reflection.test;

import static java.util.stream.Collectors.*;

import java.util.Arrays;

import com.github.javalbert.reflection.MethodAccess;

public interface FooFactory {
	public static class FooFactoryClassAccess2 implements MethodAccess<FooFactory> {
		@Override
		public int methodIndex(String name, Class<?>... parameterTypes) {
			switch (name) {
				case "newInstance":
					if (Arrays.equals(parameterTypes, new Class[] {})) {
						return 0;
					} else if (Arrays.equals(parameterTypes, new Class[] { boolean.class })) {
						return 1;
					} else if (Arrays.equals(parameterTypes, new Class[] { boolean.class, byte.class })) {
						return 2;
					}
					break;
				case "zzz":
					if (Arrays.equals(parameterTypes, new Class[] {})) {
						return 3;
					}
					break;
			}
			throw new IllegalArgumentException("No method called " + name 
					+ " with parameters " + Arrays.stream(parameterTypes)
					.map(Class::getName)
					.collect(toList()));
		}
		
		@Override
		public Object call(FooFactory obj, int methodIndex) {
			return null;
		}
	}

	Foo newInstance();
	Foo newInstance(boolean booleanVal);
	Foo newInstance(boolean booleanVal, byte byteVal);
	void zzz();
}
