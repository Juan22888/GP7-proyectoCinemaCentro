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
public class Comprador {
    private int codComprador;
    private int dni;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String password;
    private boolean estado;

    public Comprador() {
    }

    
    
    public Comprador(int codComprador, int dni, String nombre, LocalDate fechaNacimiento, String password, boolean estado) {
        this.codComprador = codComprador;
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.password = password;
        this.estado=estado;
    }

    public int getCodComprador() {
        return codComprador;
    }

    public void setCodComprador(int codComprador) {
        this.codComprador = codComprador;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String possword) {
        this.password = possword;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    

    @Override
    public String toString() {
        return "Comprador{" + "dni=" + dni + ", nombre=" + nombre + '}';
    }

    
     
}
