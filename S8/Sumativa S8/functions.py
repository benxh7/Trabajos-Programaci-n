import numpy as np
import re

vehiculos = [] # Lista para almacenar los vehículos

# Validacion del formato de patente de tipo AA1000 o BBBB10 y retorno si la patente valida
def validar_patente():
    while True:
        patente = input("Ingresa la patente del vehículo (EJ: AA1000 o BBBB10): ").strip().upper()
        patron = r'^[A-Z]{2}[0-9]{4}$|^[A-Z]{4}[0-9]{2}$'
        if re.match(patron, patente):
            return patente  # Retorna la patente válida
        else:
            print("[ERROR]: La patente es inválida. Inténtalo de nuevo.")

# Validacion de formato de fecha DD-MM-AAAA
def validar_fecha(fecha):
    patron = r'^\d{2}-\d{2}-\d{4}$'
    return re.match(patron, fecha) is not None

# Validacion del precio si es mayor a $6.880.000
def validar_precio(precio):
    return precio > 6880000

# Funcion para tranformar el precio ejemplo 7000000 pasarian a $7.000.000
def formatear_precio(precio):
    return f"${precio:,.0f}".replace(',', '.')

# Funcion para validar el ingreso correcto del input ingresado por el usuario
def validar_input(mensaje, tipo_valor, validacion=None):
    while True:
        valor_input = input(mensaje)
        try:
            valor = tipo_valor(valor_input)
            if validacion is None or validacion(valor):
                return valor
            else:
                print("[ERROR]: El dato ingresado es incorrecto.")
        except ValueError:
            print("[ERROR]: Ocurrio un error al ingresar el valor o dato.")

# Funcion para validar el tipo de vehiculo ingresado por el usuario, retornar el tipo valido y ademas se corrige
# si el usuario ingresa con mayusculas o minusculas el tipo de vehiculo.
def tipo_vehiculo():
    tipos_validos = ["Auto", "Suvs", "Pickups", "Vans"]
    while True:
        tipo = input("Ingresa el tipo de vehículo (Auto, Suvs, Pickups, Vans): ").strip().capitalize()
        if tipo in tipos_validos:  # Validar el tipo de vehículo
            return tipo  # Devuelve el tipo válido
        else:
            print("[ERROR]: Tipo de vehículo inválido. Inténtalo de nuevo.")

# Funcion que se encarga de guardar los datos de un vehiculo y agregarlo a la lista de vehiculos
def guardar():
    tipo = tipo_vehiculo()
    patente = validar_patente()
    marca = validar_input("Ingresa la marca del vehiculo (2-12 caracteres): ", str, lambda x: 2 <= len(x) <= 12)
    modelo = input("Ingresa el modelo del vehículo: ")
    precio = validar_input("Ingresa el precio del vehiculo (debe ser mayor a $6.880.000): ", float, validar_precio)
    cantidad_multas = validar_input("Ingresa el número de multas (0 si no tiene): ", int, lambda x: x >= 0)
    multas = []

    # Si el vehículo tiene multas, se solicita la informacion de cada una
    # pero si se ingresa 0 no se solicita nada mas y se salta al siguiente paso.
    if cantidad_multas > 0:
        for _ in range(cantidad_multas):
            monto = np.random.randint(2300, 5400)  # Monto aleatorio entre $2.300 y $5.400
            fecha = validar_input("Ingresa la fecha de la multa (DD-MM-AAAA): ", str, validar_fecha)
            multas.append((monto, fecha))

    fecha_registro = validar_input("Ingresa la fecha de registro del vehículo (DD-MM-AAAA): ", str, validar_fecha)
    dueno = input("Para finalizar, ingresa el nombre del dueño: ")

    # Crea un diccionario con los datos del vehículo y lo agrega a la lista de vehiculos
    vehiculo = {
        'tipo': tipo,
        'patente': patente,
        'marca': marca,
        'modelo': modelo,
        'precio': precio,
        'multas': multas,
        'fecha_registro': fecha_registro,
        'dueno': dueno
    }

    vehiculos.append(vehiculo)
    print("\n[REGISTRO]: Vehículo guardado y registrado exitosamente.")

# Funcion para buscar un vehiculo por patente y mostrar su informacion
def buscar():
    patente = input("Ingrese la patente del vehículo a buscar: ").upper()
    for vehiculo in vehiculos:
        if vehiculo['patente'] == patente:
            imprimir_info_vehiculo(vehiculo)
            return
    print("\n[ERROR]: Vehículo no encontrado.")

# Función para imprimir información del vehículo
def imprimir_info_vehiculo(vehiculo):
    print("")
    print("=" * 40)
    print("Información del Vehículo")
    print(f" Tipo: {vehiculo['tipo']}")
    print(f" Patente: {vehiculo['patente']}")
    print(f" Marca: {vehiculo['marca']}")
    print(f" Modelo: {vehiculo['modelo']}")
    print(f" Precio: {formatear_precio(vehiculo['precio'])}")
    # Formato de mensaje para las multas del vehículo
    if len(vehiculo['multas']) == 0:
        print(" Multas (0): Sin multas.")
    else:
        print(f" Multas ({len(vehiculo['multas'])}):")
        for multa in vehiculo['multas']:
            print(f"  - Monto: {formatear_precio(multa[0])} - Fecha: {multa[1]}")
    print(f" Fecha de Registro: {vehiculo['fecha_registro']}")
    print(f" Nombre del Dueño: {vehiculo['dueno']}")
    print("=" * 40)

