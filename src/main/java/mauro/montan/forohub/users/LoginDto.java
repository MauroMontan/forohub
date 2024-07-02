package mauro.montan.forohub.users;


@lombok.Data
public class LoginDto {
    String email;
    String password;

    public LoginDto() {
    }

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginDto fromPrincipal(Object princial) {
        User loginData = (User) princial;

        this.email = loginData.getEmail();
        this.password = loginData.getPassword();
        return this;
    }
};
