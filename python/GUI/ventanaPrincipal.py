from tkinter import *
from tkinter import Menu


class VentanaUsuario(Tk):
    VSabierta = False
    def __init__(self):
        super().__init__()
        self.title("Sistema de administracion de aeropuertos")
        self.option_add("*tearOff",  False)
        self.geometry("1000x600")
        self.ventanaInicio = None
        self.iconbitmap('./imagenes/icono.ico')
        
        def prueba():
            pass

        self._barraMenu = Menu(self)

        archivo = Menu(self._barraMenu)
        archivo.add_command(label = "Aplicacion", command = prueba)
        archivo.add_command(label = "Salir y guardar", command = self.salir)
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

        #Frames
        self.frame = Frame(self,relief="ridge",bd=2,bg="black")
        self.frame.pack(padx=15,pady=15,expand=True,fill=BOTH)
        self.fp = Frame(self.frame,bg="gray80")
        self.fp.pack(ipadx=6, padx=2,ipady=2,pady=2,fill = X)
        self.fd = Frame(self.frame)
        self.fd.pack(ipadx=2, padx=2,ipady=2,pady=2,fill = X)
        self.ventanaOpera = Frame(self.frame,bd=2)
        self.ventanaOpera.pack(padx = 10, pady= 20,fill=BOTH,expand = True)

        #Labels
        self.lp = Label(self.fp,text= "Nombre proceso", font = ("Courier", 12),height=2, bg="gray80")
        self.lp.pack()
        self.ld = Label(self.fd, text = "Descripción", font = ("Courier", 10))
        self.ld.pack()
        self.label = Label(self.ventanaOpera, text = "Resultado", font = ("Courier", 10))
        self.label.pack()
    
    def salir(self):
        self.__class__.VSabierta = False
        self.ventanaInicio.iconify()
        self.ventanaInicio.deiconify()
        self.destroy()
        