# Función para imprimir certificados
def imprimir_certificados():
    patente = input("Ingrese la patente del vehículo para imprimir certificados: ").upper()
    for vehiculo in vehiculos:
        if vehiculo['patente'] == patente:
            print("")
            print("=" * 40)
            print(f"Certificado de Emisión de Contaminantes")
            print(f" Patente: {vehiculo['patente']}")
            print(f" Dueño: {vehiculo['dueno']}")
            print(f" Costo: ${np.random.randint(2300, 5400)}")
            print("=" * 40)
            return
    print("\n[ERROR]: Vehículo no encontrado.")

# Funcion para editar las multas de un vehiculo
def editar_multas():
    patente = input("Ingresa la patente del vehículo para editar sus multas: ").upper()
    # Buscamos el vehiculo por la patente ingresada
    for vehiculo in vehiculos:
        if vehiculo['patente'] == patente:
            # Mostrar las multas actuales del vehiculo y las enumeramos para que el usuario pueda seleccionar la multa
            print(f"Multas actuales ({len(vehiculo['multas'])}):")
            for idx, multa in enumerate(vehiculo['multas']):
                print(f" {idx + 1}. Monto: ${multa[0]} - Fecha: {multa[1]}")

            # Validar que el vehiculo tenga multas, si no tiene se muestra un mensaje de error y retorna
            if len(vehiculo['multas']) == 0:
                print("[ERROR]: Este vehículo no tiene multas.")
                return

            # Solicitar al usuario el numero de la multa que desea editar
            indice = int(input("Selecciona el número de la multa que desea editar: ")) - 1
            if 0 <= indice < len(vehiculo['multas']): # Valida que el indice ingresado sea valido
                nuevo_monto = input("Ingresa el nuevo monto de la multa: ")
                nueva_fecha = input("Ingresa la nueva fecha de la multa (DD-MM-AAAA): ")
                # Valida que el monto sea un numero y que la fecha sea valida con la funcion validar_fecha
                while not validar_fecha(nueva_fecha):
                    print("[ERROR]: La fecha es inválida. Intentalo de nuevo.")
                    nueva_fecha = input("Por favor, Ingresa la nueva fecha de la multa (DD-MM-AAAA): ")

                # Actualiza la multa del vehiculo con los nuevos datos ingresados
                vehiculo['multas'][indice] = (nuevo_monto, nueva_fecha)
                print("[EDITOR]: Multa actualizada correctamente.")
            else:
                print("[ERROR]: Número de multa inválido.")
            return
    print("\n[ERROR]: Vehículo no encontrado.")


# Funcion para editar los datos de un vehiculo
def editar_datos_vehiculo():
    patente = input("Ingresa la patente del vehículo para editar sus datos: ").upper()
    # Buscamos el vehiculo por la patente ingresada y mostramos los datos actuales del vehiculo
    for vehiculo in vehiculos:
        if vehiculo['patente'] == patente:
            print("Datos actuales del vehículo:")
            imprimir_info_vehiculo(vehiculo)

            vehiculo['marca'] = input("Ingresa la nueva marca del vehiculo: ")
            vehiculo['modelo'] = input("Ingresa el nuevo modelo del vehiculo: ")

            # Validamos que el precio sea un numero y que sea mayor a $6.880.000 si es asi
            # se actualizara el precio y  sus otros nuevos datos ingresados para el vehiculo
            while True:
                nuevo_precio_input = input("Ingrese el nuevo precio del vehiculo: ").replace('.', '')
                try:
                    nuevo_precio = float(nuevo_precio_input)
                    if validar_precio(nuevo_precio):
                        vehiculo['precio'] = nuevo_precio
                        break
                    else:
                        print("[ERROR]: El precio debe ser mayor a $6.880.000")
                except ValueError:
                    print("[ERROR]: El valor es inválido. Asegurate de ingresar solo números.")

            print("[EDITOR] Los datos del vehículo fueron actualizados correctamente.")
            return
    print("\n[ERROR]: Vehículo no encontrado.")

# Función para agregar una multa a un vehículo
def agregar_multa():
    patente = input("Ingresa la patente del vehículo para agregar una multa: ").upper()
    # Buscamos el vehiculo por la patente ingresada
    for vehiculo in vehiculos:
        if vehiculo['patente'] == patente:
            # Bucle para agregar multas al vehiculo
            while True:
                nuevo_monto = np.random.randint(2300, 5400)
                while True:
                    nueva_fecha = input("Ingresa la fecha de la nueva multa (dd-mm-aaaa): ")
                    if validar_fecha(nueva_fecha):
                        break
                    else:
                        print("[ERROR]: La fecha es inválida. El formato correcto es DD-MM-AAAA.")

                # Añadir la multa al vehículo
                vehiculo['multas'].append((nuevo_monto, nueva_fecha))
                print(f"[EDITOR] Multa añadida exitosamente. Monto: {formatear_precio(nuevo_monto)}, Fecha: {nueva_fecha}")

                # Validar si se desea agregar otra multa
                while True:
                    otra = input("¿Deseas agregar otra multa? (Escriba: S/N): ").strip().lower()
                    if otra in ['s', 'n']:
                        break
                    else:
                        print("[ERROR]: Ingresa 'S' para agregar otra multa o 'N' para volver al menu de edicion.")

                if otra == 'n':  # Si la respuesta es 'n', salimos del bucle
                    break
            return
    print("\n[ERROR]: Vehículo no encontrado.")