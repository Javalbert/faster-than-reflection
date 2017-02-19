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

public class ClassAccessPrimitivesTest {
	@Test
	public void getBooleanFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo object = new Foo();
		object.setBooleanVal(true);
		
		boolean booleanVal = access.getBooleanField(object, access.fieldIndex("booleanVal"));
		
		assertThat(booleanVal, equalTo(true));
	}
	
	@Test
	public void getByteFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo object = new Foo();
		object.setByteVal((byte)-21);
		
		byte byteVal = access.getByteField(object, access.fieldIndex("byteVal"));
		
		assertThat(byteVal, equalTo((byte)-21));
	}
	
	@Test
	public void getCharFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo object = new Foo();
		object.setCharVal('.');
		
		char charVal = access.getCharField(object, access.fieldIndex("charVal"));
		
		assertThat(charVal, equalTo('.'));
	}
	
	@Test
	public void getDoubleFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo object = new Foo();
		object.setDoubleVal(0.14753383239666462d);
		
		double doubleVal = access.getDoubleField(object, access.fieldIndex("doubleVal"));
		
		assertThat(doubleVal, equalTo(0.14753383239666462d));
	}
	
	@Test
	public void getFloatFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo object = new Foo();
		object.setFloatVal(0.27210158f);
		
		float floatVal = access.getFloatField(object, access.fieldIndex("floatVal"));
		
		assertThat(floatVal, equalTo(0.27210158f));
	}
	
	@Test
	public void getIntFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo object = new Foo();
		object.setIntVal(1);

		int intVal = access.getIntField(object, access.fieldIndex("intVal"));
		
		assertThat(intVal, equalTo(1));
	}

	@Test
	public void getLongFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo object = new Foo();
		object.setLongVal(3285927007071017350L);

		long longVal = access.getLongField(object, access.fieldIndex("longVal"));
		
		assertThat(longVal, equalTo(3285927007071017350L));
	}
	
	@Test
	public void getShortFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo object = new Foo();
		object.setShortVal((short)-8406);

		short shortVal = access.getShortField(object, access.fieldIndex("shortVal"));
		
		assertThat(shortVal, equalTo((short)-8406));
	}
}
