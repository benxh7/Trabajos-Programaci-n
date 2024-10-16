class Pasajero:
    def __init__(self, nombre, rut, telefono, banco, precio):
        self.nombre = nombre
        self.rut = rut
        self.telefono = telefono
        self.banco = banco
        self.precio = precio

    def __str__(self):
        return f"Pasajero: {self.nombre}, RUT: {self.rut}, Teléfono: {self.telefono}, Banco: {self.banco}, Precio: ${self.precio:,.0f}".replace(',', '.')