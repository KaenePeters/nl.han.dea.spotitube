package nl.han.dea.DTO;

public class UserDTO {


    private String user = "kaene";
    private String password = "kaene1234";
    private String name = "Kaene Peters";

    public UserDTO() {
    }

    public UserDTO(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName(String name) {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }
}

