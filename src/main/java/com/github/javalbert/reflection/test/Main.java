package com.github.javalbert.reflection.test;

import java.util.Random;

import com.github.javalbert.reflection.ClassAccess;
import com.github.javalbert.reflection.ClassAccessFactory;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
		main.testClassAccess();
	}

	private Foo foo = new Foo();
	private Random r = new Random();

	public void testClassAccess() {
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		foo.setIntVal(r.nextInt());
		System.out.println(access.getIntField(foo, 0));
	}
}
