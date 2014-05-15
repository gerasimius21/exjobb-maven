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
@Table(name = "leagues")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Leagues.findAll", query = "SELECT l FROM Leagues l"),
    @NamedQuery(name = "Leagues.findByIdleagues", query = "SELECT l FROM Leagues l WHERE l.idleagues = :idleagues"),
    @NamedQuery(name = "Leagues.findByLeaguename", query = "SELECT l FROM Leagues l WHERE l.leaguename = :leaguename")})
public class Leagues implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idleagues")
    private Integer idleagues;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "leaguename")
    private String leaguename;
    @JoinColumn(name = "landid", referencedColumnName = "idlands")
    @ManyToOne
    private Lands landid;
    @OneToMany(mappedBy = "leagueid")
    private Collection<Clubs> clubsCollection;

    public Leagues() {
    }

    public Leagues(Integer idleagues) {
        this.idleagues = idleagues;
    }

    public Leagues(Integer idleagues, String leaguename) {
        this.idleagues = idleagues;
        this.leaguename = leaguename;
    }

    public Integer getIdleagues() {
        return idleagues;
    }

    public void setIdleagues(Integer idleagues) {
        this.idleagues = idleagues;
    }

    public String getLeaguename() {
        return leaguename;
    }

    public void setLeaguename(String leaguename) {
        this.leaguename = leaguename;
    }

    public Lands getLandid() {
        return landid;
    }

    public void setLandid(Lands landid) {
        this.landid = landid;
    }

    @XmlTransient
    public Collection<Clubs> getClubsCollection() {
        return clubsCollection;
    }

    public void setClubsCollection(Collection<Clubs> clubsCollection) {
        this.clubsCollection = clubsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idleagues != null ? idleagues.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leagues)) {
            return false;
        }
        Leagues other = (Leagues) object;
        if ((this.idleagues == null && other.idleagues != null) || (this.idleagues != null && !this.idleagues.equals(other.idleagues))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Leagues[ idleagues=" + idleagues + " ]";
    }
    
}
