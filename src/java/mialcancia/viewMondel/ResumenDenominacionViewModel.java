/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mialcancia.viewMondel;

import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;
import mialcancia.controller.AlcanciaController;
import mialcancia.model.Moneda;
import mialcancia.model.MonedasData;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;

/**
 *
 * @author jefe.programacion
 */
public class ResumenDenominacionViewModel {

    private final AlcanciaController alcancia = AlcanciaController.getInstance();
    private List<Moneda> monedas;
    private NumberFormat nf = NumberFormat.getNumberInstance();
    @Wire
    Combobox filtro;

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
    }
 
    @Init
    public void init() {
        monedas = alcancia.getCantidadMonedasPorDenoinacion();
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public List<Moneda> getMonedas_cbx() {
        return MonedasData.getMonedas();
    }

    @Command
    @NotifyChange("monedas")
    public void filtrar(@BindingParam("denominacion") String denominacion) {
        if (denominacion.equals("-1")) {
            monedas = alcancia.getCantidadMonedasPorDenoinacion();
            filtro.setSelectedIndex(-1);
        }else{
            monedas = alcancia.getCantidadMonedasPorDenoinacion().stream().filter(m -> {
                return nf.format(m.getDenominacion()).equals(denominacion);
            }).collect(Collectors.toList());
        }

    }

}
