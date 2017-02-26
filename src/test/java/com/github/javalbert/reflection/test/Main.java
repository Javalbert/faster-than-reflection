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

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.javalbert.reflection.ClassAccess;
import com.github.javalbert.reflection.ClassAccessFactory;

public class Main {
	public static void main(String[] args) throws IntrospectionException {
		Main main = new Main();
		main.printInfo(FooFactory.class);
	}
	
	public <T> void printInfo(Class<T> clazz) throws IntrospectionException {
		ClassAccess<T> access = ClassAccessFactory.get(clazz);
		
		BeanInfo fooInfo = Introspector.getBeanInfo(clazz);
		
		System.out.println("FIELDS\n");

		List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
				.sorted(comparing(Field::getName))
				.collect(toList());
		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
			System.out.println(field.getName() + " = " 
					+ access.fieldIndex(field.getName())
					);
		}
		
		System.out.println("\nPROPERTIES\n");
		
		List<PropertyDescriptor> propertyDescriptors = Arrays.stream(fooInfo.getPropertyDescriptors())
				.filter(prop -> !prop.getName().equals("class"))
				.collect(Collectors.toList());
		for (int i = 0; i < propertyDescriptors.size(); i++) {
			PropertyDescriptor propertyDescriptor = propertyDescriptors.get(i);
			System.out.println(propertyDescriptor.getName() + " = " 
					+ access.propertyIndex(propertyDescriptor.getName())
					);
		}

		System.out.println("\nMETHODS\n");
		
		List<Method> methods = Arrays.stream(clazz.getDeclaredMethods())
				.sorted((a, b) -> {
					int compareMethodName = a.getName().compareTo(b.getName());
					if (compareMethodName != 0) {
						return compareMethodName;
					}
					
					Class<?>[] aparams = a.getParameterTypes();
					Class<?>[] bparams = b.getParameterTypes();
					
					int len = Math.min(aparams.length, bparams.length);
					for (int i = 0; i < len; i++) {
						int compareParamType = aparams[i].getName().compareTo(bparams[i].getName());
						if (compareParamType != 0) {
							return compareParamType;
						}
					}
					return Integer.compare(aparams.length, bparams.length);
				}).collect(toList());
		for (int i = 0; i < methods.size(); i++) {
			Method method = methods.get(i);
			System.out.println(method.getName() + Arrays.stream(method.getParameterTypes())
					.map(Class::getName)
					.collect(toList())
					+ " = " + access.methodIndex(method.getName(), method.getParameterTypes())
					);
		}
	}
}
