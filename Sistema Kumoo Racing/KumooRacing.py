camionetas = []
opcion = 0

while opcion != 4:
    print("\n--- Menú Serviteca KUMOO RACING ---\n"
          "1. Ingresar camioneta\n"
          "2. Registro mantenimiento\n"
          "3. Consultar camioneta\n"
          "4. Salir\n")

    try:
        opcion = int(input("Por favor, seleccione una opción: "))
        # ESTA ES LA OPCION 1. Ingresar camioneta
        if opcion == 1:
            # Ingresas y validas los datos de la camioneta
            # PATENTE
            while True:
                patente = input("Ingresa la patente de la camioneta, formato de patente (AA1000-BBBB10): ").upper()
                if (len(patente) == 6 and
                    patente[:2].isalpha() and
                    patente[2:].isdigit()) or (len(patente) == 6 and patente[:4].isalpha() and patente[4:].isdigit()):
                    break
                else:
                    print("La patente es inválida. Debes seguir el formato de patente (AA1000 o BBBB10).")
            # AÑO
            while True:
                try:
                    anio = int(input("Ingresa el año de fabricación de la camioneta (1975-2024): "))
                    if 1975 <= anio <= 2024:
                        break
                    else:
                        print("Año de fabricación inválido, debe estar entre 1975 y 2024.")
                except ValueError:
                    print("Solo debes ingresar números.")
            # TIPO
            while True:
                try:
                    tipo = int(input("Ingresa el tipo de camioneta (2 = 4x2, 4 = 4x4): "))
                    if tipo in [2, 4]:
                        break
                    else:
                        print("El tipo de camioneta seleccionado es inválido.")
                except ValueError:
                    print("Por favor, solo ingresa los digitos 2 o 4 (2 = 4x2, 4 = 4x4).")
            # MARCA
            while True:
                marca = input("Ingresa la marca de la camioneta: ").strip()
                if marca:
                    break
                print("La marca de la camioneta no puede estar vacía.")
            # MODELO
            while True:
                modelo = input("Ingresa el modelo de la camioneta: ").strip()
                if modelo:
                    break
                print("El modelo de la camioneta no puede estar vacío.")

            # Almacenamos los datos de la camioneta en una lista
            camioneta = [patente, marca, modelo, anio, tipo, []]  # Registros de mantenimiento se almacena en la posición 5
            camionetas.append(camioneta)
            print("\n[REGISTRO] Camioneta registrada exitosamente.")

        # ESTA ES LA OPCION 2. Registro mantenimiento
        elif opcion == 2:
            # Funcion para registrar el mantenimiento de una camioneta
            patente = input("Ingresa la patente de la camioneta, formato de patente (AA1000-BBBB10): ").upper()
            encontrada = False

            for camioneta in camionetas:
                if camioneta[0] == patente:  # Patente se encuentra en la posición 0 de la lista
                    fecha = input("Ingresa la fecha del mantenimiento (DD-MM-AAAA): ")
                    observaciones = input("Ingresa las observaciones del mantenimiento: ")
                    camioneta[5].append(f"Fecha: {fecha} - Observaciones: {observaciones}")
                    print("\n[REGISTRO] Mantenimiento registrado.")
                    encontrada = True
                    break

            if not encontrada:
                print("[ERROR] No se encontró una camioneta registrada con esa patente.")

        # ESTA ES LA OPCION 3. Consultar camioneta
        elif opcion == 3:
            # Funcion para consultar la información de una camioneta
            patente = input("Ingrese la patente de la camioneta a consultar, formato de patente (AA1000-BBBB10): ").upper()
            encontrada = False

            for camioneta in camionetas:
                if camioneta[0] == patente:  # Patente en la posición 0
                    print(f"\n--- Información de la Camioneta ---\n"
                          f"Patente: {camioneta[0]}\n"
                          f"Marca: {camioneta[1]}\n"
                          f"Modelo: {camioneta[2]}\n"
                          f"Año de Fabricación: {camioneta[3]}\n"
                          f"Tipo: {'4x2' if camioneta[4] == 2 else '4x4'}\n"
                          f"Registros ({len(camioneta[5])}):")
                    for registro in camioneta[5]:
                        print(f"- {registro}")
                    encontrada = True
                    break

            if not encontrada:
                print("\n[ERROR] No se encontró una camioneta registrada con esa patente.")

        # ESTA ES LA OPCION 4. Salir (del menú)
        elif opcion == 4:
            print("Gracias por atenderse con nosotros.")
        else:
            print("Opción no válida. Seleccione entre 1 y 4.")

    except ValueError:
        print("Opción inválida. Por favor, ingrese un número.")