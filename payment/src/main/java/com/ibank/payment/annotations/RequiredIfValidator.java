package com.ibank.payment.annotations;

import java.lang.reflect.Field;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequiredIfValidator implements ConstraintValidator<RequiredIf, Object> {
 
    private String field;
    private String value;
    private String requiredField;
    private String requiredValue;
 
    @Override
    public void initialize(RequiredIf constraint) {
    	field = constraint.field();
    	value = constraint.value();
    	requiredField = constraint.requiredField();
    	requiredValue = constraint.requiredValue();
    }
 
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try {	
            Object fieldValue = getFieldValue(object, this.field);
            if (fieldValue != null && fieldValue.toString().equals(this.value)) {
            	if (this.requiredValue.equals("")) {
            		return getFieldValue(object, this.requiredField) != null;
            	} else {
            		//disable existing violation message
            	    context.disableDefaultConstraintViolation();
            	    //build new violation message and add it
            	    context.buildConstraintViolationWithTemplate("Field '{requiredField}' should contain value '{requiredValue}' if '{field}'='{value}'").addConstraintViolation();
            		return getFieldValue(object, this.requiredField).toString().equals(this.requiredValue) ;
            	}
            } else 
            	return true;
           
        } catch (Exception e) {
            // log error
            return false;
        }
    }
 
    
    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> cl = object.getClass();
        Field field = cl.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }
 
}
