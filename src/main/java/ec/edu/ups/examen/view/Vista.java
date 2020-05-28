/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.examen.view;

import ec.edu.ups.examen.business.FacturaONLocal;
import ec.edu.ups.examen.entidades.Detalle;
import ec.edu.ups.examen.entidades.Factura;
import ec.edu.ups.examen.entidades.Producto;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vinicio
 */
@WebServlet(name = "Vista", urlPatterns = {"/Vista"})
public class Vista extends HttpServlet {
    @Inject
    private FacturaONLocal facturaON;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.getWriter().println("<h1>Ricardo Jara</h1>");
            Producto p = facturaON.buscarProductoById(1);
            System.out.println(p.getNombre());
            Factura f = new Factura();
            f.setCedula("0105452171");
            
            f.setNombrecli("Ricardo Jara");
            f.setFecha(new Date(10, 05, 200));
            f.setTotol(100);

            Detalle d = new Detalle();
            d.setIdproducto(p);
            d.setCantidad(10);
            d.setIdfectura(f);
            d.setTotal(new BigDecimal(100.5));
            
            response.getWriter().println("<h1>Ricardo Jara</h1>" +p.getNombre());
            ArrayList<Detalle> listaDetalle = new ArrayList<>();
            listaDetalle.add(d);
            f.setDetalleList(listaDetalle);
            facturaON.guardarFactura(f);
            
            response.getWriter().println("<h1>Ricardo Jara</h1>" +p.getNombre());
        } catch (Exception e) {
            response.getWriter().println(e.getMessage() +e.getLocalizedMessage());
            System.out.println(e.getMessage());
        }
    }

}
