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
package com.github.javalbert.reflection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface PropertyAccess<T> {
	int propertyIndex(String name);
	
	// Get primitive type properties
	//
	boolean getBooleanProperty(T obj, int propertyIndex);
	byte getByteProperty(T obj, int propertyIndex);
	char getCharProperty(T obj, int propertyIndex);
	double getDoubleProperty(T obj, int propertyIndex);
	float getFloatProperty(T obj, int propertyIndex);
	int getIntProperty(T obj, int propertyIndex);
	long getLongProperty(T obj, int propertyIndex);
	short getShortProperty(T obj, int propertyIndex);

	// Get boxed primitive type properties
	//
	Boolean getBoxedBooleanProperty(T obj, int propertyIndex);
	Byte getBoxedByteProperty(T obj, int propertyIndex);
	Character getBoxedCharProperty(T obj, int propertyIndex);
	Double getBoxedDoubleProperty(T obj, int propertyIndex);
	Float getBoxedFloatProperty(T obj, int propertyIndex);
	Integer getBoxedIntProperty(T obj, int propertyIndex);
	Long getBoxedLongProperty(T obj, int propertyIndex);
	Short getBoxedShortProperty(T obj, int propertyIndex);

	// Set primitive type properties
	//
	void setBooleanProperty(T obj, int propertyIndex, boolean x);
	void setByteProperty(T obj, int propertyIndex, byte x);
	void setCharProperty(T obj, int propertyIndex, char x);
	void setDoubleProperty(T obj, int propertyIndex, double x);
	void setFloatProperty(T obj, int propertyIndex, float x);
	void setIntProperty(T obj, int propertyIndex, int x);
	void setLongProperty(T obj, int propertyIndex, long x);
	void setShortProperty(T obj, int propertyIndex, short x);
	
	// Set primitive wrapper type properties
	//
	void setBoxedBooleanProperty(T obj, int propertyIndex, Boolean x);
	void setBoxedByteProperty(T obj, int propertyIndex, Byte x);
	void setBoxedCharProperty(T obj, int propertyIndex, Character x);
	void setBoxedDoubleProperty(T obj, int propertyIndex, Double x);
	void setBoxedFloatProperty(T obj, int propertyIndex, Float x);
	void setBoxedIntProperty(T obj, int propertyIndex, Integer x);
	void setBoxedLongProperty(T obj, int propertyIndex, Long x);
	void setBoxedShortProperty(T obj, int propertyIndex, Short x);
	
	// Common reference types
	//
	BigDecimal getBigDecimalProperty(T obj, int propertyIndex);
	void setBigDecimalProperty(T obj, int propertyIndex, BigDecimal x);
	Date getDateProperty(T obj, int propertyIndex);
	void setDateProperty(T obj, int propertyIndex, Date x);
	LocalDate getLocalDateProperty(T obj, int propertyIndex);
	void setLocalDateProperty(T obj, int propertyIndex, LocalDate x);
	LocalDateTime getLocalDateTimeProperty(T obj, int propertyIndex);
	void setLocalDateTimeProperty(T obj, int propertyIndex, LocalDateTime x);
	String getStringProperty(T obj, int propertyIndex);
	void setStringProperty(T obj, int propertyIndex, String x);
	
	// For all properties
	//
	Object getProperty(T obj, int propertyIndex);
	void setProperty(T obj, int propertyIndex, Object x);
}
