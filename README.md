## Modificaciones realizadas respecto a la versión previa:

1. **Patrón Singleton** aplicado a la capa de acceso a datos.
2. Implementación de un **controlador principal** que recibe todas las peticiones y las redirige al servlet correspondiente.
3. Creación de una **capa de servicio** entre DAOs y servlets.
4. Generación de nuevas **excepciones** personalizadas. Cualquier excepción se propagará, siendo normalizada en la capa de servicio, hasta llegar al controlador principal, donde este procederá a su manejo.
