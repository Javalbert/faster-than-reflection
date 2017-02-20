# Faster Than Reflection

## Design Principles
- Get and set fields directly (including private fields)
- Get and set via JavaBean properties
- Methods for getting and setting primitive fields
- Methods for getting and setting boxed versions of primitive fields
- Methods for getting and setting fields of common classes e.g. String, Date, BigDecimal
- 23 Methods for calling methods with 0 to 22 parameters (no expensive varags creation)
- Method that accepts Object varags for calling any method
- Not a reinvention of Reflection API (but just field access and method invocation)
