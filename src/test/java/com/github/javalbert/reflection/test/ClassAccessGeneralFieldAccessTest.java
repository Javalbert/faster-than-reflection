package com.github.javalbert.reflection.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;

import com.github.javalbert.reflection.ClassAccess;
import com.github.javalbert.reflection.ClassAccessFactory;

public class ClassAccessGeneralFieldAccessTest {
	/* START Primitive types */
	
	@Test
	public void getBooleanFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBooleanVal(true);
		
		boolean booleanVal = (boolean)access.getField(obj, access.fieldIndex("booleanVal"));
		
		assertThat(booleanVal, equalTo(obj.getBooleanVal()));
	}
	
	@Test
	public void getByteFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setByteVal((byte)-21);
		
		byte byteVal = (byte)access.getField(obj, access.fieldIndex("byteVal"));
		
		assertThat(byteVal, equalTo(obj.getByteVal()));
	}
	
	@Test
	public void getCharFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setCharVal('.');
		
		char charVal = (char)access.getField(obj, access.fieldIndex("charVal"));
		
		assertThat(charVal, equalTo(obj.getCharVal()));
	}
	
	@Test
	public void getDoubleFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setDoubleVal(0.14753383239666462d);
		
		double doubleVal = (double)access.getField(obj, access.fieldIndex("doubleVal"));
		
		assertThat(doubleVal, equalTo(obj.getDoubleVal()));
	}
	
	@Test
	public void getFloatFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setFloatVal(0.27210158f);
		
		float floatVal = (float)access.getField(obj, access.fieldIndex("floatVal"));
		
		assertThat(floatVal, equalTo(obj.getFloatVal()));
	}
	
	@Test
	public void getIntFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setIntVal(1);

		int intVal = (int)access.getField(obj, access.fieldIndex("intVal"));
		
		assertThat(intVal, equalTo(obj.getIntVal()));
	}

	@Test
	public void getLongFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLongVal(3285927007071017350L);

		long longVal = (long)access.getField(obj, access.fieldIndex("longVal"));
		
		assertThat(longVal, equalTo(obj.getLongVal()));
	}
	
	@Test
	public void getShortFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setShortVal((short)-8406);

		short shortVal = (short)access.getField(obj, access.fieldIndex("shortVal"));
		
		assertThat(shortVal, equalTo(obj.getShortVal()));
	}
	
	@Test
	public void setBooleanFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		boolean booleanVal = true;
		
		access.setField(obj, access.fieldIndex("booleanVal"), booleanVal);
		
		assertThat(obj.getBooleanVal(), equalTo(booleanVal));
	}
	
	@Test
	public void setByteFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		byte byteVal = (byte)-21;
		
		access.setField(obj, access.fieldIndex("byteVal"), byteVal);
		
		assertThat(obj.getByteVal(), equalTo(byteVal));
	}
	
	@Test
	public void setCharFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		char charVal = '.';
		
		access.setField(obj, access.fieldIndex("charVal"), charVal);
		
		assertThat(obj.getCharVal(), equalTo(charVal));
	}
	
	@Test
	public void setDoubleFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		double doubleVal = 0.14753383239666462d;
		
		access.setField(obj, access.fieldIndex("doubleVal"), doubleVal);
		
		assertThat(obj.getDoubleVal(), equalTo(doubleVal));
	}
	
	@Test
	public void setFloatFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		float floatVal = 0.27210158f;
		
		access.setField(obj, access.fieldIndex("floatVal"), floatVal);
		
		assertThat(obj.getFloatVal(), equalTo(floatVal));
	}
	
	@Test
	public void setIntFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		int intVal = 1;

		access.setField(obj, access.fieldIndex("intVal"), intVal);
		
		assertThat(obj.getIntVal(), equalTo(intVal));
	}

	@Test
	public void setLongFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		long longVal = 3285927007071017350L;

		access.setField(obj, access.fieldIndex("longVal"), longVal);
		
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
	
	/* END Primitive types */
	
	/* START Primitive wrapper types */
	
	@Test
	public void getBoxedBooleanFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedBoolean(true);
		
		Boolean boxedBoolean = (Boolean)access.getField(obj, access.fieldIndex("boxedBoolean"));
		
		assertThat(boxedBoolean, equalTo(obj.getBoxedBoolean()));
	}
	
	@Test
	public void getBoxedByteFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedByte((byte)-55);
		
		Byte boxedByte = (Byte)access.getField(obj, access.fieldIndex("boxedByte"));
		
		assertThat(boxedByte, equalTo(obj.getBoxedByte()));
	}
	
	@Test
	public void getBoxedCharFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedChar('\n');
		
		Character boxedChar = (Character)access.getField(obj, access.fieldIndex("boxedChar"));
		
		assertThat(boxedChar, equalTo(obj.getBoxedChar()));
	}
	
	@Test
	public void getBoxedDoubleFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedDouble(0.9553896885798474d);
		
		Double boxedDouble = (Double)access.getField(obj, access.fieldIndex("boxedDouble"));
		
		assertThat(boxedDouble, equalTo(obj.getBoxedDouble()));
	}
	
	@Test
	public void getBoxedFloatFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedFloat(0.2747032f);
		
		Float boxedFloat = (Float)access.getField(obj, access.fieldIndex("boxedFloat"));
		
		assertThat(boxedFloat, equalTo(obj.getBoxedFloat()));
	}
	
	@Test
	public void getBoxedIntFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedInt(274991538);

		Integer boxedInt = (Integer)access.getField(obj, access.fieldIndex("boxedInt"));
		
		assertThat(boxedInt, equalTo(obj.getBoxedInt()));
	}

	@Test
	public void getBoxedLongFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedLong(6774924159498401640L);

		Long boxedLong = (Long)access.getField(obj, access.fieldIndex("boxedLong"));
		
		assertThat(boxedLong, equalTo(obj.getBoxedLong()));
	}
	
	@Test
	public void getBoxedShortFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBoxedShort((short)-31848);

		Short boxedShort = (Short)access.getField(obj, access.fieldIndex("boxedShort"));
		
		assertThat(boxedShort, equalTo(obj.getBoxedShort()));
	}
	
	@Test
	public void setBoxedBooleanFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Boolean boxedBoolean = true;
		
		access.setField(obj, access.fieldIndex("boxedBoolean"), boxedBoolean);
		
		assertThat(obj.getBoxedBoolean(), equalTo(boxedBoolean));
	}
	
	@Test
	public void setBoxedByteFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Byte boxedByte = (byte)-55;
		
		access.setField(obj, access.fieldIndex("boxedByte"), boxedByte);
		
		assertThat(obj.getBoxedByte(), equalTo(boxedByte));
	}
	
	@Test
	public void setBoxedCharFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Character boxedChar = '\n';
		
		access.setField(obj, access.fieldIndex("boxedChar"), boxedChar);
		
		assertThat(obj.getBoxedChar(), equalTo(boxedChar));
	}
	
	@Test
	public void setBoxedDoubleFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Double boxedDouble = 0.9553896885798474d;
		
		access.setField(obj, access.fieldIndex("boxedDouble"), boxedDouble);
		
		assertThat(obj.getBoxedDouble(), equalTo(boxedDouble));
	}
	
	@Test
	public void setBoxedFloatFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Float boxedFloat = 0.2747032f;
		
		access.setField(obj, access.fieldIndex("boxedFloat"), boxedFloat);
		
		assertThat(obj.getBoxedFloat(), equalTo(boxedFloat));
	}
	
	@Test
	public void setBoxedIntFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Integer boxedInt = 274991538;

		access.setField(obj, access.fieldIndex("boxedInt"), boxedInt);
		
		assertThat(obj.getBoxedInt(), equalTo(boxedInt));
	}

	@Test
	public void setBoxedLongFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Long boxedLong = 6774924159498401640L;

		access.setField(obj, access.fieldIndex("boxedLong"), boxedLong);
		
		assertThat(obj.getBoxedLong(), equalTo(boxedLong));
	}
	
	@Test
	public void setBoxedShortFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Short boxedShort = (short)-31848;

		access.setField(obj, access.fieldIndex("boxedShort"), boxedShort);
		
		assertThat(obj.getBoxedShort(), equalTo(boxedShort));
	}

	/* END Primitive wrapper types */
	
	/* START Common reference types */
	
	@Test
	public void getBigDecimalFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setBigDecimal(new BigDecimal("123.456"));
		
		BigDecimal bigDecimal = (BigDecimal)access.getField(obj, access.fieldIndex("bigDecimal"));
		
		assertThat(bigDecimal, equalTo(obj.getBigDecimal()));
	}
	
	@Test
	public void setBigDecimalFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		BigDecimal bigDecimal = new BigDecimal("123.456");
		
		access.setField(obj, access.fieldIndex("bigDecimal"), bigDecimal);
		
		assertThat(obj.getBigDecimal(), equalTo(bigDecimal));
	}
	
	@Test
	public void getDateFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setDate(new Date());
		
		Date date = (Date)access.getField(obj, access.fieldIndex("date"));
		
		assertThat(date, equalTo(obj.getDate()));
	}
	
	@Test
	public void setDateFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		Date date = new Date();
		
		access.setField(obj, access.fieldIndex("date"), date);
		
		assertThat(obj.getDate(), equalTo(date));
	}
	
	@Test
	public void getLocalDateFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLocalDate(LocalDate.now());
		
		LocalDate localDate = (LocalDate)access.getField(obj, access.fieldIndex("localDate"));
		
		assertThat(localDate, equalTo(obj.getLocalDate()));
	}
	
	@Test
	public void setLocalDateFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		LocalDate localDate = LocalDate.now();
		
		access.setField(obj, access.fieldIndex("localDate"), localDate);
		
		assertThat(obj.getLocalDate(), equalTo(localDate));
	}
	
	@Test
	public void getLocalDateTimeFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setLocalDateTime(LocalDateTime.now());
		
		LocalDateTime localDateTime = (LocalDateTime)access.getField(obj, access.fieldIndex("localDateTime"));
		
		assertThat(localDateTime, equalTo(obj.getLocalDateTime()));
	}
	
	@Test
	public void setLocalDateTimeFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		LocalDateTime localDateTime = LocalDateTime.now();
		
		access.setField(obj, access.fieldIndex("localDateTime"), localDateTime);
		
		assertThat(obj.getLocalDateTime(), equalTo(localDateTime));
	}
	
	@Test
	public void getStringFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		obj.setString("Pizza Hut");
		
		String string = (String)access.getField(obj, access.fieldIndex("string"));
		
		assertThat(string, equalTo(obj.getString()));
	}
	
	@Test
	public void setStringFieldValueAndVerify() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		Foo obj = new Foo();
		String string = "Pizza Hut";
		
		access.setField(obj, access.fieldIndex("string"), string);
		
		assertThat(obj.getString(), equalTo(string));
	}
	
	/* END Common reference types */
}
