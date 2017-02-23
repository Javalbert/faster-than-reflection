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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;

import com.github.javalbert.reflection.ClassAccess;
import com.github.javalbert.reflection.ClassAccessFactory;

public class ClassAccessCommonReferenceTypesTest {
	/* START Fields */
	
	@Test
	public void getBigDecimalFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBigDecimal(new BigDecimal("123.456"));
		
		BigDecimal bigDecimal = access.getBigDecimalField(obj, access.fieldIndex("bigDecimal"));
		
		assertThat(bigDecimal, equalTo(obj.getBigDecimal()));
	}
	
	@Test
	public void setBigDecimalFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		BigDecimal bigDecimal = new BigDecimal("123.456");
		
		access.setBigDecimalField(obj, access.fieldIndex("bigDecimal"), bigDecimal);
		
		assertThat(obj.getBigDecimal(), equalTo(bigDecimal));
	}
	
	@Test
	public void getDateFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setDate(new Date());
		
		Date date = access.getDateField(obj, access.fieldIndex("date"));
		
		assertThat(date, equalTo(obj.getDate()));
	}
	
	@Test
	public void setDateFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Date date = new Date();
		
		access.setDateField(obj, access.fieldIndex("date"), date);
		
		assertThat(obj.getDate(), equalTo(date));
	}
	
	@Test
	public void getLocalDateFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLocalDate(LocalDate.now());
		
		LocalDate localDate = access.getLocalDateField(obj, access.fieldIndex("localDate"));
		
		assertThat(localDate, equalTo(obj.getLocalDate()));
	}
	
	@Test
	public void setLocalDateFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		LocalDate localDate = LocalDate.now();
		
		access.setLocalDateField(obj, access.fieldIndex("localDate"), localDate);
		
		assertThat(obj.getLocalDate(), equalTo(localDate));
	}
	
	@Test
	public void getLocalDateTimeFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLocalDateTime(LocalDateTime.now());
		
		LocalDateTime localDateTime = access.getLocalDateTimeField(obj, access.fieldIndex("localDateTime"));
		
		assertThat(localDateTime, equalTo(obj.getLocalDateTime()));
	}
	
	@Test
	public void setLocalDateTimeFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		LocalDateTime localDateTime = LocalDateTime.now();
		
		access.setLocalDateTimeField(obj, access.fieldIndex("localDateTime"), localDateTime);
		
		assertThat(obj.getLocalDateTime(), equalTo(localDateTime));
	}
	
	@Test
	public void getStringFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setString("Pizza Hut");
		
		String string = access.getStringField(obj, access.fieldIndex("string"));
		
		assertThat(string, equalTo(obj.getString()));
	}
	
	@Test
	public void setStringFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		String string = "Pizza Hut";
		
		access.setStringField(obj, access.fieldIndex("string"), string);
		
		assertThat(obj.getString(), equalTo(string));
	}

	/* END Fields */

	/* START Properties */
	
	@Test
	public void getBigDecimalPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBigDecimal(new BigDecimal("123.456"));
		
		BigDecimal bigDecimal = access.getBigDecimalProperty(obj, access.propertyIndex("bigDecimal"));
		
		assertThat(bigDecimal, equalTo(obj.getBigDecimal()));
	}
	
	@Test
	public void setBigDecimalPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		BigDecimal bigDecimal = new BigDecimal("123.456");
		
		access.setBigDecimalProperty(obj, access.propertyIndex("bigDecimal"), bigDecimal);
		
		assertThat(obj.getBigDecimal(), equalTo(bigDecimal));
	}
	
	@Test
	public void getDatePropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setDate(new Date());
		
		Date date = access.getDateProperty(obj, access.propertyIndex("date"));
		
		assertThat(date, equalTo(obj.getDate()));
	}
	
	@Test
	public void setDatePropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Date date = new Date();
		
		access.setDateProperty(obj, access.propertyIndex("date"), date);
		
		assertThat(obj.getDate(), equalTo(date));
	}
	
	@Test
	public void getLocalDatePropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLocalDate(LocalDate.now());
		
		LocalDate localDate = access.getLocalDateProperty(obj, access.propertyIndex("localDate"));
		
		assertThat(localDate, equalTo(obj.getLocalDate()));
	}
	
	@Test
	public void setLocalDatePropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		LocalDate localDate = LocalDate.now();
		
		access.setLocalDateProperty(obj, access.propertyIndex("localDate"), localDate);
		
		assertThat(obj.getLocalDate(), equalTo(localDate));
	}
	
	@Test
	public void getLocalDateTimePropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLocalDateTime(LocalDateTime.now());
		
		LocalDateTime localDateTime = access.getLocalDateTimeProperty(obj, access.propertyIndex("localDateTime"));
		
		assertThat(localDateTime, equalTo(obj.getLocalDateTime()));
	}
	
	@Test
	public void setLocalDateTimePropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		LocalDateTime localDateTime = LocalDateTime.now();
		
		access.setLocalDateTimeProperty(obj, access.propertyIndex("localDateTime"), localDateTime);
		
		assertThat(obj.getLocalDateTime(), equalTo(localDateTime));
	}
	
	@Test
	public void getStringPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setString("Pizza Hut");
		
		String string = access.getStringProperty(obj, access.propertyIndex("string"));
		
		assertThat(string, equalTo(obj.getString()));
	}
	
	@Test
	public void setStringPropertyValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		String string = "Pizza Hut";
		
		access.setStringProperty(obj, access.propertyIndex("string"), string);
		
		assertThat(obj.getString(), equalTo(string));
	}
	
	/* END Properties */
}
