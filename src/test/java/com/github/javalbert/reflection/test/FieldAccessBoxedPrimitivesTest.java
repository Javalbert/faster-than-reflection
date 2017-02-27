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

import com.github.javalbert.reflection.ClassAccessFactory;
import com.github.javalbert.reflection.FieldAccess;

public class FieldAccessBoxedPrimitivesTest {
	@Test
	public void getBoxedBooleanFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedBoolean(true);
		
		Boolean boxedBoolean = access.getBoxedBooleanField(obj, access.fieldIndex("boxedBoolean"));
		
		assertThat(boxedBoolean, equalTo(obj.getBoxedBoolean()));
	}
	
	@Test
	public void getBoxedByteFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedByte((byte)-55);
		
		Byte boxedByte = access.getBoxedByteField(obj, access.fieldIndex("boxedByte"));
		
		assertThat(boxedByte, equalTo(obj.getBoxedByte()));
	}
	
	@Test
	public void getBoxedCharFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedChar('\n');
		
		Character boxedChar = access.getBoxedCharField(obj, access.fieldIndex("boxedChar"));
		
		assertThat(boxedChar, equalTo(obj.getBoxedChar()));
	}
	
	@Test
	public void getBoxedDoubleFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedDouble(0.9553896885798474d);
		
		Double boxedDouble = access.getBoxedDoubleField(obj, access.fieldIndex("boxedDouble"));
		
		assertThat(boxedDouble, equalTo(obj.getBoxedDouble()));
	}
	
	@Test
	public void getBoxedFloatFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedFloat(0.2747032f);
		
		Float boxedFloat = access.getBoxedFloatField(obj, access.fieldIndex("boxedFloat"));
		
		assertThat(boxedFloat, equalTo(obj.getBoxedFloat()));
	}
	
	@Test
	public void getBoxedIntFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedInt(274991538);

		Integer boxedInt = access.getBoxedIntField(obj, access.fieldIndex("boxedInt"));
		
		assertThat(boxedInt, equalTo(obj.getBoxedInt()));
	}

	@Test
	public void getBoxedLongFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedLong(6774924159498401640L);

		Long boxedLong = access.getBoxedLongField(obj, access.fieldIndex("boxedLong"));
		
		assertThat(boxedLong, equalTo(obj.getBoxedLong()));
	}
	
	@Test
	public void getBoxedShortFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedShort((short)-31848);

		Short boxedShort = access.getBoxedShortField(obj, access.fieldIndex("boxedShort"));
		
		assertThat(boxedShort, equalTo(obj.getBoxedShort()));
	}
	
	@Test
	public void setBoxedBooleanFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Boolean boxedBoolean = true;
		
		access.setBoxedBooleanField(obj, access.fieldIndex("boxedBoolean"), boxedBoolean);
		
		assertThat(obj.getBoxedBoolean(), equalTo(boxedBoolean));
	}
	
	@Test
	public void setBoxedByteFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Byte boxedByte = (byte)-55;
		
		access.setBoxedByteField(obj, access.fieldIndex("boxedByte"), boxedByte);
		
		assertThat(obj.getBoxedByte(), equalTo(boxedByte));
	}
	
	@Test
	public void setBoxedCharFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Character boxedChar = '\n';
		
		access.setBoxedCharField(obj, access.fieldIndex("boxedChar"), boxedChar);
		
		assertThat(obj.getBoxedChar(), equalTo(boxedChar));
	}
	
	@Test
	public void setBoxedDoubleFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Double boxedDouble = 0.9553896885798474d;
		
		access.setBoxedDoubleField(obj, access.fieldIndex("boxedDouble"), boxedDouble);
		
		assertThat(obj.getBoxedDouble(), equalTo(boxedDouble));
	}
	
	@Test
	public void setBoxedFloatFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Float boxedFloat = 0.2747032f;
		
		access.setBoxedFloatField(obj, access.fieldIndex("boxedFloat"), boxedFloat);
		
		assertThat(obj.getBoxedFloat(), equalTo(boxedFloat));
	}
	
	@Test
	public void setBoxedIntFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Integer boxedInt = 274991538;

		access.setBoxedIntField(obj, access.fieldIndex("boxedInt"), boxedInt);
		
		assertThat(obj.getBoxedInt(), equalTo(boxedInt));
	}

	@Test
	public void setBoxedLongFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Long boxedLong = 6774924159498401640L;

		access.setBoxedLongField(obj, access.fieldIndex("boxedLong"), boxedLong);
		
		assertThat(obj.getBoxedLong(), equalTo(boxedLong));
	}
	
	@Test
	public void setBoxedShortFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Short boxedShort = (short)-31848;

		access.setBoxedShortField(obj, access.fieldIndex("boxedShort"), boxedShort);
		
		assertThat(obj.getBoxedShort(), equalTo(boxedShort));
	}
}
