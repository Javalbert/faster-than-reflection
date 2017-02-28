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
package com.github.javalbert;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import com.github.javalbert.reflection.ClassAccessFactory;
import com.github.javalbert.reflection.FieldAccess;

public class FasterThanReflectionBenchmark {
	/* START Field access */
	
	@State(Scope.Thread)
	public static class FieldAccessDirectState {
		public Foo foo = new Foo();
	}
	
	@State(Scope.Thread)
	public static class FieldAccessFasterThanReflectionState {
		public Foo foo = new Foo();
		public FieldAccess<Foo> fieldAccess;
		public int fieldIndex;
		
		@Setup(Level.Trial)
        public void doSetup() {
			fieldAccess = ClassAccessFactory.get(Foo.class);
			fieldIndex = fieldAccess.fieldIndex("intVal");
        }
	}
	
	@State(Scope.Thread)
	public static class FieldAccessReflectionApiState {
		public Foo foo = new Foo();
		public Field intValField;
		
		@Setup(Level.Trial)
        public void doSetup() {
            try {
				intValField = Foo.class.getDeclaredField("intVal");
	            intValField.setAccessible(true);
			} catch (NoSuchFieldException | SecurityException e) {
				throw new RuntimeException(e);
			}
        }
	}
	
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	@BenchmarkMode(Mode.AverageTime)
    @Benchmark
    public int testFieldAccessDirect(FieldAccessDirectState state) {
    	return state.foo.intVal;
    }
	
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	@BenchmarkMode(Mode.AverageTime)
    @Benchmark
    public int testFieldAccessFasterThanReflection(FieldAccessFasterThanReflectionState state) {
    	return state.fieldAccess.getIntField(state.foo, state.fieldIndex);
    }
	
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	@BenchmarkMode(Mode.AverageTime)
    @Benchmark
    public int testFieldAccessReflectionApi(FieldAccessReflectionApiState state)
    		throws IllegalArgumentException, IllegalAccessException {
		return state.intValField.getInt(state.foo);
    }
	
	/* END Field access */
}
