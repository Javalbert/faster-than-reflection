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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.javalbert.reflection.ClassAccessFactory;
import com.github.javalbert.reflection.PropertyAccess;

public class ClassAccessPrimitivePropertiesTest {
	@Test
	public void getBooleanPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBooleanVal(true);
		
		boolean booleanVal = access.getBooleanProperty(obj, access.propertyIndex("booleanVal"));
		
		assertThat(booleanVal, equalTo(obj.getBooleanVal()));
	}
	
	@Test
	public void getBytePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setByteVal((byte)-21);
		
		byte byteVal = access.getByteProperty(obj, access.propertyIndex("byteVal"));
		
		assertThat(byteVal, equalTo(obj.getByteVal()));
	}
	
	@Test
	public void getCharPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setCharVal('.');
		
		char charVal = access.getCharProperty(obj, access.propertyIndex("charVal"));
		
		assertThat(charVal, equalTo(obj.getCharVal()));
	}
	
	@Test
	public void getDoublePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setDoubleVal(0.14753383239666462d);
		
		double doubleVal = access.getDoubleProperty(obj, access.propertyIndex("doubleVal"));
		
		assertThat(doubleVal, equalTo(obj.getDoubleVal()));
	}
	
	@Test
	public void getFloatPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setFloatVal(0.27210158f);
		
		float floatVal = access.getFloatProperty(obj, access.propertyIndex("floatVal"));
		
		assertThat(floatVal, equalTo(obj.getFloatVal()));
	}
	
	@Test
	public void getIntPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setIntVal(1);

		int intVal = access.getIntProperty(obj, access.propertyIndex("intVal"));
		
		assertThat(intVal, equalTo(obj.getIntVal()));
	}

	@Test
	public void getLongPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLongVal(3285927007071017350L);

		long longVal = access.getLongProperty(obj, access.propertyIndex("longVal"));
		
		assertThat(longVal, equalTo(obj.getLongVal()));
	}
	
	@Test
	public void getShortPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setShortVal((short)-8406);

		short shortVal = access.getShortProperty(obj, access.propertyIndex("shortVal"));
		
		assertThat(shortVal, equalTo(obj.getShortVal()));
	}
	
	@Test
	public void setBooleanPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		boolean booleanVal = true;
		
		access.setBooleanProperty(obj, access.propertyIndex("booleanVal"), booleanVal);
		
		assertThat(obj.getBooleanVal(), equalTo(booleanVal));
	}
	
	@Test
	public void setBytePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		byte byteVal = (byte)-21;
		
		access.setByteProperty(obj, access.propertyIndex("byteVal"), byteVal);
		
		assertThat(obj.getByteVal(), equalTo(byteVal));
	}
	
	@Test
	public void setCharPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		char charVal = '.';
		
		access.setCharProperty(obj, access.propertyIndex("charVal"), charVal);
		
		assertThat(obj.getCharVal(), equalTo(charVal));
	}
	
	@Test
	public void setDoublePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		double doubleVal = 0.14753383239666462d;
		
		access.setDoubleProperty(obj, access.propertyIndex("doubleVal"), doubleVal);
		
		assertThat(obj.getDoubleVal(), equalTo(doubleVal));
	}
	
	@Test
	public void setFloatPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		float floatVal = 0.27210158f;
		
		access.setFloatProperty(obj, access.propertyIndex("floatVal"), floatVal);
		
		assertThat(obj.getFloatVal(), equalTo(floatVal));
	}
	
	@Test
	public void setIntPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		int intVal = 1;

		access.setIntProperty(obj, access.propertyIndex("intVal"), intVal);
		
		assertThat(obj.getIntVal(), equalTo(intVal));
	}

	@Test
	public void setLongPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		long longVal = 3285927007071017350L;

		access.setLongProperty(obj, access.propertyIndex("longVal"), longVal);
		
		assertThat(obj.getLongVal(), equalTo(longVal));
	}
	
	@Test
	public void setShortPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		short shortVal = (short)-8406;

		access.setShortProperty(obj, access.propertyIndex("shortVal"), shortVal);
		
		assertThat(obj.getShortVal(), equalTo(shortVal));
	}
}
