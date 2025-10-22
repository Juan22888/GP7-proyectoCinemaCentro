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
    private int sala;
    private boolean apta3d;
    private int capacidad;
    private boolean estado;

    public Sala() {
    }

    public Sala(int sala, boolean apta3d, int capacidad, boolean estado) {
        this.sala = sala;
        this.apta3d = apta3d;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
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
