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
import javax.persistence.Lob;
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

/**
 *
 * @author SQA
 */
@Entity
@Table(name = "escenarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Escenario.findAll", query = "SELECT e FROM Escenario e")
    , @NamedQuery(name = "Escenario.findById", query = "SELECT e FROM Escenario e WHERE e.id = :id")
    , @NamedQuery(name = "Escenario.findByNombreEscenario", query = "SELECT e FROM Escenario e WHERE e.nombreEscenario = :nombreEscenario")
    , @NamedQuery(name = "Escenario.findByFechaCreacion", query = "SELECT e FROM Escenario e WHERE e.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Escenario.findByEstadoEscenario", query = "SELECT e FROM Escenario e WHERE e.estadoEscenario = :estadoEscenario")})
public class Escenario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombreEscenario")
    private String nombreEscenario;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcionEscenario")
    private String descripcionEscenario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "estadoEscenario")
    private Integer estadoEscenario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEscenario", fetch = FetchType.LAZY)
    private List<Caso> casos;
    @JoinColumn(name = "idSuit", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Suit idSuit;
    @JoinColumn(name = "idAmbiente", referencedColumnName = "Id_Ambientes")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ambiente idAmbiente;
    @JoinColumn(name = "iddispositivo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DispositivoAppium iddispositivo;

    
    public Escenario() {
    }

    public Escenario(Integer id) {
        this.id = id;
    }

    public Escenario(Integer id, String nombreEscenario, String descripcionEscenario, Date fechaCreacion) {
        this.id = id;
        this.nombreEscenario = nombreEscenario;
        this.descripcionEscenario = descripcionEscenario;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEscenario() {
        return nombreEscenario;
    }

    public void setNombreEscenario(String nombreEscenario) {
        this.nombreEscenario = nombreEscenario;
    }

    public String getDescripcionEscenario() {
        return descripcionEscenario;
    }

    public void setDescripcionEscenario(String descripcionEscenario) {
        this.descripcionEscenario = descripcionEscenario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getEstadoEscenario() {
        return estadoEscenario;
    }

    public void setEstadoEscenario(Integer estadoEscenario) {
        this.estadoEscenario = estadoEscenario;
    }

    public List<Caso> getCasos() {
        return casos;
    }

    public void setCasos(List<Caso> casos) {
        this.casos = casos;
    }

    public Ambiente getIdAmbiente() {
        return idAmbiente;
    }

    public void setIdAmbiente(Ambiente idAmbiente) {
        this.idAmbiente = idAmbiente;
    }

    public Suit getIdSuit() {
        return idSuit;
    }

    public void setIdSuit(Suit idSuit) {
        this.idSuit = idSuit;
    }

    public DispositivoAppium getIddispositivo() {
        return iddispositivo;
    }

    public void setIddispositivo(DispositivoAppium iddispositivo) {
        this.iddispositivo = iddispositivo;
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
        if (!(object instanceof Escenario)) {
            return false;
        }
        Escenario other = (Escenario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.framework.modelo.entities.Escenario[ id=" + id + " ]";
    }
}
