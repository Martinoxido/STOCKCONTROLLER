/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.*;
/**
 *
 * @author mainc
 */
public class OpInventario extends Inventario {
    Crud crud= new Crud();
    public OpInventario() {
        Teo_cantidad=crud.InitTeo();
        Conteo_actual=crud.InitCont();
    }
    public int Porcentaje(){
        int porcentaje=this.Conteo_actual*100/this.Teo_cantidad;
        return porcentaje;
    }
}
