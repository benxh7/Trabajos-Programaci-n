# SISTEMA MESON CINEDUOC
# Consultar si el cliente pertenece a Duoc
print("¿Eres estudiante de DuocUC?\n1. Sí\n2. No")
opcion2 = int(input("Ingresa el número de la opción (1/2): "))
aplicar_descuento = opcion2 == 1

# Total de precios de las entradas.
precio_estreno = 4800
precio_normal = 2900

# Se le pregunta el tipo de entrada y cantidad para seleccionar.
print("¿Qué tipo de entrada vas a querer?\n1. Estreno -> $4.800\n2. Normal -> $2.900")
tipo_entrada = int(input("Ingrese el número de la opción (1/2): "))
cantidad_entradas = int(input("¿Cuántas entradas vas a querer? Escribe el total: "))

# Se calcula el precio total de las entradas.
if tipo_entrada == 1:
    total_entradas = cantidad_entradas * precio_estreno
elif tipo_entrada == 2:
    total_entradas = cantidad_entradas * precio_normal
else:
    print("El tipo de entrada no es correcto. Debes ingresar un número valido (1/2/3)")
    total_entradas = 0

# Se le aplica al cliente el 30% de descuento si es estudiante de DuocUC.
if aplicar_descuento:
    descuento = 0.3 * total_entradas
    total_entradas -= descuento
else:
    descuento = 0

# Se le pregunta si desea añadir cabritas :D
print("¿Desea añadir cabritas?\n1. Sí\n2. No")
opcion_palomitas = int(input("Ingrese el número de la opción (1/2): "))
agrega_palomitas = opcion_palomitas == 1
total_palomitas = 0

# Le mostramos al cliente los tipos de cabritas y su valor para que pueda elegir.
if agrega_palomitas:
    print("Promociones de cabritas:\n1. Cabritas pequeñas -> $2.500\n2. Cabritas medianas -> $4.500\n3. Cabritas grandes -> $7.800")
    tipo_palomitas = int(input("Seleccione el tipo de cabritas que quieres elegir (1/2/3): "))
    cantidad_palomitas = int(input("¿Cuántas cabritas quieres?: "))

    if tipo_palomitas == 1:
        total_palomitas = cantidad_palomitas * 2500
    elif tipo_palomitas == 2:
        total_palomitas = cantidad_palomitas * 4500
    elif tipo_palomitas == 3:
        total_palomitas = cantidad_palomitas * 7800
    else:
        print("El tipo de cabritas no es correcto. Debes ingresar un número valido (1/2/3)")
        total_palomitas = 0

# Se le consulta si desea añadir una bebida, no puede faltar.
print("¿Desea añadir bebidas?\n1. Sí\n2. No")
opcion_bebida = int(input("Ingrese el número de la opción: "))
agrega_bebida = opcion_bebida == 1
total_bebidas = 0

# Le mostramos al cliente los tipos de bebida y su valor para que pueda elegir.
if agrega_bebida:
    print("Promociones de bebidas:\n1. Bebida pequeña -> $1.000\n2. Bebida mediana -> $1.250\n3. Bebida grande -> $2.000")
    tipo_bebida = int(input("Selecciona el tipo de bebida que quieres elegir (1/2/3): "))
    cantidad_bebidas = int(input("¿Cuántas bebidas desea?: "))

    if tipo_bebida == 1:
        total_bebidas = cantidad_bebidas * 1000
    elif tipo_bebida == 2:
        total_bebidas = cantidad_bebidas * 1250
    elif tipo_bebida == 3:
        total_bebidas = cantidad_bebidas * 2000
    else:
        print("El tipo de bebida no es correcto. Debes ingresar un número valido (1/2/3)")
        total_bebidas = 0

# Calculamos el total a pagar para que lo vea el cliente.
total_a_pagar = total_entradas + total_palomitas + total_bebidas
print(f"El total a pagar es: ${total_a_pagar} pesos.")

# Le solicitamos el dinero en efectivo al cliente y calculamos tambien el vuelto que este recibe.
efectivo = int(input("Escribe el efectivo disponible para pagar: $"))
vuelto = efectivo - total_a_pagar

# Le mostramos al cliente su vuelto o si le falta dinero para hacer el pago.
if vuelto >= 0:
    print(f"Su vuelto es de: ${vuelto} pesos. ¡Gracias por su compra!")
else:
    print(f"Falta dinero. Debe pagar ${abs(vuelto)} pesos.")