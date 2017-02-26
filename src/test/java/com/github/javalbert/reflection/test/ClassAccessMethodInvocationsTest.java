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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.github.javalbert.reflection.ClassAccessFactory;
import com.github.javalbert.reflection.MethodAccess;

public class ClassAccessMethodInvocationsTest {
	@Test
	public void callMethodWithOneParameter() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		
		access.call(factory, access.methodIndex("newInstance"));
		
		verify(factory).newInstance();
	}
}
