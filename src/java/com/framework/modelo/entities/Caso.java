/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.entities;

import java.io.Serializable;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SQA
 */
@Entity
@Table(name = "casos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caso.findAll", query = "SELECT c FROM Caso c")
    , @NamedQuery(name = "Caso.findById", query = "SELECT c FROM Caso c WHERE c.id = :id")
    , @NamedQuery(name = "Caso.findByNombreCaso", query = "SELECT c FROM Caso c WHERE c.nombreCaso = :nombreCaso")
    , @NamedQuery(name = "Caso.findByFechaCreacion", query = "SELECT c FROM Caso c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Caso.findByEstadoCaso", query = "SELECT c FROM Caso c WHERE c.estadoCaso = :estadoCaso")})
public class Caso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "nombreCaso")
    private String nombreCaso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "estadoCaso")
    private Integer estadoCaso;
    @JoinColumn(name = "IdEscenario", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Escenario idEscenario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCaso", fetch = FetchType.LAZY)
    private List<Paso> pasos;

    public Caso() {
    }

    public Caso(Integer id) {
        this.id = id;
    }

    public Caso(Integer id, String nombreCaso, Date fechaCreacion) {
        this.id = id;
        this.nombreCaso = nombreCaso;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getEstadoCaso() {
        return estadoCaso;
    }

    public void setEstadoCaso(Integer estadoCaso) {
        this.estadoCaso = estadoCaso;
    }

    public Escenario getIdEscenario() {
        return idEscenario;
    }

    public void setIdEscenario(Escenario idEscenario) {
        this.idEscenario = idEscenario;
    }

    @XmlTransient
    public List<Paso> getPasos() {
        return pasos;
    }

    public void setPasos(List<Paso> pasos) {
        this.pasos = pasos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caso)) {
            return false;
        }
        Caso other = (Caso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.framework.modelo.entities.Caso[ id=" + id + " ]";
    }
    
}
