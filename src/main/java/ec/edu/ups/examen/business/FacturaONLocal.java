/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.examen.business;


import ec.edu.ups.examen.entidades.Factura;
import ec.edu.ups.examen.entidades.Detalle;
import ec.edu.ups.examen.entidades.Producto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vinic
 */
@Local
public interface FacturaONLocal {
    
    public boolean guardarFactura(Factura fatura) throws Exception;

    public List<Factura> listarFacutura() throws Exception;

    public List<Producto> listarProductos() throws Exception;
    
    public Producto buscarProductoById(int id) throws Exception;

    public boolean validarCedula(String ced) throws Exception;

}
