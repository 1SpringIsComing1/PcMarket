package my.market.model.response;

public enum ErrorMessage {

    MISSING_REQUIRED_FIELD("Missing required fild.Please che docunmentation for required fields"),
    RECORD_ALREADY_EXISTS("Record allready exists"),
    INTERNAL_SERVER_ERROR("Intelrnal server error"),
    NO_RECORD_FOUND("Record with provided id is not found"),
    AUTHENTICATION_FAILD("Authentication faild"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified");

    private String errorMassage;

    ErrorMessage(String errorMassage) {
        this.errorMassage = errorMassage;
    }

    public String getErrorMassage() {
        return errorMassage;
    }

    public void setErrorMassage(String errorMassage) {
        this.errorMassage = errorMassage;
    }
}
