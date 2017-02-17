package com.github.javalbert.reflection.test;

import com.github.javalbert.reflection.ClassAccess;

public class Foo {
	public static class FooClassAccess2 implements ClassAccess<Foo> {
		@Override
		public int fieldIndex(String name) {
			switch (name) {
				case "intVal":
					return 0;
				case "longVal":
					return 1;
				case "stringVal":
					return 2;
				default:
					throw new IllegalArgumentException("No field with name: " + name);
			}
		}
		
		@Override
		public int intField(Foo object, int index) {
			switch (index) {
				case 0:
					return object.intVal;
				default:
					throw new IllegalArgumentException("No field with index: " + index);
			}
		}
	}
	
	private int intVal;
	private long longVal;
	private String stringVal;
	
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
	public String getStringVal() {
		return stringVal;
	}
	public void setStringVal(String stringVal) {
		this.stringVal = stringVal;
	}
}
