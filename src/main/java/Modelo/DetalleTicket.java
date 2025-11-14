/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Fede-
 */
public class DetalleTicket {

    private int codDetalle;
    private Lugar lugar;
    private TicketCompra ticketCompra;
    private boolean estado;
    
    public DetalleTicket() {
    }

    public DetalleTicket(int codDetalle, Lugar lugar,TicketCompra ticketCompra, boolean estado) {
        this.codDetalle = codDetalle;
        this.lugar = lugar;
        this.ticketCompra = ticketCompra;
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

    public TicketCompra getTicketCompra() {
        return ticketCompra;
    }

    public void setTicketCompra(TicketCompra ticketCompra) {
        this.ticketCompra = ticketCompra;
    }
    

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
