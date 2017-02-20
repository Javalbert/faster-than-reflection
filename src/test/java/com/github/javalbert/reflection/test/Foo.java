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

import com.github.javalbert.reflection.ClassAccess;

public class Foo {
	public static class FooAccess2 implements ClassAccess<Foo> {
		@Override
		public int fieldIndex(String name) {
			return 0;
		}

		@Override
		public boolean getBooleanField(Foo obj, int fieldIndex) {
			switch (fieldIndex) {
				case 0:
					return obj.booleanVal;
				default:
					throw new IllegalArgumentException("No field with index: " + fieldIndex);
			}
		}

		@Override
		public byte getByteField(Foo obj, int fieldIndex) {
			return 0;
		}

		@Override
		public char getCharField(Foo obj, int fieldIndex) {
			return 0;
		}

		@Override
		public double getDoubleField(Foo obj, int fieldIndex) {
			return 0;
		}

		@Override
		public float getFloatField(Foo obj, int fieldIndex) {
			return 0;
		}

		@Override
		public int getIntField(Foo obj, int fieldIndex) {
			return 0;
		}

		@Override
		public long getLongField(Foo obj, int fieldIndex) {
			return 0;
		}

		@Override
		public short getShortField(Foo obj, int fieldIndex) {
			return 0;
		}

		@Override
		public Boolean getBoxedBooleanField(Foo obj, int fieldIndex) {
			switch (fieldIndex) {
				case 8:
					return obj.boxedBoolean;
				default:
					throw new IllegalArgumentException("No field with index: " + fieldIndex);
			}
		}

		@Override
		public Byte getBoxedByteField(Foo obj, int fieldIndex) {
			return null;
		}

		@Override
		public Character getBoxedCharField(Foo obj, int fieldIndex) {
			return null;
		}

		@Override
		public Double getBoxedDoubleField(Foo obj, int fieldIndex) {
			return null;
		}

		@Override
		public Float getBoxedFloatField(Foo obj, int fieldIndex) {
			return null;
		}

		@Override
		public Integer getBoxedIntField(Foo obj, int fieldIndex) {
			return null;
		}

		@Override
		public Long getBoxedLongField(Foo obj, int fieldIndex) {
			return null;
		}

		@Override
		public Short getBoxedShortField(Foo obj, int fieldIndex) {
			return null;
		}

		@Override
		public void setBooleanField(Foo obj, int fieldIndex, boolean x) {
			switch (fieldIndex) {
				case 0:
					obj.booleanVal = x;
				default:
					throw new IllegalArgumentException("No field with index: " + fieldIndex);
			}
		}

		@Override
		public void setByteField(Foo obj, int fieldIndex, byte x) {
		}

		@Override
		public void setCharField(Foo obj, int fieldIndex, char x) {
		}

		@Override
		public void setDoubleField(Foo obj, int fieldIndex, double x) {
		}

		@Override
		public void setFloatField(Foo obj, int fieldIndex, float x) {
		}

		@Override
		public void setIntField(Foo obj, int fieldIndex, int x) {
		}

		@Override
		public void setLongField(Foo obj, int fieldIndex, long x) {
		}

		@Override
		public void setShortField(Foo obj, int fieldIndex, short x) {
		}

		@Override
		public void setBoxedBooleanField(Foo obj, int fieldIndex, Boolean x) {
			switch (fieldIndex) {
				case 8:
					obj.boxedBoolean = x;
					break;
				default:
					throw new IllegalArgumentException("No field with index: " + fieldIndex);
			}
		}

		@Override
		public void setBoxedByteField(Foo obj, int fieldIndex, Byte x) {
		}

		@Override
		public void setBoxedCharField(Foo obj, int fieldIndex, Character x) {
		}

		@Override
		public void setBoxedDoubleField(Foo obj, int fieldIndex, Double x) {
		}

		@Override
		public void setBoxedFloatField(Foo obj, int fieldIndex, Float x) {
		}

		@Override
		public void setBoxedIntField(Foo obj, int fieldIndex, Integer x) {
		}

		@Override
		public void setBoxedLongField(Foo obj, int fieldIndex, Long x) {
		}

		@Override
		public void setBoxedShortField(Foo obj, int fieldIndex, Short x) {
		}

		@Override
		public BigDecimal getBigDecimalField(Foo obj, int fieldIndex) {
			return null;
		}

		@Override
		public void setBigDecimalField(Foo obj, int fieldIndex, BigDecimal x) {
			switch (fieldIndex) {
				case 8:
					obj.bigDecimal = x;
					break;
				default:
					throw new IllegalArgumentException("No field with index: " + fieldIndex);
			}
		}

		@Override
		public Date getDateField(Foo obj, int fieldIndex) {
			return null;
		}

		@Override
		public void setDateField(Foo obj, int fieldIndex, Date x) {
		}

		@Override
		public String getStringField(Foo obj, int fieldIndex) {
			return null;
		}

		@Override
		public void setStringField(Foo obj, int fieldIndex, String x) {
		}

		@Override
		public LocalDate getLocalDateField(Foo obj, int fieldIndex) {
			return null;
		}

		@Override
		public void setLocalDateField(Foo obj, int fieldIndex, LocalDate x) {
		}

		@Override
		public LocalDateTime getLocalDateTimeField(Foo obj, int fieldIndex) {
			return null;
		}

		@Override
		public void setLocalDateTimeField(Foo obj, int fieldIndex, LocalDateTime x) {
		}
	}
	
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
