import enum

class Cargos(enum.Enum):
    AZAFATA=("Azafata", 1000)
    CONTROL_DE_PISTA=("Control de pista", 1500)
    PILOTO=("Piloto", 2000)
    COPILOTO=("Copiloto", 1800)

    def __init__(self,cargo,sueldoBase):
        self._cargo=cargo
        self._sueldoBase=sueldoBase

    def getCargo(self):
        return self._cargo
    
    def getSueldoBase(self):
        return self._sueldoBase