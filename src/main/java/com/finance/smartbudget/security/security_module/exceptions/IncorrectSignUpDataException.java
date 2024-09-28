package com.finance.smartbudget.security.security_module.exceptions;


import com.finance.smartbudget.security.security_module.web.response.SignUpResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public @Data class IncorrectSignUpDataException extends Exception {
    private final SignUpResponse signUpResponse;
    public IncorrectSignUpDataException (SignUpResponse signUpResponse) {
        super(signUpResponse.getMessage());
        this.signUpResponse = signUpResponse;
    }
}
