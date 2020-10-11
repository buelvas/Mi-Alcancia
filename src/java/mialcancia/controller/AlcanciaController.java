/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mialcancia.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import mialcancia.model.Moneda;

/**
 *
 * @author jefe.programacion
 */
public class AlcanciaController { 

    private final List<Moneda> monedas = new ArrayList<>();
    
    private static final AlcanciaController ALCANCIA_CONTROLLER = new AlcanciaController();
    
    private AlcanciaController(){

    }
    
    public static AlcanciaController getInstance(){
        return ALCANCIA_CONTROLLER;
    }
    
  
    public void addMoneda(Moneda m) {
        monedas.add(m);
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    /**
     * Devuelve la cantidad de dinero en la alcancia.
     */
    public int getSaldo() {
        return monedas.stream().mapToInt(m -> m.getSaldo()).sum();
    }

    /**
     * Devuelve la cantidad de monedas en la alcancia.
     */
    public int getCantidadMonedas() {
        return monedas.stream().mapToInt(m -> m.getCantidad()).sum();
    }
    /**
     * Devuelve la suma de monedas por denominacion en la alcancia.
     */
    public List<Moneda> getCantidadMonedasPorDenoinacion() {
        List<Moneda> list = new ArrayList<>();
        monedas.stream().collect(Collectors.groupingBy(Moneda::getDenominacion, Collectors.summingInt(Moneda::getCantidad))).forEach((k,v)->{
            list.add(new Moneda(k, v));
        });
        list.sort(Comparator.comparing(Moneda::getDenominacion));
        return list;
    }

}
