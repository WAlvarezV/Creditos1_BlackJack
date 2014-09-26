/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fup.casino.logica;

import java.util.ArrayList;

/**
 * Esta clase representa a una baraja que se compone de 52 Objetos de tipo carta
 * @author WilliamEduardo
 */
public class Baraja { 
    /**
     * declaracion de un objeto baraja para poder utilizar el patron singleton
     */
    private static Baraja baraja;
    /**
     * declaracion de una lista de objetos tipo Carta, sern en total 52
     */
    private ArrayList<Carta> cartas;
   
    /**
     * constructor de la clase baraja
     */
    private Baraja() {
        cartas = new ArrayList(52);
        crearBaraja();
    }
    
    public ArrayList<Carta>  getCartas() {
        return cartas;
    }
    public void setCartas(ArrayList<Carta>  cartas) {
        this.cartas = cartas;
    }  
    
    /**
     * metodo que se encarga de que la baraja sea instanciada una sola vez y 
     * si ya fue instanciada retorne la misma
     * @return 
     */
    public static Baraja getInstance(){
        if (baraja == null){
            baraja = new Baraja();
        }
        return baraja;
    }
    /**
    *Metodo que genera una baraja con 52 cartas de las cuatro pintas y valores 
    *del 2 al 10 y las letras J,Q,K y A 
    */
    public final void crearBaraja(){
        String[] pintas = {"♥", "♦", "♣", "♠"};
        String[] valores = {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};        
        for (String pinta : pintas) {
            for (String valor : valores) {
                Carta carta = new Carta(valor, pinta);
                cartas.add(carta);
            }
        }       
    }
}
