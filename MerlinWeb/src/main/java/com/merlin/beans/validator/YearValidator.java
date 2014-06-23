/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merlin.beans.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Humberto
 */
public class YearValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String ano = (String) value;
        boolean valid = true;
        if (value == null) {
            valid = false;
        } else if (!ano.matches("[0-9]{4}")) {
            valid = false;
        }
        if (!valid) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ano inválido", "O ano inserido é inválido.");
            throw new ValidatorException(message);
        }
    }

}
