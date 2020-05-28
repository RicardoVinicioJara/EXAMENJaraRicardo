/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.examen.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vinicio
 */
@Entity
@Table(name = "detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalle.findAll", query = "SELECT d FROM Detalle d"),
    @NamedQuery(name = "Detalle.findByIddetalle", query = "SELECT d FROM Detalle d WHERE d.iddetalle = :iddetalle"),
    @NamedQuery(name = "Detalle.findByCantidad", query = "SELECT d FROM Detalle d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalle.findByTotal", query = "SELECT d FROM Detalle d WHERE d.total = :total")})
public class Detalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalle")
    private Integer iddetalle;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private BigDecimal total;
    @JoinColumn(name = "idfectura", referencedColumnName = "idfectura")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Factura idfectura;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Producto idproducto;

    public Detalle() {
    }

    public Detalle(Integer iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Integer getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Integer iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Factura getIdfectura() {
        return idfectura;
    }

    public void setIdfectura(Factura idfectura) {
        this.idfectura = idfectura;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalle != null ? iddetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalle)) {
            return false;
        }
        Detalle other = (Detalle) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ups.examen.entidades.Detalle[ iddetalle=" + iddetalle + " ]";
    }
    
}
