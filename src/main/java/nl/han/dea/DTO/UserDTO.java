package nl.han.dea.DTO;

public class UserDTO {


    private String user = "Kaene Peters";
    private String password = "MySuperSecretPassword12341";
    private String name;

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
}
