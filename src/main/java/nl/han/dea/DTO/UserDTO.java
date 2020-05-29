package nl.han.dea.DTO;

public class UserDTO {


    private String username = "kaene";
    private String password = "kaene1234";
    private String name;

    public UserDTO(){

    }
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        if (this.name.equals("")) {
            return username;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

