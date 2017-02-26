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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.github.javalbert.reflection.ClassAccessFactory;
import com.github.javalbert.reflection.MethodAccess;

public class ClassAccessMethodInvocationsTest {
	@Test
	public void callMethodWith0Parameter() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance()).thenReturn(new Foo());
		
		Object retVal = access.call(factory, access.methodIndex("newInstance"));
		
		verify(factory).newInstance();
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith1Parameter() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(true)).thenReturn(new Foo(true));
		
		Object retVal = access.call(factory, access.methodIndex("newInstance", boolean.class), true);
		
		verify(factory).newInstance(true);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWithNoReturnValue() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		
		Object nullRetVal = access.call(factory, access.methodIndex("zzz"));
		
		verify(factory).zzz();
		assertThat(nullRetVal, nullValue());
	}
}
