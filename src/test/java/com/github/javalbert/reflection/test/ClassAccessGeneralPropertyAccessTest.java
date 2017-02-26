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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;

import com.github.javalbert.reflection.ClassAccessFactory;
import com.github.javalbert.reflection.PropertyAccess;

public class ClassAccessGeneralPropertyAccessTest {
	/* START Primitive types */
	
	@Test
	public void getBooleanPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBooleanVal(true);
		
		boolean booleanVal = (boolean)access.getProperty(obj, access.propertyIndex("booleanVal"));
		
		assertThat(booleanVal, equalTo(obj.getBooleanVal()));
	}
	
	@Test
	public void getBytePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setByteVal((byte)-21);
		
		byte byteVal = (byte)access.getProperty(obj, access.propertyIndex("byteVal"));
		
		assertThat(byteVal, equalTo(obj.getByteVal()));
	}
	
	@Test
	public void getCharPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setCharVal('.');
		
		char charVal = (char)access.getProperty(obj, access.propertyIndex("charVal"));
		
		assertThat(charVal, equalTo(obj.getCharVal()));
	}
	
	@Test
	public void getDoublePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setDoubleVal(0.14753383239666462d);
		
		double doubleVal = (double)access.getProperty(obj, access.propertyIndex("doubleVal"));
		
		assertThat(doubleVal, equalTo(obj.getDoubleVal()));
	}
	
	@Test
	public void getFloatPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setFloatVal(0.27210158f);
		
		float floatVal = (float)access.getProperty(obj, access.propertyIndex("floatVal"));
		
		assertThat(floatVal, equalTo(obj.getFloatVal()));
	}
	
	@Test
	public void getIntPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setIntVal(1);

		int intVal = (int)access.getProperty(obj, access.propertyIndex("intVal"));
		
		assertThat(intVal, equalTo(obj.getIntVal()));
	}

	@Test
	public void getLongPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLongVal(3285927007071017350L);

		long longVal = (long)access.getProperty(obj, access.propertyIndex("longVal"));
		
		assertThat(longVal, equalTo(obj.getLongVal()));
	}
	
	@Test
	public void getShortPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setShortVal((short)-8406);

		short shortVal = (short)access.getProperty(obj, access.propertyIndex("shortVal"));
		
		assertThat(shortVal, equalTo(obj.getShortVal()));
	}
	
	@Test
	public void setBooleanPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		boolean booleanVal = true;
		
		access.setProperty(obj, access.propertyIndex("booleanVal"), booleanVal);
		
		assertThat(obj.getBooleanVal(), equalTo(booleanVal));
	}
	
	@Test
	public void setBytePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		byte byteVal = (byte)-21;
		
		access.setProperty(obj, access.propertyIndex("byteVal"), byteVal);
		
		assertThat(obj.getByteVal(), equalTo(byteVal));
	}
	
	@Test
	public void setCharPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		char charVal = '.';
		
		access.setProperty(obj, access.propertyIndex("charVal"), charVal);
		
		assertThat(obj.getCharVal(), equalTo(charVal));
	}
	
	@Test
	public void setDoublePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		double doubleVal = 0.14753383239666462d;
		
		access.setProperty(obj, access.propertyIndex("doubleVal"), doubleVal);
		
		assertThat(obj.getDoubleVal(), equalTo(doubleVal));
	}
	
	@Test
	public void setFloatPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		float floatVal = 0.27210158f;
		
		access.setProperty(obj, access.propertyIndex("floatVal"), floatVal);
		
		assertThat(obj.getFloatVal(), equalTo(floatVal));
	}
	
	@Test
	public void setIntPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		int intVal = 1;

		access.setProperty(obj, access.propertyIndex("intVal"), intVal);
		
		assertThat(obj.getIntVal(), equalTo(intVal));
	}

	@Test
	public void setLongPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		long longVal = 3285927007071017350L;

		access.setProperty(obj, access.propertyIndex("longVal"), longVal);
		
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
	
	/* END Primitive types */
	
	/* START Primitive wrapper types */
	
	@Test
	public void getBoxedBooleanPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedBoolean(true);
		
		Boolean boxedBoolean = (Boolean)access.getProperty(obj, access.propertyIndex("boxedBoolean"));
		
		assertThat(boxedBoolean, equalTo(obj.getBoxedBoolean()));
	}
	
	@Test
	public void getBoxedBytePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedByte((byte)-55);
		
		Byte boxedByte = (Byte)access.getProperty(obj, access.propertyIndex("boxedByte"));
		
		assertThat(boxedByte, equalTo(obj.getBoxedByte()));
	}
	
	@Test
	public void getBoxedCharPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedChar('\n');
		
		Character boxedChar = (Character)access.getProperty(obj, access.propertyIndex("boxedChar"));
		
		assertThat(boxedChar, equalTo(obj.getBoxedChar()));
	}
	
	@Test
	public void getBoxedDoublePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedDouble(0.9553896885798474d);
		
		Double boxedDouble = (Double)access.getProperty(obj, access.propertyIndex("boxedDouble"));
		
		assertThat(boxedDouble, equalTo(obj.getBoxedDouble()));
	}
	
	@Test
	public void getBoxedFloatPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedFloat(0.2747032f);
		
		Float boxedFloat = (Float)access.getProperty(obj, access.propertyIndex("boxedFloat"));
		
		assertThat(boxedFloat, equalTo(obj.getBoxedFloat()));
	}
	
	@Test
	public void getBoxedIntPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedInt(274991538);

		Integer boxedInt = (Integer)access.getProperty(obj, access.propertyIndex("boxedInt"));
		
		assertThat(boxedInt, equalTo(obj.getBoxedInt()));
	}

	@Test
	public void getBoxedLongPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedLong(6774924159498401640L);

		Long boxedLong = (Long)access.getProperty(obj, access.propertyIndex("boxedLong"));
		
		assertThat(boxedLong, equalTo(obj.getBoxedLong()));
	}
	
	@Test
	public void getBoxedShortPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedShort((short)-31848);

		Short boxedShort = (Short)access.getProperty(obj, access.propertyIndex("boxedShort"));
		
		assertThat(boxedShort, equalTo(obj.getBoxedShort()));
	}
	
	@Test
	public void setBoxedBooleanPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Boolean boxedBoolean = true;
		
		access.setProperty(obj, access.propertyIndex("boxedBoolean"), boxedBoolean);
		
		assertThat(obj.getBoxedBoolean(), equalTo(boxedBoolean));
	}
	
	@Test
	public void setBoxedBytePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Byte boxedByte = (byte)-55;
		
		access.setProperty(obj, access.propertyIndex("boxedByte"), boxedByte);
		
		assertThat(obj.getBoxedByte(), equalTo(boxedByte));
	}
	
	@Test
	public void setBoxedCharPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Character boxedChar = '\n';
		
		access.setProperty(obj, access.propertyIndex("boxedChar"), boxedChar);
		
		assertThat(obj.getBoxedChar(), equalTo(boxedChar));
	}
	
	@Test
	public void setBoxedDoublePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Double boxedDouble = 0.9553896885798474d;
		
		access.setProperty(obj, access.propertyIndex("boxedDouble"), boxedDouble);
		
		assertThat(obj.getBoxedDouble(), equalTo(boxedDouble));
	}
	
	@Test
	public void setBoxedFloatPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Float boxedFloat = 0.2747032f;
		
		access.setProperty(obj, access.propertyIndex("boxedFloat"), boxedFloat);
		
		assertThat(obj.getBoxedFloat(), equalTo(boxedFloat));
	}
	
	@Test
	public void setBoxedIntPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Integer boxedInt = 274991538;

		access.setProperty(obj, access.propertyIndex("boxedInt"), boxedInt);
		
		assertThat(obj.getBoxedInt(), equalTo(boxedInt));
	}

	@Test
	public void setBoxedLongPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Long boxedLong = 6774924159498401640L;

		access.setProperty(obj, access.propertyIndex("boxedLong"), boxedLong);
		
		assertThat(obj.getBoxedLong(), equalTo(boxedLong));
	}
	
	@Test
	public void setBoxedShortPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Short boxedShort = (short)-31848;

		access.setProperty(obj, access.propertyIndex("boxedShort"), boxedShort);
		
		assertThat(obj.getBoxedShort(), equalTo(boxedShort));
	}

	/* END Primitive wrapper types */
	
	/* START Common reference types */
	
	@Test
	public void getBigDecimalPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBigDecimal(new BigDecimal("123.456"));
		
		BigDecimal bigDecimal = (BigDecimal)access.getProperty(obj, access.propertyIndex("bigDecimal"));
		
		assertThat(bigDecimal, equalTo(obj.getBigDecimal()));
	}
	
	@Test
	public void setBigDecimalPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		BigDecimal bigDecimal = new BigDecimal("123.456");
		
		access.setProperty(obj, access.propertyIndex("bigDecimal"), bigDecimal);
		
		assertThat(obj.getBigDecimal(), equalTo(bigDecimal));
	}
	
	@Test
	public void getDatePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setDate(new Date());
		
		Date date = (Date)access.getProperty(obj, access.propertyIndex("date"));
		
		assertThat(date, equalTo(obj.getDate()));
	}
	
	@Test
	public void setDatePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Date date = new Date();
		
		access.setProperty(obj, access.propertyIndex("date"), date);
		
		assertThat(obj.getDate(), equalTo(date));
	}
	
	@Test
	public void getLocalDatePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLocalDate(LocalDate.now());
		
		LocalDate localDate = (LocalDate)access.getProperty(obj, access.propertyIndex("localDate"));
		
		assertThat(localDate, equalTo(obj.getLocalDate()));
	}
	
	@Test
	public void setLocalDatePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		LocalDate localDate = LocalDate.now();
		
		access.setProperty(obj, access.propertyIndex("localDate"), localDate);
		
		assertThat(obj.getLocalDate(), equalTo(localDate));
	}
	
	@Test
	public void getLocalDateTimePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLocalDateTime(LocalDateTime.now());
		
		LocalDateTime localDateTime = (LocalDateTime)access.getProperty(obj, access.propertyIndex("localDateTime"));
		
		assertThat(localDateTime, equalTo(obj.getLocalDateTime()));
	}
	
	@Test
	public void setLocalDateTimePropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		LocalDateTime localDateTime = LocalDateTime.now();
		
		access.setProperty(obj, access.propertyIndex("localDateTime"), localDateTime);
		
		assertThat(obj.getLocalDateTime(), equalTo(localDateTime));
	}
	
	@Test
	public void getStringPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setString("Pizza Hut");
		
		String string = (String)access.getProperty(obj, access.propertyIndex("string"));
		
		assertThat(string, equalTo(obj.getString()));
	}
	
	@Test
	public void setStringPropertyValueAndVerify() {
		PropertyAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		String string = "Pizza Hut";
		
		access.setProperty(obj, access.propertyIndex("string"), string);
		
		assertThat(obj.getString(), equalTo(string));
	}
	
	/* END Common reference types */
}
