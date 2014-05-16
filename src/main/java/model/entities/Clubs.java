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
@Table(name = "clubs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clubs.findAll", query = "SELECT c FROM Clubs c"),
    @NamedQuery(name = "Clubs.findByIdclubs", query = "SELECT c FROM Clubs c WHERE c.idclubs = :idclubs"),
    @NamedQuery(name = "Clubs.findByClubname", query = "SELECT c FROM Clubs c WHERE c.clubname = :clubname"),
    @NamedQuery(name = "Clubs.findByEmail", query = "SELECT c FROM Clubs c WHERE c.email = :email")})
public class Clubs implements Serializable {
    @OneToMany(mappedBy = "favoriteTeam")
    private Collection<Userinformation> userinformationCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idclubs")
    private Integer idclubs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "clubname")
    private String clubname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "clubid")
    private Collection<Transfer> transferCollection;
    @OneToMany(mappedBy = "clubid")
    private Collection<Players> playersCollection;
    @JoinColumn(name = "leagueid", referencedColumnName = "idleagues")
    @ManyToOne(optional = false)
    private Leagues leagueid;

    public Clubs() {
    }

    public Clubs(Integer idclubs) {
        this.idclubs = idclubs;
    }

    public Clubs(Integer idclubs, String clubname, String email) {
        this.idclubs = idclubs;
        this.clubname = clubname;
        this.email = email;
    }

    public Integer getIdclubs() {
        return idclubs;
    }

    public void setIdclubs(Integer idclubs) {
        this.idclubs = idclubs;
    }

    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Transfer> getTransferCollection() {
        return transferCollection;
    }

    public void setTransferCollection(Collection<Transfer> transferCollection) {
        this.transferCollection = transferCollection;
    }

    @XmlTransient
    public Collection<Players> getPlayersCollection() {
        return playersCollection;
    }

    public void setPlayersCollection(Collection<Players> playersCollection) {
        this.playersCollection = playersCollection;
    }

    public Leagues getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(Leagues leagueid) {
        this.leagueid = leagueid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idclubs != null ? idclubs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clubs)) {
            return false;
        }
        Clubs other = (Clubs) object;
        if ((this.idclubs == null && other.idclubs != null) || (this.idclubs != null && !this.idclubs.equals(other.idclubs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Clubs[ idclubs=" + idclubs + " ]";
    }

    @XmlTransient
    public Collection<Userinformation> getUserinformationCollection() {
        return userinformationCollection;
    }

    public void setUserinformationCollection(Collection<Userinformation> userinformationCollection) {
        this.userinformationCollection = userinformationCollection;
    }
    
}
