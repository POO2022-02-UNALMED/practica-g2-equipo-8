from excepciones.excepcionC2 import ExcepcionC2

class ExcepcionInventada3(ExcepcionC2):
    def __init__(self, error):
        super().__init__(f"Error:\n{error}")