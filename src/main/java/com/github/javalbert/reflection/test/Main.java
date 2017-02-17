package com.github.javalbert.reflection.test;

import java.util.Random;

import com.github.javalbert.reflection.ClassAccess;
import com.github.javalbert.reflection.ClassAccessFactory;

public class Main {
	public static void main(String[] args) {
		new Main().testClassAccess();
	}

	public void testClassAccess() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		System.out.println(access.fieldIndex("intVal"));
		System.out.println(access.fieldIndex("longVal"));
		System.out.println(access.fieldIndex("stringVal"));
		
		Foo foo = new Foo();
		foo.setIntVal(new Random().nextInt());
		System.out.println(access.intField(foo, 0));
	}
}
