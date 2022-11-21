from tkinter import *
import tkinter as tk 

class VentanaUsuario():
    abierta = False
    def __init__(self):
        self.ventanausu = tk.Tk()
        self.ventanausu.title("Sistema de aeropuertos")
        self.ventanausu.geometry("700x500")
        self.ventanaInicio = None
