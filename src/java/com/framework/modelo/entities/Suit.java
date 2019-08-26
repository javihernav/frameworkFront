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
@Table(name = "suits")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suit.findAll", query = "SELECT s FROM Suit s")
    , @NamedQuery(name = "Suit.findById", query = "SELECT s FROM Suit s WHERE s.id = :id")
    , @NamedQuery(name = "Suit.findByNombreSuit", query = "SELECT s FROM Suit s WHERE s.nombreSuit = :nombreSuit")
    , @NamedQuery(name = "Suit.findByDescripcionSuit", query = "SELECT s FROM Suit s WHERE s.descripcionSuit = :descripcionSuit")
    , @NamedQuery(name = "Suit.findByFechaCreacion", query = "SELECT s FROM Suit s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Suit.findByEstadoSuit", query = "SELECT s FROM Suit s WHERE s.estadoSuit = :estadoSuit")
    , @NamedQuery(name = "Suit.findPermisos", query= "SELECT s FROM Suit s JOIN Usuario u WHERE u.id= :id")})

public class Suit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombreSuit")
    private String nombreSuit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "descripcionSuit")
    private String descripcionSuit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estadoSuit")
    private int estadoSuit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSuit", fetch = FetchType.LAZY)
    private List<Escenario> escenarios;
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idUsuario;

    public Suit() {
    }

    public Suit(Integer id) {
        this.id = id;
    }

    public Suit(Integer id, String nombreSuit, String descripcionSuit, Date fechaCreacion, int estadoSuit) {
        this.id = id;
        this.nombreSuit = nombreSuit;
        this.descripcionSuit = descripcionSuit;
        this.fechaCreacion = fechaCreacion;
        this.estadoSuit = estadoSuit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreSuit() {
        return nombreSuit;
    }

    public void setNombreSuit(String nombreSuit) {
        this.nombreSuit = nombreSuit;
    }

    public String getDescripcionSuit() {
        return descripcionSuit;
    }

    public void setDescripcionSuit(String descripcionSuit) {
        this.descripcionSuit = descripcionSuit;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getEstadoSuit() {
        return estadoSuit;
    }

    public void setEstadoSuit(int estadoSuit) {
        this.estadoSuit = estadoSuit;
    }

    @XmlTransient
    public List<Escenario> getEscenarios() {
        return escenarios;
    }

    public void setEscenarios(List<Escenario> escenarios) {
        this.escenarios = escenarios;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof Suit)) {
            return false;
        }
        Suit other = (Suit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.framework.modelo.entities.Suit[ id=" + id + " ]";
    }
    
}
