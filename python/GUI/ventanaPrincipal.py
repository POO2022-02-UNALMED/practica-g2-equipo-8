from tkinter import *
from tkinter import Menu


class VentanaUsuario(Tk):
    def __init__(self):

        super().__init__()

        self.title("Sistema de administracion de aeropuertos")
        self.option_add("*tearOff",  False)
        self.geometry("1280x720")
        self.ventanaInicio = None
        self.iconbitmap('./imagenes/icono.ico')
        
        def prueba():
            pass

        self._barraMenu = Menu(self)

        archivo = Menu(self._barraMenu)
        archivo.add_command(label = "Aplicacion", command = prueba)
        archivo.add_command(label = "Salir y guardar", command = prueba)
        self._barraMenu.add_cascade(label = "Archivo", menu = archivo)

        procesosYConsultas = Menu(self._barraMenu)
        self._barraMenu.add_cascade(label="Procesos y consultas", menu=procesosYConsultas)
        procesosYConsultas.add_command(label = "Funcionalidad 1", command = prueba)
        procesosYConsultas.add_command(label = "Funcionalidad 2", command = prueba)
        procesosYConsultas.add_command(label = "Funcionalidad 3", command = prueba)
        procesosYConsultas.add_command(label = "Funcionalidad 4", command = prueba)
        procesosYConsultas.add_command(label = "Funcionalidad 5", command = prueba)

        ayuda = Menu(self._barraMenu)
        self._barraMenu.add_cascade(label="Ayuda", menu=ayuda)
        ayuda.add_command(label="Acerca de", command = prueba)

        self.config(menu=self._barraMenu)
        
