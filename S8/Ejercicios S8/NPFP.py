# Verifica si un número es primo.
def es_primo(numero):
    if numero < 2:
        return False
    for i in range(2, int(numero ** 0.5) + 1):
        if numero % i == 0:
            return False
    return True

# Calcula el factorial de un número.
def calcular_factorial(numero):
    factorial = 1
    for i in range(1, numero + 1):
        factorial *= i
    return factorial

# Verificamos si una frase es un palindrome.
def es_palindrome(frase):
    frase_limpia = ''.join(frase.lower().split()) # Remueve espacios y convierte a minúsculas
    return frase_limpia == frase_limpia[::-1]

# Le muestra el menu al usuario.
def mostrar_menu():
    while True:
        print("\n--- MENÚ PRINCIPAL ---")
        print("1. Número primo")
        print("2. Factorial")
        print("3. Palindrome")
        print("4. Salir")

        opcion = input("Elija una opción (1-4): ")

        if opcion == "1":
            numero = int(input("Ingrese un número entre 1 y 15: "))
            if 1 <= numero <= 15:
                if es_primo(numero):
                    print(f"El número {numero} es primo.")
                else:
                    print(f"El número {numero} no es primo.")
            else:
                print("Número fuera de rango.")

        elif opcion == "2":
            numero = int(input("Ingrese un número positivo entre 3 y 10: "))
            if 3 <= numero <= 10:
                print(f"El factorial de {numero} es {calcular_factorial(numero)}.")
            else:
                print("Número fuera de rango.")

        elif opcion == "3":
            frase = input("Ingrese una frase: ")
            if es_palindrome(frase):
                print(f"La frase '{frase}' es un palindrome.")
            else:
                print(f"La frase '{frase}' no es un palindrome.")

        elif opcion == "4":
            print("Gracias por utilizar el programa. Autor: Benjamin.")
            break

        else:
            print("Opción inválida. Intente nuevamente.")

# Llamar al menú para iniciar el programa
mostrar_menu()