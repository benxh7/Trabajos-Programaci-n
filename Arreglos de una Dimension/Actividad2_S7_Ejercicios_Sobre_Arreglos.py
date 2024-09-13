import numpy as np

matrix = np.zeros((3, 3)) # Crear un arreglo 3x3 con ceros
np.fill_diagonal(matrix, [1, 2, 3]) # Asignar los valores 1, 2 y 3 a la diagonal principal

print("Matriz con ceros y diagonal principal con 1, 2, 3:")
print(matrix)