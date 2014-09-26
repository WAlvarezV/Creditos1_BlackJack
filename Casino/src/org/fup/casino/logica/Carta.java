/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fup.casino.logica;

/**
 * Esta clase representa a una carta con atributos como valor, pinta y si se
 * encuentra tapada o destapada
 *
 * @author WilliamEduardo
 */
public class Carta {

    /**
     * declaración del atributo tapada de tipo boolean que indica si la carta
     * esta tapada o destapada
     */
    private boolean tapada;
    /**
     * declaración del atributo valor el cual puede ser una letra o numero
     */
    private String valor;
    /**
     * declaración del atributo pinta el cual es un simbolo que representara a
     * los diamantes, corazones, picas y treboles
     */
    private String pinta;

    /**
     * Constructor de la clase
     *
     * @param valor
     * @param pinta
     */
    public Carta(String valor, String pinta) {
        this.valor = valor;
        this.pinta = pinta;
        this.tapada = true;
    }

    public Carta() {
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPinta() {
        return pinta;
    }

    public void setPinta(String pinta) {
        this.pinta = pinta;
    }

    public boolean isTapada() {
        return tapada;
    }

    public void setTapada(boolean tapada) {
        this.tapada = tapada;
    }

    /**
     * método que retorna un entero que corresponde al valor en puntos de la
     * carta
     *
     * @return int
     */
    public int getPeso() {
        if (null != valor) {
            switch (valor) {
                case "J":
                case "Q":
                case "K":
                    return 10;
                case "A":
                    return 11;
                default:
                    return Integer.parseInt(valor);
            }
        }
        return -100;
    }
}
