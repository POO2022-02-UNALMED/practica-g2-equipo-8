from tkinter import *
import tkinter as tk 

class VentanaUsuario():
    abierta = False
    def __init__(self):
        self.ventanausu = tk.Tk()
        self.ventanausu.title("Sistema de administracion de aeropuertos")
        self.ventanausu.geometry("1280x720")
        self.ventanaInicio = None
