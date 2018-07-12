package pl.marcelbaumgardt.naukarestsql.errors;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found in DB");
    }
}
