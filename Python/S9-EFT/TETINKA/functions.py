import numpy as np
import re

class SistemaReservas:
    def __init__(self):
        # Iniciamos el arreglo de asientos, datos de pasajeros, contador de asientos y ingresos totales
        self.asientos = np.full(48, False, dtype=bool)
        self.datos_pasajeros = [None] * 48

        self.contador_asientos_ocupados = 0
        self.ingresos_totales = 0

    # Funcion para imprimir un asiento individual
    def imprimir_asiento(self, i):
        if self.asientos[i]:  # Marcamos los asientos ocupados con una X
            print(" X", end="  ")
        else:  # Mostramos los asientos libres
            print(f"{i + 1:2}", end="  ")

    # Funcion para imprimir los asientos
    def imprimir_fila(self, inicio, fin, espacio_pasillo=True):
        print("|", end=" ")
        # Imprimir los asientos de inicio a fin
        for i in range(inicio, inicio + 3):
            self.imprimir_asiento(i)
        if espacio_pasillo:
            print("   ", end=" ")

        for i in range(inicio + 3, fin):
            self.imprimir_asiento(i)
        print("|")

    # Funcion para mostrar los asientos disponibles y ocupados
    def mostrar_asientos(self):
        # Calculamos la cantidad de asientos disponibles y en uso
        asientos_disponibles = np.sum(self.asientos == False)
        asientos_en_uso = np.sum(self.asientos == True)

        print(f"\nAsientos disponibles: {asientos_disponibles} | Asientos en uso (X = ocupado): {asientos_en_uso}\n")

        # Imprimir asientos del 1 al 30 con espacio entre filas
        for i in range(0, 30, 6):
            self.imprimir_fila(i, i + 6)
            if i < 24:
                print("|                             |")

        print("|============    =============|")

        # Imprimir asientos del 31 al 48 sin espacio en blanco entre filas
        self.imprimir_fila(30, 36)
        print("|", end=" ")
        for i in range(36, 39):
            self.imprimir_asiento(i)
        print(" ", end="   ")
        for i in range(39, 42):
            self.imprimir_asiento(i)
        print("|")
        self.imprimir_fila(42, 48)

    def formatear_rut(self, rut):
        # Eliminar puntos y guion del rut ingresado por el usuario
        rut = rut.replace(".", "").replace("-", "")

        # Validamos que el rut ingresado tenga al menos 2 caracteres (cuerpo + dígito verificador)
        if len(rut) < 2:
            raise ValueError("[ERROR]: El rut debe tener al menos 2 caracteres.")

        # Separar el cuerpo y el dígito verificador
        cuerpo = rut[:-1]
        verificador = rut[-1].upper()  # Convertimos las mayúsculas si es una letra

        # Validamos si el formato del rut ingresado es correcto
        if not re.match(r'^\d{1,8}[K\d]$', rut):  # El cuerpo debe tener entre 1 y 8 dígitos y el verificador puede ser un dígito o una K
            raise ValueError("[ERROR]: El rut es inválido. Debes ingresarlo usando el formato 12345678-K o 12345678-9.")

        # Añadimos el puntos y guion al cuerpo del rut (ej: 12.345.678-9)
        cuerpo_formateado = f"{int(cuerpo):,}".replace(",", ".")
        return f"{cuerpo_formateado}-{verificador}"

    # Funcion para calcular el precio segun el tipo de asiento
    def calcular_precio(self, num_asiento):
        if num_asiento in [1, 7, 13, 19, 25, 31, 37, 43, 3, 9, 15, 21, 27, 33, 39, 45, 4, 10, 16, 22, 28, 34, 40, 46, 6, 12, 18, 24, 30, 36, 42, 48]:  # Asientos premium
            return 250000
        else:  # Asientos público en general
            return 170300

    # Funcion para verificar si el usuario quiere volver al menu principal
    def verificar_menu_principal(self, num_asiento):
        if num_asiento == 0:
            print("\nVolviendo al menú principal...")
            return True
        return False

    # Funcion para comprar un asiento
    def comprar_asiento(self):
        self.mostrar_asientos()
        while True:
            try:
                num_asiento = int(input("Selecciona el número de asiento que deseas comprar (1-48): "))
                if num_asiento < 1 or num_asiento > 48:
                    print("\n[ERROR]: El numero de asiento no es valido. Por favor, selecciona un asiento entre 1 a 48.\n")
                    continue
                elif self.asientos[num_asiento - 1]:
                    print("\n[ERROR]: El asiento ya esta ocupado. Por favor, selecciona otro.\n")
                    continue

                # Solicitamos el nombre del pasajero
                nombre = input("Por favor, ingresa el nombre del pasajero: ")

                # Solicitamos y validamos el rut del pasajero
                while True:
                    try:
                        rut = input("Ingresa el rut del pasajero (ej: 123456789): ")
                        rut_formateado = self.formatear_rut(rut)
                        break
                    except ValueError as e:
                        print(e)

                # Solicitamos el teléfono del pasajero
                telefono = input("Ingresa el teléfono del pasajero (ej: +56912345678): ")

                # Solicitamos el banco del pasajero
                banco = input("Por ultimo, ingresa el banco del pasajero: ")

                # Calculamos el precio y aplicar descuento si pertenece a Banco Duoc
                precio = self.calcular_precio(num_asiento)

                if banco.lower() == "bancoduoc":
                    precio *= 0.88  # Se aplica el 12% de descuento
                    print("¡Felicidades! Se aplicó un 12% de descuento en tu pasaje.")

                self.asientos[num_asiento - 1] = True  # Marcar el asiento como ocupado

                # Almacenamos los datos del pasajero incluyendo el precio final
                self.datos_pasajeros[num_asiento - 1] = {
                    'nombre': nombre,
                    'rut': rut_formateado,
                    'telefono': telefono,
                    'banco': banco,
                    'precio': precio
                }

                # Actualizamos el contador y el acumulador de ingresos
                self.contador_asientos_ocupados += 1
                self.ingresos_totales += precio

                print(f"Seleccionaste el asiento N°{num_asiento} con un costo total de" + f" ${precio:,.0f}".replace(',', '.') + " pesos.")
                return  # Retornar los valores actualizados
            except ValueError:
                print("\n[ERROR]: Valor incorrecto. Por favor, ingresa un valor correcto.\n")

    # Funcion para anular un asiento comprado
    def anular_asiento(self):
        self.mostrar_asientos()
        while True:
            try:
                num_asiento = int(input("Ingresa el número del asiento que deseas anular (1 a 48) o 0 para salir: "))

                # Verificamos si el usuario quiere volver al menu principal
                if self.verificar_menu_principal(num_asiento):
                    return

                if num_asiento < 1 or num_asiento > 48:  # Verifica si el número de asiento es válido
                    print("[ERROR]: Número de asiento inválido. Por favor, ingresa un número entre 1 a 48.")
                elif not self.asientos[num_asiento - 1]:  # Verifica si el asiento está ocupado
                    print(f"[ERROR]: El asiento N°{num_asiento} está libre. Por favor, ingresa otro número de asiento.")
                else:
                    # Calculamos el precio del asiento anulado
                    precio = self.datos_pasajeros[num_asiento - 1]['precio']
                    nombre_pasajero = self.datos_pasajeros[num_asiento - 1]['nombre']
                    self.asientos[num_asiento - 1] = False  # Marcar el asiento como libre
                    self.datos_pasajeros[num_asiento - 1] = None  # Limpiar los datos del pasajero
                    self.contador_asientos_ocupados -= 1  # Disminuir contador de asientos ocupados
                    self.ingresos_totales -= precio  # Decrementar el acumulador de ingresos

                    print("\nAnulando asiento...\n")

                    print("¡Asiento anulado con éxito!\n")
                    print(f" • Asiento N°{num_asiento} ha sido anulado.")
                    print(f" • Comprado por: {nombre_pasajero}")
                    print(f" • Costo total de ${precio:,.0f}".replace(',', '.'))
                return  # Retornar los valores actualizados
            except ValueError:
                print("\n[ERROR]: Valor incorrecto. Por favor, ingresa un valor correcto.\n")

    # Funcion para modificar los datos de un pasajero que compro un asiento
    def modificar_datos_pasajero(self):
        self.mostrar_asientos()
        while True:
            try:
                num_asiento = int(input("Ingresa el número del asiento que deseas modificar (0 para salir:): "))

                # Verificamos si el usuario quiere volver al menu principal
                if self.verificar_menu_principal(num_asiento):
                    return

                if 1 <= num_asiento <= len(self.datos_pasajeros):  # Verificamos si el número de asiento es válido
                    pasajero = self.datos_pasajeros[num_asiento - 1]

                    if pasajero is None:
                        print("[ERROR]: Este asiento está desocupado. Por favor, selecciona uno que este en uso.")
                        continue

                    # Si el asiento está ocupado, pedimos el rut del pasajero para verificarlo
                    rut = input("Ingresa el rut del pasajero para verificar su asiento: ")
                    rut_formateado = self.formatear_rut(rut)  # Formatear el RUT ingresado
                    if pasajero['rut'] != rut_formateado:
                        print("[ERROR]: El rut ingresado no coincide con el pasajero de este asiento.")
                        continue
                    break
                else:
                    print("[ERROR]: El numero del asiento no es valido. Por favor, selecciona un numero valido de 1 a 48.")
            except ValueError:
                print("\n[ERROR]: Valor incorrecto. Por favor, ingresa un valor correcto.\n")

        while True:
            # Mostramos los datos actuales del pasajero
            print("\nDatos actuales del Pasajero\n")
            print(f" • Asiento N°{num_asiento}")
            print(f" • Nombre: {pasajero['nombre']}")
            print(f" • RUT: {pasajero['rut']}")
            print(f" • Teléfono: {pasajero['telefono']}")
            print(f" • Banco: {pasajero['banco']}")

            # Mostramos el submenu de modificación de datos
            print("\nSubmenú Modificación de Datos\n")
            print(" 1. Modificar nombre")
            print(" 2. Modificar teléfono")
            print(" 3. Volver al menu principal\n")
            opcion = input("Selecciona una opción: ")

            if opcion == "1":
                nuevo_nombre = input("Ingresa el nuevo nombre: ")
                pasajero['nombre'] = nuevo_nombre
                print("Nombre modificado correctamente.")
            elif opcion == "2":
                nuevo_telefono = input("Ingresa el nuevo teléfono: ")
                pasajero['telefono'] = nuevo_telefono
                print("Teléfono modificado correctamente.")
            elif opcion == "3":
                print("\nVolviendo al menú principal...")
                break
            else:
                print("Opción no válida.")

        print(f"\nLos datos del pasajero en el asiento N°{num_asiento} han sido actualizados correctamente.")
        return  # Terminar la modificación y regresar al menú principal

    # Funcion para consultar la informacion de un asiento
    def consultar_informacion_asiento(self):
        self.mostrar_asientos()
        while True:
            try:
                num_asiento = int(input("Ingresa el número del asiento que deseas consultar (1-48) o 0 para cancelar: "))

                # Verificamos si el usuario quiere volver al menu principal
                if self.verificar_menu_principal(num_asiento):
                    return

                if num_asiento < 1 or num_asiento > 48:  # Verificamos si el número de asiento es válido
                    print("[ERROR]: Número de asiento inválido. Por favor, ingresa un número entre 1 a 48.")
                elif not self.asientos[num_asiento - 1]:  # Verificamos si el asiento está ocupado
                    print(f"[ERROR]: El asiento N°{num_asiento} está libre. Por favor, ingresa otro número de asiento.")
                else:
                    pasajero = self.datos_pasajeros[num_asiento - 1]
                    print("\nInformación de Asiento\n")
                    print(f" • Asiento N°{num_asiento}")
                    print(f" • Nombre: {pasajero['nombre']}")
                    print(f" • RUT: {pasajero['rut']}")
                    print(f" • Teléfono: {pasajero['telefono']}")
                    print(f" • Banco: {pasajero['banco']}")
                    print(f" • Precio: ${pasajero['precio']:,.0f}".replace(',', '.'))
                return  # Terminar la consulta y regresar al menú principal
            except ValueError:
                print("\n[ERROR]: Valor incorrecto. Por favor, ingresa un valor correcto.\n")

    # Funcion para consultar los ingresos totales
    def consulta_de_ingresos(self):
        print("\nConsultando ingresos...\n")

        print("Ingresos y Asientos\n")
        print(f" • Ingresos Totales: ${self.ingresos_totales:,.0f}".replace(',', '.'))
        print(f" • Asientos ocupados: {self.contador_asientos_ocupados}")
        print(f" • Asientos disponibles: {48 - self.contador_asientos_ocupados}")
