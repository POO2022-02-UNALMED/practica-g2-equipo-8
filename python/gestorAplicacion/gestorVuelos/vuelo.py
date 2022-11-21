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
        Aeropuerto.addVuelo(self)
    
    @staticmethod
    def nuevoID():
        if len(Aeropuerto.getVuelos()) != 0:
            return Aeropuerto.getVuelos().get(len(Aeropuerto.getVuelos())-1).getId() + 1

        else:
            return 1

    #def tiquete(self):
     #   tique = "\n" + "Ha sido registrado exitosamente" + "\n" + "\n" + "------------------------------------\n"
	#			+ "             Tiquete " + "\n" + "------------------------------------\n"
	#			+ "Nombre Pasajero: " + Pasajero.getNombre() + "\n" + "Fecha: " + self._fecha + "\n" + "Vuelo: " + getId()
	#			+ "\n" + "Sala de embarque: " + Pasajero.getVuelo().getSalaEmbarque() + "\n" + "Clase: "
	#			+ Pasajero.getAsiento().getClase() + "\n" + "Num Silla: " + Pasajero.getAsiento().getNumero() + "\n"
	#			+ "Origen: " + self._origen + "\n" + "Destino: " + getDestino() + "\n" + "Precio Total: "
	#			+ Pasajero.getInversion() + "\n" + "------------------------------------\n"
     #   return tique

    def getAvion(self):
        return self._avion
    
    def setAvion(self,avion):
        self._avion = avion
    
    def getEmpleados(self):
        return self._empleados

    def setEmpleados(self,empleados):
        self._empleados = empleados

    def getPasajeros(self):
        return self._pasajeros

    def setPasajeros(self,pasajeros):
        self._pasajeros = pasajeros

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

    def setAeropuerto(self,aeropuerto):
        Vuelo._aeropuerto = aeropuerto

    def encontrarVuelo(self,id):
        if id - 1 <= len(Aeropuerto.getVuelos()):
            return Aeropuerto.getVuelos().get(id-1)

        return None