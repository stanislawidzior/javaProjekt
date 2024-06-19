
public class User {
    private String name;
    private BooksList borrowedBooks;
    private BooksList favouriteBooks;
    public User(String name) {
        this.name = name;
        borrowedBooks = new BooksList();
        favouriteBooks = new BooksList();
    }
    public BooksList getBorrowedBooks(){
        return borrowedBooks;
    }
    public BooksList getFavouriteBooks(){
        return favouriteBooks;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void borrowBook(Book book) throws BookNotFoundException{
        borrowedBooks.addBook(book);
    }
    public void returnBook(Book book) throws BookNotFoundException{
        borrowedBooks.removeBook(book);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            if(((User)obj).getName().equals(this.name)){
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    @Override
    public String toString(){
        return name;
    }
}
