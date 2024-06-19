import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.*;
public class Library {
    private BooksList allBooks;
    private BooksList currentAvailableBooks;
    private UsersList users = new UsersList();
    private String currentUser;
    public Library(){
        allBooks = new BooksList();
        currentAvailableBooks = new BooksList();
    }
    public Library(BooksList books, UsersList users) {
        this.allBooks = books;
        this.currentAvailableBooks = books;
        this.users = users;
    }
    public void addBook(Book book){
        allBooks.addBook(book);
        currentAvailableBooks.addBook(book);
    }
    public void removeBook(Book book){
        try{
            allBooks.removeBook(book);
            currentAvailableBooks.removeBook(book);
        }catch(Exception e){
            System.out.println(e);
        }

    }
    public void addUser(User user){
        try{
            users.addUser(user);
        }catch(Exception e){
            System.out.println("jestem");
            System.out.println(e);
        }
    }
    public void removeUser(User user){
        try{
            users.removeUser(user);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void lendToUser(User user, Book book){

        try{
            currentAvailableBooks.removeBook(book);
            user.borrowBook(book);
        }catch(Exception e){
            System.out.println(e);
        }

    }
    public void returnBookFromUser(User user, Book book){
        try{
            currentAvailableBooks.addBook(book);
            user.returnBook(book);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public  Map<Integer, Book> parseBooks(String input) {
        Map<Integer, Book> bookMap = new HashMap<>();
        String[] lines = input.split("\n");

        for (String line : lines) {
            String[] parts = line.split("\t");

            if (parts.length >= 3) {
                int counter = Integer.parseInt(parts[0]);
                String title = parts[1];
                String author = parts[2];

                Book book = new Book(title, author, 0, "", "", 0);
                bookMap.put(counter, book);
            }
        }

        return bookMap;
    }
    public String getCurrentlyAvailableBooksInfo(){
        StringBuilder builder = new StringBuilder();
        int counter = 1;
        for(Map.Entry<Book,Integer> book : allBooks.getBooksAmountTable().entrySet()){
            builder.append(counter);
            builder.append("\t");
            builder.append(book.getKey().getTitle());
            builder.append("\t");
            builder.append(book.getKey().getAuthor());
            builder.append("\tAll: ");
            builder.append(book.getValue());
            builder.append("\tAvailable: ");
            builder.append(currentAvailableBooks.getAmount(book.getKey()));
            builder.append("\n");
            counter++;
        }
        return builder.toString();
    }

    public String getUsersInfo(){
        return "Amount of users" + users.getUsersAmount();
    }
    private void libraryChooseUser(){
        Scanner s = new Scanner(System.in);
        System.out.println("---------------");
        System.out.println("----Wpisz nazwę swojego profilu:----");
        String choice;
        System.out.println("Dostępne profile:");
        System.out.println(users.toString());
        System.out.println("----type in q to exit----");

        while(true) {
            choice = s.nextLine();
            if(choice.equals("q")){
                return;
            }
            if (users.findUser(choice)) {
                currentUser = choice;
                break;
            } else {
                System.err.println("Nie ma takiego profilu, wybierz profil z listy");
            }
        }
        }
        private void libraryCreateUser(){
            Scanner s = new Scanner(System.in);
            System.out.print("---------------");
            System.out.println("----Wpisz nazwę swojego profilu:----");
            System.out.println("----type in q to exit----");
            String choice;
            String name;
            while(true){
                try{
                    choice = s.nextLine();
                    if(choice.equals("q")){
                        return;
                    }
                    users.addUser(new User(choice));
                    name = choice;
                    break;
                }catch (Exception e){
                    System.err.println(e + "\nspróbuj ponownie");
                }
            }
            System.out.println("Czy chcesz się zalogować? (y/n)");
            while(true){
                choice = s.nextLine();
                if(!(choice.equals("y") || choice.equals("n"))){
                    System.err.println("Wpisz y lub n");
                }else{
                    break;
                }
            }
            if(choice.equals("y")){
                currentUser = name;
                return;
            }else{
                return;
            }
        }
private void usersPanel(){
    System.out.println("---------------");
        System.out.println("----Witaj " + currentUser+"----");
        System.out.println("Wybierz opcje:");
        System.out.println("1. Zobacz listę swoich książek");
    System.out.println("2. Zobacz listę swoich ulubionych książek");
    System.out.println("3. Wypozycz książkę");
    System.out.println("4. Oddaj książkę");
    System.out.println("5. Wyloguj");
    int choice;
    Scanner s = new Scanner(System.in);
   while(true) {
       try {
           choice = s.nextInt();
           s.nextLine();
           if (!(choice > 0 && choice < 5)) {
               System.err.println("Wybierz jedna z dostepnych opcji");
               continue;
           }
           break;

       } catch (Exception e) {
           System.err.println("Wpisz cyfre odpowiadającą opcji");
           s.nextLine();
       }
   }
   switch(choice){
       case 1:
           printUserBooks();
           usersPanel();
           break;
       case 2:
           printUserFavouriteBooks();
           usersPanel();
           break;
       case 3:
           printBorrowBooks();

           usersPanel();
           break;

   }


}
private void  printBorrowBooks(){
    System.out.println("----Dostępne ksiązki----");
    System.out.println(getCurrentlyAvailableBooksInfo());
    System.out.println("----Wpisz cyfre odpowiadającą książce którą chcesz wypożyczyć----");
    System.out.println("----Wpisz 0 zeby powrocic----");
    int choice;
    Scanner s = new Scanner(System.in);
    Map<Integer,Book> integerBookMap = parseBooks(getCurrentlyAvailableBooksInfo());
    while(true) {
        try {
            choice = s.nextInt();
            s.nextLine();
            if(choice == 0){
                return ;
            }
            if(choice > 0 && choice< integerBookMap.size()){
                try{
                lendToUser(users.getUser(currentUser),integerBookMap.get(choice));
                }catch(Exception e){
                    System.err.println(e);
                }
                System.out.println("Udalo się wypożyczyć książkę" + integerBookMap.get(choice).toString());
                System.out.println("Wcisnij enter aby kontynuowac");
                s.nextLine();
                return ;
            }else{
                System.out.println("Niepoprawna wartość");
                s.nextLine();
                continue;
            }
        } catch (Exception e) {
            System.err.println("Wpisz cyfre odpowiadającą książce");
            s.nextLine();
        }
    }

}
    private void printUserBooks(){
        System.out.println(users.getUser(currentUser).getBorrowedBooks().getBooksAmountString());
        Scanner s = new Scanner(System.in);
        System.out.println("----Wcisnij enter aby powrocic----");
        s.nextLine();

    }
    private void printUserFavouriteBooks(){
        System.out.println(users.getUser(currentUser).getFavouriteBooks().getBooksAmountString());
        Scanner s = new Scanner(System.in);
        System.out.println("----Wcisnij enter aby powrocic----");
        s.nextLine();
    }

    public void runLibrary(){
        Scanner s = new Scanner(System.in);
        System.out.println("Witaj w bibliotece!");
        System.out.println("1. Wybierz profil");
        System.out.println("2. Utwórz profil");
        System.out.println("3. Wyjdz");
        int choice;
        while(true) {
            try {
                choice = s.nextInt();
                s.nextLine();
                if(!(choice > 0 && choice < 4)){
                    System.err.println("Wybierz jedna z dostepnych opcji");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.err.println("Wpisz cyfrę odpowiadającą opcji");
                s.nextLine();
            }
        }

        switch(choice){
            case 1:
                libraryChooseUser();
                break;
                case 2:
                    libraryCreateUser();
                    break;
            case 3:
                return;
        }
        if(currentUser != null && !currentUser.isEmpty()){
            usersPanel();
        }else{
            runLibrary();
        }
    }
}
