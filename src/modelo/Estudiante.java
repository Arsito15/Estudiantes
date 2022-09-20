/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aruns
 */
public class Estudiante extends Persona{
    private String carnet;
    private int id;
    private String email;
    private String genero;
    private Conexion cn;
    public Estudiante (){}
    

    public Estudiante(String carnet, int id, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String genero, String email) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.carnet = carnet;
        this.id = id;
        this.email = email;
        this.genero = genero;
    }

    public Estudiante(int i, String text, String text0, String text1, String text2, String text3, String text4, String text5,String text6) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Conexion getCn() {
        return cn;
    }

    public void setCn(Conexion cn) {
        this.cn = cn;
    }
    
    
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion(); 
            cn.abrir_conexion();
            String query;
            query = "Select id_estudiante as nit,nombres,apellidos,direccion,telefono,fecha_nacimiento,genero,email from estudiantes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            String encabezado[] = {"id","Carnet","Nombres","Apellidos","Direccion","Telefono","Nacimiento","Genero","Email"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[]=new String[8];
            
            while(consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("Carnet");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[4] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");
                datos[7] = consulta.getString("genero");
                datos[8] = consulta.getString("email");
                tabla.addRow(datos);
            }
                cn.cerrar_conexion();
            
            
            
            
        }catch (SQLException ex){
            cn.cerrar_conexion();
            System.out.println("Error:" + ex.getMessage() );
        }
        return tabla;
    }
    
    @Override
    public void agregar(){
       try{
           PreparedStatement parametro;
           String query = "insert into estudiantes (carnet,nombres,apellidos,direccion,telefono,fecha_nacimiento,genero,email) VALUES (?,?,?,?,?,?,?,?);";
            cn = new Conexion();   
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getCarnet());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            parametro.setString(7, getTelefono());
            parametro.setString(8, getFecha_nacimiento());
            
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Ingresado", "Agregar",JOptionPane.INFORMATION_MESSAGE);
            
       }catch (HeadlessException | SQLException ex){
           System.out.println("Error...."+ex.getMessage());
       }
    }
    
    @Override 
      public void actualizar(){
        try{
           PreparedStatement parametro;
           String query = "update estudiante set carnet = ?,nombres= ?,apellidos= ?,direccion= ?,telefono= ?,fecha_nacimiento= ?,genero= ?, email= ? "+
                 "where id_estudiante = ?";     
            cn = new Conexion();   
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getCarnet());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            parametro.setInt(7, getId());
            parametro.setString(8, getGenero());
            parametro.setString(9, getEmail());
            
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Actualizado", "Agregar",JOptionPane.INFORMATION_MESSAGE);
            
       }catch (HeadlessException | SQLException ex){
           System.out.println("Error...."+ex.getMessage());
       }
      }
      
     @Override 
      public void eliminar(){
      try{
           PreparedStatement parametro;
           String query = "delete from estudiantes where id_estudiante = ?";
                   
            cn = new Conexion();   
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
            
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Eliminado", "Agregar",JOptionPane.INFORMATION_MESSAGE);
            
       }catch (HeadlessException | SQLException ex){
           System.out.println("Error...."+ex.getMessage());
       }
      } 
}


   
