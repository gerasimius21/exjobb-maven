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

/**
 *
 * @author gerasim
 */
@Named("continentConverterBean")
@FacesConverter(value = "continentConverter")
public class ContinentConverter implements Converter {

    @PersistenceContext(unitName = "fansferPU")
    private transient EntityManager em;

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component,
            String value) {
        return em.createNamedQuery("Continents.findByContinentname", Continents.class).setParameter("continentname", value).getSingleResult();
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Continents) o).getContinentname();
    }

}