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
		
		// 0-4 table (2 elements no more than 4 in gap)
		// 0-5 lookup  (2 elements no less than 5 in gap)
		@Override
		public int getIntField(Foo object, int index) {
			switch (index) {
				case 0:
					return object.intVal;
				case 5:
					return object.version;
				case 6:
					return object.version;
				case 7:
					return object.version;
				case 12:
					return object.version;
				case 13:
					return object.version;
				default:
					throw new IllegalArgumentException("No field with index: " + index);
			}
		}
	}
	
	private int intVal;
	private long longVal;
	private String stringVal;
	private int age;
	private String stage;
	private int version;
	private Double price;
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
