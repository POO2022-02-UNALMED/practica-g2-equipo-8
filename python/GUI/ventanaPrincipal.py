from tkinter import *
from tkinter import Menu
from tkinter import messagebox
from tkinter import ttk
from fieldFrame import FieldFrame
#from gestorAplicacion.gestorVuelos.aeropuerto import Aeropuerto        NO ME FUNCIONA :(

class VentanaUsuario(Tk):
    VSabierta = False
    def __init__(self):
        super().__init__()
        self.title("Sistema de administracion de aeropuertos")
        self.option_add("*tearOff",  False)
        self.geometry("1280x720")
        self.ventanaInicio = None
        self.iconbitmap('./imagenes/icono.ico')
        self.widgetsActuales = []
        #self.aeropuerto = Aeropuerto()

        #Funciones
        def prueba():
            pass

        def descripcion():
            messagebox.showinfo("Descripcion del sistema", "La principal utilidad de la aplicación de gestión del aeropuerto es la administración de los aspectos principales de esta misma, en donde se guardará la información de los pasajeros, de los vuelos, aviones, empleados y finanzas, también se implementan funcionalidades para la gestión, modificación y adición de estos elementos.")

        def info():
            messagebox.showinfo("Desarrolladores del aplicativo","Maria Camila Zapata Arrubla\nJuan Camilo Molina Roncancio\nJuan Jose Zapata Cadavid\nNombre4")

        def borrarElementos():
            for i in self.widgetsActuales:
                i.destroy()
            self.widgetsActuales=[]

        #Pantallas
        def pantallaPrincipal():
            borrarElementos()
            self.lp = Label(self.fp,text= "Pantalla principal", font = ("Courier", 12),height=2, bg="gray80")
            self.lp.pack()
            self.ld = Label(self.fd, text = "Descripción de funcionalidades", font = ("Courier", 10))
            self.ld.pack()
            descripcion = '''Bienvenidos a la aplicacion de gestion y modificacion de aeropuerto, aqui podra realizar diferentes funciones
            relacionadas a la administracion del aeropuerto.

             En la parte superior en el menu de procesos y consultas se listan Las principales funcionalidades, las cuales son:


            1. Reserva de vuelo
            En esta opcion esta disponible lo necesario para agendar un vuelo a determinado pasajero, se muestran en pantalla
            todos los vuelos disponibles que se dirijan hacia la ciudad de destino deseada, luego se muestran los diferentes
            vuelos disponibles. En el mismo apartado se implementa un pequeño formulario de datos personales para la
            identificación del pasajero y así mismo la cantidad de equipajes que desea transportar. Tambien se le da la
            posibilidad de elegir el asiento que desea, los cuales están separados por turista, ejecutiva y primera clase, 
            esta elección influirá directamente en el costo del tiquete.
        

            2. Programar vuelos
            Esta funcionalidad permite programar nuevos vuelos que se llevarán a cabo en el aeropuerto, para posteriormente
            interactuar con ellos mediante otras funcionalidades, además de que implementa un sistema de recomendación de
            empleados asignados al vuelo seguidamente de su creación, basándose en la experiencia del empleado.


            3. Gestionar empleados
            Esta funcionalidad imprime los múltiples empleados que trabajan en el aeropuerto, junto a su cédula para poder 
            seleccionarlo y elegir entre opciones como cambiar de cargo, cambiar sueldo, asignar un vuelo y despedir empleados.
            

            4. Informacion sobre finanzas
            Esta funcionalidad muestra el saldo actual del aeropuerto y reúne las siguientes opciones, pagar nomina, consultar
            el historial de transacciones, contratar nuevos empleados e ingresar fondos al aeropuerto.


            5. Modificaciones generales
            Esta funcionalidad agrupa ciertas modificaciones posibles en dentro del sistema tales como cambiar de asiento de un
            pasajero, cancelar un vuelo, eliminar un avion e ingresar nuevos aviones al aeropuerto.
            '''

            self.label = Label(self.ventanaOpera, text = descripcion, font = ("Courier", 10))
            self.label.pack(ipadx=8, padx=8,ipady=8,pady=8,fill = X, anchor="w")
            self.widgetsActuales.extend([self.lp, self.ld, self.label])

        def pantallaReservaDeVuelo():
            borrarElementos()
            self.lp = Label(self.fp,text= "Reserva de vuelo", font = ("Courier", 12),height=2, bg="gray80")
            self.lp.pack()
            self.ld = Label(self.fd, text = "En este apartado puede realizar el agendamiento de un vuelo para un pasajero", font = ("Courier", 10))
            self.ld.pack()
            
            self.destino = Label(self.ventanaOpera, text = "Ingrese el destino deseado", font = ("Courier", 10))
            self.destino.grid(ipadx=8, padx=8, row=0, column=0, sticky="w")
            
            destinos = ["ejemplo 1", "ejemplo 2"]
            #for vuelo in self.aeropuerto.getVuelos():
            #    destino = vuelo.getDestino()
            #    destinos.append(destino)

            self.listaDestinos = ttk.Combobox(self.ventanaOpera, values=destinos, textvariable=StringVar(value=""))
            self.listaDestinos.grid(ipadx=8, padx=8, row=0, column=1, sticky="w")


            self.widgetsActuales.extend([self.lp, self.ld, self.destino, self.listaDestinos])
        
        def pantallaEmpleados():
            borrarElementos()
            self.lp=Label(self.fp,text="Gestor de empleados", font = ("Courier", 12),height=2, bg="gray80")
            self.lp.pack()
            self.widgetsActuales.extend([self.lp])
        
        def pantallaCambiarAsiento():
            borrarElementos()
            self.lp = Label(self.fp,text= "Cambiar silla", font = ("Courier", 12),height=2, bg="gray80")
            self.lp.pack()
            self.ld = Label(self.fd, text = "En este apartado puede realizar el cambio de silla a un determinado pasajero", font = ("Courier", 10))
            self.ld.pack()
            self.widgetsActuales.extend([self.lp,self.ld])

        def pantallaCancelarVuelo():
            borrarElementos()
            self.lp = Label(self.fp,text= "Cancelar vuelo", font = ("Courier", 12),height=2, bg="gray80")
            self.lp.pack()
            self.ld = Label(self.fd, text = "En este apartado puede cancelar un determinado vuelo", font = ("Courier", 10))
            self.ld.pack()

            criteriosEliminarAvion=["ID"]
            self.ventanaOpera.pack_forget()
            self.ventanaOpera = FieldFrame(self.frame,"Datos",criteriosEliminarAvion,"",None,None)
            self.ventanaOpera.crearBotones(prueba)

            self.widgetsActuales.extend([self.lp,self.ld,self.ventanaOpera])
        
        def pantallaEliminarAvion():
            borrarElementos()
            self.lp = Label(self.fp,text= "Eliminar avión", font = ("Courier", 12),height=2, bg="gray80")
            self.lp.pack()
            self.ld = Label(self.fd, text = "En este apartado puede eliminar un avión con su respectivo ID", font = ("Courier", 10))
            self.ld.pack()
            self.widgetsActuales.extend([self.lp,self.ld])
        
        def pantallaComprarAvion():
            borrarElementos()
            self.lp = Label(self.fp,text= "Comprar avión", font = ("Courier", 12),height=2, bg="gray80")
            self.lp.pack()
            self.ld = Label(self.fd, text = "En este apartado puede realizar la compra de un avión y asignarle un determinado vuelo", font = ("Courier", 10))
            self.ld.pack()
            self.widgetsActuales.extend([self.lp,self.ld])
            
        #Menus
        self._barraMenu = Menu(self)

        archivo = Menu(self._barraMenu)
        self._barraMenu.add_cascade(label = "Archivo", menu = archivo)
        archivo.add_command(label = "Aplicacion", command = descripcion)
        archivo.add_command(label = "Salir y guardar", command = self.salir)

        self.procesosYConsultas = Menu(self._barraMenu)
        self._barraMenu.add_cascade(label="Procesos y consultas", menu = self.procesosYConsultas)
        self.procesosYConsultas.add_command(label = "Funcionalidad 1", command = pantallaReservaDeVuelo)
        self.procesosYConsultas.add_command(label = "Funcionalidad 2", command = prueba)
        self.procesosYConsultas.add_command(label = "Funcionalidad 3", command = pantallaEmpleados)
        self.procesosYConsultas.add_command(label = "Funcionalidad 4", command = prueba)
        
        self.menuModificaciones = Menu(self.procesosYConsultas)
        self.procesosYConsultas.add_cascade(menu = self.menuModificaciones,label = "Administración de vuelos y aviones")
        self.menuModificaciones.add_command(label= "Cambiar asiento",command=pantallaCambiarAsiento)
        self.menuModificaciones.add_command(label= "Cancelar vuelo",command=pantallaCancelarVuelo)
        self.menuModificaciones.add_command(label= "Eliminar avión",command=pantallaEliminarAvion)
        self.menuModificaciones.add_command(label= "Comprar avión",command=pantallaComprarAvion)

        ayuda = Menu(self._barraMenu)
        self._barraMenu.add_cascade(label="Ayuda", menu=ayuda)
        ayuda.add_command(label="Acerca de", command = info)

        self.config(menu=self._barraMenu)

        #Frames
        self.frame = Frame(self,relief="ridge",bd=2,bg="#C2C4B6")
        self.frame.pack(padx=15,pady=15,expand=True,fill=BOTH)
        self.fp = Frame(self.frame,bg="gray80")
        self.fp.pack(ipadx=6, padx=2,ipady=2,pady=2,fill = X)
        self.fd = Frame(self.frame)
        self.fd.pack(ipadx=2, padx=2,ipady=2,pady=2,fill = X)
        self.ventanaOpera = Frame(self.frame,bd=2)
        self.ventanaOpera.pack(padx = 5, pady= 5,fill=BOTH,expand = True)

        #Llamado a pantalla principal
        pantallaPrincipal()

    def salir(self):
        self.__class__.VSabierta = False
        self.ventanaInicio.iconify()
        self.ventanaInicio.deiconify()
        self.destroy()
        
