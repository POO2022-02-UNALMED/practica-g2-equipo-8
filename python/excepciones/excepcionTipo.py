from excepciones.errorAplicacion import ErrorAplicacion

class ExcepcionTipo(ErrorAplicacion):
    def __init__(self, error):
        super().__init__(f"Error por tipo de dato:\n{error}")

class ExcepcionVacio(ExcepcionTipo):
    def __init__(self,entradas):
         super().__init__(f"\nFalta llenar entradas: {entradas} \nPor favor ingreselas e intente de nuevo.")

class ExcepcionEnteroString(ExcepcionTipo):
    def __init__(self,valor):
        super().__init__(f"\n{valor} es un texto, por favor modificar a un numero entero." )

class ExcepcionEnteroFloat(ExcepcionTipo):
    def __init__(self,valor):
        super().__init__(f"\n{valor} no válido, por favor modificar a un entero.")

class ExcepcionStringNumero(ExcepcionTipo):
    def __init__(self,valor):
        super().__init__(f"la entrada {valor} es un número, debería ser texto, por favor modificar.")


