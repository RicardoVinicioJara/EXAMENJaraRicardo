/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.examen.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Vinicio
 */
@Entity
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByIdfectura", query = "SELECT f FROM Factura f WHERE f.idfectura = :idfectura"),
    @NamedQuery(name = "Factura.findByFecha", query = "SELECT f FROM Factura f WHERE f.fecha = :fecha"),
    @NamedQuery(name = "Factura.findByTotol", query = "SELECT f FROM Factura f WHERE f.totol = :totol"),
    @NamedQuery(name = "Factura.findByCedula", query = "SELECT f FROM Factura f WHERE f.cedula = :cedula"),
    @NamedQuery(name = "Factura.findByNombrecli", query = "SELECT f FROM Factura f WHERE f.nombrecli = :nombrecli")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfectura")
    private Integer idfectura;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "totol")
    private Integer totol;
    @Column(name = "cedula")
    private String cedula;
    @Column(name = "nombrecli")
    private String nombrecli;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfectura", fetch = FetchType.EAGER)
    private List<Detalle> detalleCollection;

    public Factura() {
    }

    public Factura(Integer idfectura) {
        this.idfectura = idfectura;
    }

    public Integer getIdfectura() {
        return idfectura;
    }

    public void setIdfectura(Integer idfectura) {
        this.idfectura = idfectura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getTotol() {
        return totol;
    }

    public void setTotol(Integer totol) {
        this.totol = totol;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombrecli() {
        return nombrecli;
    }

    public void setNombrecli(String nombrecli) {
        this.nombrecli = nombrecli;
    }

    @XmlTransient
    public List<Detalle> getDetalleLis() {
        return detalleCollection;
    }

    public void setDetalleList(List<Detalle> detalleCollection) {
        this.detalleCollection = detalleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfectura != null ? idfectura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.idfectura == null && other.idfectura != null) || (this.idfectura != null && !this.idfectura.equals(other.idfectura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ups.examen.entidades.Factura[ idfectura=" + idfectura + " ]";
    }
    
     public void addDetalle(Detalle d){
        if(detalleCollection == null)
            detalleCollection = new ArrayList<>();
        detalleCollection.add(d);
    }
    
}
