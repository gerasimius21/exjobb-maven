/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.entities.Continents;
import model.entities.Leagues;

/**
 *
 * @author gerasim
 */
@Named("leagueConverterBean")
@FacesConverter(value = "leagueConverter")
public class LeagueConverter implements Converter {

    @PersistenceContext(unitName = "fansferPU")
    private transient EntityManager em;

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component,
            String value) {
        return em.createNamedQuery("Leagues.findByLeaguename", Leagues.class).setParameter("leaguename", value).getSingleResult();
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Leagues) o).getLeaguename();
    }

}