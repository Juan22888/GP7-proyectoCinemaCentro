/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Fede-
 */
public class Sala {
    private int nroSala;
    private int coSala;
    private boolean apta3d;
    private int capacidad;
    private boolean estado;

    public Sala() {
    }

    public Sala(int nroSala, int coSala, boolean apta3d, int capacidad, boolean estado) {
        this.nroSala = nroSala;
        this.coSala = coSala;
        this.apta3d = apta3d;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public int getNroSala() {
        return nroSala;
    }

    public void setNroSala(int nroSala) {
        this.nroSala = nroSala;
    }

    public int getCoSala() {
        return coSala;
    }

    public void setCoSala(int coSala) {
        this.coSala = coSala;
    }

    public boolean isApta3d() {
        return apta3d;
    }

    public void setApta3d(boolean apta3d) {
        this.apta3d = apta3d;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

  
    
    
    
}
