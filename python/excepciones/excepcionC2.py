from excepciones.errorAplicacion import ErrorAplicacion

class ExcepcionC2(ErrorAplicacion):
    def __init__(self, error):
        super().__init__(f"Error:\n{error}")