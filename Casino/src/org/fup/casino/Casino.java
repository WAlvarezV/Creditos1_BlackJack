/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fup.casino;

import org.fup.casino.logica.Dealer;
import org.fup.casino.logica.MesaJuego;

/**
 *
 * @author William Eduardo Alvarez V
 */
public class Casino {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Dealer d = new Dealer();
        MesaJuego miMesa = new MesaJuego(d);
        miMesa.jugarPardida(3);
    }
}
