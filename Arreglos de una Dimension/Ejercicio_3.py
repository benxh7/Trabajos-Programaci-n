import numpy as np

# 1. Creamos los arreglos A y B de tamaño 10, con elementos aleatorios entre 0 y 300
arreglo_A = np.random.randint(0, 301, size=10)
arreglo_B = np.random.randint(0, 301, size=10)

# 2. Hacemos la suma de los elementos de cada arreglo
suma_A = np.sum(arreglo_A)
suma_B = np.sum(arreglo_B)

# 3. Mostramos la suma de los elementos de ambos arreglos y las sumas totales de ambos
suma_total = suma_A + suma_B
print("--- RESULTADOS OBTENIDOS ---")
print(f"Suma de Elementos Arreglo A: {suma_A}")
print(f"Suma de Elementos Arreglo B: {suma_B}")
print(f"Suma Total de ambos Arreglos: {suma_total}")

# 4. Mostramos la tabla de multiplicar de los elementos impares del arreglo B
def imprimir_tabla_multiplicar(numero):
    print(f"\nTabla de multiplicar de {numero}:")
    for i in range(1, 11):
        print(f"- {numero} x {i} = {numero * i}")

elementos_impares_B = arreglo_B[arreglo_B % 2 != 0]
print("\nTABLAS DE MULTIPLICAR DE ELEMENTOS IMPARES ARREGLO B:")
for numero in elementos_impares_B:
    imprimir_tabla_multiplicar(numero)