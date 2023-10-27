package macrobrang.dpvat.validation;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import macrobrang.dpvat.validation.constraints.NumeroCelularConstraint;

public class NumeroCelularValidation implements ConstraintValidator<NumeroCelularConstraint, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        verificaSeEstaVazio(value);

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        
        try {
            PhoneNumber phoneNumberParsed = phoneNumberUtil.parse(value, "BR");

            boolean isPhoneMobileBR = phoneNumberUtil.getNumberType(phoneNumberParsed) == PhoneNumberType.MOBILE; 

            if (!isPhoneMobileBR) {
                return false;
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    private boolean verificaSeEstaVazio(String value) {
        
        if (value == null || value.isBlank()) {
            return false;
        }
        return true;
    }
    
}
