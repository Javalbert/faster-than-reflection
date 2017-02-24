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

import static java.util.stream.Collectors.toList;

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
		ClassAccess<Foo> fooAccess = ClassAccessFactory.get(Foo.class);
		
		BeanInfo fooInfo = Introspector.getBeanInfo(Foo.class);
		
		System.out.println("FIELDS\n");
		
		List<Field> fields = Arrays.asList(Foo.class.getDeclaredFields());
		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
			System.out.println(field.getName() + " = " + fooAccess.fieldIndex(field.getName()));
		}
		
		System.out.println("\nPROPERTIES\n");
		
		List<PropertyDescriptor> propertyDescriptors = Arrays.stream(fooInfo.getPropertyDescriptors())
				.filter(prop -> !prop.getName().equals("class"))
				.collect(Collectors.toList());
		for (int i = 0; i < propertyDescriptors.size(); i++) {
			PropertyDescriptor propertyDescriptor = propertyDescriptors.get(i);
			System.out.println(propertyDescriptor.getName() + " = " + fooAccess.propertyIndex(propertyDescriptor.getName()));
		}

		System.out.println("\nMETHODS\n");
		
		List<Method> methods = Arrays.stream(Foo.class.getDeclaredMethods())
				.sorted((a, b) -> a.getName().compareTo(b.getName()))
				.collect(toList());
		for (int i = 0; i < methods.size(); i++) {
			Method method = methods.get(i);
			System.out.println(method.getName() + " = " + fooAccess.methodIndex(method.getName()));
		}
	}
}
