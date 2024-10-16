import datetime
import functions

# Funcion para mostrar el menú principal
def menu():
    sistema_reservas = functions.SistemaReservas()
    while True:
        print("\nMenú Principal - Línea Aérea TETINKA\n")
        print(" 1. Ver asientos disponibles")
        print(" 2. Comprar asiento")
        print(" 3. Anular vuelo")
        print(" 4. Modificar datos de pasajero")
        print(" 5. Consultar información de un asiento")
        print(" 6. Consultar ingresos")
        print(" 7. Salir\n")

        opcion = input("Selecciona una opción: ")

        if opcion == '1':
            sistema_reservas.mostrar_asientos()
        elif opcion == '2':
            sistema_reservas.comprar_asiento()
        elif opcion == '3':
            sistema_reservas.anular_asiento()
        elif opcion == '4':
            sistema_reservas.modificar_datos_pasajero()
        elif opcion == '5':
            sistema_reservas.consultar_informacion_asiento()
        elif opcion == '6':
            sistema_reservas.consulta_de_ingresos()
        elif opcion == '7':
            print("\nAutor: Benjamin Morales")
            print(f"Saliendo del sistema... {datetime.datetime.now().strftime('%d-%m-%Y')}")
            break
        else:
            print("[ERROR]: Esa opcion no es valida. Por favor, selecciona una opción que sea válida.")

# Ejecuta el menú
if __name__ == "__main__":
    menu()