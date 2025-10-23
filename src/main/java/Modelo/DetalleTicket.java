/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;

/**
 *
 * @author Fede-
 */
public class DetalleTicket {
    private int codDetalle;
    private List<Lugar> lugar;
    private int cantidad;
    private boolean estado;

    public DetalleTicket() {
    }

    public DetalleTicket(int codDetalle, List<Lugar> lugar, int cantidad, boolean estado) {
        this.codDetalle = codDetalle;
        this.lugar = lugar;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public int getCodDetalle() {
        return codDetalle;
    }

    public void setCodDetalle(int codDetalle) {
        this.codDetalle = codDetalle;
    }

    public List<Lugar> getLugar() {
        return lugar;
    }

    public void setLugar(List<Lugar> lugar) {
        this.lugar = lugar;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
    
}
