/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Fede-
 */
public class Funcion {
    private int codFuncion;
    private Pelicula pelicula;
    private String idioma;
    private boolean es3d;
    private boolean subtitulada;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private List<Lugar> lugaresDisponibles;
    private Sala salaFuncion;
    private double precioLugar;

    public Funcion() {
    }

    public Funcion(int codFuncion,Pelicula pelicula, String idioma, boolean es3d, boolean subtitulada, LocalTime horaInicio, LocalTime horaFin, List<Lugar> lugaresDisponibles, Sala salaFuncion, double precioLugar) {
        this.codFuncion=codFuncion;
        this.pelicula = pelicula;
        this.idioma = idioma;
        this.es3d = es3d;
        this.subtitulada = subtitulada;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.lugaresDisponibles = lugaresDisponibles;
        this.salaFuncion = salaFuncion;
        this.precioLugar = precioLugar;
    }

    public int getCodFuncion() {
        return codFuncion;
    }

    public void setCodFuncion(int codFuncion) {
        this.codFuncion = codFuncion;
    }
    

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isEs3d() {
        return es3d;
    }

    public void setEs3d(boolean es3d) {
        this.es3d = es3d;
    }

    public boolean isSubtitulada() {
        return subtitulada;
    }

    public void setSubtitulada(boolean subtitulada) {
        this.subtitulada = subtitulada;
    }
    

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public List<Lugar> getLugaresDisponibles() {
        return lugaresDisponibles;
    }

    public void setLugaresDisponibles(List<Lugar> lugaresDisponibles) {
        this.lugaresDisponibles = lugaresDisponibles;
    }

    public Sala getSalaFuncion() {
        return salaFuncion;
    }

    public void setSalaFuncion(Sala salaFuncion) {
        this.salaFuncion = salaFuncion;
    }

  

    public double getPrecioLugar() {
        return precioLugar;
    }

    public void setPrecioLugar(double precioLugar) {
        this.precioLugar = precioLugar;
    }

    
    
    
}
