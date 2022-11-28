from tkinter import *
from tkinter import messagebox,Menu,ttk
from tkinter.font import Font

import tkinter as tk
from fieldFrame import FieldFrame

from datetime import datetime

from gestorAplicacion.gestorVuelos.aeropuerto import Aeropuerto
from gestorAplicacion.gestorVuelos.asiento import Asiento
from gestorAplicacion.gestorVuelos.equipaje import Equipaje
from gestorAplicacion.gestorVuelos.vuelo import Vuelo
from gestorAplicacion.gestorVuelos.avion import Avion

from gestorAplicacion.gestorHumana.cargos import Cargos
from gestorAplicacion.gestorHumana.empleado import Empleado
from gestorAplicacion.gestorHumana.pasajero import Pasajero
from gestorAplicacion.gestorHumana.persona import Persona

from baseDatos.deserializador import deserializar
from baseDatos.serializador import serializar

class VentanaUsuario(Tk):
    VSabierta = False
    def __init__(self):
        super().__init__()
        self.title("Sistema de administracion de aeropuertos")
        self.option_add("*tearOff",  False)
        self.geometry("1280x720")
        self.ventanaInicio = None
        self.resizable(0,0)
        self.iconbitmap('./imagenes/icono.ico')
        self.widgetsActuales = []
        self.aeropuerto = Aeropuerto()
        Persona.setAeropuerto(self.aeropuerto)
        Avion.setAeropuerto(self.aeropuerto)
        Vuelo.setAeropuerto(self.aeropuerto)
        #self.valoresIniciales()
        deserializar(self.aeropuerto)

        #Funciones
        def prueba():
            pass
        

        self.bind('<Destroy>',lambda x:serializar(self.aeropuerto))

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
            def limpiarFrame():
                for widget in self.of.winfo_children():
                    widget.destroy()
            def verDatos():
                limpiarFrame()
                if len(self.lb.curselection())!=0:
                    empleado=self.aeropuerto.buscarEmpleado(int(self.lb.get(self.lb.curselection()[0]).split()[1]))

                    self.nl=Label(self.of,text=empleado.getNombre()+", "+str(empleado.getEdad())+" años",font=Font(family='Courier',size=60))
                    self.nl.grid(row=0,column=0,padx=5,pady=5,sticky="w")
                    
                    self.cl=Label(self.of,text="Numero de cedula: "+str(empleado.getCedula()),font=Font(family='Courier',size=35))
                    self.cl.grid(row=1,column=0,padx=5,pady=5,sticky="w")

                    self.sl=Label(self.of,text="Sueldo actual: "+str(empleado.getSueldo()),font=Font(family='Courier',size=35))
                    self.sl.grid(row=2,column=0,padx=5,pady=5,sticky="w")

                    self.carl=Label(self.of,text="Cargo: "+str(empleado.getCargo()),font=Font(family='Courier',size=35))
                    self.carl.grid(row=3,column=0,padx=5,pady=5,sticky="w")

                    try:
                        self.v1l=Label(self.of,text="Destino del vuelo asignado: "+str(empleado.getVuelo().getDestino()),font=Font(family='Courier',size=35))
                        self.v2l=Label(self.of,text="Fecha: "+str(empleado.getVuelo().getFecha()),font=Font(family='Courier',size=35))
                        self.v2l.grid(row=4,column=1,padx=5,pady=5,sticky="w")

                        self.val=Label(self.of,text="Avión del vuelo: "+str(empleado.getVuelo().getAvion().getModelo()),font=Font(family='Courier',size=35))
                        self.val.grid(row=5,column=0,padx=5,pady=5,sticky="w")

                        peso=0
                        for i in empleado.getVuelo().getPasajeros():
                            try:
                                pass
                            except:
                                pass
                        self.vpal=Label(self.of,text="Peso actual: "+str(peso)+"/"+str(empleado.getVuelo()),font=Font(family='Courier',size=35))
                        self.vpal.grid(row=5,column=0,padx=5,pady=5,sticky="w")
                    except:
                        self.v1l=Label(self.of,text="Este empleado aún no tiene un vuelo asignado",font=Font(family='Courier',size=35))
                    self.v1l.grid(row=4,column=0,padx=5,pady=5,sticky="w")

                    

            borrarElementos()
            self.lp=Label(self.fp,text="Gestor de empleados", font = ("Courier", 12),height=2, bg="gray80")
            self.lp.pack()
            self.ld = Label(self.fd, text = "En este apartado puede visualizar los datos de los empleados y gestionar los mismos", font = ("Courier", 10))
            self.ld.pack()

            self.scroll=Scrollbar(self.ventanaOpera,orient='vertical')

            self.lb=Listbox(self.ventanaOpera,yscrollcommand=self.scroll.set,font='Courier',width=40,height=20)
            self.lb.grid(row=0,column=0,columnspan=4,sticky="snew",padx=5,pady=5)

            self.scroll.configure(command=self.lb.yview)         
            self.scroll.grid(column=4, row=0, sticky='NS')    

            for i in self.aeropuerto.getEmpleados():
                self.lb.insert(tk.END,"Cedula: "+str(i.getCedula())+" "*(15-len(str(i.getCedula()))) +"Nombre: "+i.getNombre())
            
            self.of=Frame(self.ventanaOpera)
            self.of.grid(row=0,column=5,rowspan=4,sticky='nsew')

            self.datosButton=Button(self.ventanaOpera,text="Ver datos",command=verDatos)
            self.datosButton.grid(row=1,padx=5,pady=5)

            self.widgetsActuales.extend([self.lp,self.datosButton,self.ld,self.lb,self.scroll,self.of])
        
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
        self.procesosYConsultas.add_command(label = "Gestion de empleados", command = pantallaEmpleados)
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

   

    def valoresIniciales(self):

        self.aeropuerto.setDinero(10000000)

        vuelo1=Vuelo(Avion("XYZ",100,50000),datetime(2022,11,30,10,0,0),"Cancun",1500,"A1")
        vuelo2=Vuelo(Avion("YY3X",150,75000),datetime(2022,12,5,10,0,0),"Madrid",5500,"A2")
        vuelo3=Vuelo(Avion("XCF",75,25000),datetime(2022,12,5,10,0,0),"Madrid",5500,"A2")
        e1=Empleado("Juan",12345,30,"M",2300,Cargos.PILOTO.getCargo())
        e2=Empleado("Pedro",543657,35,"M",1800,Cargos.COPILOTO.getCargo())
        e3=Empleado("Sara",4235246,66,"F",1500,Cargos.CONTROL_DE_PISTA.getCargo())
        e4=Empleado("Carla",67436653,35,"F",1500,Cargos.AZAFATA.getCargo())
        e5=Empleado("Federico",9787845,40,"M",1800,Cargos.AZAFATA.getCargo())
        e6=Empleado("Andrea",2425,38,"F",1800,Cargos.PILOTO.getCargo())
        e7=Empleado("Camilo",27354235,60,"M",1800,Cargos.PILOTO.getCargo())
        e8=Empleado("Maria",14136,55,"F",1800,Cargos.CONTROL_DE_PISTA.getCargo())
        e9=Empleado("Fernando",64378,23,"M",1800,Cargos.AZAFATA.getCargo())
        Empleado("Paco",451675,48,"M",1800,Cargos.CONTROL_DE_PISTA.getCargo())
        Empleado("Camila",2565324,19,"F",1800,Cargos.COPILOTO.getCargo())
        Empleado("Luisa",47594,34,"F",1800,Cargos.PILOTO.getCargo())
        Empleado("Jose",11055,45,"M",1800,Cargos.AZAFATA.getCargo())

        e1.setVuelo(vuelo1)
        e2.setVuelo(vuelo1)
        e3.setVuelo(vuelo1)
        e4.setVuelo(vuelo2)
        e5.setVuelo(vuelo2)
        e6.setVuelo(vuelo2)
        e7.setVuelo(vuelo3)
        e8.setVuelo(vuelo3)
        e9.setVuelo(vuelo3)

        p1=Pasajero("Samara",4535436,18,"F")
        p2=Pasajero("Carolina",14364535,20,"F")
        p3=Pasajero("Catalina",654623562,27,"F")
        p4=Pasajero("Ana",1443524,56,"F")
        p5=Pasajero("Sofia",13424565,31,"F")
        p6=Pasajero("Valentina",348764,43,"F")
        p7=Pasajero("Felipe",534556,64,"M")
        p8=Pasajero("Sebastian",37467,46,"M")
        p9=Pasajero("Cristian",453786,36,"M")
        p10=Pasajero("Andres",237237,36,"M")
        p11=Pasajero("Julian",764521,65,"M")
        p12=Pasajero("Julio",7868754,35,"M")

        p1.setVuelo(vuelo1)
        p2.setVuelo(vuelo1)
        p3.setVuelo(vuelo1)
        p4.setVuelo(vuelo1)
        p5.setVuelo(vuelo2)
        p6.setVuelo(vuelo2)
        p7.setVuelo(vuelo2)
        p8.setVuelo(vuelo2)
        p9.setVuelo(vuelo3)
        p10.setVuelo(vuelo3)
        p11.setVuelo(vuelo3)
        p12.setVuelo(vuelo3)

    def salir(self):
        self.__class__.VSabierta = False
        self.ventanaInicio.iconify()
        self.ventanaInicio.deiconify()
        serializar(self.aeropuerto)
        self.destroy()