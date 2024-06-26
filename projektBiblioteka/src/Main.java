import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
        public static void main(String[] args) {
            // Dodawanie Ksiazek do biblioteki
            //ksiazki nie moga sie powtarzac
            // dodawanie uzytkownikow do biblioteki
            //usuwanie uzytkownikow z biblioteki
            // usuwanie ksiazek z biblioteki
            // all done
            // pozyczanie ksiazek przez uzytkownikow
            // oddawanie ksiazek przez uzytkownikow

            Library lib = new Library();
            Book book = new Book("title","author",22,"22","publisher",199);
            lib.addBook(book);
            lib.addBook(book);
            lib.addBook(new Book("Wpustyni i w puszczy", "Henryk Sienkiewicz", 100,"22", "Publisher",1999));
            User user = new User("Janek");
            lib.getCurrentlyAvailableBooksInfo();

            lib.addUser(user);
            lib.addUser(user);
           // Map<Integer,Book> integerBookMap = lib.parseBooks(lib.getCurrentlyAvailableBooksInfo());

            //System.out.print(integerBookMap);

            lib.runLibrary();

    }
}