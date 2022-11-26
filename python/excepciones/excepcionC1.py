from excepciones.errorAplicacion import ErrorAplicacion

class ExcepcionC1(ErrorAplicacion):
    def __init__(self, error):
        super().__init__(f"Error:\n{error}")