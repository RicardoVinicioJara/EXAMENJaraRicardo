/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.examen.DAO;

import ec.edu.ups.examen.entidades.Producto;
import ec.edu.ups.examen.entidades.Producto;
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
public class ProductoDAO {

    @PersistenceContext
    private EntityManager em;

    public ProductoDAO() {
    }
    
    
    public void insert(Producto producto) throws Exception {
        try {
            System.out.println("si creo que llega aca");
            em.persist(producto);
        } catch (Exception e) {
            throw new Exception("Erro ingreso Producto " + e.getMessage());
        }
    }

    public void delete(Producto producto) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(producto.getIdproducto()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Producto " +e.getMessage());
        }
    }

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Producto " +e.getMessage());
        }
    }
    
    public void update(Producto producto) throws Exception {
        try {
            em.merge(producto);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Producto " +e.getMessage());
        }
    }

    public Producto read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Producto.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Producto " +e.getMessage());
        }
    }

    public List<Producto> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Producto.findAll");
            List<Producto> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Producto " +e.getMessage());
        }

    }

    public Producto findByCedula(String cedula) throws Exception {
        try {
            String jpql = "SELECT P FROM Producto p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Producto.class);
            q.setParameter("cedula", cedula);

            return (Producto) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  cedula");
        }

    }

    public int maxId() throws Exception {
        try {
            String jpql = "SELECT P FROM Producto p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Producto.class);
            return (int) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Error MaxID", e.getCause());
        }
    }

}
