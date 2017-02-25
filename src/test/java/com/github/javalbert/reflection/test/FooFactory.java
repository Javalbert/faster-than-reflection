package com.github.javalbert.reflection.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.github.javalbert.reflection.ClassAccess;

public interface FooFactory {
//	public static class FooFactoryClassAccess2 implements ClassAccess<FooFactory> {
//		@Override
//		public int fieldIndex(String name) {
//			return 0;
//		}
//
//		@Override
//		public boolean getBooleanField(FooFactory obj, int fieldIndex) {
//			return false;
//		}
//
//		@Override
//		public byte getByteField(FooFactory obj, int fieldIndex) {
//			return 0;
//		}
//
//		@Override
//		public char getCharField(FooFactory obj, int fieldIndex) {
//			return 0;
//		}
//
//		@Override
//		public double getDoubleField(FooFactory obj, int fieldIndex) {
//			return 0;
//		}
//
//		@Override
//		public float getFloatField(FooFactory obj, int fieldIndex) {
//			return 0;
//		}
//
//		@Override
//		public int getIntField(FooFactory obj, int fieldIndex) {
//			return 0;
//		}
//
//		@Override
//		public long getLongField(FooFactory obj, int fieldIndex) {
//			return 0;
//		}
//
//		@Override
//		public short getShortField(FooFactory obj, int fieldIndex) {
//			return 0;
//		}
//
//		@Override
//		public Boolean getBoxedBooleanField(FooFactory obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public Byte getBoxedByteField(FooFactory obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public Character getBoxedCharField(FooFactory obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public Double getBoxedDoubleField(FooFactory obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public Float getBoxedFloatField(FooFactory obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public Integer getBoxedIntField(FooFactory obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public Long getBoxedLongField(FooFactory obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public Short getBoxedShortField(FooFactory obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public void setBooleanField(FooFactory obj, int fieldIndex, boolean x) {
//		}
//
//		@Override
//		public void setByteField(FooFactory obj, int fieldIndex, byte x) {
//		}
//
//		@Override
//		public void setCharField(FooFactory obj, int fieldIndex, char x) {
//		}
//
//		@Override
//		public void setDoubleField(FooFactory obj, int fieldIndex, double x) {
//		}
//
//		@Override
//		public void setFloatField(FooFactory obj, int fieldIndex, float x) {
//		}
//
//		@Override
//		public void setIntField(FooFactory obj, int fieldIndex, int x) {
//		}
//
//		@Override
//		public void setLongField(FooFactory obj, int fieldIndex, long x) {
//		}
//
//		@Override
//		public void setShortField(FooFactory obj, int fieldIndex, short x) {
//		}
//
//		@Override
//		public void setBoxedBooleanField(FooFactory obj, int fieldIndex, Boolean x) {
//		}
//
//		@Override
//		public void setBoxedByteField(FooFactory obj, int fieldIndex, Byte x) {
//		}
//
//		@Override
//		public void setBoxedCharField(FooFactory obj, int fieldIndex, Character x) {
//		}
//
//		@Override
//		public void setBoxedDoubleField(FooFactory obj, int fieldIndex, Double x) {
//		}
//
//		@Override
//		public void setBoxedFloatField(FooFactory obj, int fieldIndex, Float x) {
//		}
//
//		@Override
//		public void setBoxedIntField(FooFactory obj, int fieldIndex, Integer x) {
//		}
//
//		@Override
//		public void setBoxedLongField(FooFactory obj, int fieldIndex, Long x) {
//		}
//
//		@Override
//		public void setBoxedShortField(FooFactory obj, int fieldIndex, Short x) {
//		}
//
//		@Override
//		public BigDecimal getBigDecimalField(FooFactory obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public void setBigDecimalField(FooFactory obj, int fieldIndex, BigDecimal x) {
//		}
//
//		@Override
//		public Date getDateField(FooFactory obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public void setDateField(FooFactory obj, int fieldIndex, Date x) {
//		}
//
//		@Override
//		public LocalDate getLocalDateField(FooFactory obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public void setLocalDateField(FooFactory obj, int fieldIndex, LocalDate x) {
//		}
//
//		@Override
//		public LocalDateTime getLocalDateTimeField(FooFactory obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public void setLocalDateTimeField(FooFactory obj, int fieldIndex, LocalDateTime x) {
//		}
//
//		@Override
//		public String getStringField(FooFactory obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public void setStringField(FooFactory obj, int fieldIndex, String x) {
//		}
//
//		@Override
//		public Object getField(FooFactory obj, int fieldIndex) {
//			return null;
//		}
//
//		@Override
//		public void setField(FooFactory obj, int fieldIndex, Object x) {
//		}
//
//		@Override
//		public int propertyIndex(String name) {
//			return 0;
//		}
//
//		@Override
//		public boolean getBooleanProperty(FooFactory obj, int propertyIndex) {
//			return false;
//		}
//
//		@Override
//		public byte getByteProperty(FooFactory obj, int propertyIndex) {
//			return 0;
//		}
//
//		@Override
//		public char getCharProperty(FooFactory obj, int propertyIndex) {
//			return 0;
//		}
//
//		@Override
//		public double getDoubleProperty(FooFactory obj, int propertyIndex) {
//			return 0;
//		}
//
//		@Override
//		public float getFloatProperty(FooFactory obj, int propertyIndex) {
//			return 0;
//		}
//
//		@Override
//		public int getIntProperty(FooFactory obj, int propertyIndex) {
//			return 0;
//		}
//
//		@Override
//		public long getLongProperty(FooFactory obj, int propertyIndex) {
//			return 0;
//		}
//
//		@Override
//		public short getShortProperty(FooFactory obj, int propertyIndex) {
//			return 0;
//		}
//
//		@Override
//		public Boolean getBoxedBooleanProperty(FooFactory obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public Byte getBoxedByteProperty(FooFactory obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public Character getBoxedCharProperty(FooFactory obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public Double getBoxedDoubleProperty(FooFactory obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public Float getBoxedFloatProperty(FooFactory obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public Integer getBoxedIntProperty(FooFactory obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public Long getBoxedLongProperty(FooFactory obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public Short getBoxedShortProperty(FooFactory obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public void setBooleanProperty(FooFactory obj, int propertyIndex, boolean x) {
//		}
//
//		@Override
//		public void setByteProperty(FooFactory obj, int propertyIndex, byte x) {
//		}
//
//		@Override
//		public void setCharProperty(FooFactory obj, int propertyIndex, char x) {
//		}
//
//		@Override
//		public void setDoubleProperty(FooFactory obj, int propertyIndex, double x) {
//		}
//
//		@Override
//		public void setFloatProperty(FooFactory obj, int propertyIndex, float x) {
//		}
//
//		@Override
//		public void setIntProperty(FooFactory obj, int propertyIndex, int x) {
//		}
//
//		@Override
//		public void setLongProperty(FooFactory obj, int propertyIndex, long x) {
//		}
//
//		@Override
//		public void setShortProperty(FooFactory obj, int propertyIndex, short x) {
//		}
//
//		@Override
//		public void setBoxedBooleanProperty(FooFactory obj, int propertyIndex, Boolean x) {
//		}
//
//		@Override
//		public void setBoxedByteProperty(FooFactory obj, int propertyIndex, Byte x) {
//		}
//
//		@Override
//		public void setBoxedCharProperty(FooFactory obj, int propertyIndex, Character x) {
//		}
//
//		@Override
//		public void setBoxedDoubleProperty(FooFactory obj, int propertyIndex, Double x) {
//		}
//
//		@Override
//		public void setBoxedFloatProperty(FooFactory obj, int propertyIndex, Float x) {
//		}
//
//		@Override
//		public void setBoxedIntProperty(FooFactory obj, int propertyIndex, Integer x) {
//		}
//
//		@Override
//		public void setBoxedLongProperty(FooFactory obj, int propertyIndex, Long x) {
//		}
//
//		@Override
//		public void setBoxedShortProperty(FooFactory obj, int propertyIndex, Short x) {
//		}
//
//		@Override
//		public BigDecimal getBigDecimalProperty(FooFactory obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public void setBigDecimalProperty(FooFactory obj, int propertyIndex, BigDecimal x) {
//		}
//
//		@Override
//		public Date getDateProperty(FooFactory obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public void setDateProperty(FooFactory obj, int propertyIndex, Date x) {
//		}
//
//		@Override
//		public LocalDate getLocalDateProperty(FooFactory obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public void setLocalDateProperty(FooFactory obj, int propertyIndex, LocalDate x) {
//		}
//
//		@Override
//		public LocalDateTime getLocalDateTimeProperty(FooFactory obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public void setLocalDateTimeProperty(FooFactory obj, int propertyIndex, LocalDateTime x) {
//		}
//
//		@Override
//		public String getStringProperty(FooFactory obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public void setStringProperty(FooFactory obj, int propertyIndex, String x) {
//		}
//
//		@Override
//		public Object getProperty(FooFactory obj, int propertyIndex) {
//			return null;
//		}
//
//		@Override
//		public void setProperty(FooFactory obj, int propertyIndex, Object x) {
//		}
//
//		@Override
//		public int methodIndex(String name) {
//			return 0;
//		}
//
//		@Override
//		public Object call(FooFactory obj, int methodIndex) {
//			return null;
//		}
//	}
	
	Foo newInstance();
}
