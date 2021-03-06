/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.entities;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SQA-PRUEBA
 */
@Entity
@Table(name = "salida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salida.findAll", query = "SELECT s FROM Salida s")
    , @NamedQuery(name = "Salida.findById", query = "SELECT s FROM Salida s WHERE s.id = :id")
    , @NamedQuery(name = "Salida.findByNombre", query = "SELECT s FROM Salida s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Salida.findByTipo", query = "SELECT s FROM Salida s WHERE s.tipo = :tipo")
    , @NamedQuery(name = "Salida.findByOrigen", query = "SELECT s FROM Salida s WHERE s.origen = :origen")})
public class Salida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "origen")
    private String origen;
    @JoinColumn(name = "idmetodo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Metodo idmetodo;

    public Salida() {
    }

    public Salida(Integer id) {
        this.id = id;
    }

    public Salida(Integer id, String nombre, String tipo, String origen) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.origen = origen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Metodo getIdmetodo() {
        return idmetodo;
    }

    public void setIdmetodo(Metodo idmetodo) {
        this.idmetodo = idmetodo;
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
        if (!(object instanceof Salida)) {
            return false;
        }
        Salida other = (Salida) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.framework.modelo.entities.Salida{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", origen=" + origen + ", idmetodo=" + idmetodo + '}';
    }
}
