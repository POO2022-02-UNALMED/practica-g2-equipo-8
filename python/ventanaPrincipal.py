from tkinter import *
from tkinter import messagebox, Menu, ttk
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

from excepciones.excepcionTipo import *


class VentanaUsuario(Tk):
    VSabierta = False

    def __init__(self):
        super().__init__()
        self.title("Sistema de administracion de aeropuertos")
        self.option_add("*tearOff", False)
        self.geometry("1280x720")
        self.ventanaInicio = None
        self.resizable(0, 0)
        self.iconbitmap('./imagenes/icono.ico')
        self.widgetsActuales = []
        self.aeropuerto = Aeropuerto()
        Persona.setAeropuerto(self.aeropuerto)
        Avion.setAeropuerto(self.aeropuerto)
        Vuelo.setAeropuerto(self.aeropuerto)
        self.valoresIniciales()
        deserializar(self.aeropuerto)

        # Funciones
        def prueba():
            pass

        self.bind('<Destroy>', lambda x: serializar(self.aeropuerto))

        def descripcion():
            messagebox.showinfo("Descripcion del sistema",
                                "La principal utilidad de la aplicación de gestión del aeropuerto es la administración de los aspectos principales de esta misma, en donde se guardará la información de los pasajeros, de los vuelos, aviones, empleados y finanzas, también se implementan funcionalidades para la gestión, modificación y adición de estos elementos.")

        def info():
            messagebox.showinfo("Desarrolladores del aplicativo",
                                "Maria Camila Zapata Arrubla\nJuan Camilo Molina Roncancio\nJuan Jose Zapata Cadavid\nNombre4")

        def borrarElementos():
            for i in self.widgetsActuales:
                i.destroy()
            self.widgetsActuales = []

        # Pantallas
        def pantallaPrincipal():
            borrarElementos()
            self.lp = Label(self.fp, text="Pantalla principal", font=("Courier", 12), height=2, bg="gray80")
            self.lp.pack()
            self.ld = Label(self.fd, text="Descripción de funcionalidades", font=("Courier", 10))
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

            self.label = Label(self.ventanaOpera, text=descripcion, font=("Courier", 10))
            self.label.pack(ipadx=8, padx=8, ipady=8, pady=8, fill=X, anchor="w")
            self.widgetsActuales.extend([self.lp, self.ld, self.label])

        def pantallaReservaDeVuelo():
            borrarElementos()
            self.lp = Label(self.fp, text="Reserva de vuelo", font=("Courier", 12), height=2, bg="gray80")
            self.lp.pack()
            self.ld = Label(self.fd,
                            text="En este apartado puede realizar el agendamiento de un vuelo para un pasajero",
                            font=("Courier", 10))
            self.ld.pack()

            self.destino = Label(self.ventanaOpera, text="Ingrese el destino deseado", font=("Courier", 10))
            self.destino.grid(ipadx=8, padx=8, row=0, column=0, sticky="w")

            
            destinos = []
            for vuelo in self.aeropuerto.getVuelos():
                destino = vuelo.getDestino()
                if destino not in destinos: destinos.append(destino)

            self.listaDestinos = ttk.Combobox(self.ventanaOpera, values=destinos, textvariable=StringVar(value=""), state="readonly")
            self.listaDestinos.grid(ipadx=8, padx=8, row=0, column=1, sticky="w")

            def seleccion(Event):
                self.vuelo = Label(self.ventanaOpera, text = "Vuelos al destino seleccionado", font = ("Courier", 10))
                self.vuelo.grid(ipadx=8, padx=8, row=1, column=0, sticky="w")
                sel = self.listaDestinos.get()
                vuelos = []
                for vuelo in self.aeropuerto.getVuelos():
                    v = "Destino: "+vuelo.getDestino()+" - Costo: "+str(vuelo.getCosto())+" - Fecha: "+str(vuelo.getFecha())
                    if destino not in destinos and vuelo.getDestino() == sel: vuelos.append(v)
                self.listaVuelos = ttk.Combobox(self.ventanaOpera, values=vuelos, textvariable=StringVar(value=""), state="readonly")
                self.listaVuelos.grid(ipadx=8, padx=8, row=1, column=1, sticky="w")
                self.widgetsActuales.extend([self.vuelo, self.listaVuelos])
            
            self.listaDestinos.bind("<<ComboboxSelected>>", seleccion)
            self.widgetsActuales.extend([self.lp, self.ld, self.destino, self.listaDestinos])

        def pantallaEmpleados():
            def limpiarFrame():
                for widget in self.of.winfo_children():
                    widget.destroy()

            def nuevoEmpleado():
                limpiarFrame()

                def checkbox_clicked():
                    if self.suevalue.get():
                        self.suee.delete(0, tk.END)
                        self.suee.insert(0, Cargos.buscarCargo(self.cc.get()).getSueldoBase())
                        self.cc.config(state='disable')
                        self.suee.config(state='disabled')
                    else:
                        self.cc.config(state='normal')
                        self.suee.config(state='normal')
                        self.suee.delete(0, tk.END)

                def ingresarNuevoEmpleado():
                    fallo = False
                    try:
                        ExcepcionString.tipoString(self.ne.get())
                    except:
                        fallo = True
                        messagebox.showwarning(title="Advertencia",
                                               message=f"La entrada {self.ne.get()} contiene al menos un número, debería ser completamente texto, por favor modificar.")

                    try:
                        ExcepcionEntero.tipoInt(self.ee.get())
                    except:
                        fallo = True
                        messagebox.showwarning(title="Advertencia",
                                               message=f"La entrada {self.ee.get()} debe ser un número, por favor modificar.")

                    try:
                        ExcepcionEntero.tipoInt(self.cce.get())
                    except:
                        fallo = True
                        messagebox.showwarning(title="Advertencia",
                                               message=f"La entrada {self.cce.get()} debe ser un número, por favor modificar.")

                    try:
                        ExcepcionEntero.tipoInt(self.suee.get())
                    except:
                        fallo = True
                        messagebox.showwarning(title="Advertencia",
                                               message=f"La entrada {self.suee.get()} debe ser un número, por favor modificar.")

                    try:
                        ExcepcionVacio.valorVacio(self.ne.get())
                    except:
                        fallo = True
                        messagebox.showwarning(title="Advertencia",
                                               message=f"La entrada Nombre está vacía, por favor completarla.")

                    try:
                        ExcepcionVacio.valorVacio(self.ee.get())
                    except:
                        fallo = True
                        messagebox.showwarning(title="Advertencia",
                                               message=f"La entrada Edad está vacía, por favor completarla.")

                    try:
                        ExcepcionVacio.valorVacio(self.cce.get())
                    except:
                        fallo = True
                        messagebox.showwarning(title="Advertencia",
                                               message=f"La entrada Cedula está vacía, por favor completarla.")

                    try:
                        ExcepcionVacio.valorVacio(self.cc.get())
                    except:
                        fallo = True
                        messagebox.showwarning(title="Advertencia",
                                               message=f"La entrada Cargo está vacía, por favor completarla.")

                    try:
                        ExcepcionVacio.valorVacio(self.sc.get())
                    except:
                        fallo = True
                        messagebox.showwarning(title="Advertencia",
                                               message=f"La entrada Sexo está vacía, por favor completarla.")

                    try:
                        ExcepcionVacio.valorVacio(self.suee.get())
                    except:
                        fallo = True
                        messagebox.showwarning(title="Advertencia",
                                               message=f"La entrada Sueldo está vacía, por favor completarla.")

                    if not fallo:
                        e = Empleado(self.ne.get(), int(self.cce.get()), int(self.ee.get()), self.sc.get(),
                                     int(self.suee.get()), self.cc.get())
                        self.lb.insert(tk.END, "Cedula: " + str(e.getCedula()) + " " * (
                                    15 - len(str(e.getCedula()))) + "Nombre: " + e.getNombre())
                        self.lb.selection_set(tk.END)
                        verDatos()

                self.tl = Label(self.of, text="Ingrese los datos del nuevo empleado",
                                font=Font(family='Courier', size=100))
                self.tl.grid(row=0, column=0, padx=0, pady=5, sticky="w", columnspan=2)

                self.nl = Label(self.of, text="Nombre:", font=Font(family='Courier', size=100))
                self.nl.grid(row=1, column=0, padx=0, pady=5, sticky="w")
                self.ne = Entry(self.of, width=20)
                self.ne.grid(row=1, column=1, padx=0, pady=5, sticky="nsew")

                self.el = Label(self.of, text="Edad:", font=Font(family='Courier', size=100))
                self.el.grid(row=2, column=0, padx=0, pady=5, sticky="w")
                self.ee = Entry(self.of, width=20)
                self.ee.grid(row=2, column=1, padx=0, pady=5, sticky="nsew")

                self.ccl = Label(self.of, text="Cedula:", font=Font(family='Courier', size=100))
                self.ccl.grid(row=3, column=0, padx=0, pady=5, sticky="w")
                self.cce = Entry(self.of, width=20)
                self.cce.grid(row=3, column=1, padx=0, pady=5, sticky="nsew")

                self.cl = Label(self.of, text="Cargo:", font=Font(family='Courier', size=100))
                self.cl.grid(row=4, column=0, padx=0, pady=5, sticky="w")
                self.cc = ttk.Combobox(self.of, state="readonly", values=Cargos.getTodosLosCargos(), width=20)
                self.cc.grid(row=4, column=1, padx=0, pady=5, sticky="nsew")

                self.sl = Label(self.of, text="Sexo:", font=Font(family='Courier', size=100))
                self.sl.grid(row=5, column=0, padx=0, pady=5, sticky="w")
                self.sc = ttk.Combobox(self.of, state="readonly", values=["M", "F"], width=20)
                self.sc.grid(row=5, column=1, padx=0, pady=5, sticky="nsew")

                self.suevalue = BooleanVar(self.of)
                self.suel = Label(self.of, text="Sueldo:", font=Font(family='Courier', size=100))
                self.suel.grid(row=6, column=0, padx=0, pady=5, sticky="w")
                self.suee = Entry(self.of, width=20)
                self.suee.grid(row=6, column=1, padx=0, pady=5, sticky="nsew")
                self.suec = ttk.Checkbutton(self.of, text="Asignar valor predeterminado por el cargo",
                                            variable=self.suevalue, command=checkbox_clicked)
                self.suec.grid(row=6, column=2, padx=5, pady=5, sticky="nsew")

                self.ingresar = Button(self.of, text="Ingresar nuevo empleado", command=ingresarNuevoEmpleado)
                self.ingresar.grid(row=7, column=0, padx=0, pady=5, sticky="nsew", columnspan=3)

            def verDatos():
                limpiarFrame()
                if len(self.lb.curselection()) != 0:

                    empleado = self.aeropuerto.buscarEmpleado(int(self.lb.get(self.lb.curselection()[0]).split()[1]))

                    self.nl = Label(self.of, text=empleado.getNombre() + ", " + str(empleado.getEdad()) + " años",
                                    font=Font(family='Courier', size=60))
                    self.nl.grid(row=0, column=0, padx=5, pady=5, sticky="w")

                    self.cl = Label(self.of, text="Numero de cedula: " + str(empleado.getCedula()),
                                    font=Font(family='Courier', size=35))
                    self.cl.grid(row=1, column=0, padx=5, pady=5, sticky="w")

                    self.sl = Label(self.of, text="Sueldo actual: " + str(empleado.getSueldo()),
                                    font=Font(family='Courier', size=35))
                    self.sl.grid(row=2, column=0, padx=5, pady=5, sticky="w")

                    self.carl = Label(self.of, text="Cargo: " + str(empleado.getCargo()),
                                      font=Font(family='Courier', size=35))
                    self.carl.grid(row=3, column=0, padx=5, pady=5, sticky="w")

                    try:
                        self.v1l = Label(self.of,
                                         text="Destino del vuelo asignado: " + str(empleado.getVuelo().getDestino()),
                                         font=Font(family='Courier', size=35))
                        self.v2l = Label(self.of, text="Fecha: " + str(empleado.getVuelo().getFecha()),
                                         font=Font(family='Courier', size=35))
                        self.v2l.grid(row=5, column=1, padx=5, pady=5, sticky="w")

                        self.val = Label(self.of,
                                         text="Avión del vuelo: " + str(empleado.getVuelo().getAvion().getModelo()),
                                         font=Font(family='Courier', size=35))
                        self.val.grid(row=6, column=0, padx=5, pady=5, sticky="w")

                        self.vpal = Label(self.of,
                                          text="Peso actual: " + str(empleado.getVuelo().getPesoActual()) + "/" + str(
                                              empleado.getVuelo().getAvion().getPesoMaximo()),
                                          font=Font(family='Courier', size=35))
                        self.vpal.grid(row=7, column=0, padx=5, pady=5, sticky="w")
                    except:
                        self.v1l = Label(self.of, text="Este empleado aún no tiene un vuelo asignado",
                                         font=Font(family='Courier', size=35))
                    self.v1l.grid(row=5, column=0, padx=5, pady=5, sticky="w")

            def cambiarSaldo():
                limpiarFrame()

                def cambioValor(op):
                    if op == "+":
                        empleado.setSueldo(empleado.getSueldo() + 100)
                    elif op == "-":
                        empleado.setSueldo(empleado.getSueldo() - 100)
                    self.sueldo["text"] = empleado.getSueldo()

                if len(self.lb.curselection()) != 0:
                    empleado = self.aeropuerto.buscarEmpleado(int(self.lb.get(self.lb.curselection()[0]).split()[1]))

                    self.sl = Label(self.of,
                                    text="El sueldo de " + empleado.getNombre() + ", que es " + empleado.getCargo() + " actualmente es de: ")
                    self.sl.grid(row=0, column=0, sticky="nsew", columnspan=3)

                    self.sueldo = Label(self.of, text=empleado.getSueldo(), font=Font(family="Courier", size=80))
                    self.sueldo.grid(row=1, column=1, sticky="w", columnspan=2)

                    self.restab = Button(self.of, text="-", command=lambda: cambioValor("-"))
                    self.restab.grid(row=1, column=0, sticky="nsew")
                    self.sumab = Button(self.of, text="+", command=lambda: cambioValor("+"))
                    self.sumab.grid(row=1, column=2, sticky="nsew")

            def despedir():
                limpiarFrame()
                if len(self.lb.curselection()) != 0:
                    curr = self.lb.curselection()[0]
                    empleado = self.aeropuerto.buscarEmpleado(int(self.lb.get(curr).split()[1]))
                    desp = messagebox.askyesno(
                        message="¿Está seguro que desea despedir a " + empleado.getNombre() + "?", title="Despedir")
                    if desp:
                        self.aeropuerto.despedirEmpleado(empleado)
                        self.lb.delete(curr)

            def cambiarCargo():
                limpiarFrame()

                def cambio():
                    if len(self.cargos.curselection()) != 0:
                        empleado.setCargo(self.cargos.get(self.cargos.curselection()[0]))
                        self.lb.selection_set(curr)
                        cambiarCargo()

                if len(self.lb.curselection()) != 0:
                    curr = self.lb.curselection()[0]
                    empleado = self.aeropuerto.buscarEmpleado(int(self.lb.get(self.lb.curselection()[0]).split()[1]))
                    self.carg = Label(self.of, text="El cargo actual de " + empleado.getNombre() + " es " + str(
                        empleado.getCargo()))
                    self.carg.grid(row=0, column=0, sticky="nsew")

                    self.cargos = Listbox(self.of, font='Courier', width=40, height=4)
                    self.cargos.grid(row=1, column=0, sticky="n")

                    for i in Cargos.getTodosLosCargos():
                        if i != empleado.getCargo():
                            self.cargos.insert(tk.END, i)

                    self.cambiar = Button(self.of, text="Cambiar cargo", command=cambio)
                    self.cambiar.grid(row=1, column=1, rowspan=2, padx=15)

            def cambiarVuelo():
                limpiarFrame()
                if len(self.lb.curselection()) != 0:
                    curr = self.lb.curselection()[0]
                    empleado = self.aeropuerto.buscarEmpleado(int(self.lb.get(self.lb.curselection()[0]).split()[1]))
                    try:
                        self.carg = Label(self.of, text="El vuelo actual de " + empleado.getNombre() + " es:")
                        self.vuelo = Label(self.of,
                                           text="Sala " + empleado.getVuelo().getSalaEmbarque() + " - Destino: " + empleado.getVuelo().getDestino() + " - Avión: " + empleado.getVuelo().getAvion().getModelo())
                        self.vuelo.grid(row=1, column=0)
                    except:
                        self.carg = Label(self.of, text="Este empleado aún no tiene un vuelo asignado")

                    self.carg.grid(row=0, column=0, sticky="nsew")

                    self.scroll2 = Scrollbar(self.of, orient='vertical')

                    self.vuelos = Listbox(self.of, yscrollcommand=self.scroll2.set, font='Courier', width=40, height=20)
                    self.vuelos.grid(row=2, column=0, columnspan=4, sticky="snew", padx=5, pady=5)

                    self.scroll2.configure(command=self.vuelos.yview)
                    self.scroll2.grid(column=4, row=2, sticky='NS')

                    for i in self.aeropuerto.getVuelos():
                        if i != empleado.getVuelo():
                            self.vuelos.insert(tk.END,
                                               i.getSalaEmbarque() + " - " + i.getAvion().getModelo() + " - " + i.getDestino())

                    def cambiar():
                        if self.vuelos.curselection() != 0:
                            lista = self.vuelos.get(self.vuelos.curselection()[0]).split(" - ")
                            vuelo = self.aeropuerto.buscarVuelo(lista[0], lista[1], lista[2])
                            empleado.setVuelo(vuelo)
                            self.lb.selection_set(curr)
                            cambiarVuelo()

                    self.cambiar = Button(self.of, text="Cambiar vuelo", command=cambiar)
                    self.cambiar.grid(column=0, row=3, columnspan=4, sticky="nsew")

            borrarElementos()
            self.lp = Label(self.fp, text="Gestor de empleados", font=("Courier", 12), height=2, bg="gray80")
            self.lp.pack()
            self.ld = Label(self.fd,
                            text="En este apartado puede visualizar los datos de los empleados y gestionar los mismos",
                            font=("Courier", 10))
            self.ld.pack()

            self.scroll = Scrollbar(self.ventanaOpera, orient='vertical')

            self.lb = Listbox(self.ventanaOpera, yscrollcommand=self.scroll.set, font='Courier', width=40, height=20)
            self.lb.grid(row=0, column=0, columnspan=4, sticky="snew", padx=5, pady=5)

            self.scroll.configure(command=self.lb.yview)
            self.scroll.grid(column=4, row=0, sticky='NS')

            for i in self.aeropuerto.getEmpleados():
                self.lb.insert(tk.END, "Cedula: " + str(i.getCedula()) + " " * (
                            15 - len(str(i.getCedula()))) + "Nombre: " + i.getNombre())

            self.of = Frame(self.ventanaOpera)
            self.of.grid(row=0, column=5, rowspan=4, sticky='nsew', padx=30, pady=30)

            self.datosButton = Button(self.ventanaOpera, text="Ver datos", command=verDatos)
            self.datosButton.grid(row=1, columnspan=4, padx=5, pady=5, sticky="nsew")
            self.nuevoEmpleadoButton = Button(self.ventanaOpera, text="Crear nuevo empleado", command=nuevoEmpleado)
            self.nuevoEmpleadoButton.grid(row=2, column=0, padx=5, pady=5, sticky="nsew", columnspan=2)
            self.cambiarSaldo = Button(self.ventanaOpera, text="Cambiar saldo", command=cambiarSaldo)
            self.cambiarSaldo.grid(row=2, column=2, padx=5, pady=5, sticky="nsew", columnspan=2)
            self.cambiarCargo = Button(self.ventanaOpera, text="Cambiar cargo", command=cambiarCargo)
            self.cambiarCargo.grid(row=3, column=2, padx=5, pady=5, sticky="nsew", columnspan=2)
            self.cambiarVuelo = Button(self.ventanaOpera, text="Cambiar vuelo", command=cambiarVuelo)
            self.cambiarVuelo.grid(row=3, column=0, padx=5, pady=5, sticky="nsew", columnspan=2)
            self.despedir = Button(self.ventanaOpera, text="Despedir", command=despedir)
            self.despedir.grid(row=4, column=0, padx=5, pady=5, sticky="nsew", columnspan=4)

            self.widgetsActuales.extend(
                [self.lp, self.despedir, self.cambiarVuelo, self.cambiarCargo, self.datosButton, self.ld, self.lb,
                 self.scroll, self.of, self.cambiarSaldo, self.nuevoEmpleadoButton])

        def pantallaCambiarAsiento():
            borrarElementos()
            self.lp = Label(self.fp,text= "Cambiar silla", font = ("Courier", 12),height=2, bg="gray80")
            self.lp.pack()
            self.ld = Label(self.fd, text = "En este apartado puede realizar el cambio de silla a un determinado pasajero", font = ("Courier", 10))
            self.ld.pack()            

            
            self.widgetsActuales.extend([self.lp,self.ld])

        def pantallaCancelarVuelo():
            borrarElementos()
            self.lp = Label(self.fp, text="Cancelar vuelo", font=("Courier", 12), height=2, bg="gray80")
            self.lp.pack()
            self.ld = Label(self.fd, text="En este apartado puede cancelar un determinado vuelo", font=("Courier", 10))
            self.ld.pack()

            self.scroll = Scrollbar(self.ventanaOpera, orient='vertical')

            self.lb = Listbox(self.ventanaOpera, yscrollcommand=self.scroll.set, font='Courier', width=20, height=20)
            self.lb.grid(row=0, column=0, columnspan=4, sticky="snew", padx=5, pady=5)

            self.scroll.configure(command=self.lb.yview)
            self.scroll.grid(column=4, row=0, sticky='NS')

            for i in self.aeropuerto.getVuelos():
                self.lb.insert(tk.END, "Destino: " + str(i.getDestino()))

            self.of = Frame(self.ventanaOpera)
            self.of.grid(row=0, column=5, rowspan=4, sticky='nsew', padx=30, pady=30)

            def aceptar():
                valorUsuario = FieldFrame.getValorEntry()
                tipo = "string"
                fallo = False
                if valorUsuario == "":
                    try:
                        raise ExcepcionVacio(valorUsuario)
                    except ExcepcionVacio as e:
                        fallo = True
                        messagebox.showwarning(title="Aviso", message=e)
                if tipo == "string" and valorUsuario != "":
                    if valorUsuario.isdigit() == True:
                        try:
                            raise ExcepcionString(valorUsuario)
                        except ExcepcionString as e:
                            fallo = True
                            messagebox.showwarning(title="Aviso", message=e)
                if not fallo:
                    for vuelo in self.aeropuerto.getVuelos():
                        if vuelo.getDestino() == valorUsuario:
                            self.getVuelos.remove(vuelo)
                        
                    print(self.aeropuerto.getVuelos())
                    pantallaCancelarVuelo()

            criteriosEliminarAvion = ["Destino"]
            # self.ventanaOpera.pack_forget()
            self.operaciones = FieldFrame(self.of, "Datos", criteriosEliminarAvion, "Valor", None, None)
            self.operaciones.crearBotones(aceptar, prueba)

            self.widgetsActuales.extend([self.lp, self.ld, self.operaciones, self.lb, self.scroll, self.of])

        def pantallaEliminarAvion():
            borrarElementos()
            self.lp = Label(self.fp, text="Eliminar avión", font=("Courier", 12), height=2, bg="gray80")
            self.lp.pack()
            self.ld = Label(self.fd, text="En este apartado puede eliminar un avión con su respectivo ID",
                            font=("Courier", 10))
            self.ld.pack()

            self.scroll = Scrollbar(self.ventanaOpera, orient='vertical')

            self.lb = Listbox(self.ventanaOpera, yscrollcommand=self.scroll.set, font='Courier', width=20, height=20)
            self.lb.grid(row=0, column=0, columnspan=4, sticky="snew", padx=5, pady=5)

            self.scroll.configure(command=self.lb.yview)
            self.scroll.grid(column=4, row=0, sticky='NS')

            for i in self.aeropuerto.getAviones():
                self.lb.insert(tk.END, "Modelo: " + str(i.getModelo()))

            self.of = Frame(self.ventanaOpera)
            self.of.grid(row=0, column=5, rowspan=4, sticky='nsew', padx=30, pady=30)

            self.widgetsActuales.extend([self.lp, self.ld, self.lb, self.scroll, self.of])

        def pantallaComprarAvion():
            borrarElementos()

            def limpiarFrame():
                for widget in self.of.winfo_children():
                    widget.destroy()

            self.lp = Label(self.fp, text="Comprar avión", font=("Courier", 12), height=2, bg="gray80")
            self.lp.pack()
            self.ld = Label(self.fd,
                            text="En este apartado puede realizar la compra de un avión y asignarle un determinado vuelo",
                            font=("Courier", 10))
            self.ld.pack()

            self.nuevoAvionButton = Button(self.ventanaOpera, text="Comprar avión", command=prueba)
            self.nuevoAvionButton.grid(row=1, column=2, padx=5, pady=5)

            self.widgetsActuales.extend([self.lp, self.ld, self.nuevoAvionButton])

        def pantallaNomina():
            def aceptarfun():
                empleadosi = []
                total = 0
                for i in self.checkemp.curselection():
                    empleadosi.append(empleados[i])
                    total += empleados[i].getSueldo()

                opcion = messagebox.askokcancel('Alerta de pago', f'Está a punto de pagar ${total}\nCofirmar?')
                if opcion:
                    if total > self.aeropuerto.getDinero():
                        messagebox.showerror('Error',
                                             f'Fondos insuficientes\nsolo tienes {self.aeropuerto.getDinero()}')
                    else:
                        for e in empleadosi:
                            self.aeropuerto.transaccion(f'nomina de {e.getNombre()}', (-1) * e.getSueldo())
                            pantallaFinanzas()

            empleados = self.aeropuerto.getEmpleados()
            empleadosnames = list(map(lambda x: '$ ' + str(x.getSueldo()) + ' | ' + x.getNombre(), empleados))

            self.label1 = Label(self.ventanaOpera, text="Seleccione los empleados\na los que les va a pagar",
                                font=("Courier", 10))

            self.framelist = Frame(self.ventanaOpera)
            self.checkemp = Listbox(self.framelist, selectmode='multiple')
            self.scroll = Scrollbar(self.framelist, orient=VERTICAL)
            self.checkemp.config(yscrollcommand=self.scroll.set)
            self.scroll.config(command=self.checkemp.yview)
            self.checkemp.insert(0, *empleadosnames)

            self.aceptar = Button(self.ventanaOpera, text='Aceptar', command=aceptarfun)

            self.label1.grid(padx=8, pady=8, row=2, column=0)
            self.scroll.pack(side=RIGHT, fill=Y)
            self.checkemp.pack(fill=X)
            self.framelist.grid(ipadx=80, padx=80, pady=30, row=3, column=0, rowspan=5)
            self.aceptar.grid(ipadx=80, padx=80, pady=30, row=8, column=0)

            self.widgetsActuales.extend([self.checkemp, self.aceptar, self.label1, self.scroll])

        def pantallaTransacciones():
            def aceptarfun():
                valor = self.entrada_valor.get()
                concepto = self.entrada_concepto.get()
                combo = self.combo.get()
                if combo == 'Retiro':
                    opcion = messagebox.askokcancel('Alerta de pago', f'Está a punto de pagar ${valor}\nCofirmar?')
                else:
                    opcion = messagebox.askokcancel('Alerta de pago', f'Está a punto de ingresar ${valor}\nCofirmar?')
                if concepto == '':
                    concepto = 'Sin concepto'

                if opcion:
                    try:
                        valor = int(valor)
                        if valor < 0:
                            messagebox.showerror('Error', 'No puedes introducir un numero negativo')
                        elif valor > self.aeropuerto.getDinero():
                            messagebox.showerror('Error',
                                                 f'Fondos insuficientes\nsolo tienes {self.aeropuerto.getDinero()}')
                        elif combo == 'Ingreso':
                            self.aeropuerto.transaccion(concepto, valor)
                            pantallaFinanzas()
                        elif combo == 'Retiro':
                            self.aeropuerto.transaccion(concepto, valor * (-1))
                            pantallaFinanzas()
                        else:
                            messagebox.showerror('Error', 'Debes seleccionar Ingreso o Retiro')
                    except ValueError:
                        messagebox.showerror('Error', 'Debes introducir solamente numeros en el monto')

            textstr = ""
            textconcept = ""
            textvalue = ""
            for i in range(len(self.aeropuerto.getTransaccionesKeys())):
                textstr += str(self.aeropuerto.getTransaccionesValues()[i]) + \
                           ' ' * (10 - len(str(self.aeropuerto.getTransaccionesValues()[i]))) + '|  ' + \
                           self.aeropuerto.getTransaccionesKeys()[i] + '\n'

            self.frametrans = Frame(self.ventanaOpera, width='25', height='25')
            self.scrolltrans = Scrollbar(self.frametrans)
            self.text = Text(self.frametrans, wrap=NONE, width='50', yscrollcommand=self.scrolltrans.set)
            self.text.bind("<Key>", lambda e: "break")
            self.text.delete(1.0, END)
            self.text.insert(END, textstr)
            self.newtrans = Label(self.ventanaOpera, text='Nueva transaccion', bg='gray80')
            self.combo = ttk.Combobox(self.ventanaOpera, state='readonly', values=['Ingreso', 'Retiro'])
            self.le = Label(self.ventanaOpera, text='Ingresa el concepto de la transaccion:')
            self.entrada_concepto = Entry(self.ventanaOpera)
            self.le2 = Label(self.ventanaOpera, text='Ingresa el monto de la transaccion:')
            self.entrada_valor = Entry(self.ventanaOpera)
            self.aceptar = Button(self.ventanaOpera, text='Aceptar', command=aceptarfun)

            self.scrolltrans.config(command=self.text.yview)

            self.frametrans.grid(row=2, column=1, rowspan=7, columnspan=1)
            self.newtrans.grid(row=2, column=2, columnspan=2)
            self.combo.grid(row=3, column=2)
            self.le.grid(row=4, column=2)
            self.entrada_concepto.grid(row=5, column=2)
            self.le2.grid(row=6, column=2)
            self.entrada_valor.grid(row=7, column=2)
            self.aceptar.grid(row=8, column=2)
            self.scrolltrans.pack(side=RIGHT, fill=Y)
            self.text.pack(side=LEFT)
            self.widgetsActuales.extend([self.frametrans, self.newtrans, self.combo, self.le, self.entrada_concepto,
                                         self.le2, self.entrada_valor, self.aceptar])

        def pantallaFinanzas():
            borrarElementos()

            self.lp = Label(self.fp, text="Gestor de Finanzas", font=("Courier", 12), height=2, bg="gray80")
            self.ld = Label(self.fd,
                            text="En este apartado puede realizar el pago de nomina a los empleados, registrar los "
                                 "cambios con el dinero del aeropuerto, y ver el historial de transacciones",
                            font=("Courier", 10))
            self.labeldinero = Label(self.ventanaOpera, text=f'Dinero del Aeropuerto: {self.aeropuerto.getDinero()}')
            self.bnomina = Button(self.ventanaOpera, text='Nomina', command=pantallaNomina)
            self.btrans = Button(self.ventanaOpera, text='Transacciones', command=pantallaTransacciones)

            # self.checkemp = ChecklistBox(self.ventanaOpera)
            self.lp.pack()
            self.ld.pack()
            self.labeldinero.grid(ipadx=0, padx=0, pady=0, row=0, column=0, columnspan=3)
            self.bnomina.grid(ipadx=80, padx=80, pady=30, row=1, column=0)
            self.btrans.grid(ipadx=80, padx=80, pady=30, row=1, column=1, columnspan=1)

            self.widgetsActuales.extend([self.lp, self.ld, self.bnomina, self.btrans])

        # Menus
        self._barraMenu = Menu(self)

        archivo = Menu(self._barraMenu)
        self._barraMenu.add_cascade(label="Archivo", menu=archivo)
        archivo.add_command(label="Aplicacion", command=descripcion)
        archivo.add_command(label="Salir y guardar", command=self.salir)

        self.procesosYConsultas = Menu(self._barraMenu)

        self._barraMenu.add_cascade(label="Procesos y consultas", menu = self.procesosYConsultas)
        self.procesosYConsultas.add_command(label = "Reserva de vuelo", command = pantallaReservaDeVuelo)
        self.procesosYConsultas.add_command(label = "Funcionalidad 2", command = prueba)
        self.procesosYConsultas.add_command(label = "Gestion de empleados", command = pantallaEmpleados)
        self.procesosYConsultas.add_command(label = "Funcionalidad 4", command = pantallaFinanzas)

        self.menuModificaciones = Menu(self.procesosYConsultas)
        self.procesosYConsultas.add_cascade(menu=self.menuModificaciones, label="Administración de vuelos y aviones")
        self.menuModificaciones.add_command(label="Cambiar asiento", command=pantallaCambiarAsiento)
        self.menuModificaciones.add_command(label="Cancelar vuelo", command=pantallaCancelarVuelo)
        self.menuModificaciones.add_command(label="Eliminar avión", command=pantallaEliminarAvion)
        self.menuModificaciones.add_command(label="Comprar avión", command=pantallaComprarAvion)

        ayuda = Menu(self._barraMenu)
        self._barraMenu.add_cascade(label="Ayuda", menu=ayuda)
        ayuda.add_command(label="Acerca de", command=info)

        self.config(menu=self._barraMenu)

        # Frames
        self.frame = Frame(self, relief="ridge", bd=2, bg="black")
        self.frame.pack(padx=15, pady=15, expand=True, fill=BOTH)
        self.fp = Frame(self.frame, bg="gray80")
        self.fp.pack(ipadx=6, padx=2, ipady=2, pady=2, fill=X)
        self.fd = Frame(self.frame)
        self.fd.pack(ipadx=2, padx=2, ipady=2, pady=2, fill=X)
        self.ventanaOpera = Frame(self.frame, bd=2)
        self.ventanaOpera.pack(padx=5, pady=5, fill=BOTH, expand=True)

        # Llamado a pantalla principal
        pantallaPrincipal()

    def valoresIniciales(self):

        self.aeropuerto.setDinero(10000000)
        a1 = Avion("XYZ", 100, 50000)
        vuelo1 = Vuelo(a1, datetime(2022, 11, 30, 10, 0, 0), "Cancun", 1500, "A1")
        vuelo2 = Vuelo(Avion("YY3X", 150, 75000), datetime(2022, 12, 5, 10, 0, 0), "Madrid", 5500, "A2")
        vuelo3 = Vuelo(Avion("XCF", 75, 25000), datetime(2022, 12, 5, 10, 0, 0), "Paris", 5500, "A2")
        e1 = Empleado("Juan", 12345, 30, "M", 2300, Cargos.PILOTO.getCargo())
        e2 = Empleado("Pedro", 543657, 35, "M", 1800, Cargos.COPILOTO.getCargo())
        e3 = Empleado("Sara", 4235246, 66, "F", 1500, Cargos.CONTROL_DE_PISTA.getCargo())
        e4 = Empleado("Carla", 67436653, 35, "F", 1500, Cargos.AZAFATA.getCargo())
        e5 = Empleado("Federico", 9787845, 40, "M", 1800, Cargos.AZAFATA.getCargo())
        e6 = Empleado("Andrea", 2425, 38, "F", 1800, Cargos.PILOTO.getCargo())
        e7 = Empleado("Camilo", 27354235, 60, "M", 1800, Cargos.PILOTO.getCargo())
        e8 = Empleado("Maria", 14136, 55, "F", 1800, Cargos.CONTROL_DE_PISTA.getCargo())
        e9 = Empleado("Fernando", 64378, 23, "M", 1800, Cargos.AZAFATA.getCargo())
        Empleado("Paco", 451675, 48, "M", 1800, Cargos.CONTROL_DE_PISTA.getCargo())
        Empleado("Camila", 2565324, 19, "F", 1800, Cargos.COPILOTO.getCargo())
        Empleado("Luisa", 47594, 34, "F", 1800, Cargos.PILOTO.getCargo())
        Empleado("Jose", 11055, 45, "M", 1800, Cargos.AZAFATA.getCargo())

        e1.setVuelo(vuelo1)
        e2.setVuelo(vuelo1)
        e3.setVuelo(vuelo1)
        e4.setVuelo(vuelo2)
        e5.setVuelo(vuelo2)
        e6.setVuelo(vuelo2)
        e7.setVuelo(vuelo3)
        e8.setVuelo(vuelo3)
        e9.setVuelo(vuelo3)

        p1 = Pasajero("Samara", 4535436, 18, "F")
        p2 = Pasajero("Carolina", 14364535, 20, "F")
        p3 = Pasajero("Catalina", 654623562, 27, "F")
        p4 = Pasajero("Ana", 1443524, 56, "F")
        p5 = Pasajero("Sofia", 13424565, 31, "F")
        p6 = Pasajero("Valentina", 348764, 43, "F")
        p7 = Pasajero("Felipe", 534556, 64, "M")
        p8 = Pasajero("Sebastian", 37467, 46, "M")
        p9 = Pasajero("Cristian", 453786, 36, "M")
        p10 = Pasajero("Andres", 237237, 36, "M")
        p11 = Pasajero("Julian", 764521, 65, "M")
        p12 = Pasajero("Julio", 7868754, 35, "M")

        for i in self.aeropuerto.getPasajeros():
            i.addEquipaje(Equipaje(5))

        vuelo1.agregarPasajero(p1, 1)
        vuelo1.agregarPasajero(p2, 2)
        vuelo1.agregarPasajero(p3, 3)
        vuelo1.agregarPasajero(p4, 4)
        vuelo2.agregarPasajero(p5, 1)
        vuelo2.agregarPasajero(p6, 2)
        vuelo2.agregarPasajero(p7, 3)
        vuelo2.agregarPasajero(p8, 4)
        vuelo3.agregarPasajero(p9, 1)
        vuelo3.agregarPasajero(p10, 2)
        vuelo3.agregarPasajero(p11, 3)
        vuelo3.agregarPasajero(p12, 4)

    def salir(self):
        self.__class__.VSabierta = False
        self.ventanaInicio.iconify()
        self.ventanaInicio.deiconify()
        serializar(self.aeropuerto)
        self.destroy()
