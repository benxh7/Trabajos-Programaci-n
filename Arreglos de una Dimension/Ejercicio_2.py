import numpy as np

# 1. Creamos el arreglo_1 con un tamaño de 10, con elementos aleatorios entre 0 y 1000
arreglo_1 = np.random.randint(0, 1001, size=10)

# 3. Contamos solo los elementos son pares
elementos_pares = arreglo_1[arreglo_1 % 2 == 0]
conteo_pares = len(elementos_pares)

# 4. Ahora solo sumamos los elementos impares
elementos_impares = arreglo_1[arreglo_1 % 2 != 0]
suma_impares = np.sum(elementos_impares)

# 5. Emitimos mensaje cuando cada elemento que sea primo
def es_primo(num):
    if num <= 1:
        return False
    for i in range(2, int(num**0.5) + 1):
        if num % i == 0:
            return False
    return True

primos = [elem for elem in arreglo_1 if es_primo(elem)]

# 6. Mostramos los resultados obtenidos
print("--- RESULTADOS OBTENIDOS ---")
print(f"- ELEMENTOS DEL ARREGLO: {arreglo_1}")
print(f"- CANTIDAD DE ELEMENTOS PARES: {conteo_pares}")
print(f"- SUMA DE LOS ELEMENTOS IMPARES: {suma_impares}")
print("- ELEMENTOS PRIMOS DEL ARREGLO")
for elemento in arreglo_1:
    if es_primo(elemento):
      print(elemento)