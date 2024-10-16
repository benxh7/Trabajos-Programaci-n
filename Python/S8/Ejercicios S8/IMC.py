# Función para calcular el IVA
def calcular_iva(precio):
    iva = precio * 0.19
    return iva

# Función para calcular descuento
def calcular_descuento(precio, descuento):
    precio_final = precio - descuento
    return precio_final

# Función para calcular IMC
def calcular_imc(peso, estatura):
    imc = peso / (estatura ** 2)
    if imc < 18.5:
        estado = "Bajo peso"
    elif 18.5 <= imc < 24.9:
        estado = "Adecuado"
    elif 25.0 <= imc < 29.9:
        estado = "Sobrepeso"
    elif 30.0 <= imc < 34.9:
        estado = "Obesidad grado 1"
    elif 35.0 <= imc < 39.9:
        estado = "Obesidad grado 2"
    else:
        estado = "Obesidad grado 3"
    return imc, estado

# Función del menú
def mostrar_menu():
    print("Seleccione una opción:")
    print("1. Calcular IVA")
    print("2. Calcular Descuento")
    print("3. Calcular IMC")
    print("4. Salir")

# Programa para ejecutar las opciones del menú principal
while True:
    mostrar_menu()
    opcion = int(input("Ingrese su opción: "))

    if opcion == 1:
        precio = float(input("Ingrese el precio del producto: "))
        iva = calcular_iva(precio)
        print(f"El IVA del producto es: {iva}")
    elif opcion == 2:
        precio = float(input("Ingrese el precio del producto: "))
        descuento = float(input("Ingrese el valor del descuento: "))
        precio_final = calcular_descuento(precio, descuento)
        print(f"El precio final con descuento es: {precio_final}")
    elif opcion == 3:
        peso = float(input("Ingrese su peso en kg: "))
        estatura = float(input("Ingrese su estatura en metros (Ejemplo: 1.60): "))
        imc, estado = calcular_imc(peso, estatura)
        print(f"Su IMC es: {imc:.2f}")
        print(f"Estado: {estado}")
    elif opcion == 4:
        print("Saliendo del programa...")
        break
    else:
        print("Opción no válida. Intente nuevamente.")