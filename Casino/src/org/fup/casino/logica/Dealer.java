/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fup.casino.logica;

import java.util.ArrayList;
import java.util.Random;

/**
 * esta clase representa a un Dealer (repartidor) de casino
 *
 * @author WilliamEduardo
 */
public class Dealer {

    /**
     * declaracion de un objeto tipo Baraja que sera administrada por el Dealer
     */
    private Baraja baraja;
    /**
     * lista de objetos tipo carta que seran las que el mismo se entregue para
     * el juego
     */
    private ArrayList<Carta> misCartas;

    /**
     * constructor de la clase Dealer
     */
    public Dealer() {
        baraja = Baraja.getInstance();
        misCartas = new ArrayList<>();
    }

    public ArrayList<Carta> getMisCartas() {
        return misCartas;
    }

    public void setMisCartas(ArrayList<Carta> misCartas) {
        this.misCartas = misCartas;
    }

    public void mostarCartas() {
        baraja.getCartas().stream().forEach((carta) -> {

            if (carta.isTapada()) {
                System.out.print(carta.getValor() + "" + carta.getPinta() + ",");
            } else {
                System.out.print("(" + carta.getValor() + "" + carta.getPinta() + "),");
            }

        });

    }

    /**
     * metodo que recibe un entero y revuelve la baraja esa cantidad de veces
     *
     * @param nVeces
     */
    public void barajar(int nVeces) {
        ArrayList<Carta> cartas = baraja.getCartas();
        int i = 0, n = nVeces;
        Random numAleatorio = new Random();
        while (i < n) {
            for (int j = 0; j < cartas.size(); j++) {
                int pibote = numAleatorio.nextInt(52);
                Carta temp = cartas.get(i);
                cartas.set(i, cartas.get(pibote));
                cartas.set(pibote, temp);
            }
            i++;
        }
    }

    /**
     * metodo que recibe un booleano que sera el valor que se le dara al
     * atributo tapada de la carta que saque, retorna un objeto de tipo carta de
     * la baraja
     *
     * @param tapada
     * @return Carta
     */
    public Carta sacarCarta(boolean tapada) {
        Carta temp = baraja.getCartas().get(0);
        baraja.getCartas().remove(temp);
        baraja.getCartas().add(new Carta("_", "_ "));
        temp.setTapada(tapada);
        return temp;
    }

    /**
     * metodo que retorna un objeto de tipo carta de la baraja
     * @return Carta 
     */
    public Carta sacarCarta() {
        Carta temp = baraja.getCartas().get(0);
        baraja.getCartas().remove(temp);
        baraja.getCartas().add(new Carta("_", "_"));
        return temp;
    }

    /**
     * metodo que de acuerdo a la suma de los puntos de las cartas retorna un 
     * dato tipo boolean que representa si debe pedir o no una carta adicional para su
     * juego
     * @return boolean
     */
    public boolean pensar() {
        int miAcumulador = 0;
        for (Carta carta : misCartas) {
            miAcumulador = miAcumulador + carta.getPeso();
        }
        return miAcumulador < 17;
    }

    /**
     * metodo que suma los puntos de las cartas de juego del Dealer
     * @return int
     */
    public int sumarCartas() {
        int miAcumulador = 0;
        for (Carta carta : misCartas) {
            miAcumulador = miAcumulador + carta.getPeso();
        }
        return miAcumulador;
    }

    /**
     * metodo que ingresa una carta a la lista de cartas de juego del Dealer
     * @param carta
     */
    public void pedirCarta(Carta carta) {
        if (misCartas.size() < Constantes.MAXIMO_CARTAS_JUGADOR) {
            misCartas.add(carta);
        } else {
            System.out.println("*** NO PUEDE RECIBIR MAS CARTAS YA TIENE" + Constantes.MAXIMO_CARTAS_JUGADOR + " CARTAS ***");
        }
    }

    /**
     * metodo que imprime de las cartas,los valores con su respectiva pinta de
     * la lista de cartas de juego
     */
    public void mostarJuego() {
        misCartas.stream().forEach((carta) -> {

            if (carta.isTapada()) {
                System.out.print(carta.getValor() + "" + carta.getPinta() + ",");
            } else {
                System.out.print("(" + carta.getValor() + "" + carta.getPinta() + "),");
            }

        });
    }
}
