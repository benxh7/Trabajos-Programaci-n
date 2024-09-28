from functions import *

# Funcion para mostrar el menú principal del programa
def mostrar_menu():
    print("")
    print("=" * 40)
    print("Menú - Automotora Ruta 40:")
    print(" 1. Guardar vehículo")
    print(" 2. Buscar vehículo por patente")
    print(" 3. Impresión de certificados")
    print(" 4. Editor de vehículos")
    print(" 5. Salir")
    print("=" * 40)

# Funcion para mostrar el submenu del editor de vehiculos
def mostrar_menu_editor():
    print("")
    print("=" * 40)
    print("Menú de Edición - Automotora Ruta 40:")
    print(" 1. Editar multas de un vehículo")
    print(" 2. Añadir multa a un vehículo")
    print(" 3. Editar datos de un vehículo")
    print(" 4. Volver al menú principal")
    print("=" * 40)

# Clase principal
def main():
    while True:
        # Mostrar el menu
        mostrar_menu()
        opcion = input("Seleccione una opción: ")
        # Validacion de la opciones del menu
        if opcion == '1':
            guardar()
        elif opcion == '2':
            buscar()
        elif opcion == '3':
            imprimir_certificados()
        elif opcion == '4':
            # Mostrar el submenu del editor de vehiculos
            while True:
                mostrar_menu_editor()
                editor_opcion = input("Seleccione una opción del editor: ")
                # Validacion de las opciones del submenu
                if editor_opcion == '1':
                    editar_multas()
                elif editor_opcion == '2':
                    agregar_multa()
                elif editor_opcion == '3':
                    editar_datos_vehiculo()
                elif editor_opcion == '4':
                    break
                else:
                    print("\n[ERROR]: Esa opción no es válida. Intentalo de nuevo.")
        elif opcion == '5':
            print("\nGracias por usar el programa.")
            print("\nAutor: Benjamin Morales")
            print("Versión 1.0.0")
            break
        else:
            print("\n[ERROR]: Esa opción no es válida. Intentalo de nuevo.")

# Ejecucion del programa
if __name__ == "__main__":
    main()