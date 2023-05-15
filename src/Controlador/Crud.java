/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import Modelo.Conexion;
import javax.swing.JOptionPane;

import javax.swing.table.TableModel;
/**
 *
 * @author mainc
 */
public class Crud {
    Conexion connUno = new Conexion();
    private Connection connect;
    private Statement st;
    private ResultSet rs;
    public void ModificarEquipo(String Rut,String x,String y){
        try {
            String sql = "update Equipo set PasilloB ='"+x+"' where Rut_Operador= '"+Rut+"'";
            connect=connUno.getConnection();
            st=connect.createStatement();
            st.execute(sql);
            String sql1 = "update Equipo set Nivel ='"+y+"' where Rut_Operador= '"+Rut+"'";
            connect=connUno.getConnection();
            st=connect.createStatement();
            st.execute(sql1);
            JOptionPane.showMessageDialog(null, "modificado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error modificar");
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public TableModel consultarTotalEquipo(DefaultTableModel modelo){
        
        try {
            String sql = "select * from Equipo";
            connect = connUno.getConnection();
            st = connect.createStatement();
            rs = st.executeQuery(sql);
            
            Object[] LoginArreglo = new Object [4];
            
            
            while(rs.next()){
                LoginArreglo[0]=rs.getString("Rut_Operador");
                LoginArreglo[1]=rs.getString("PasilloB");
                LoginArreglo[2]=rs.getString("Nivel");
                
                modelo.addRow(LoginArreglo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (TableModel)modelo;
    }
    public void AñadirEquipo(String x, String y,String z){
        try {
            String comilla="'";
            String sql = "insert into Equipo (Rut_Operador,PasilloB,Nivel) values("+comilla+x+comilla+","+comilla+y+comilla+","+comilla+z+comilla+")";
            connect=connUno.getConnection();
            st=connect.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ModificarOperador(String x,String y,String exRut){
        try {
            String sql = "update Operador set Nombre_Operador ='"+x+"' where Rut_Operador= '"+y+"'";
            connect=connUno.getConnection();
            st=connect.createStatement();
            st.execute(sql);
            String sql1 = "update Operador set Rut_Operador ='"+y+"' where Rut_Operador= '"+y+"'";
            connect=connUno.getConnection();
            st=connect.createStatement();
            st.execute(sql1);
            JOptionPane.showMessageDialog(null, "modificado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error modificar");
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AñadirOperador(String y, String x){
        try {
            String comilla="'";
            String sql = "insert into Operador (Nombre_operador, Rut_Operador) values("+comilla+x+comilla+","+comilla+y+comilla+")";
            connect=connUno.getConnection();
            st=connect.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public TableModel consultarUsuariosxNombre(DefaultTableModel modelo,String Nombre){
        
        try {
            String sql = "SELECT * FROM Operador WHERE (Nombre_Operador LIKE '%"+Nombre+"%')";
            connect = connUno.getConnection();
            st = connect.createStatement();
            rs = st.executeQuery(sql);
            
            Object[] LoginArreglo = new Object [2];
            
            
            while(rs.next()){
                LoginArreglo[0]=rs.getString("Nombre_Operador");
                LoginArreglo[1]=rs.getString("Rut_operador");
                modelo.addRow(LoginArreglo);
            }
            
        } catch (SQLException ex) {
            
        }
        
        return (TableModel)modelo;
    }
    public TableModel consultarUsuarios(DefaultTableModel modelo){
        
        try {
            String sql = "select * from Operador";
            connect = connUno.getConnection();
            st = connect.createStatement();
            rs = st.executeQuery(sql);
            
            Object[] LoginArreglo = new Object [4];
            
            
            while(rs.next()){
                LoginArreglo[0]=rs.getString("Nombre_Operador");
                LoginArreglo[1]=rs.getString("Rut_operador");
                modelo.addRow(LoginArreglo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (TableModel)modelo;
    }
    public int resCont(int x){
        int conteo=InitCont();
        try {
            
            conteo=conteo-x;
            String sql1 = "update progreso set Contado ='"+conteo+"' where Teoria= "+InitTeo();
            connect=connUno.getConnection();
            st=connect.createStatement();
            st.execute(sql1);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conteo;
    }
    public int sumCont(int x){
        int conteo=InitCont();
        try {
            
            conteo=conteo+x;
            String sql1 = "update progreso set Contado ='"+conteo+"' where Teoria= "+InitTeo();
            connect=connUno.getConnection();
            st=connect.createStatement();
            st.execute(sql1);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conteo;
    }
    public int InitCont(){
        int Teo=0;
        try {
            String sql = "select * from progreso";
            connect=connUno.getConnection();
            st=connect.createStatement();
            rs=st.executeQuery(sql);
            while (rs.next()){
                Teo=rs.getInt("Contado");
            }
            
        } catch (SQLException ex) {
            
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Teo;
    }
    public int InitTeo(){
        int Teo=0;
        try {
            String sql = "select * from progreso";
            connect=connUno.getConnection();
            st=connect.createStatement();
            rs=st.executeQuery(sql);
            while (rs.next()){
                Teo=rs.getInt("Teoria");
            }
            
        } catch (SQLException ex) {
            
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Teo;
    }
    public void SetContado(String x ){
        try {
            String sql = "update Ubicacion set Estado ='Contado' where Codigo_Ubicacion= "+"'"+x+"'";
            connect=connUno.getConnection();
            st=connect.createStatement();
            st.execute(sql);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public TableModel consultarEstadoxNivel(DefaultTableModel modelo,String Pasillo){
        
        try {
            String sql = "SELECT * FROM Ubicacion WHERE (Codigo_Ubicacion LIKE '%"+"N"+Pasillo+"%')";
            connect = connUno.getConnection();
            st = connect.createStatement();
            rs = st.executeQuery(sql);
            
            Object[] LoginArreglo = new Object [4];
            
            
            while(rs.next()){
                LoginArreglo[0]=rs.getString("Codigo_Ubicacion");
                LoginArreglo[1]=rs.getString("Estado");
                
                modelo.addRow(LoginArreglo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (TableModel)modelo;
        
    }
    public TableModel consultarEstadoxPasillo(DefaultTableModel modelo,String Pasillo){
        
        try {
            String sql = "SELECT * FROM Ubicacion WHERE (Codigo_Ubicacion LIKE '"+Pasillo+"%')";
            connect = connUno.getConnection();
            st = connect.createStatement();
            rs = st.executeQuery(sql);
            
            Object[] LoginArreglo = new Object [4];
            
            
            while(rs.next()){
                LoginArreglo[0]=rs.getString("Codigo_Ubicacion");
                LoginArreglo[1]=rs.getString("Estado");
                
                modelo.addRow(LoginArreglo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (TableModel)modelo;
        
    }
    public TableModel consultarEstadoTotal(DefaultTableModel modelo){
        
        try {
            String sql = "select * from Ubicacion";
            connect = connUno.getConnection();
            st = connect.createStatement();
            rs = st.executeQuery(sql);
            
            Object[] LoginArreglo = new Object [4];
            
            
            while(rs.next()){
                LoginArreglo[0]=rs.getString("Codigo_Ubicacion");
                LoginArreglo[1]=rs.getString("estado");
              
                modelo.addRow(LoginArreglo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (TableModel)modelo;
    }
    public void AñadirConteo(String x, String y, int z,String w){
        try {
            String comilla="'";
            String sql = "insert into Conteo (Codigo_Ubicacion_fk,Codigo_Producto_fk,Cantidad,Rut_Operador_fk) values("+comilla+x+comilla+","+comilla+y+comilla+","+z+","+comilla+w+comilla+")"; 
            connect=connUno.getConnection();
            st=connect.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void EliminarConteo(String x, int y, int z,int id){
        try {
            char comilla='"';
            String sql = "delete From Conteo where Codigo_Ubicacion_fk="+'"'+x+'"'+" AND Codigo_Producto_fk= "+y+" AND cantidad="+z+" AND id_conteo="+id; 
            connect=connUno.getConnection();
            st=connect.createStatement();
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Eliminado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Eliminar");
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getIDConteo(String x, int y,int z){
        int Id = 0;
        try {
            
            String sql = "Select * from Conteo where Codigo_Ubicacion_fk="+'"'+x+'"'+" AND Codigo_Producto_fk= "+y+" AND cantidad="+z; 
            connect=connUno.getConnection();
            st=connect.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                Id=rs.getInt("id_conteo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Id;
    }
    public void ModificarConteo(String x, int y, int z,int ID ){
        try {
            String sql = "update Conteo set Codigo_Ubicacion_fk ='" + x + "', Codigo_Producto_fk ='" + y + "',cantidad ='" + z + "' where id_conteo = " + ID;
            connect=connUno.getConnection();
            st=connect.createStatement();
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "modificado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error modificar");
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Boolean consultarUsuario(String Rut){
        boolean found=false;
        try {
            
            String sql="Select * from Operador";
            connect=connUno.getConnection();
            st=connect.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
            if(rs.getString("Rut_Operador").equals(Rut)){
                found=true;
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return found;
    }
    public String getNombrexRut(String Rut){
        String Nombre="";
        try {
            
            String sql="Select * from Operador";
            connect=connUno.getConnection();
            st=connect.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
            if(rs.getString("Rut_Operador").equals(Rut)){
                Nombre=rs.getString("Nombre_Operador");
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        String nm=Nombre;
        return nm;
    }
    public TableModel consultarTotal(DefaultTableModel modelo){
        
        try {
            String sql = "select * from Conteo";
            connect = connUno.getConnection();
            st = connect.createStatement();
            rs = st.executeQuery(sql);
            
            Object[] LoginArreglo = new Object [4];
            
            
            while(rs.next()){
                LoginArreglo[0]=rs.getString("Codigo_Ubicacion_fk");
                LoginArreglo[1]=rs.getString("Codigo_Producto_fk");
                LoginArreglo[2]=rs.getString("Cantidad");
                LoginArreglo[3]=rs.getString("Rut_Operador_fk");
                modelo.addRow(LoginArreglo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (TableModel)modelo;
    }
    public TableModel consultarxPasillo(DefaultTableModel modelo,String Pasillo){
        
        try {
            String sql = "SELECT * FROM Conteo WHERE (Codigo_Ubicacion_fk LIKE '"+Pasillo+"%')";
            connect = connUno.getConnection();
            st = connect.createStatement();
            rs = st.executeQuery(sql);
            
            Object[] LoginArreglo = new Object [4];
            
            
            while(rs.next()){
                LoginArreglo[0]=rs.getString("Codigo_Ubicacion_fk");
                LoginArreglo[1]=rs.getString("Codigo_Producto_fk");
                LoginArreglo[2]=rs.getString("Cantidad");
                LoginArreglo[3]=rs.getString("Rut_Operador_fk");
                modelo.addRow(LoginArreglo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (TableModel)modelo;
        
    }
    public TableModel consultarxUbicacion(DefaultTableModel modelo,String Ubicacion){
        
        try {
            String sql = "SELECT * FROM Conteo WHERE Codigo_Ubicacion_fk="+"'"+Ubicacion+"'";
            connect = connUno.getConnection();
            st = connect.createStatement();
            rs = st.executeQuery(sql);
            
            Object[] LoginArreglo = new Object [4];
            
            
            while(rs.next()){
                LoginArreglo[0]=rs.getString("Codigo_Ubicacion_fk");
                LoginArreglo[1]=rs.getString("Codigo_Producto_fk");
                LoginArreglo[2]=rs.getString("Cantidad");
                LoginArreglo[3]=rs.getString("Rut_Operador_fk");
                modelo.addRow(LoginArreglo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (TableModel)modelo;
}
}
