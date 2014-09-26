
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fup.casino.logica;

import java.util.ArrayList;

/**
 * esta clase representa a un jugador de Black Jack
 *
 * @author WilliamEduardo
 */
public class Jugador {

    /**
     * declaracion de variable tipo string para el nombre del jugador
     */
    private String nombre;
    /**
     * declaracion de variable tipo int que almacena el valor de fichas para
     * jugar
     */
    private int fichas;
    /**
     * lista de objetos tipo carta que seran las que el mismo se entregue para
     * el juego
     */
    ArrayList<Carta> cartasJ;

    /**
     * constructor de la clase Dealer
     *
     * @param nombre
     * @param fichas
     */
    public Jugador(String nombre, int fichas) {
        this.nombre = nombre;
        this.fichas = fichas;
        this.cartasJ = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFichas() {
        return fichas;
    }

    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

    public ArrayList<Carta> getCartasJ() {
        return cartasJ;
    }

    public void setCartasJ(ArrayList<Carta> cartasJ) {
        this.cartasJ = cartasJ;
    }

    /**
     * metodo que ingresa una carta a la lista de cartas de juego del Dealer
     *
     * @param carta
     */
    public void pedirCarta(Carta carta) {
        if (cartasJ.size() < Constantes.MAXIMO_CARTAS_JUGADOR) {
            cartasJ.add(carta);
        } else {
            System.out.println("*** NO PUEDE RECIBIR MAS CARTAS YA TIENE" + Constantes.MAXIMO_CARTAS_JUGADOR + " CARTAS ***");
        }
    }

    /**
     * metodo que imprime de las cartas,los valores con su respectiva pinta de
     * la lista de cartas de juego
     */
    public void mostarJuego() {
        cartasJ.stream().forEach((carta) -> {
            if (carta.isTapada()) {
                System.out.print(carta.getValor() + "" + carta.getPinta() + ",");
            } else {
                System.out.print("(" + carta.getValor() + "" + carta.getPinta() + "),");
            }
        });
    }

    /**
     * metodo retorna un arreglo de tipo boolean , con el cual se decide de
     * acuerdo a la posicion que hacer, se tiene en cuenta la suma en puntos de 
     * las cartas y ademas tiene en cuenta el valor de la carta destapada del
     * Dealer, este metodo esta basado en la tabla que se peude encontar en la
     * en la carpeta imegenes o en la pagina a continuacion
     * http://www.comoganarcasino.com/estrategias-para-jugar-black-jack.html
     * @param cartasAbiertas
     * @return boolean[]
     */
    public boolean[] pensaR(ArrayList<Carta> cartasAbiertas) {
        //variable sC que recibe el dato de la suma de las cartas del jugador
        int sC = sumarCartas();
        //variable vrCD que recibe el valor de la carta destapada del Dealer
        int vrCD = cartasAbiertas.get(cartasAbiertas.size() - 1).getPeso();
        //Arreglos de tipo boolean que en la primer posición indica si se planta o pide carta
        // en la segunda posición indica si dobla la apuesta
        //en la tercer posición indica si divide el juego
        boolean pideCarta[] = {true, false, false};
        boolean pideCartayDobla[] = {true, true, false};
        boolean sePlanta[] = {false, false, false};
        boolean sePlantayDobla[] = {false, true, false};
        boolean divideJuego[] = {true, false, true};

//        System.out.println("Carta Abierta Dealer: " + vrCD);
//        System.out.println("Suma Cartas: " + sC);
        //variable cC que recibe el valor de la cuenta de las cartas destapadas
        int cC = contarCartas(cartasAbiertas);

        //validacion de las diferentes variables
        if (sC == 21) {
            return sePlantayDobla;
        } else {

            if (tengoDobles()) {

                if (cantdAs() == 2 || sC == 16) {
                    return divideJuego;
                } else if (sC == 20) {
                    return sePlanta;
                } else if ((sC == 4 || sC == 6 || sC == 14) && vrCD <= 7) {
                    return divideJuego;
                } else if ((sC == 4 || sC == 6 || sC == 14) && vrCD > 7) {
                    return pideCarta;
                } else if (sC == 8 && (vrCD == 5 || vrCD == 6)) {
                    return divideJuego;
                } else if (sC == 8 && (vrCD < 5 || vrCD > 6)) {
                    return pideCarta;
                } else if (sC == 10 && vrCD > 9) {
                    return pideCarta;
                } else if (sC == 10 && vrCD <= 9) {
                    return pideCartayDobla;
                } else if (sC == 12 && vrCD <= 7) {
                    return divideJuego;
                } else if (sC == 12 && vrCD > 7) {
                    return pideCarta;
                } else if (sC == 18 && (vrCD == 7 || vrCD >= 10)) {
                    return sePlanta;
                } else {
                    return divideJuego;
                }

            } else if (cantdAs() != 1) {
                if (sC <= 8 || sC == 10 && vrCD >= 10 || sC == 11 && vrCD == 11) {
                    return pideCarta;
                } else if (sC == 10 && vrCD < 10 || sC == 11 && vrCD < 11) {
                    return pideCartayDobla;
                } else if (sC == 9 && vrCD >= 3 && vrCD <= 6) {
                    return pideCartayDobla;
                } else if (sC == 9 && (vrCD < 3 || vrCD > 6)) {
                    return pideCarta;
                } else if (sC >= 12 && sC <= 16 && vrCD <= 6) {
                    return sePlanta;
                } else if (sC >= 12 && sC <= 16 && vrCD > 6) {
                    return pideCarta;
                } else {
                    return sePlanta;
                }

            } else if (cantdAs() == 1) {

                if ((sC == 13 || sC == 14) && (vrCD == 5 || vrCD == 6)) {
                    return pideCartayDobla;
                } else if ((sC == 13 || sC == 14) && (vrCD < 5 || vrCD > 6)) {
                    return pideCarta;
                } else if ((sC == 15 || sC == 16) && (vrCD >= 4 && vrCD <= 6)) {
                    return pideCartayDobla;
                } else if ((sC == 15 || sC == 16) && (vrCD < 4 || vrCD > 6)) {
                    return pideCarta;
                } else if (sC == 17 && (vrCD >= 3 && vrCD <= 6)) {
                    return pideCartayDobla;
                } else if (sC == 17 && (vrCD == 2 || vrCD > 6)) {
                    return pideCarta;
                } else if (sC == 18 && (vrCD >= 3 && vrCD <= 6)) {
                    return pideCartayDobla;
                } else if (sC == 18 && vrCD > 8) {
                    return pideCarta;
                } else if (sC == 18 && (vrCD == 2 || vrCD == 7 || vrCD == 8)) {
                    return sePlanta;
                } else {
                    return sePlanta;
                }
            }

        }
//        System.out.println("Cuenta de Cartas Final = " + cC);
        return sePlanta;
    }

    /**
     * metodo que suma los puntos de las cartas de juego del Dealer
     *
     * @return int
     */
    public int sumarCartas() {
        int miAcumulador = 0;
        for (Carta carta : cartasJ) {
            miAcumulador = miAcumulador + carta.getPeso();
            if (tengoUnAs() && miAcumulador > 21) {
                miAcumulador = miAcumulador - 10;
            }
        }
        return miAcumulador;
    }

    /**
     * metodo que valida si tengo una carta de valor A
     *
     * @return boolean
     */
    public boolean tengoUnAs() {
        return cartasJ.stream().anyMatch((carta) -> ("A".equals(carta.getValor())));
    }

    /**
     * metodo que me devuelve un entero con la cantidad de cartas de valor A
     *
     * @return int
     */
    public int cantdAs() {
        int cantAs = 0;
        for (Carta carta : cartasJ) {
            if (carta.getValor().equals("A")) {
                cantAs++;
            }
        }
        return cantAs;

    }

    /**
     * metodo que implementa el conteo de cartas, otro forma de tratar de
     * obtener alguna ventaja sobre el Dealer, aunque en este programa no se
     * esta implementando
     *
     * @param cartasAbiertas
     * @return int
     */
    public int contarCartas(ArrayList<Carta> cartasAbiertas) {
        int cuentaCartas = 0;
        for (Carta carta : cartasAbiertas) {
            switch (carta.getValor()) {
                case "10":
                case "J":
                case "Q":
                case "K":
                case "A":
                    cuentaCartas--;
                    break;
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                    cuentaCartas++;
                    break;
                case "7":
                case "8":
                case "9":
                    cuentaCartas = cuentaCartas + 0;
                    break;
                default:
                    cuentaCartas = cuentaCartas + 0;
                    break;
            }
//            System.out.println("Cuenta de Cartas Parcial = " + cuentaCartas);
        }
//        System.out.println("Cuenta de Cartas Final = " + cuentaCartas);
        return cuentaCartas;
    }

    /**
     * metodo que valida si hay dos cartas con el mismo valor
     *
     * @return boolean
     */
    public boolean tengoDobles() {
        return cartasJ.size() == 2 && cartasJ.get(0).getValor().equals(cartasJ.get(1).getValor());
    }

    /**
     * metodo que recibe el valor de la apuesta y lo resta a la cantidad de
     * fichas del jugador
     *
     * @param minima
     */
    public void apostar(int minima) {
        fichas = fichas - minima;
    }

}
