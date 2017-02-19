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

import com.github.javalbert.reflection.ClassAccess;

public class Foo {
	public static class FooAccess2 implements ClassAccess<Foo> {
		@Override
		public int fieldIndex(String name) {
			return 0;
		}

		@Override
		public boolean getBooleanField(Foo object, int fieldIndex) {
			switch (fieldIndex) {
				case 0:
					return object.booleanVal;
				default:
					throw new IllegalArgumentException("No field with index: " + fieldIndex);
			}
		}

		@Override
		public byte getByteField(Foo object, int fieldIndex) {
			return 0;
		}

		@Override
		public char getCharField(Foo object, int fieldIndex) {
			return 0;
		}

		@Override
		public double getDoubleField(Foo object, int fieldIndex) {
			return 0;
		}

		@Override
		public float getFloatField(Foo object, int fieldIndex) {
			return 0;
		}

		@Override
		public int getIntField(Foo object, int fieldIndex) {
			return 0;
		}

		@Override
		public long getLongField(Foo object, int fieldIndex) {
			return 0;
		}

		@Override
		public short getShortField(Foo object, int fieldIndex) {
			return 0;
		}

		@Override
		public Boolean getBoxedBooleanField(Foo object, int fieldIndex) {
			switch (fieldIndex) {
				case 8:
					return object.boxedBoolean;
				default:
					throw new IllegalArgumentException("No field with index: " + fieldIndex);
			}
		}

		@Override
		public Byte getBoxedByteField(Foo object, int fieldIndex) {
			return null;
		}

		@Override
		public Character getBoxedCharField(Foo object, int fieldIndex) {
			return null;
		}

		@Override
		public Double getBoxedDoubleField(Foo object, int fieldIndex) {
			return null;
		}

		@Override
		public Float getBoxedFloatField(Foo object, int fieldIndex) {
			return null;
		}

		@Override
		public Integer getBoxedIntField(Foo object, int fieldIndex) {
			return null;
		}

		@Override
		public Long getBoxedLongField(Foo object, int fieldIndex) {
			return null;
		}

		@Override
		public Short getBoxedShortField(Foo object, int fieldIndex) {
			return null;
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

	/* START Primitive type properties */
	
	public boolean isBooleanVal() {
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
}
