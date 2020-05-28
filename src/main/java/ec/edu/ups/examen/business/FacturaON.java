/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.examen.business;

import ec.edu.ups.examen.DAO.FacturaDAO;
import ec.edu.ups.examen.DAO.ProductoDAO;
import ec.edu.ups.examen.entidades.Detalle;
import ec.edu.ups.examen.entidades.Factura;
import ec.edu.ups.examen.entidades.Producto;
import java.math.BigDecimal;
import java.util.Date;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author vinic
 */
@Stateless
public class FacturaON implements  FacturaONLocal {

    @Inject
    FacturaDAO facturaDAO;
    
    @Inject
    ProductoDAO productoDAO;
    

    public FacturaON() {
    }

    @Override
    public boolean guardarFactura(Factura factura) throws Exception {
        if (validarCedula(factura.getCedula())) {
            try {
                factura.setFecha(new Date());
                List<Detalle> listaDetalles = factura.getDetalleLis();
                double suma = 0;
                for (Detalle d : listaDetalles) {
                    double pre = d.getIdproducto().getPreciouni().doubleValue();
                    d.setTotal(new BigDecimal(pre * d.getCantidad()));
                    suma += d.getTotal().doubleValue();
                }
               factura.setTotol((int)(suma));
                
                facturaDAO.insert(factura);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        } else {
            throw new Exception("Cedula Incorrecta");
        }

        return true;
    }

    @Override
    public List<Factura> listarFacutura() throws Exception {
        try {
            System.out.println("this is site");
            return facturaDAO.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Producto> listarProductos() throws Exception {
        try {
            return productoDAO.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    
    @Override
    public Producto buscarProductoById(int id) throws Exception {
        try {
            System.out.println("this is site");
            return productoDAO.read(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    
    @Override
    public boolean validarCedula(String ced) {
        boolean verdadera = false;
        int num = 0;
        int ope = 0;
        int suma = 0;
        for (int cont = 0; cont < ced.length(); cont++) {
            num = Integer.valueOf(ced.substring(cont, cont + 1));
            if (cont == ced.length() - 1) {
                break;
            }
            if (cont % 2 != 0 && cont > 0) {
                suma = suma + num;
            } else {
                ope = num * 2;
                if (ope > 9) {
                    ope = ope - 9;
                }
                suma = suma + ope;
            }
        }
        if (suma != 0) {
            suma = suma % 10;
            if (suma == 0) {
                if (suma == num) {
                    verdadera = true;
                }
            } else {
                ope = 10 - suma;
                if (ope == num) {
                    verdadera = true;
                }
            }
        } else {
            verdadera = false;
        }
        return verdadera;
    }

}
