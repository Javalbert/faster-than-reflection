# FasterThanReflection

Mainly inspired by [ReflectASM library](https://github.com/EsotericSoftware/reflectasm) and a desire to provide faster performance in [SqlbuilderORM library](https://github.com/Javalbert/sql-builder-orm). Many ORMs use bytecode generation to generate classes that can set the fields of different entities, which is faster than using the Reflection API.

## Design Principles
- Get and set fields directly (including private fields)
- Get and set via JavaBean properties
- Methods for getting and setting primitive fields or properties
- Methods for getting and setting boxed versions of primitive fields or properties
- Methods for getting and setting fields or properties of common classes e.g. String, Date, BigDecimal
- 23 Methods for calling methods with 0 to 22 parameters (no expensive varargs creation)
- Method that accepts Object varargs for calling any method
- Not a reinvention of Reflection API (but faster field, property and method access)
- Uses `new ClassWriter(0)` for best performance, instead of `new ClassWriter(ClassWriter.COMPUTE_MAXS)` (10% slower) or `new ClassWriter(ClassWriter.COMPUTE_FRAMES)` (2x slower)

## Installation

Maven

```xml
<dependency>
    <groupId>com.github.javalbert</groupId>
    <artifactId>faster-than-reflection</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage

Retrieve an instance of ClassAccess.

```java
ClassAccess<Foo> classAccess = ClassAccessFactory.get(Foo.class);
```

ClassAccess interface extends interfaces: FieldAccess, PropertyAccess, and MethodAccess.

```java
FieldAccess<Foo> fieldAccess = ClassAccessFactory.get(Foo.class);
PropertyAccess<Foo> propertyAccess = ClassAccessFactory.get(Foo.class);
MethodAccess<Foo> methodAccess = ClassAccessFactory.get(Foo.class);
```

Get and set fields or properties

```java
// Instance of a class whose fields or properties you want to get or set
Foo foo = new Foo();

/* Fields */

int intValFieldIndex = fieldAccess.fieldIndex("intVal");

fieldAccess.getIntField(foo, intValFieldIndex); // i.e. foo.intVal
fieldAccess.setIntField(foo, intValFieldIndex, 1); // i.e. foo.intVal = 1

/* Properties */

int intValPropertyIndex = propertyAccess.propertyIndex("intVal");

propertyAccess.getIntProperty(foo, intValPropertyIndex); // i.e. foo.getIntVal()
propertyAccess.setIntProperty(foo, intValPropertyIndex, 2); // i.e. foo.setIntVal(2);
```

Call methods

```java
int methodIndex = methodAccess.methodIndex("newInstance");

// Call a method that has 2 parameters
methodAccess.call(fooFactory, methodIndex, true, 1337); // i.e. fooFactory.newInstance(true, 1337)

// Or use invoke for methods that have more than 22 parameters
methodAccess.invoke(fooFactory, someCrazyMethod, (Object[])lotsOfVarargs);
```

## Performance

JMH benchmark code can be found in [faster-than-reflection-benchmark](faster-than-reflection-benchmark) folder in the root of this project.

Test environment: Intel Core i7-4790k, Windows 10 x64 + High Performance Power Plan, Java version "1.8.0_121" Java Hotspot(TM) 64-Bit Server VM (build 25.121-b13, mixed mode)

| Benchmark | Mode | Samples | Score | Score error | Units |
|---|---|---:|---:|---:|---|
| testFieldAccessDirect | avgt | 200 | 0.686 | 0.001 | ns/op |
| testFieldAccessFasterThanReflection | avgt | 200 | 1.028 | 0.002 | ns/op |
| testFieldAccessReflectAsm | avgt | 200 | 0.912 | 0.001 | ns/op |
| testFieldAccessReflectionApi | avgt | 200 | 2.901 | 0.002 | ns/op |
| testPropertyAccessDirect | avgt | 200 | 0.685 | 0.001 | ns/op |
| testPropertyAccessFasterThanReflection | avgt | 200 | 1.027 | 0.002 | ns/op |
| testPropertyAccessReflectAsm | avgt | 200 | 1.301 | 0.025 | ns/op |
| testPropertyAccessReflectionApi | avgt | 200 | 4.026 | 0.006 | ns/op |
<!---
|  |  |  |  |  |  |
--->

## Contributing

This is a one-man project but feel free to fork.

## Acknowledgments

* [ReflectASM](https://github.com/EsotericSoftware/reflectasm), main inspiration, AccessClassLoader.java
* dimzon's [fork](https://github.com/dimzon/reflectasm) of ReflectASM, where I figured out how his changes allow access to private class members (which is a limitation of ReflectASM)