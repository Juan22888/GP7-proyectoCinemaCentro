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
    private Pelicula pelicula;
    private String idioma;
    private boolean es3d;
    private String subtitulada;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private List<Lugar> lugaresDisponibles;
    private Sala salaFuncion;
    private Sala coFuncion;
    private double precioLugar;

    public Funcion() {
    }

    public Funcion(Pelicula pelicula, String idioma, boolean es3d, String subtitulada, LocalTime horaInicio, LocalTime horaFin, List<Lugar> lugaresDisponibles, Sala salaFuncion, Sala coFuncion, double precioLugar) {
        this.pelicula = pelicula;
        this.idioma = idioma;
        this.es3d = es3d;
        this.subtitulada = subtitulada;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.lugaresDisponibles = lugaresDisponibles;
        this.salaFuncion = salaFuncion;
        this.coFuncion = coFuncion;
        this.precioLugar = precioLugar;
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

    public String getSubtitulada() {
        return subtitulada;
    }

    public void setSubtitulada(String subtitulada) {
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

    public Sala getCoFuncion() {
        return coFuncion;
    }

    public void setCoFuncion(Sala coFuncion) {
        this.coFuncion = coFuncion;
    }

    public double getPrecioLugar() {
        return precioLugar;
    }

    public void setPrecioLugar(double precioLugar) {
        this.precioLugar = precioLugar;
    }

    
    
    
}
