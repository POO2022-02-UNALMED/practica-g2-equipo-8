import time

import baseDatos.deserializador as deserializador
from gestorAplicacion.gestorHumana.pasajero import Pasajero
from gestorAplicacion.gestorVuelos.aeropuerto import Aeropuerto


class Vuelo:
    _origen = "Medellín"
    _aeropuerto = None
    def __init__(self,avion,fecha,destino,costo,salaEmbarque):
        self._avion = avion
        self._empleados = []
        self._pasajeros = []
        self._fecha = fecha
        self._destino = destino
        self._envuelo = False
        self._costo = costo
        self._salaEmbarque = salaEmbarque
        self._pesoActual = 0
        self._id = 0
        self._aeropuerto.addVuelo(self)
    
    @classmethod
    def nuevoID(cls):
        if len(cls._aeropuerto.getVuelos()) != 0:
            return cls._aeropuerto.getVuelos().get(len(cls._aeropuerto.getVuelos())-1).getId() + 1

        else:
            return 1

    def tiquete(self,pasajero):
       tique = str("\n" + "Ha sido registrado exitosamente" + "\n" + "\n" + "------------------------------------\n"
				+ "             Tiquete " + "\n" + "------------------------------------\n"
				+ "Nombre Pasajero: " + pasajero.getNombre() + "\n" + "Fecha: " + self._fecha + "\n" + "Vuelo: " + self.getId()
				+ "\n" + "Sala de embarque: " + pasajero.getVuelo().getSalaEmbarque() + "\n" + "Clase: "
				+ pasajero.getAsiento().getClase() + "\n" + "Num Silla: " + pasajero.getAsiento().getNumero() + "\n"
				+ "Origen: " + self._origen + "\n" + "Destino: " + self.getDestino() + "\n" + "Precio Total: "
				+ pasajero.getInversion() + "\n" + "------------------------------------\n")
       return tique

    def getAvion(self):
        return self._avion
    
    def setAvion(self,avion):
        self._avion = avion
    
    def getEmpleados(self):
        return self._empleados

    def setEmpleados(self,empleados):
        self._empleados = empleados

    def addEmpleado(self,e):
        self._empleados.append(e)

    def getPasajeros(self):
        return self._pasajeros

    def setPasajeros(self,pasajeros):
        self._pasajeros = pasajeros
    
    def addPasajero(self,pasajero):
        self._pasajeros.append(pasajero)

    def getFecha(self):
        return self._fecha
    
    def setFecha(self,fecha):
        self._fecha = fecha

    def getDestino(self):
        return self._destino

    def setDestino(self,destino):
        self._destino = destino
    
    def isEnVuelo(self):
        return self._envuelo 
    
    def setEnVuelo(self,envuelo):
        self._envuelo = envuelo

    def getCosto(self):
        return self._costo

    def setCosto(self,costo):
        self._costo = costo

    def getSalaEmbarque(self):
        return self._salaEmbarque

    def setSalaEmbarque(self,sala):
        self._salaEmbarque = sala

    def getPesoActual(self):
        return self._pesoActual

    def setPesoActual(self,peso):
        self._pesoActual = peso

    def getId(self):
        return self._id
    @classmethod
    def setAeropuerto(cls,aeropuerto):
        cls._aeropuerto = aeropuerto

    def encontrarVuelo(self,id):
        if id - 1 <= len(Vuelo._aeropuerto.getVuelos()):
            return Vuelo._aeropuerto.getVuelos().get(id-1)

        return None