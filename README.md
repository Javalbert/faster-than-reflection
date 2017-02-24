# Faster Than Reflection

## Design Principles
- Get and set fields directly (including private fields)
- Get and set via JavaBean properties
- Methods for getting and setting primitive fields or properties
- Methods for getting and setting boxed versions of primitive fields or properties
- Methods for getting and setting fields or properties of common classes e.g. String, Date, BigDecimal
- 23 Methods for calling methods with 0 to 22 parameters (no expensive varargs creation)
- Method that accepts Object varargs for calling any method
- Not a reinvention of Reflection API (but just field access and method invocation)
