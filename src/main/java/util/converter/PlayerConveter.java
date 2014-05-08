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
import model.Players;

/**
 *
 * @author gerasim
 */
@Named("playerConverterBean")
@FacesConverter(value = "playerConverter")
public class PlayerConveter implements Converter {

    @PersistenceContext(unitName = "fansferPU")
    private transient EntityManager em;

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component,
            String value) {
        return em.createNamedQuery("Players.findByPlayername", Players.class).setParameter("playername", value).getSingleResult();
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Players) o).getPlayername();
    }   
}
