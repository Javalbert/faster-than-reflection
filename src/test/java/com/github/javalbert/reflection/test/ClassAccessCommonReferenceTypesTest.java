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
}
