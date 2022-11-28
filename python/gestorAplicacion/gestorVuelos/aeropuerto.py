class Aeropuerto:
    def __init__(self,nombre=None):
        self._nombre=nombre
        self._vuelos=[]
        self._pasajeros=[]
        self._empleados=[]
        self._aviones=[]
        self._transaccionesKeys=[]
        self._transaccionesValues=[]
        self._dinero=0

    def getNombre(self):
        return self._nombre

    def setNombre(self,nombre):
        self._nombre=nombre

    def getVuelos(self):
        return self._vuelos

    def setVuelos(self,vuelos):
        self._vuelos=vuelos

    def addVuelo(self,vuelo):
        self._vuelos.append(vuelo)

    def getPasajeros(self):
        return self._pasajeros

    def setPasajeros(self,pasajeros):
        self._pasajeros=pasajeros

    def addPasajero(self,pasajeros):
        self._pasajeros.append(pasajeros)

    def getEmpleados(self):
        return self._empleados

    def setEmpleados(self,empleados):
        self._empleados=empleados

    def addEmpleado(self,empleados):
        self._empleados.append(empleados)

    def getAviones(self):
        return self._aviones

    def setAviones(self,aviones):
        self._aviones=aviones

    def addAvion(self,aviones):
        self._aviones.append(aviones)

    def getTransaccionesKeys(self):
        return self._transaccionesKeys

    def setTransaccionesKeys(self,transaccionesKeys):
        self._transaccionesKeys=transaccionesKeys

    def addTransaccionesKeys(self,transaccionesKeys):
        self._transaccionesKeys.append(transaccionesKeys)

    def getTransaccionesValues(self):
        return self._transaccionesValues

    def setTransaccionesValues(self,transaccionesValues):
        self._transaccionesValues=transaccionesValues

    def addTransaccionesValues(self,transaccionesValues):
        self._transaccionesValues.append(transaccionesValues)

    def getDinero(self):
        return self._dinero

    def setDinero(self,dinero):
        self._dinero=dinero