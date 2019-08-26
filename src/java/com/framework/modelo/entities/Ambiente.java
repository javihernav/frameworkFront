/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "ambientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ambiente.findAll", query = "SELECT a FROM Ambiente a")
    , @NamedQuery(name = "Ambiente.findByIdAmbientes", query = "SELECT a FROM Ambiente a WHERE a.idAmbientes = :idAmbientes")
    , @NamedQuery(name = "Ambiente.findByNombreAmbiente", query = "SELECT a FROM Ambiente a WHERE a.nombreAmbiente = :nombreAmbiente")})
public class Ambiente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Ambientes")
    private Integer idAmbientes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre_Ambiente")
    private String nombreAmbiente;
    @ManyToMany(mappedBy = "ambientes", fetch = FetchType.LAZY)
    private List<Accion> acciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAmbiente", fetch = FetchType.LAZY)
    private List<Escenario> escenarios;

    public Ambiente() {
    }

    public Ambiente(Integer idAmbientes) {
        this.idAmbientes = idAmbientes;
    }

    public Ambiente(Integer idAmbientes, String nombreAmbiente) {
        this.idAmbientes = idAmbientes;
        this.nombreAmbiente = nombreAmbiente;
    }

    public Integer getIdAmbientes() {
        return idAmbientes;
    }

    public void setIdAmbientes(Integer idAmbientes) {
        this.idAmbientes = idAmbientes;
    }

    public String getNombreAmbiente() {
        return nombreAmbiente;
    }

    public void setNombreAmbiente(String nombreAmbiente) {
        this.nombreAmbiente = nombreAmbiente;
    }

    @XmlTransient
    public List<Accion> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<Accion> acciones) {
        this.acciones = acciones;
    }

    @XmlTransient
    public List<Escenario> getEscenarios() {
        return escenarios;
    }

    public void setEscenarios(List<Escenario> escenarios) {
        this.escenarios = escenarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAmbientes != null ? idAmbientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ambiente)) {
            return false;
        }
        Ambiente other = (Ambiente) object;
        if ((this.idAmbientes == null && other.idAmbientes != null) || (this.idAmbientes != null && !this.idAmbientes.equals(other.idAmbientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.framework.modelo.entities.Ambiente[ idAmbientes=" + idAmbientes + " ]";
    }
    
}
