/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author Emiliano
 */
public class TicketCompra {

    private int codTicket;
    private LocalDate FechaCompra;
    private double monto;
    private Comprador comprador;
    private boolean metodoPago;
    private DetalleTicket detalleTicket;

    public TicketCompra(int codTicket, LocalDate FechaCompra, double monto, Comprador comprador, DetalleTicket detalleTicket,boolean metodoPago) {
        this.codTicket = codTicket;
        this.FechaCompra = FechaCompra;
        this.monto = monto;
        this.comprador = comprador;
        this.detalleTicket = detalleTicket;
        this.metodoPago = metodoPago;
    }

    public TicketCompra() {
    }

    public DetalleTicket getDetalleTicket() {
        return detalleTicket;
    }

    public void setDetalleTicket(DetalleTicket detalleTicket) {
        this.detalleTicket = detalleTicket;
    }

    public int getCodTicket() {
        return codTicket;
    }

    public void setCodTicket(int codTicket) {
        this.codTicket = codTicket;
    }

    public LocalDate getFechaCompra() {
        return FechaCompra;
    }

    public void setFechaCompra(LocalDate FechaCompra) {
        this.FechaCompra = FechaCompra;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(boolean metodoPago) {
        this.metodoPago = metodoPago;
    }
    
    

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    @Override
    public String toString() {
        return "TicketCompra{" + "codTicket=" + codTicket + ", monto= $" + monto + ", comprador=" + comprador.getNombre() + '}';
    }

}
