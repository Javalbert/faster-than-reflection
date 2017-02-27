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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;

import com.github.javalbert.reflection.ClassAccessFactory;
import com.github.javalbert.reflection.MethodAccess;

public class MethodAccessCallTest {
	@Test
	public void callMethodWithNoReturnValue() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		
		Object nullRetVal = access.call(factory, access.methodIndex("zzz"));
		
		verify(factory).zzz();
		assertThat(nullRetVal, nullValue());
	}
	
	@Test
	public void callMethodWith0Parameters() {
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
		when(factory.newInstance(
				true))
		.thenReturn(new Foo(
				true));
		
		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true);
		
		verify(factory).newInstance(
				true);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith2Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55))
		.thenReturn(new Foo(
				true,
				(byte)-55));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55);
		
		verify(factory).newInstance(
				true,
				(byte)-55);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith3Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n'))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n'));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n');
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n');
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith4Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith5Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith6Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith7Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith8Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class,
				short.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith9Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class,
				short.class,
				Boolean.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith10Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class,
				short.class,
				Boolean.class,
				Byte.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith11Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n'))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n'));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class,
				short.class,
				Boolean.class,
				Byte.class,
				Character.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n');
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n');
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith12Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class,
				short.class,
				Boolean.class,
				Byte.class,
				Character.class,
				Double.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith13Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class,
				short.class,
				Boolean.class,
				Byte.class,
				Character.class,
				Double.class,
				Float.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith14Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class,
				short.class,
				Boolean.class,
				Byte.class,
				Character.class,
				Double.class,
				Float.class,
				Integer.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith15Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class,
				short.class,
				Boolean.class,
				Byte.class,
				Character.class,
				Double.class,
				Float.class,
				Integer.class,
				Long.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith16Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class,
				short.class,
				Boolean.class,
				Byte.class,
				Character.class,
				Double.class,
				Float.class,
				Integer.class,
				Long.class,
				Short.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith17Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456")))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456")));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class,
				short.class,
				Boolean.class,
				Byte.class,
				Character.class,
				Double.class,
				Float.class,
				Integer.class,
				Long.class,
				Short.class,
				BigDecimal.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"));
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"));
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith18Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		Date date = new Date();
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class,
				short.class,
				Boolean.class,
				Byte.class,
				Character.class,
				Double.class,
				Float.class,
				Integer.class,
				Long.class,
				Short.class,
				BigDecimal.class,
				Date.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith19Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		Date date = new Date();
		LocalDate localDate = LocalDate.now();
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date,
				localDate))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date,
				localDate));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class,
				short.class,
				Boolean.class,
				Byte.class,
				Character.class,
				Double.class,
				Float.class,
				Integer.class,
				Long.class,
				Short.class,
				BigDecimal.class,
				Date.class,
				LocalDate.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date,
				localDate);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date,
				localDate);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith20Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		Date date = new Date();
		LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = LocalDateTime.now();
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date,
				localDate,
				localDateTime))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date,
				localDate,
				localDateTime));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class,
				short.class,
				Boolean.class,
				Byte.class,
				Character.class,
				Double.class,
				Float.class,
				Integer.class,
				Long.class,
				Short.class,
				BigDecimal.class,
				Date.class,
				LocalDate.class,
				LocalDateTime.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date,
				localDate,
				localDateTime);
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date,
				localDate,
				localDateTime);
		assertThat(retVal, notNullValue());
	}
	
	@Test
	public void callMethodWith21Parameters() {
		MethodAccess<FooFactory> access = ClassAccessFactory.get(FooFactory.class);
		FooFactory factory = mock(FooFactory.class);
		Date date = new Date();
		LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = LocalDateTime.now();
		when(factory.newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date,
				localDate,
				localDateTime,
				"Sushi-Ya Japan"))
		.thenReturn(new Foo(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date,
				localDate,
				localDateTime,
				"Sushi-Ya Japan"));

		int methodIndex = access.methodIndex(
				"newInstance",
				boolean.class,
				byte.class,
				char.class,
				double.class,
				float.class,
				int.class,
				long.class,
				short.class,
				Boolean.class,
				Byte.class,
				Character.class,
				Double.class,
				Float.class,
				Integer.class,
				Long.class,
				Short.class,
				BigDecimal.class,
				Date.class,
				LocalDate.class,
				LocalDateTime.class,
				String.class);
		Object retVal = access.call(
				factory,
				methodIndex,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date,
				localDate,
				localDateTime,
				"Sushi-Ya Japan");
		
		verify(factory).newInstance(
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				true,
				(byte)-55,
				'\n',
				0.9553896885798474d,
				0.2747032f,
				274991538,
				6774924159498401640L,
				(short)-31848,
				new BigDecimal("123.456"),
				date,
				localDate,
				localDateTime,
				"Sushi-Ya Japan");
		assertThat(retVal, notNullValue());
	}
}
