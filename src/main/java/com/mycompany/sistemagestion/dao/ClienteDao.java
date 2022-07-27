package com.mycompany.sistemagestion.dao;

import com.mycompany.sistemagestion.model.MCliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDao {
    public void agregar(MCliente cliente){
        
        String connectionBD = "sistemagestion";
        String user = "root";
        String password = "";
        String host = "localhost";
        String port = "3306";
        String driver = "com.mysql.jdbc.Driver";
        String connectionUrl = "jdbc:mysql://" + host + ":" + port + "/" + connectionBD + "?useSSL=false";
        
        Connection conn = null;
        
        try {
            System.out.println("Conectando a la Base de Datos " + connectionUrl);
            Class.forName(driver);
            conn = DriverManager.getConnection(connectionUrl, user, password);
            System.out.println("¡Conexión exitosa!");
            
            String sql = "INSERT INTO `cliente` (`id_cliente`, `nombre`, `apellido`, `email`, `telefono`) VALUES "
                    + "(NULL, '" + cliente.getNombre() + "', '" + cliente.getApellido()+ "', '" + cliente.getEmail()+ "', '" + cliente.getTelefono()+ "');";
            System.out.println("Se ha insertado el registro exitosamente. " + sql);
            
            Statement stm = conn.createStatement();
            stm.execute(sql);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"¡Error revisa la conexión a la Dase de datos!");
        }
    }
    
    public List<MCliente> mostrar(){
        
        String connectionBD = "sistemagestion";
        String user = "root";
        String password = "";
        String host = "localhost";
        String port = "3306";
        String driver = "com.mysql.jdbc.Driver";
        String connectionUrl = "jdbc:mysql://" + host + ":" + port + "/" + connectionBD + "?useSSL=false";
        
        Connection conn = null;
        List<MCliente> listado = new ArrayList<>();
        
        try {
            System.out.println("Conectando a la Base de Datos " + connectionUrl);
            Class.forName(driver);
            conn = DriverManager.getConnection(connectionUrl, user, password);
            System.out.println("¡Conexión exitosa!");
            
            String sql = "SELECT * FROM `cliente`";
            
            Statement stm = conn.createStatement();
            ResultSet rst = stm.executeQuery(sql);
            
            while (rst.next()) {
                MCliente cliente = new MCliente();
                cliente.setId(rst.getInt("id_cliente"));
                cliente.setNombre(rst.getString("nombre"));
                cliente.setApellido(rst.getString("apellido"));
                cliente.setEmail(rst.getString("email"));
                cliente.setTelefono(rst.getString("telefono"));
                listado.add(cliente);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"¡Error revisa la conexión a la Dase de datos!");
        }
        return listado;
    }
    public void eliminar(int id){
        String connectionBD = "sistemagestion";
        String user = "root";
        String password = "";
        String host = "localhost";
        String port = "3306";
        String driver = "com.mysql.jdbc.Driver";
        String connectionUrl = "jdbc:mysql://" + host + ":" + port + "/" + connectionBD + "?useSSL=false";
        
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(connectionUrl, user, password);
            
            String sql = "DELETE FROM `cliente` WHERE `cliente`.`id_cliente` = " + id;
            System.out.println("¡Se ha eliminado el registro exitosamente! " + sql);
            
            Statement stm = conn.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "¡Error, revisa la conexión a la base de datos!");
        }
    }
}
