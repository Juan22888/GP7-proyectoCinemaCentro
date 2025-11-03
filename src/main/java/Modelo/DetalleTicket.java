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
    private Lugar lugar;
    private boolean estado;
    
    public DetalleTicket() {
    }

    public DetalleTicket(int codDetalle, Lugar lugar, boolean estado) {
        this.codDetalle = codDetalle;
        this.lugar = lugar;
        this.estado = estado;
    }

    public int getCodDetalle() {
        return codDetalle;
    }

    public void setCodDetalle(int codDetalle) {
        this.codDetalle = codDetalle;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
