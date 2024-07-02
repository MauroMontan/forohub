package mauro.montan.forohub.users.errorHandlers;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
    }

    @Override
    public String getMessage() {
        return "El usuario no existe.";
    }
}
