import numpy as np

# Generamos una matriz de 3x3 con valores aleatorios entre 0 y 100
matrix = np.random.randint(0, 101, size=(3, 3))
promedio = np.mean(matrix) # Calculamos el promedio de los elementos
suma = np.sum(matrix) # Suma de los elementos
elemento_mayor = np.max(matrix) # Obtenemos el elemento mayor
elemento_menor = np.min(matrix) # Obtenemos el elemento menor
diagonal_principal = np.diag(matrix) # Mostramos sólo los elementos de la diagonal principal
print("Matriz generada:")
print(matrix)
print("\nPromedio de los elementos:", promedio)
print("Suma de los elementos:", suma)
print("Elemento mayor:", elemento_mayor)
print("Elemento menor:", elemento_menor)
print("Elementos de la diagonal principal:", diagonal_principal)