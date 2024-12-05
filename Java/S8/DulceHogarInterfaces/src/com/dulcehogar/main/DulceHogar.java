package com.dulcehogar.main;

import com.dulcehogar.database.DBConnection;
import com.dulcehogar.guis.Menu;

public class DulceHogar {

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();

        if (dbConnection.isConnectionValid()) {
            System.out.println("Conexi�n a la base de datos establecida con �xito.");
        } else {
            System.err.println("No se pudo establecer una conexi�n v�lida a la base de datos.");
            return;
        }
        
        // Creamos y mostramos el menu principal de la aplicacion
        Menu frmMenu = new Menu();
        frmMenu.setVisible(true);
    }
}
