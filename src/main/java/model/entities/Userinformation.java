/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Semir
 */
@Entity
@Table(name="userinformation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userinformation.findAll", query = "SELECT u FROM Userinformation u"),
    @NamedQuery(name = "Userinformation.findByName", query = "SELECT u FROM Userinformation u WHERE u.name = :name"),
    @NamedQuery(name = "Userinformation.findByAge", query = "SELECT u FROM Userinformation u WHERE u.age = :age"),
    @NamedQuery(name = "Userinformation.findByCountry", query = "SELECT u FROM Userinformation u WHERE u.country = :country"),
    @NamedQuery(name = "Userinformation.findById", query = "SELECT u FROM Userinformation u WHERE u.id = :id"),
    @NamedQuery(name = "Userinformation.findByEmail", query = "SELECT u FROM Userinformation u WHERE u.email = :email")})
public class Userinformation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 32)
    @Column(length = 32)
    private String name;
    private Integer age;
    @Size(max = 32)
    @Column(length = 32)
    private String country;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(length = 45)
    private String email;
    @JoinColumn(name = "favoriteTeam", referencedColumnName = "idclubs")
    @ManyToOne
    private Clubs favoriteTeam;

    public Userinformation() {
    }

    public Userinformation(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Clubs getFavoriteTeam() {
        return favoriteTeam;
    }

    public void setFavoriteTeam(Clubs favoriteTeam) {
        this.favoriteTeam = favoriteTeam;
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
        if (!(object instanceof Userinformation)) {
            return false;
        }
        Userinformation other = (Userinformation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Userinformation[ id=" + id + " ]";
    }
    
}
