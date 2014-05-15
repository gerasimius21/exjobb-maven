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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "lands")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lands.findAll", query = "SELECT l FROM Lands l"),
    @NamedQuery(name = "Lands.findByIdlands", query = "SELECT l FROM Lands l WHERE l.idlands = :idlands"),
    @NamedQuery(name = "Lands.findByLandname", query = "SELECT l FROM Lands l WHERE l.landname = :landname")})
public class Lands implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlands")
    private Integer idlands;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "landname")
    private String landname;
    @JoinColumn(name = "continentid", referencedColumnName = "idcontinents")
    @ManyToOne
    private Continents continentid;
    @OneToMany(mappedBy = "landid")
    private Collection<Leagues> leaguesCollection;

    public Lands() {
    }

    public Lands(Integer idlands) {
        this.idlands = idlands;
    }

    public Lands(Integer idlands, String landname) {
        this.idlands = idlands;
        this.landname = landname;
    }

    public Integer getIdlands() {
        return idlands;
    }

    public void setIdlands(Integer idlands) {
        this.idlands = idlands;
    }

    public String getLandname() {
        return landname;
    }

    public void setLandname(String landname) {
        this.landname = landname;
    }

    public Continents getContinentid() {
        return continentid;
    }

    public void setContinentid(Continents continentid) {
        this.continentid = continentid;
    }

    @XmlTransient
    public Collection<Leagues> getLeaguesCollection() {
        return leaguesCollection;
    }

    public void setLeaguesCollection(Collection<Leagues> leaguesCollection) {
        this.leaguesCollection = leaguesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlands != null ? idlands.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lands)) {
            return false;
        }
        Lands other = (Lands) object;
        if ((this.idlands == null && other.idlands != null) || (this.idlands != null && !this.idlands.equals(other.idlands))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Lands[ idlands=" + idlands + " ]";
    }
    
}
