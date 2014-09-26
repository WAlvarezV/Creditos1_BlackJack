/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fup.casino.logica;

import java.util.ArrayList;

/**
 * clase que representa una mesa de juego de Black Jack
 *
 * @author WilliamEduardo
 */
public class MesaJuego {

    /**
     * declaracion de una lista de objetos de tipo Jugador
     */
    private ArrayList<Jugador> jugadores;
    /**
     * declaracion de un objeto de tipo Dealer
     */
    private Dealer dealer;

    /**
     * declaracion de una varible para el valor de la apuesta minima
     */
    private int apuestaMinima;

    /**
     * constructor de la clase MesaJuego
     *
     * @param dealer
     */
    public MesaJuego(Dealer dealer) {
        this.dealer = dealer;
        jugadores = new ArrayList<>();
        apuestaMinima = 50;
    }

    /**
     * metodo que ingresa un objeto jugador a la lista de jugadores
     *
     * @param jugador
     */
    public void sentarJugador(Jugador jugador) {
        if (jugadores.size() < Constantes.MAXIMO_JUGADORES) {
            jugadores.add(jugador);
        } else {
            System.out.println("*** LA MESA ESTA LLENA CUENTA CON " + Constantes.MAXIMO_JUGADORES + " JUGADORES ***");
        }
    }

    /**
     * metodo que elimina el objeto jugador a la lista de jugadores con la
     * posicion
     *
     * @param posicion
     */
    public void levantarJugador(int posicion) {
        jugadores.remove(posicion);
    }

    /**
     * metodo que imprime los datos de los jugadores sentados en la mesa y los
     * datos del dealer
     */
    public void mostarJugadores() {
        System.out.println();
        System.out.print("Posición\tNombre\tFichas\tCartas");
        System.out.println("");
        for (Jugador jugador : jugadores) {
            System.out.print("# " + jugadores.indexOf(jugador));
            System.out.print("\t\t" + jugador.getNombre());
            System.out.print("\t" + jugador.getFichas());
            System.out.print("\t");
            jugador.mostarJuego();
            System.out.println("");
        }
        System.out.print("# " + jugadores.size());
        System.out.print("\t\tDealer");
        System.out.print("\tMany");
        System.out.print("\t");
        dealer.mostarJuego();
        System.out.println("");

    }

    /**
     * metodo que da nicio a la partida repartiendo dos cartas a cada jugador y
     * al dealer, una carta destapada y la otra tapada, retorna una lista con
     * las cartas cuyo atributo tapada es false
     *
     * @return ArrayList
     */
    public ArrayList iniciarPartida() {
        ArrayList<Carta> cartasAbiertas = new ArrayList<>();
        Carta unaCarta;
        for (Jugador jugador : jugadores) {
            unaCarta = dealer.sacarCarta(false);
            jugador.pedirCarta(unaCarta);
            cartasAbiertas.add(unaCarta);
        }
        unaCarta = dealer.sacarCarta(false);
        dealer.pedirCarta(unaCarta);
        cartasAbiertas.add(unaCarta);

        for (Jugador jugador : jugadores) {
            unaCarta = dealer.sacarCarta();
            jugador.pedirCarta(unaCarta);
        }
        unaCarta = dealer.sacarCarta();
        dealer.pedirCarta(unaCarta);
        return cartasAbiertas;
    }

    /**
     * metodo que simula el juego de una partida de Black Jack, en este metodo
     * se hacen llamdos a los otros metodos recibe la cantidad de jugadores de
     * la partida
     *
     * @param nJugadores
     */

    public void jugarPardida(int nJugadores) {
        dealer.mostarCartas();
        System.out.println();
        dealer.barajar(3);
        dealer.mostarCartas();
        String jugador = "Jugador";

        for (int i = 0; i < nJugadores; i++) {
            sentarJugador(new Jugador(jugador + i, 3000));
        }

        mostarJugadores();
        ArrayList<Carta> cartasAbiertas = iniciarPartida();

        for (Jugador jugadorA : jugadores) {
            boolean[] desicionJugador = jugadorA.pensaR(cartasAbiertas);

            while (desicionJugador[0]) {
                jugadorA.pedirCarta(dealer.sacarCarta(false));
                desicionJugador = jugadorA.pensaR(cartasAbiertas);
            }
        }

        while (dealer.pensar()) {
            dealer.pedirCarta(dealer.sacarCarta(false));
            dealer.pensar();
        }

        ganadorPartida();
        mostarJugadores();

    }

    /**
     * metodo que valida que jugadores ganaron la partida o si al contrario lo
     * hizo el dealer, imprime por consola quien gana
     */
    public void ganadorPartida() {

        for (Jugador jugador : jugadores) {
            if ((jugador.sumarCartas() > dealer.sumarCartas()) && (jugador.sumarCartas() <= 21)) {
                System.out.println("Gano :" + jugador.getNombre());
                jugador.setFichas(jugador.getFichas() + apuestaMinima);
            } else {
                System.out.println("Gano el Dealer a :" + jugador.getNombre());
            }
        }
    }

}
