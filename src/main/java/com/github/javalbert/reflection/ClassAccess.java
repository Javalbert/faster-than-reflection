package com.github.javalbert.reflection;

public interface ClassAccess<T> {
	int fieldIndex(String name);
	
	int intField(T object, int index);
}
