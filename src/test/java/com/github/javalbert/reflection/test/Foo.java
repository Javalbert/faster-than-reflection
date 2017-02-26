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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Foo {
//	public static class FooAccess2 implements FieldAccess<Foo>, PropertyAccess<Foo> {
//		@Override
//		public int fieldIndex(String name) {
//			switch (name) {
//				case "booleanVal":
//					return 0;
//				default:
//					throw new IllegalArgumentException("No field with name: " + name);
//			}
//		}
//
//		@Override
//		public boolean getBooleanField(Foo obj, int fieldIndex) {
//			switch (fieldIndex) {
//				case 0:
//					return obj.booleanVal;
//				default:
//					throw new IllegalArgumentException("No field with index: " + fieldIndex);
//			}
//		}
//
//		@Override
//		public byte getByteField(Foo obj, int fieldIndex) {
//			return 0;
//		}
//
//		@Override
//		public char getCharField(Foo obj, int fieldIndex) {
//			return 0;
//		}
//
//		@Override
//		public double getDoubleField(Foo obj, int fieldIndex) {
//			switch (fieldIndex) {
//				case 3:
//					return obj.doubleVal;
//				default:
//					throw new IllegalArgumentException("No field with index: " + fieldIndex);
//			}
//		}
//
//		@Override
//		public float getFloatField(Foo obj, int fieldIndex) {
//			return 0;
//		}
//
//		@Override
//		public int getIntField(Foo obj, int fieldIndex) {
//			return 0;
//		}
//
//		@Override
//		public long getLongField(Foo obj, int fieldIndex) {
//			return 0;
//		}
//
//		@Override
//		public short getShortField(Foo obj, int fieldIndex) {
//			return 0;
//		}
//
//		@Override
//		public Boolean getBoxedBooleanField(Foo obj, int fieldIndex) {
//			switch (fieldIndex) {
//				case 8:
//					return obj.boxedBoolean;
//				default:
//					throw new IllegalArgumentException("No field with index: " + fieldIndex);
//			}
//		}
//
//		@Override
//		public Byte getBoxedByteField(Foo obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public Character getBoxedCharField(Foo obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public Double getBoxedDoubleField(Foo obj, int fieldIndex) {
//			switch (fieldIndex) {
//				case 11:
//					return obj.boxedDouble;
//				default:
//					throw new IllegalArgumentException("No field with index: " + fieldIndex);
//			}
//		}
//
//		@Override
//		public Float getBoxedFloatField(Foo obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public Integer getBoxedIntField(Foo obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public Long getBoxedLongField(Foo obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public Short getBoxedShortField(Foo obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public void setBooleanField(Foo obj, int fieldIndex, boolean x) {
//			switch (fieldIndex) {
//				case 0:
//					obj.booleanVal = x;
//				default:
//					throw new IllegalArgumentException("No field with index: " + fieldIndex);
//			}
//		}
//
//		@Override
//		public void setByteField(Foo obj, int fieldIndex, byte x) {
//		}
//
//		@Override
//		public void setCharField(Foo obj, int fieldIndex, char x) {
//		}
//
//		@Override
//		public void setDoubleField(Foo obj, int fieldIndex, double x) {
//			switch (fieldIndex) {
//				case 3:
//					obj.doubleVal = x;
//				default:
//					throw new IllegalArgumentException("No field with index: " + fieldIndex);
//			}
//		}
//
//		@Override
//		public void setFloatField(Foo obj, int fieldIndex, float x) {
//		}
//
//		@Override
//		public void setIntField(Foo obj, int fieldIndex, int x) {
//		}
//
//		@Override
//		public void setLongField(Foo obj, int fieldIndex, long x) {
//		}
//
//		@Override
//		public void setShortField(Foo obj, int fieldIndex, short x) {
//		}
//
//		@Override
//		public void setBoxedBooleanField(Foo obj, int fieldIndex, Boolean x) {
//			switch (fieldIndex) {
//				case 8:
//					obj.boxedBoolean = x;
//					break;
//				default:
//					throw new IllegalArgumentException("No field with index: " + fieldIndex);
//			}
//		}
//
//		@Override
//		public void setBoxedByteField(Foo obj, int fieldIndex, Byte x) {
//		}
//
//		@Override
//		public void setBoxedCharField(Foo obj, int fieldIndex, Character x) {
//		}
//
//		@Override
//		public void setBoxedDoubleField(Foo obj, int fieldIndex, Double x) {
//			switch (fieldIndex) {
//				case 11:
//					obj.boxedDouble = x;
//				default:
//					throw new IllegalArgumentException("No field with index: " + fieldIndex);
//			}
//		}
//
//		@Override
//		public void setBoxedFloatField(Foo obj, int fieldIndex, Float x) {
//		}
//
//		@Override
//		public void setBoxedIntField(Foo obj, int fieldIndex, Integer x) {
//		}
//
//		@Override
//		public void setBoxedLongField(Foo obj, int fieldIndex, Long x) {
//		}
//
//		@Override
//		public void setBoxedShortField(Foo obj, int fieldIndex, Short x) {
//		}
//
//		@Override
//		public BigDecimal getBigDecimalField(Foo obj, int fieldIndex) {
//			switch (fieldIndex) {
//				case 16:
//					return obj.bigDecimal;
//				default:
//					throw new IllegalArgumentException("No field with index: " + fieldIndex);
//			}
//		}
//
//		@Override
//		public void setBigDecimalField(Foo obj, int fieldIndex, BigDecimal x) {
//			switch (fieldIndex) {
//				case 16:
//					obj.bigDecimal = x;
//					break;
//				default:
//					throw new IllegalArgumentException("No field with index: " + fieldIndex);
//			}
//		}
//
//		@Override
//		public Date getDateField(Foo obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public void setDateField(Foo obj, int fieldIndex, Date x) {
//		}
//
//		@Override
//		public String getStringField(Foo obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public void setStringField(Foo obj, int fieldIndex, String x) {
//		}
//
//		@Override
//		public LocalDate getLocalDateField(Foo obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public void setLocalDateField(Foo obj, int fieldIndex, LocalDate x) {
//		}
//
//		@Override
//		public LocalDateTime getLocalDateTimeField(Foo obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public void setLocalDateTimeField(Foo obj, int fieldIndex, LocalDateTime x) {
//		}
//		
//		@Override
//		public Object getField(Foo obj, int fieldIndex) {
//			switch (fieldIndex) {
//				case 0:
//					return obj.booleanVal;
//				case 1:
//					return obj.byteVal;
//				case 2:
//					return obj.charVal;
//				case 3:
//					return obj.doubleVal;
//				case 4:
//					return obj.floatVal;
//				case 5:
//					return obj.intVal;
//				case 6:
//					return obj.longVal;
//				case 7:
//					return obj.shortVal;
//				case 8:
//					return obj.boxedBoolean;
//				default:
//					throw new IllegalArgumentException("No field with index: " + fieldIndex);
//			}
//		}
//		
//		@Override
//		public void setField(Foo obj, int fieldIndex, Object x) {
//			switch (fieldIndex) {
//				case 0:
//					obj.booleanVal = (boolean)x;
//					break;
//				case 1:
//					obj.byteVal = (byte)x;
//					break;
//				case 2:
//					obj.charVal = (char)x;
//					break;
//				case 3:
//					obj.doubleVal = (double)x;
//					break;
//				case 4:
//					obj.floatVal = (float)x;
//					break;
//				case 5:
//					obj.intVal = (int)x;
//					break;
//				case 6:
//					obj.longVal = (long)x;
//					break;
//				case 7:
//					obj.shortVal = (short)x;
//					break;
//				case 8:
//					obj.boxedBoolean = (Boolean)x;
//					break;
//				default:
//					throw new IllegalArgumentException("No field with index: " + fieldIndex);
//			}
//		}
//
//		@Override
//		public int propertyIndex(String name) {
//			switch (name) {
//				case "BooleanVal":
//					return 0;
//				default:
//					throw new IllegalArgumentException("No property with name: " + name);
//			}
//		}
//		
//		@Override
//		public boolean getBooleanProperty(Foo obj, int propertyIndex) {
//			switch (propertyIndex) {
//				case 1:
//					return obj.getBooleanVal();
//				default:
//					throw new IllegalArgumentException("No property with index: " + propertyIndex);
//			}
//		}
//
//		@Override
//		public byte getByteProperty(Foo obj, int propertyIndex) {
//			return 0;
//		}
//
//		@Override
//		public char getCharProperty(Foo obj, int propertyIndex) {
//			return 0;
//		}
//
//		@Override
//		public double getDoubleProperty(Foo obj, int propertyIndex) {
//			switch (propertyIndex) {
//				case 13:
//					return obj.getDoubleVal();
//				default:
//					throw new IllegalArgumentException("No property with index: " + propertyIndex);
//			}
//		}
//
//		@Override
//		public float getFloatProperty(Foo obj, int propertyIndex) {
//			return 0;
//		}
//
//		@Override
//		public int getIntProperty(Foo obj, int propertyIndex) {
//			return 0;
//		}
//
//		@Override
//		public long getLongProperty(Foo obj, int propertyIndex) {
//			return 0;
//		}
//
//		@Override
//		public short getShortProperty(Foo obj, int propertyIndex) {
//			return 0;
//		}
//
//		@Override
//		public Boolean getBoxedBooleanProperty(Foo obj, int propertyIndex) {
//			switch (propertyIndex) {
//				case 2:
//					return obj.getBoxedBoolean();
//				default:
//					throw new IllegalArgumentException("No property with index: " + propertyIndex);
//			}
//		}
//
//		@Override
//		public Byte getBoxedByteProperty(Foo obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public Character getBoxedCharProperty(Foo obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public Double getBoxedDoubleProperty(Foo obj, int propertyIndex) {
//			switch (propertyIndex) {
//				case 5:
//					return obj.getBoxedDouble();
//				default:
//					throw new IllegalArgumentException("No property with index: " + propertyIndex);
//			}
//		}
//
//		@Override
//		public Float getBoxedFloatProperty(Foo obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public Integer getBoxedIntProperty(Foo obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public Long getBoxedLongProperty(Foo obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public Short getBoxedShortProperty(Foo obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public void setBooleanProperty(Foo obj, int propertyIndex, boolean x) {
//			switch (propertyIndex) {
//				case 1:
//					obj.setBooleanVal(x);
//					break;
//				default:
//					throw new IllegalArgumentException("No property with index: " + propertyIndex);
//			}
//		}
//
//		@Override
//		public void setByteProperty(Foo obj, int propertyIndex, byte x) {
//		}
//
//		@Override
//		public void setCharProperty(Foo obj, int propertyIndex, char x) {
//		}
//
//		@Override
//		public void setDoubleProperty(Foo obj, int propertyIndex, double x) {
//			switch (propertyIndex) {
//				case 13:
//					obj.setDoubleVal(x);
//					break;
//				default:
//					throw new IllegalArgumentException("No property with index: " + propertyIndex);
//			}
//		}
//
//		@Override
//		public void setFloatProperty(Foo obj, int propertyIndex, float x) {
//		}
//
//		@Override
//		public void setIntProperty(Foo obj, int propertyIndex, int x) {
//		}
//
//		@Override
//		public void setLongProperty(Foo obj, int propertyIndex, long x) {
//		}
//
//		@Override
//		public void setShortProperty(Foo obj, int propertyIndex, short x) {
//		}
//
//		@Override
//		public void setBoxedBooleanProperty(Foo obj, int propertyIndex, Boolean x) {
//			switch (propertyIndex) {
//				case 2:
//					obj.setBoxedBoolean(x);
//					break;
//				default:
//					throw new IllegalArgumentException("No property with index: " + propertyIndex);
//			}
//		}
//
//		@Override
//		public void setBoxedByteProperty(Foo obj, int propertyIndex, Byte x) {
//		}
//
//		@Override
//		public void setBoxedCharProperty(Foo obj, int propertyIndex, Character x) {
//		}
//
//		@Override
//		public void setBoxedDoubleProperty(Foo obj, int propertyIndex, Double x) {
//			switch (propertyIndex) {
//				case 5:
//					obj.setBoxedDouble(x);
//					break;
//				default:
//					throw new IllegalArgumentException("No property with index: " + propertyIndex);
//			}
//		}
//
//		@Override
//		public void setBoxedFloatProperty(Foo obj, int propertyIndex, Float x) {
//		}
//
//		@Override
//		public void setBoxedIntProperty(Foo obj, int propertyIndex, Integer x) {
//		}
//
//		@Override
//		public void setBoxedLongProperty(Foo obj, int propertyIndex, Long x) {
//		}
//
//		@Override
//		public void setBoxedShortProperty(Foo obj, int propertyIndex, Short x) {
//		}
//
//		@Override
//		public BigDecimal getBigDecimalProperty(Foo obj, int propertyIndex) {
//			switch (propertyIndex) {
//				case 0:
//					return obj.getBigDecimal();
//				default:
//					throw new IllegalArgumentException("No property with index: " + propertyIndex);
//			}
//		}
//
//		@Override
//		public void setBigDecimalProperty(Foo obj, int propertyIndex, BigDecimal x) {
//			switch (propertyIndex) {
//				case 0:
//					obj.setBigDecimal(x);
//					break;
//				default:
//					throw new IllegalArgumentException("No property with index: " + propertyIndex);
//			}
//		}
//
//		@Override
//		public Date getDateProperty(Foo obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public void setDateProperty(Foo obj, int propertyIndex, Date x) {
//		}
//
//		@Override
//		public LocalDate getLocalDateProperty(Foo obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public void setLocalDateProperty(Foo obj, int propertyIndex, LocalDate x) {
//		}
//
//		@Override
//		public LocalDateTime getLocalDateTimeProperty(Foo obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public void setLocalDateTimeProperty(Foo obj, int propertyIndex, LocalDateTime x) {
//		}
//
//		@Override
//		public String getStringProperty(Foo obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public void setStringProperty(Foo obj, int propertyIndex, String x) {
//		}
//
//		
//		@Override
//		public Object getProperty(Foo obj, int propertyIndex) {
//			switch (propertyIndex) {
//				case 1:
//					return obj.getBooleanVal();
//				case 2:
//					return obj.getBoxedBoolean();
//				case 10:
//					return obj.getByteVal();
//				case 11:
//					return obj.getCharVal();
//				case 13:
//					return obj.getDoubleVal();
//				case 14:
//					return obj.getFloatVal();
//				case 15:
//					return obj.getIntVal();
//				case 18:
//					return obj.getLongVal();
//				case 19:
//					return obj.getShortVal();
//				default:
//					throw new IllegalArgumentException("No property with index: " + propertyIndex);
//			}
//		}
//
//		@Override
//		public void setProperty(Foo obj, int propertyIndex, Object x) {
//			switch (propertyIndex) {
//				case 1:
//					obj.setBooleanVal((boolean)x);
//					break;
//				case 2:
//					obj.setBoxedBoolean((Boolean)x);
//					break;
//				case 10:
//					obj.setByteVal((byte)x);
//					break;
//				case 11:
//					obj.setCharVal((char)x);
//					break;
//				case 13:
//					obj.setDoubleVal((double)x);
//					break;
//				case 14:
//					obj.setFloatVal((float)x);
//					break;
//				case 15:
//					obj.setIntVal((int)x);
//					break;
//				case 18:
//					obj.setLongVal((long)x);
//					break;
//				case 19:
//					obj.setShortVal((short)x);
//					break;
//				default:
//					throw new IllegalArgumentException("No property with index: " + propertyIndex);
//			}
//		}
//	}
	
	// Primitive types
	//
	private boolean booleanVal;
	private byte byteVal;
	private char charVal;
	private double doubleVal;
	private float floatVal;
	private int intVal;
	private long longVal;
	private short shortVal;
	
	// Primitive wrapper types
	//
	private Boolean boxedBoolean;
	private Byte boxedByte;
	private Character boxedChar;
	private Double boxedDouble;
	private Float boxedFloat;
	private Integer boxedInt;
	private Long boxedLong;
	private Short boxedShort;
	
	// Common reference types
	//
	private BigDecimal bigDecimal;
	private Date date;
	private LocalDate localDate;
	private LocalDateTime localDateTime;
	private String string;
	
	/* START Primitive type properties */
	
	public boolean getBooleanVal() {
		return booleanVal;
	}
	public void setBooleanVal(boolean booleanVal) {
		this.booleanVal = booleanVal;
	}
	public byte getByteVal() {
		return byteVal;
	}
	public void setByteVal(byte byteVal) {
		this.byteVal = byteVal;
	}
	public char getCharVal() {
		return charVal;
	}
	public void setCharVal(char charVal) {
		this.charVal = charVal;
	}
	public double getDoubleVal() {
		return doubleVal;
	}
	public void setDoubleVal(double doubleVal) {
		this.doubleVal = doubleVal;
	}
	public float getFloatVal() {
		return floatVal;
	}
	public void setFloatVal(float floatVal) {
		this.floatVal = floatVal;
	}
	public int getIntVal() {
		return intVal;
	}
	public void setIntVal(int intVal) {
		this.intVal = intVal;
	}
	public long getLongVal() {
		return longVal;
	}
	public void setLongVal(long longVal) {
		this.longVal = longVal;
	}
	public short getShortVal() {
		return shortVal;
	}
	public void setShortVal(short shortVal) {
		this.shortVal = shortVal;
	}
	
	/* END Primitive type properties */

	/* START Primitive wrapper type properties */
	
	public Boolean getBoxedBoolean() {
		return boxedBoolean;
	}
	public void setBoxedBoolean(Boolean boxedBoolean) {
		this.boxedBoolean = boxedBoolean;
	}
	public Byte getBoxedByte() {
		return boxedByte;
	}
	public void setBoxedByte(Byte boxedByte) {
		this.boxedByte = boxedByte;
	}
	public Character getBoxedChar() {
		return boxedChar;
	}
	public void setBoxedChar(Character boxedChar) {
		this.boxedChar = boxedChar;
	}
	public Double getBoxedDouble() {
		return boxedDouble;
	}
	public void setBoxedDouble(Double boxedDouble) {
		this.boxedDouble = boxedDouble;
	}
	public Float getBoxedFloat() {
		return boxedFloat;
	}
	public void setBoxedFloat(Float boxedFloat) {
		this.boxedFloat = boxedFloat;
	}
	public Integer getBoxedInt() {
		return boxedInt;
	}
	public void setBoxedInt(Integer boxedInt) {
		this.boxedInt = boxedInt;
	}
	public Long getBoxedLong() {
		return boxedLong;
	}
	public void setBoxedLong(Long boxedLong) {
		this.boxedLong = boxedLong;
	}
	public Short getBoxedShort() {
		return boxedShort;
	}
	public void setBoxedShort(Short boxedShort) {
		this.boxedShort = boxedShort;
	}
	
	/* END Primitive wrapper type properties */

	/* START Common reference types */
	
	public BigDecimal getBigDecimal() {
		return bigDecimal;
	}
	public void setBigDecimal(BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public LocalDate getLocalDate() {
		return localDate;
	}
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	
	/* END Common reference types */
}
