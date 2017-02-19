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

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.github.javalbert.reflection.ClassAccess;
import com.github.javalbert.reflection.ClassAccessFactory;

public class ClassAccessBoxedPrimitivesTest {
	@Test
	public void getBoxedBooleanFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedBoolean(true);
		
		Boolean boxedBoolean = access.getBoxedBooleanField(obj, access.fieldIndex("boxedBoolean"));
		
		assertThat(boxedBoolean, equalTo(true));
	}
	
	@Test
	public void getBoxedByteFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedByte((byte)-55);
		
		Byte boxedByte = access.getBoxedByteField(obj, access.fieldIndex("boxedByte"));
		
		assertThat(boxedByte, equalTo((byte)-55));
	}
	
	@Test
	public void getBoxedCharFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedChar('\n');
		
		Character boxedChar = access.getBoxedCharField(obj, access.fieldIndex("boxedChar"));
		
		assertThat(boxedChar, equalTo('\n'));
	}
	
	@Test
	public void getBoxedDoubleFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedDouble(0.9553896885798474d);
		
		Double boxedDouble = access.getBoxedDoubleField(obj, access.fieldIndex("boxedDouble"));
		
		assertThat(boxedDouble, equalTo(0.9553896885798474d));
	}
	
	@Test
	public void getBoxedFloatFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedFloat(0.2747032f);
		
		Float boxedFloat = access.getBoxedFloatField(obj, access.fieldIndex("boxedFloat"));
		
		assertThat(boxedFloat, equalTo(0.2747032f));
	}
	
	@Test
	public void getBoxedIntFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedInt(274991538);

		Integer boxedInt = access.getBoxedIntField(obj, access.fieldIndex("boxedInt"));
		
		assertThat(boxedInt, equalTo(274991538));
	}

	@Test
	public void getBoxedLongFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedLong(6774924159498401640L);

		Long boxedLong = access.getBoxedLongField(obj, access.fieldIndex("boxedLong"));
		
		assertThat(boxedLong, equalTo(6774924159498401640L));
	}
	
	@Test
	public void getBoxedShortFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedShort((short)-31848);

		Short boxedShort = access.getBoxedShortField(obj, access.fieldIndex("boxedShort"));
		
		assertThat(boxedShort, equalTo((short)-31848));
	}
}
