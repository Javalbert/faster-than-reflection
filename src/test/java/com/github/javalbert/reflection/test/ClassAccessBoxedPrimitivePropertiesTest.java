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

public class ClassAccessBoxedPrimitivePropertiesTest {
	@Test
	public void getBoxedBooleanPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedBoolean(true);
		
		Boolean boxedBoolean = access.getBoxedBooleanProperty(obj, access.propertyIndex("boxedBoolean"));
		
		assertThat(boxedBoolean, equalTo(obj.getBoxedBoolean()));
	}
	
	@Test
	public void getBoxedBytePropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedByte((byte)-55);
		
		Byte boxedByte = access.getBoxedByteProperty(obj, access.propertyIndex("boxedByte"));
		
		assertThat(boxedByte, equalTo(obj.getBoxedByte()));
	}
	
	@Test
	public void getBoxedCharPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedChar('\n');
		
		Character boxedChar = access.getBoxedCharProperty(obj, access.propertyIndex("boxedChar"));
		
		assertThat(boxedChar, equalTo(obj.getBoxedChar()));
	}
	
	@Test
	public void getBoxedDoublePropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedDouble(0.9553896885798474d);
		
		Double boxedDouble = access.getBoxedDoubleProperty(obj, access.propertyIndex("boxedDouble"));
		
		assertThat(boxedDouble, equalTo(obj.getBoxedDouble()));
	}
	
	@Test
	public void getBoxedFloatPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedFloat(0.2747032f);
		
		Float boxedFloat = access.getBoxedFloatProperty(obj, access.propertyIndex("boxedFloat"));
		
		assertThat(boxedFloat, equalTo(obj.getBoxedFloat()));
	}
	
	@Test
	public void getBoxedIntPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedInt(274991538);

		Integer boxedInt = access.getBoxedIntProperty(obj, access.propertyIndex("boxedInt"));
		
		assertThat(boxedInt, equalTo(obj.getBoxedInt()));
	}

	@Test
	public void getBoxedLongPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedLong(6774924159498401640L);

		Long boxedLong = access.getBoxedLongProperty(obj, access.propertyIndex("boxedLong"));
		
		assertThat(boxedLong, equalTo(obj.getBoxedLong()));
	}
	
	@Test
	public void getBoxedShortPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedShort((short)-31848);

		Short boxedShort = access.getBoxedShortProperty(obj, access.propertyIndex("boxedShort"));
		
		assertThat(boxedShort, equalTo(obj.getBoxedShort()));
	}
	
	@Test
	public void setBoxedBooleanPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Boolean boxedBoolean = true;
		
		access.setBoxedBooleanProperty(obj, access.propertyIndex("boxedBoolean"), boxedBoolean);
		
		assertThat(obj.getBoxedBoolean(), equalTo(boxedBoolean));
	}
	
	@Test
	public void setBoxedBytePropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Byte boxedByte = (byte)-55;
		
		access.setBoxedByteProperty(obj, access.propertyIndex("boxedByte"), boxedByte);
		
		assertThat(obj.getBoxedByte(), equalTo(boxedByte));
	}
	
	@Test
	public void setBoxedCharPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Character boxedChar = '\n';
		
		access.setBoxedCharProperty(obj, access.propertyIndex("boxedChar"), boxedChar);
		
		assertThat(obj.getBoxedChar(), equalTo(boxedChar));
	}
	
	@Test
	public void setBoxedDoublePropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Double boxedDouble = 0.9553896885798474d;
		
		access.setBoxedDoubleProperty(obj, access.propertyIndex("boxedDouble"), boxedDouble);
		
		assertThat(obj.getBoxedDouble(), equalTo(boxedDouble));
	}
	
	@Test
	public void setBoxedFloatPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Float boxedFloat = 0.2747032f;
		
		access.setBoxedFloatProperty(obj, access.propertyIndex("boxedFloat"), boxedFloat);
		
		assertThat(obj.getBoxedFloat(), equalTo(boxedFloat));
	}
	
	@Test
	public void setBoxedIntPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Integer boxedInt = 274991538;

		access.setBoxedIntProperty(obj, access.propertyIndex("boxedInt"), boxedInt);
		
		assertThat(obj.getBoxedInt(), equalTo(boxedInt));
	}

	@Test
	public void setBoxedLongPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Long boxedLong = 6774924159498401640L;

		access.setBoxedLongProperty(obj, access.propertyIndex("boxedLong"), boxedLong);
		
		assertThat(obj.getBoxedLong(), equalTo(boxedLong));
	}
	
	@Test
	public void setBoxedShortPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Short boxedShort = (short)-31848;

		access.setBoxedShortProperty(obj, access.propertyIndex("boxedShort"), boxedShort);
		
		assertThat(obj.getBoxedShort(), equalTo(boxedShort));
	}
}
