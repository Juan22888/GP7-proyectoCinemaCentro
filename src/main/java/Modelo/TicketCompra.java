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
    private LocalDate FechaFucion;
    private double monto;
    private Comprador comprador;

    public TicketCompra() {
    }

    
    
    public TicketCompra(int codTicket, LocalDate FechaCompra, LocalDate FechaFucion, double monto, Comprador comprador) {
        this.codTicket = codTicket;
        this.FechaCompra = FechaCompra;
        this.FechaFucion = FechaFucion;
        this.monto = monto;
        this.comprador = comprador;
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

    public LocalDate getFechaFucion() {
        return FechaFucion;
    }

    public void setFechaFucion(LocalDate FechaFucion) {
        this.FechaFucion = FechaFucion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
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
