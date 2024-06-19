import java.util.HashSet;

public class UsersList {
    private HashSet<User> users;
    public UsersList() {
        users = new HashSet<>();
    }
    public UsersList(HashSet<User> users) {
        this.users = users;
    }
    public boolean findUser(String name){
        if(users.contains(new User(name))){
            return true;
        }return false;

    }
    public User  getUser(String name){
        for(User user:users){
            if(user.getName().equals(name)){
                return user;
            }
        }return null;
    }
    public void addUser(User user) throws UserAlreadyDefinedException {
        if(!users.contains(user)) {
            users.add(user);
        }else {
            throw new UserAlreadyDefinedException();
        }
    }
    public void removeUser(User user) throws UserNotFoundException{
        if(users.contains(user)) {
            users.remove(user);
        }else throw new UserNotFoundException();
    }

    public int getUsersAmount(){
        return users.size();
    }
    public void printUsersBooks(String name){
        StringBuilder string = new StringBuilder();
        User userFind;
        for(User user : users){
            if(user==new User(name)){
                string.append(user.getBorrowedBooks().getBooksAmountTable());
            }
        }
    }
    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        for(User user : users){
            string.append(user.toString());
            string.append("\t|\t");
        }
        return string.toString();
    }
}