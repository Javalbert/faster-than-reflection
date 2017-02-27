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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;

import com.github.javalbert.reflection.ClassAccessFactory;
import com.github.javalbert.reflection.FieldAccess;
import com.github.javalbert.reflection.PropertyAccess;

public class ClassAccessCommonReferenceTypesTest {
	/* START Fields */
	
	@Test
	public void getBigDecimalFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBigDecimal(new BigDecimal("123.456"));
		
		BigDecimal bigDecimal = access.getBigDecimalField(obj, access.fieldIndex("bigDecimal"));
		
		assertThat(bigDecimal, equalTo(obj.getBigDecimal()));
	}
	
	@Test
	public void setBigDecimalFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		BigDecimal bigDecimal = new BigDecimal("123.456");
		
		access.setBigDecimalField(obj, access.fieldIndex("bigDecimal"), bigDecimal);
		
		assertThat(obj.getBigDecimal(), equalTo(bigDecimal));
	}
	
	@Test
	public void getDateFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setDate(new Date());
		
		Date date = access.getDateField(obj, access.fieldIndex("date"));
		
		assertThat(date, equalTo(obj.getDate()));
	}
	
	@Test
	public void setDateFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Date date = new Date();
		
		access.setDateField(obj, access.fieldIndex("date"), date);
		
		assertThat(obj.getDate(), equalTo(date));
	}
	
	@Test
	public void getLocalDateFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLocalDate(LocalDate.now());
		
		LocalDate localDate = access.getLocalDateField(obj, access.fieldIndex("localDate"));
		
		assertThat(localDate, equalTo(obj.getLocalDate()));
	}
	
	@Test
	public void setLocalDateFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		LocalDate localDate = LocalDate.now();
		
		access.setLocalDateField(obj, access.fieldIndex("localDate"), localDate);
		
		assertThat(obj.getLocalDate(), equalTo(localDate));
	}
	
	@Test
	public void getLocalDateTimeFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLocalDateTime(LocalDateTime.now());
		
		LocalDateTime localDateTime = access.getLocalDateTimeField(obj, access.fieldIndex("localDateTime"));
		
		assertThat(localDateTime, equalTo(obj.getLocalDateTime()));
	}
	
	@Test
	public void setLocalDateTimeFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		LocalDateTime localDateTime = LocalDateTime.now();
		
		access.setLocalDateTimeField(obj, access.fieldIndex("localDateTime"), localDateTime);
		
		assertThat(obj.getLocalDateTime(), equalTo(localDateTime));
	}
	
	@Test
	public void getStringFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setString("Pizza Hut");
		
		String string = access.getStringField(obj, access.fieldIndex("string"));
		
		assertThat(string, equalTo(obj.getString()));
	}
	
	@Test
	public void setStringFieldValueAndVerify() {
		FieldAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		String string = "Pizza Hut";
		
		access.setStringField(obj, access.fieldIndex("string"), string);
		
		assertThat(obj.getString(), equalTo(string));
	}

	/* END Fields */

	/* START Properties */
	
	@Test
	public void getBigDecimalPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBigDecimal(new BigDecimal("123.456"));
		
		BigDecimal bigDecimal = access.getBigDecimalProperty(obj, access.propertyIndex("bigDecimal"));
		
		assertThat(bigDecimal, equalTo(obj.getBigDecimal()));
	}
	
	@Test
	public void setBigDecimalPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		BigDecimal bigDecimal = new BigDecimal("123.456");
		
		access.setBigDecimalProperty(obj, access.propertyIndex("bigDecimal"), bigDecimal);
		
		assertThat(obj.getBigDecimal(), equalTo(bigDecimal));
	}
	
	@Test
	public void getDatePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setDate(new Date());
		
		Date date = access.getDateProperty(obj, access.propertyIndex("date"));
		
		assertThat(date, equalTo(obj.getDate()));
	}
	
	@Test
	public void setDatePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Date date = new Date();
		
		access.setDateProperty(obj, access.propertyIndex("date"), date);
		
		assertThat(obj.getDate(), equalTo(date));
	}
	
	@Test
	public void getLocalDatePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLocalDate(LocalDate.now());
		
		LocalDate localDate = access.getLocalDateProperty(obj, access.propertyIndex("localDate"));
		
		assertThat(localDate, equalTo(obj.getLocalDate()));
	}
	
	@Test
	public void setLocalDatePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		LocalDate localDate = LocalDate.now();
		
		access.setLocalDateProperty(obj, access.propertyIndex("localDate"), localDate);
		
		assertThat(obj.getLocalDate(), equalTo(localDate));
	}
	
	@Test
	public void getLocalDateTimePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLocalDateTime(LocalDateTime.now());
		
		LocalDateTime localDateTime = access.getLocalDateTimeProperty(obj, access.propertyIndex("localDateTime"));
		
		assertThat(localDateTime, equalTo(obj.getLocalDateTime()));
	}
	
	@Test
	public void setLocalDateTimePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		LocalDateTime localDateTime = LocalDateTime.now();
		
		access.setLocalDateTimeProperty(obj, access.propertyIndex("localDateTime"), localDateTime);
		
		assertThat(obj.getLocalDateTime(), equalTo(localDateTime));
	}
	
	@Test
	public void getStringPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setString("Pizza Hut");
		
		String string = access.getStringProperty(obj, access.propertyIndex("string"));
		
		assertThat(string, equalTo(obj.getString()));
	}
	
	@Test
	public void setStringPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		String string = "Pizza Hut";
		
		access.setStringProperty(obj, access.propertyIndex("string"), string);
		
		assertThat(obj.getString(), equalTo(string));
	}
	
	/* END Properties */
}
