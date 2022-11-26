from excepciones.excepcionC1 import ExcepcionC1

class ExcepcionSugerida1(ExcepcionC1):
    def __init__(self, error):
        super().__init__(f"Error:\n{error}")