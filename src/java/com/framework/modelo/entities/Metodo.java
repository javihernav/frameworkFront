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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "metodo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Metodo.findAll", query = "SELECT m FROM Metodo m")
    , @NamedQuery(name = "Metodo.findById", query = "SELECT m FROM Metodo m WHERE m.id = :id")
    , @NamedQuery(name = "Metodo.findByNombre", query = "SELECT m FROM Metodo m WHERE m.nombre = :nombre")})
public class Metodo implements Serializable {

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
    @JoinColumn(name = "idPaso", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paso idPaso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmetodo", fetch = FetchType.LAZY)
    private List<Parametro> parametros;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "targetnamespace")
    private String targetnamespace;
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "valoresperado")
    private String valoresperado;
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "contenttype")
    private String contenttype;
    public Metodo() {
    }

    public Metodo(Integer id) {
        this.id = id;
    }

    public Metodo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public List<Parametro> getParametro() {
        return parametros;
    }

    public void setParametro(List<Parametro> parametro) {
        this.parametros = parametro;
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
        if (!(object instanceof Metodo)) {
            return false;
        }
        Metodo other = (Metodo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "com.framework.modelo.entities.Metodo[ id=" + id + " ]";
//    }

    @Override
    public String toString() {
        return "com.framework.modelo.entities.Metodo[" + "id=" + id + ", nombre=" + nombre + ", idPaso=" + idPaso + ", parametros=" + parametros + "]";
    }

    public Paso getIdPaso() {
        return idPaso;
    }

    public void setIdPaso(Paso idPaso) {
        this.idPaso = idPaso;
    }

    public String getTargetnamespace() {
        return targetnamespace;
    }

    public void setTargetnamespace(String targetnamespace) {
        this.targetnamespace = targetnamespace;
    }

    public String getValoresperado() {
        return valoresperado;
    }

    public void setValoresperado(String valoresperado) {
        this.valoresperado = valoresperado;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }
    
}
