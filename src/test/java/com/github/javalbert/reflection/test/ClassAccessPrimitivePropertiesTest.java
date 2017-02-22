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

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import com.github.javalbert.reflection.ClassAccess;
import com.github.javalbert.reflection.ClassAccessFactory;

public class ClassAccessPrimitivePropertiesTest {
	@Test
	public void getBooleanPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBooleanVal(true);
		
		boolean booleanVal = access.getBooleanProperty(obj, access.propertyIndex("booleanVal"));
		
		assertThat(booleanVal, equalTo(obj.getBooleanVal()));
	}
	
	@Test
	public void getBytePropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setByteVal((byte)-21);
		
		byte byteVal = access.getByteProperty(obj, access.propertyIndex("byteVal"));
		
		assertThat(byteVal, equalTo(obj.getByteVal()));
	}
	
	@Test
	public void getCharPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setCharVal('.');
		
		char charVal = access.getCharProperty(obj, access.propertyIndex("charVal"));
		
		assertThat(charVal, equalTo(obj.getCharVal()));
	}
	
	@Test
	public void getDoublePropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setDoubleVal(0.14753383239666462d);
		
		double doubleVal = access.getDoubleProperty(obj, access.propertyIndex("doubleVal"));
		
		assertThat(doubleVal, equalTo(obj.getDoubleVal()));
	}
	
	@Test
	public void getFloatPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setFloatVal(0.27210158f);
		
		float floatVal = access.getFloatProperty(obj, access.propertyIndex("floatVal"));
		
		assertThat(floatVal, equalTo(obj.getFloatVal()));
	}
	
	@Test
	public void getIntPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setIntVal(1);

		int intVal = access.getIntProperty(obj, access.propertyIndex("intVal"));
		
		assertThat(intVal, equalTo(obj.getIntVal()));
	}

	@Test
	public void getLongPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLongVal(3285927007071017350L);

		long longVal = access.getLongProperty(obj, access.propertyIndex("longVal"));
		
		assertThat(longVal, equalTo(obj.getLongVal()));
	}
	
	@Test
	public void getShortPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setShortVal((short)-8406);

		short shortVal = access.getShortProperty(obj, access.propertyIndex("shortVal"));
		
		assertThat(shortVal, equalTo(obj.getShortVal()));
	}
}
