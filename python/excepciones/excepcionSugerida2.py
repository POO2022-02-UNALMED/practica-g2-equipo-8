from excepciones.excepcionC2 import ExcepcionC2

class ExcepcionSugerida2(ExcepcionC2):
    def __init__(self, error):
        super().__init__(f"Error:\n{error}")