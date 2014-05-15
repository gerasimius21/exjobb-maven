/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gerasim
 */
@Entity
@Table(name = "continents")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Continents.findAll", query = "SELECT c FROM Continents c"),
    @NamedQuery(name = "Continents.findByIdcontinents", query = "SELECT c FROM Continents c WHERE c.idcontinents = :idcontinents"),
    @NamedQuery(name = "Continents.findByContinentname", query = "SELECT c FROM Continents c WHERE c.continentname = :continentname")})
public class Continents implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontinents")
    private Integer idcontinents;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "continentname")
    private String continentname;
    @OneToMany(mappedBy = "continentid")
    private Collection<Lands> landsCollection;

    public Continents() {
    }

    public Continents(Integer idcontinents) {
        this.idcontinents = idcontinents;
    }

    public Continents(Integer idcontinents, String continentname) {
        this.idcontinents = idcontinents;
        this.continentname = continentname;
    }

    public Integer getIdcontinents() {
        return idcontinents;
    }

    public void setIdcontinents(Integer idcontinents) {
        this.idcontinents = idcontinents;
    }

    public String getContinentname() {
        return continentname;
    }

    public void setContinentname(String continentname) {
        this.continentname = continentname;
    }

    @XmlTransient
    public Collection<Lands> getLandsCollection() {
        return landsCollection;
    }

    public void setLandsCollection(Collection<Lands> landsCollection) {
        this.landsCollection = landsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontinents != null ? idcontinents.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Continents)) {
            return false;
        }
        Continents other = (Continents) object;
        if ((this.idcontinents == null && other.idcontinents != null) || (this.idcontinents != null && !this.idcontinents.equals(other.idcontinents))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Continents[ idcontinents=" + idcontinents + " ]";
    }
    
}
