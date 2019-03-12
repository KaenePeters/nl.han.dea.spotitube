package nl.han.dea;

public class TokenDTO {

    public String token;
    public String user;

    public TokenDTO() {
    }

    public TokenDTO(String token, String user) {
        this.token = token;
        this.user = user;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


}
