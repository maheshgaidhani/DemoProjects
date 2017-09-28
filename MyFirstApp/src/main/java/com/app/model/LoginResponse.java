package com.app.model;

public class LoginResponse {

private ErrorMessage errorMessage;
private Franchisee franchisee;

public ErrorMessage getErrorMessage() {
return errorMessage;
}

public void setErrorMessage(ErrorMessage errorMessage) {
this.errorMessage = errorMessage;
}

public Franchisee getFranchisee() {
return franchisee;
}

public void setFranchisee(Franchisee franchisee) {
this.franchisee = franchisee;
}

}
