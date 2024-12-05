package com.dulcehogar.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection conexion;
    
    /**
     * Este constructor nos permito el cargar el Driver para nosotros
     * poder conectarnos hacia la base de datos MySQL.
     */
    public DBConnection() {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/BD_DulceHogar";
            String user = "root";
            String pass = "password"; // POR FAVOR PONGA LA CONTRASEÑA DE LA BASE DE DATOS
            // Creamos la conexión hacia la base de datos
            conexion = DriverManager.getConnection(url, user, pass);
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Error al crear la conexión a la base de datos: " + ex.getMessage());
            ex.printStackTrace(); // Para depuración
        }
    }
    
    /**
     * Este metodo ayuda a obtener la
     * conexión hacia la base de datos
     * 
     * @return 
     */
    public Connection getConnection() {
        return conexion;
    }
    
    /**
     * Este metodo tambien nos ayuda pero a saber
     * si los datos son validos hacia la base de datos
     * 
     * @return true
     */
    public boolean isConnectionValid() {
        try {
            return conexion != null && conexion.isValid(2);
        } catch (SQLException e) {
            System.err.println("Error al verificar la conexión: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Por ultimo este metodo nos ayuda a cerrar la conexión
     * con la base de datos por ejemplo al cerrar el programa,
     * si llegase a ocurrir un error al cerrar la conexión,
     * nos imprime un mensaje en la consola.
     */
    public void closeConnection() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("La conexión hacia la base de datos fue cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}