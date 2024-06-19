public class UserAlreadyDefinedException extends Exception{
    public UserAlreadyDefinedException(){
        super("User is already defined");
    }
}