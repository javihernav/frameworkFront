/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.entities;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SQA
 */
@Entity
@Table(name = "pasos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paso.findAll", query = "SELECT p FROM Paso p")
    , @NamedQuery(name = "Paso.findById", query = "SELECT p FROM Paso p WHERE p.id = :id")
    , @NamedQuery(name = "Paso.findByActionStep", query = "SELECT p FROM Paso p WHERE p.actionStep = :actionStep")
    , @NamedQuery(name = "Paso.findByNavegador", query = "SELECT p FROM Paso p WHERE p.navegador = :navegador")
    , @NamedQuery(name = "Paso.findByTypeStep", query = "SELECT p FROM Paso p WHERE p.typeStep = :typeStep")
    , @NamedQuery(name = "Paso.findByValueStep", query = "SELECT p FROM Paso p WHERE p.valueStep = :valueStep")
    , @NamedQuery(name = "Paso.findByParameterStep", query = "SELECT p FROM Paso p WHERE p.parameterStep = :parameterStep")
    , @NamedQuery(name = "Paso.findByCorXStep", query = "SELECT p FROM Paso p WHERE p.corXStep = :corXStep")
    , @NamedQuery(name = "Paso.findByCorYStep", query = "SELECT p FROM Paso p WHERE p.corYStep = :corYStep")
    , @NamedQuery(name = "Paso.findByIdCaso", query = "SELECT p FROM Paso p WHERE p.idCaso = :idCaso")
    , @NamedQuery(name = "Paso.findByOrderstep", query = "SELECT p FROM Paso p WHERE p.orderstep = :orderstep")})
public class Paso implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 90)
    @Column(name = "actionStep")
    private String actionStep;
    @Size(max = 20)
    @Column(name = "navegador")
    private String navegador;
    @Size(max = 80)
    @Column(name = "typeStep")
    private String typeStep;
    @Size(max = 200)
    @Column(name = "valueStep")
    private String valueStep;
    @Size(max = 100)
    @Column(name = "parameterStep")
    private String parameterStep;
    @Basic(optional = false)


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "corXStep")
    private Integer corXStep;
    @Column(name = "corYStep")
    private Integer corYStep;
    @Column(name = "orderstep")
    private Integer orderstep;
    @JoinColumn(name = "idCaso", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caso idCaso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaso", fetch = FetchType.LAZY)
    private List<Metodo> metodos;
    

    public Paso() {
    }

    public Paso(Integer id) {
        this.id = id;
    }

    public Paso(Integer id, String actionStep) {
        this.id = id;
        this.actionStep = actionStep;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getCorXStep() {
        return corXStep;
    }

    public void setCorXStep(Integer corXStep) {
        this.corXStep = corXStep;
    }

    public Integer getCorYStep() {
        return corYStep;
    }

    public void setCorYStep(Integer corYStep) {
        this.corYStep = corYStep;
    }

    public Caso getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Caso idCaso) {
        this.idCaso = idCaso;
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
        if (!(object instanceof Paso)) {
            return false;
        }
        Paso other = (Paso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.framework.modelo.entities.Paso[ id=" + id + " ]";
    }

    public String getActionStep() {
        return actionStep;
    }

    public void setActionStep(String actionStep) {
        this.actionStep = actionStep;
    }

    public String getNavegador() {
        return navegador;
    }

    public void setNavegador(String navegador) {
        this.navegador = navegador;
    }

    public String getTypeStep() {
        return typeStep;
    }

    public void setTypeStep(String typeStep) {
        this.typeStep = typeStep;
    }

    public String getValueStep() {
        return valueStep;
    }

    public void setValueStep(String valueStep) {
        this.valueStep = valueStep;
    }

    public String getParameterStep() {
        return parameterStep;
    }

    public void setParameterStep(String parameterStep) {
        this.parameterStep = parameterStep;
    }

    public Integer getOrderstep() {
        return orderstep;
    }

    public void setOrderstep(Integer orderstep) {
        this.orderstep = orderstep;
    }

    public List<Metodo> getMetodos() {
        return metodos;
    }

    public void setMetodos(List<Metodo> metodos) {
        this.metodos = metodos;
    }

    
}
