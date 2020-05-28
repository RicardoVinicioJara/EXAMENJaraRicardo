/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.examen.DAO;

import ec.edu.ups.examen.entidades.Detalle;
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
public class DetalleDAO1 {

    @PersistenceContext
    private EntityManager em;

    public DetalleDAO1() {
    }
    
    
    public void insert(Detalle detalle) throws Exception {
        try {
            System.out.println("si creo que llega aca");
            em.persist(detalle);
        } catch (Exception e) {
            throw new Exception("Erro ingreso Detalle " + e.getMessage());
        }
    }

    public void delete(Detalle detalle) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(detalle.getIddetalle()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Detalle " +e.getMessage());
        }
    }

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Detalle " +e.getMessage());
        }
    }
    
    public void update(Detalle detalle) throws Exception {
        try {
            em.merge(detalle);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Detalle " +e.getMessage());
        }
    }

    public Detalle read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Detalle.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Detalle " +e.getMessage());
        }
    }

    public List<Detalle> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Detalle.findAll");
            List<Detalle> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Detalle " +e.getMessage());
        }

    }

    public Detalle findByCedula(String cedula) throws Exception {
        try {
            String jpql = "SELECT P FROM Detalle p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Detalle.class);
            q.setParameter("cedula", cedula);

            return (Detalle) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  cedula");
        }

    }

    public int maxId() throws Exception {
        try {
            String jpql = "SELECT P FROM Detalle p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Detalle.class);
            return (int) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Error MaxID", e.getCause());
        }
    }

}
