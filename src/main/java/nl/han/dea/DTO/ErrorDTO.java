package nl.han.dea.DTO;

public class ErrorDTO {

    private String message;
    private String code;

    public ErrorDTO( String message) {

        this.message = message;

    }



    public String getMessage() {
        return message;
    }


}
