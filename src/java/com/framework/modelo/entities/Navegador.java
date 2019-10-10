/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SQA
 */
@Entity
@Table(name = "navegadores", catalog = "bd_framework_sqa", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Navegador.findAll", query = "SELECT n FROM Navegador n")
    , @NamedQuery(name = "Navegador.findById", query = "SELECT n FROM Navegador n WHERE n.id = :id")
    , @NamedQuery(name = "Navegador.findByNombreNavegador", query = "SELECT n FROM Navegador n WHERE n.nombreNavegador = :nombreNavegador")})
public class Navegador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Size(max = 30)
    @Column(name = "nombreNavegador")
    private String nombreNavegador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estadoNavegador")
    private int estadoNavegador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;


    public Navegador() {
    }

    public Navegador(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Navegador)) {
            return false;
        }
        Navegador other = (Navegador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.framework.modelo.entities.Navegador[ id=" + id + " ]";
    }

    public String getNombreNavegador() {
        return nombreNavegador;
    }

    public void setNombreNavegador(String nombreNavegador) {
        this.nombreNavegador = nombreNavegador;
    }

    public int getEstadoNavegador() {
        return estadoNavegador;
    }

    public void setEstadoNavegador(int estadoNavegador) {
        this.estadoNavegador = estadoNavegador;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
}
