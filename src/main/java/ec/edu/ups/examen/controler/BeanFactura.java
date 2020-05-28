/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.examen.controler;

import ec.edu.ups.examen.business.FacturaONLocal;
import ec.edu.ups.examen.entidades.Detalle;
import ec.edu.ups.examen.entidades.Factura;
import ec.edu.ups.examen.entidades.Producto;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Vinicio
 */
;

@ManagedBean
@ViewScoped
public class BeanFactura {

    private Factura factura;
    private Detalle detalle;
    private Producto producto;
    private List<Detalle> listaDetalle;
    private int cantidad;

    @Inject
    private FacturaONLocal facturaON;

    @PostConstruct
    public void init() {
        factura = new Factura();
        detalle = new Detalle();
        producto = new Producto();
    }

    public List<Detalle> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<Detalle> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public FacturaONLocal getFacturaON() {
        return facturaON;
    }

    public void setFacturaON(FacturaONLocal facturaON) {
        this.facturaON = facturaON;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String buscarProducto() {

        try {
            producto = facturaON.buscarProductoById(producto.getIdproducto());
        } catch (Exception ex) {
            Logger.getLogger(BeanFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String addDetalle() {
        System.out.println(cantidad +" ccccccccc");
        Detalle d = new Detalle();
        d.setIdfectura(factura);
        d.setCantidad(cantidad);
        d.setIdproducto(producto);
        factura.addDetalle(d);
        return null;
    }
    
    public String guardar() {

        try {
            System.out.println(factura.getCedula()+"cedula");
            factura.setFecha(new Date());
            factura.setTotol(100);
            facturaON.guardarFactura(factura);
           
        } catch (Exception ex) {
            System.out.println(ex.getMessage()+">>>>>>>>>>>>>>>>>>>");
        }
        return null;
    }

}
