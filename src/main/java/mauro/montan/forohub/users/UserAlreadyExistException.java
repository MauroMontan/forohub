package mauro.montan.forohub.users;

public class UserAlreadyExistException extends RuntimeException {

    private final String email;
    public UserAlreadyExistException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return "el email \"" + this.email + "\" se encuentra en uso ";
    }
}
