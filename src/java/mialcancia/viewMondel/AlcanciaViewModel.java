/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mialcancia.viewMondel;

import java.text.NumberFormat;
import java.util.List;
import mialcancia.controller.AlcanciaController;
import mialcancia.model.Moneda;
import mialcancia.model.MonedasData;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zul.Window;

/**
 *
 * @author jefe.programacion
 */
public class AlcanciaViewModel {

    private final static String srcImgMoneda = "/img/moneda_%s.fw.png";
    //Moneda seleccionada en combobox
    private Moneda denominacion;
    private final AlcanciaController alcancia = AlcanciaController.getInstance();

    @Wire
    Intbox cant_monedas;
    @Wire
    Combobox cmb_denomincaion;

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);

    }

    /**
     * Metodo para agregar monedas a la alcancia
     */
    @Command
    public void addMoneda() {
        if (denominacion == null) {
            Notification.show("Debe seleccionar la denominación", "error",
                    cmb_denomincaion, "after_start", 2000, false);
            return;
        }
        if (getInt(cant_monedas.getValue()) == 0) {
            Notification.show("Por favor ingrese la cantidad de monedas ", "error",
                    cant_monedas, "after_start", 2000, false);
            return;
        }
        alcancia.addMoneda(new Moneda(denominacion.getDenominacion(), cant_monedas.getValue()));
        cant_monedas.setValue(null);
        Notification.show("Monedas agregadas.");

    }

    /**
     * Metodo para mostrar la cantidad de monedas dentro de la alcancia.
     */
    @Command
    public void cantidadMonedas() {
        Messagebox.show(String.format("Cantidad de monedas en la alcancia: %s",
                NumberFormat.getNumberInstance().format(alcancia.getCantidadMonedas())),
                null, Messagebox.OK, Messagebox.INFORMATION);
    }

    /**
     * Metodo para mostrar la cantidad de dinero dentro de la alcancia.
     */
    @Command
    public void cantidadDinero() {
        Messagebox.show(String.format("Cantidad de dinero en la alcancia: $%s",
                NumberFormat.getNumberInstance().format(alcancia.getSaldo())),
                null, Messagebox.OK, Messagebox.INFORMATION);
    }

    /**
     * Este metodo muestra el detalle de monedas por denominacion.
     */
    @Command
    public void resumenDenominacion() {
        Window window = (Window) Executions.createComponents(
                "/detalleDenominacion.zul", null, null);
        window.doModal();
    }

    @DependsOn({"denominacion"})
    public String getImgMoneda() {
        if (denominacion == null) {
            return "/img/alcancia.png";
        }
        return String.format(srcImgMoneda, denominacion.getDenominacion());
    }

    public List<Moneda> getMonedas() {
        return MonedasData.getMonedas();
    }

    public Moneda getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(Moneda denominacion) {
        this.denominacion = denominacion;
    }

    private int getInt(Integer i) {
        return i == null ? 0 : i;
    }

}
