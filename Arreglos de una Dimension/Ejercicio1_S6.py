import numpy as np

# 1. Creamos el arreglo (A) con un tamaño de 100, con elementos aleatorios entre 0 y 500
arreglo_A = np.random.randint(0, 501, size=100)

# 2. Mostramos solo los valores que se encuentren en los índices pares del arreglo.
valores_pares = arreglo_A[::2]

# 3. Mostramos el elemento mayor, su índice y el elemento menor del arreglo.
elemento_mayor = np.max(arreglo_A)
indice_elemento_mayor = np.argmax(arreglo_A)
elemento_menor = np.min(arreglo_A)

# 4. Creamos un arreglo (B) que sea una copia del arreglo A, multiplicamos todos los elementos por 3 y mostramos la suma y el promedio de todos los elementos del arreglo B.
arreglo_B = arreglo_A.copy()
arreglo_B *= 3
suma_arreglo_B = np.sum(arreglo_B)
promedio_arreglo_B = np.mean(arreglo_B)

# 5. Mostramos los resultados obtenidos.
print("--- RESULTADOS OBTENIDOS ---")
print(f"VALORES PARES: {valores_pares}")
print(f"ELEMENTO MAYOR: {elemento_mayor}")
print(f"INDICE ELEMENTO MAYOR: {indice_elemento_mayor}")
print(f"ELEMENTO MENOR: {elemento_menor}")
print(f"ARREGLO B (MULTIPLICADO x3): {arreglo_B}")
print(f"SUMA DE TODOS LOS ELEMENTOS ARREGLO B: {suma_arreglo_B}")
print(f"PROMEDIO DE LOS ELEMENTOS ARREGLO B: {promedio_arreglo_B}")