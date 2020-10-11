/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mialcancia.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jefe.programacion
 */
public class MonedasData {
    
    private static final List<Moneda> monedas = new ArrayList<>();
    
    static {
        monedas.add(new Moneda(50,0));
        monedas.add(new Moneda(100,0));
        monedas.add(new Moneda(200,0));
        monedas.add(new Moneda(500,0));
        monedas.add(new Moneda(1000,0));
    }

    public static List<Moneda> getMonedas() {
        return monedas;
    }
    
}
