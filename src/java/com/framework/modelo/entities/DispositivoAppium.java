/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SQA
 */
@Entity
@Table(name = "dispositivosappium")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DispositivoAppium.findAll", query = "SELECT d FROM DispositivoAppium d")
    , @NamedQuery(name = "DispositivoAppium.findById", query = "SELECT d FROM DispositivoAppium d WHERE d.id = :id")
    , @NamedQuery(name = "DispositivoAppium.findByNombre", query = "SELECT d FROM DispositivoAppium d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "DispositivoAppium.findByDevice", query = "SELECT d FROM DispositivoAppium d WHERE d.device = :device")
    , @NamedQuery(name = "DispositivoAppium.findByPuertoURLAppium", query = "SELECT d FROM DispositivoAppium d WHERE d.puertoURLAppium = :puertoURLAppium")
    , @NamedQuery(name = "DispositivoAppium.findByVersionDispositivo", query = "SELECT d FROM DispositivoAppium d WHERE d.versionDispositivo = :versionDispositivo")
    , @NamedQuery(name = "DispositivoAppium.findByPlataformaDispositivo", query = "SELECT d FROM DispositivoAppium d WHERE d.plataformaDispositivo = :plataformaDispositivo")
    , @NamedQuery(name = "DispositivoAppium.findByAppPackage", query = "SELECT d FROM DispositivoAppium d WHERE d.appPackage = :appPackage")
    , @NamedQuery(name = "DispositivoAppium.findByAppActivity", query = "SELECT d FROM DispositivoAppium d WHERE d.appActivity = :appActivity")
    , @NamedQuery(name = "DispositivoAppium.findByUrlApp", query = "SELECT d FROM DispositivoAppium d WHERE d.urlApp = :urlApp")})
public class DispositivoAppium implements Serializable {

    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "device")
    private String device;
    @Size(max = 45)
    @Column(name = "puertoURLAppium")
    private String puertoURLAppium;
    @Size(max = 5)
    @Column(name = "versionDispositivo")
    private String versionDispositivo;
    @Size(max = 20)
    @Column(name = "plataformaDispositivo")
    private String plataformaDispositivo;
    @Size(max = 120)
    @Column(name = "appPackage")
    private String appPackage;
    @Size(max = 120)
    @Column(name = "appActivity")
    private String appActivity;
    @Size(max = 45)
    @Column(name = "urlApp")
    private String urlApp;
    @OneToMany(mappedBy = "iddispositivo")
    private Collection<Escenario> escenarioCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public DispositivoAppium() {
    }

    public DispositivoAppium(Integer id) {
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
        if (!(object instanceof DispositivoAppium)) {
            return false;
        }
        DispositivoAppium other = (DispositivoAppium) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.framework.modelo.entities.DispositivoAppium[ iddispositivo=" + id + " ]";
    }

    @XmlTransient
    public Collection<Escenario> getEscenarioCollection() {
        return escenarioCollection;
    }

    public void setEscenarioCollection(Collection<Escenario> escenarioCollection) {
        this.escenarioCollection = escenarioCollection;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getPuertoURLAppium() {
        return puertoURLAppium;
    }

    public void setPuertoURLAppium(String puertoURLAppium) {
        this.puertoURLAppium = puertoURLAppium;
    }

    public String getVersionDispositivo() {
        return versionDispositivo;
    }

    public void setVersionDispositivo(String versionDispositivo) {
        this.versionDispositivo = versionDispositivo;
    }

    public String getPlataformaDispositivo() {
        return plataformaDispositivo;
    }

    public void setPlataformaDispositivo(String plataformaDispositivo) {
        this.plataformaDispositivo = plataformaDispositivo;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    public String getUrlApp() {
        return urlApp;
    }

    public void setUrlApp(String urlApp) {
        this.urlApp = urlApp;
    }

}
