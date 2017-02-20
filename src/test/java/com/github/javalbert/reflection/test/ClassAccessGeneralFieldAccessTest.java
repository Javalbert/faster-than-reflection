package com.github.javalbert.reflection.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.javalbert.reflection.ClassAccess;
import com.github.javalbert.reflection.ClassAccessFactory;

public class ClassAccessGeneralFieldAccessTest {
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
}
