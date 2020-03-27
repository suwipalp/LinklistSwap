package com.examination.linklistswap.validator;

import java.util.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LinklistValidator implements ConstraintValidator<LinklistConstraint, String> {

    private String regex;

    @Override
    public void initialize(LinklistConstraint constraint) {
        this.regex = constraint.regexp();
    }

    @Override
    public boolean isValid(String linklist, ConstraintValidatorContext cxt) {

        if (linklist != null) {
            byte[] decodedBytes = Base64.getDecoder().decode(linklist);
            String decodedString = new String(decodedBytes);
            return decodedString.matches(this.regex);
        } else {
            return false;
        }
    }
}
