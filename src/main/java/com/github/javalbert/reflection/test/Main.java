package com.github.javalbert.reflection.test;

import java.lang.reflect.Field;
import java.util.Random;

import com.github.javalbert.reflection.ClassAccess;
import com.github.javalbert.reflection.ClassAccessFactory;

public class Main {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Main().runNonJMHBenchmarkClassAccess();
			new Main().runNonJMHBenchmarkReflection();
		}
	}
	
	private Foo foo = new Foo();
	private Random r = new Random();

	public void runNonJMHBenchmarkClassAccess() {
		foo.setVersion(r.nextInt());
		
		ClassAccess<Foo> access = ClassAccessFactory.get(Foo.class);
		
		long start = System.nanoTime();
		for (int i = 0; i < 10_000_000; i++) {
			access.getIntField(foo, 5);
		}
		long ns = System.nanoTime() - start;

		System.out.println("ClassAccess: " + ns);
		System.out.println("ClassAccess: " + ns / 1_000_000L);
	}
	
	public void runNonJMHBenchmarkReflection() {
		foo.setVersion(r.nextInt());
		
		try {
			Field intField = Foo.class.getDeclaredField("version");
			intField.setAccessible(true);
			
			long start = System.nanoTime();
			for (int i = 0; i < 10_000_000; i++) {
				intField.get(foo);
			}
			long ns = System.nanoTime() - start;

			System.out.println("Reflection: " + ns);
			System.out.println("Reflection: " + ns / 1_000_000L);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
