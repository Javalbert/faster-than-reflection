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
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ClassAccessPropertiesTest {
	public static class Foo2 {
		private String name;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	}
	
	public static void main(String[] args) {
		try {
			BeanInfo info = Introspector.getBeanInfo(Foo.class);
			List<PropertyDescriptor> propertyDescriptors = Collections.unmodifiableList(Arrays.stream(info.getPropertyDescriptors())
					.filter(prop -> !prop.getName().equals("class"))
					.collect(toList()));
			System.out.println(propertyDescriptors.size());
			
			propertyDescriptors.stream()
				.forEach(prop -> System.out.println(prop.getName()));
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
	}
}
