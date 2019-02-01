/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConexion {

    // Librería de MySQL
    public static String driver = "com.mysql.jdbc.Driver";

    // Nombre de la base de datos
    public static String database = "PROYECTO";

    // Host
    public static String hostname = "localhost";

    // Puerto
    public static String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

    // Nombre de usuario
    public static String username = "anaklusmos";

    // Clave de usuario
    public static String password = "anaklusmos.123";
    public static Connection conn = null;

    public static Connection conectarMySQL() {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Ready");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        return conn;
    }


 public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            System.out.println(ex);        }}

    }
