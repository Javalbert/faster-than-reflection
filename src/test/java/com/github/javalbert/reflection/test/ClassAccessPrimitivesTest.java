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
		Foo obj = new Foo();
		obj.setBooleanVal(true);
		
		boolean booleanVal = access.getBooleanField(obj, access.fieldIndex("booleanVal"));
		
		assertThat(booleanVal, equalTo(obj.getBooleanVal()));
	}
	
	@Test
	public void getByteFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setByteVal((byte)-21);
		
		byte byteVal = access.getByteField(obj, access.fieldIndex("byteVal"));
		
		assertThat(byteVal, equalTo(obj.getByteVal()));
	}
	
	@Test
	public void getCharFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setCharVal('.');
		
		char charVal = access.getCharField(obj, access.fieldIndex("charVal"));
		
		assertThat(charVal, equalTo(obj.getCharVal()));
	}
	
	@Test
	public void getDoubleFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setDoubleVal(0.14753383239666462d);
		
		double doubleVal = access.getDoubleField(obj, access.fieldIndex("doubleVal"));
		
		assertThat(doubleVal, equalTo(obj.getDoubleVal()));
	}
	
	@Test
	public void getFloatFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setFloatVal(0.27210158f);
		
		float floatVal = access.getFloatField(obj, access.fieldIndex("floatVal"));
		
		assertThat(floatVal, equalTo(obj.getFloatVal()));
	}
	
	@Test
	public void getIntFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setIntVal(1);

		int intVal = access.getIntField(obj, access.fieldIndex("intVal"));
		
		assertThat(intVal, equalTo(obj.getIntVal()));
	}

	@Test
	public void getLongFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLongVal(3285927007071017350L);

		long longVal = access.getLongField(obj, access.fieldIndex("longVal"));
		
		assertThat(longVal, equalTo(obj.getLongVal()));
	}
	
	@Test
	public void getShortFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setShortVal((short)-8406);

		short shortVal = access.getShortField(obj, access.fieldIndex("shortVal"));
		
		assertThat(shortVal, equalTo(obj.getShortVal()));
	}
	
	@Test
	public void setBooleanFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		boolean booleanVal = true;
		
		access.setBooleanField(obj, access.fieldIndex("booleanVal"), booleanVal);
		
		assertThat(obj.getBooleanVal(), equalTo(booleanVal));
	}
	
	@Test
	public void setByteFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		byte byteVal = (byte)-21;
		
		access.setByteField(obj, access.fieldIndex("byteVal"), byteVal);
		
		assertThat(obj.getByteVal(), equalTo(byteVal));
	}
	
	@Test
	public void setCharFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		char charVal = '.';
		
		access.setCharField(obj, access.fieldIndex("charVal"), charVal);
		
		assertThat(obj.getCharVal(), equalTo(charVal));
	}
	
	@Test
	public void setDoubleFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		double doubleVal = 0.14753383239666462d;
		
		access.setDoubleField(obj, access.fieldIndex("doubleVal"), doubleVal);
		
		assertThat(obj.getDoubleVal(), equalTo(doubleVal));
	}
	
	@Test
	public void setFloatFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		float floatVal = 0.27210158f;
		
		access.setFloatField(obj, access.fieldIndex("floatVal"), floatVal);
		
		assertThat(obj.getFloatVal(), equalTo(floatVal));
	}
	
	@Test
	public void setIntFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		int intVal = 1;

		access.setIntField(obj, access.fieldIndex("intVal"), intVal);
		
		assertThat(obj.getIntVal(), equalTo(intVal));
	}

	@Test
	public void setLongFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		long longVal = 3285927007071017350L;

		access.setLongField(obj, access.fieldIndex("longVal"), longVal);
		
		assertThat(obj.getLongVal(), equalTo(longVal));
	}
	
	@Test
	public void setShortFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		short shortVal = (short)-8406;

		access.setShortField(obj, access.fieldIndex("shortVal"), shortVal);
		
		assertThat(obj.getShortVal(), equalTo(shortVal));
	}
}
