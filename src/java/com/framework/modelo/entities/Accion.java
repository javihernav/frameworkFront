/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SQA
 */
@Entity
@Table(name = "acciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accion.findAll", query = "SELECT a FROM Accion a")
    , @NamedQuery(name = "Accion.findById", query = "SELECT a FROM Accion a WHERE a.id = :id")
    , @NamedQuery(name = "Accion.findByNombreAccion", query = "SELECT a FROM Accion a WHERE a.nombreAccion = :nombreAccion")})
public class Accion implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nombre_Accion")
    private String nombreAccion;
    @JoinTable(name = "acciones_ambiente", joinColumns = {
        @JoinColumn(name = "Id_Acciones", referencedColumnName = "Id")}, inverseJoinColumns = {
        @JoinColumn(name = "Id_Ambiente", referencedColumnName = "Id_Ambientes")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Ambiente> ambientes;
    @JoinColumn(name = "grupo_acciones_Id", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GrupoAccion grupoaccionesId;

    public Accion() {
    }

    public Accion(Integer id) {
        this.id = id;
    }

    public Accion(Integer id, String nombreAccion) {
        this.id = id;
        this.nombreAccion = nombreAccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreAccion() {
        return nombreAccion;
    }

    public void setNombreAccion(String nombreAccion) {
        this.nombreAccion = nombreAccion;
    }

    @XmlTransient
    public List<Ambiente> getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(List<Ambiente> ambientes) {
        this.ambientes = ambientes;
    }

    public GrupoAccion getGrupoaccionesId() {
        return grupoaccionesId;
    }

    public void setGrupoaccionesId(GrupoAccion grupoaccionesId) {
        this.grupoaccionesId = grupoaccionesId;
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
        if (!(object instanceof Accion)) {
            return false;
        }
        Accion other = (Accion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.framework.modelo.entities.Accion[ id=" + id + " ]";
    }

    @Override
    public int compareTo(Object ac) {
                int resultado;
        resultado = this.nombreAccion.compareTo(((Accion)ac).nombreAccion);
        if (resultado < 0) {
            resultado = -1;
        } else if (resultado > 0) {
            resultado = 1;
        }
        return resultado;
    }
    
}
