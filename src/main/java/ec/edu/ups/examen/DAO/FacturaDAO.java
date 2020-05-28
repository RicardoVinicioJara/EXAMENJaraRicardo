/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.examen.DAO;

import ec.edu.ups.examen.entidades.Factura;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ricardo
 */
@Stateless
public class FacturaDAO {

    @PersistenceContext
    private EntityManager em;

    public FacturaDAO() {
    }
    
    
    public void insert(Factura factura) throws Exception {
        try {
            System.out.println("si creo que llega aca");
            em.persist(factura);
        } catch (Exception e) {
            throw new Exception("Erro ingreso Factura " + e.getMessage());
        }
    }

    public void delete(Factura factura) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(factura.getIdfectura()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Factura " +e.getMessage());
        }
    }

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Factura " +e.getMessage());
        }
    }
    
    public void update(Factura factura) throws Exception {
        try {
            em.merge(factura);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Factura " +e.getMessage());
        }
    }

    public Factura read(int id) throws Exception {
        try {
            return em.find(Factura.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Factura " +e.getMessage());
        }
    }

    public List<Factura> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Factura.findAll");
            List<Factura> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Factura " +e.getMessage());
        }

    }

    public Factura findByCedula(String cedula) throws Exception {
        try {
            String jpql = "SELECT P FROM Factura p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Factura.class);
            q.setParameter("cedula", cedula);

            return (Factura) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  cedula");
        }

    }

    public int maxId() throws Exception {
        try {
            String jpql = "SELECT P FROM Factura p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Factura.class);
            return (int) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Error MaxID", e.getCause());
        }
    }

}
