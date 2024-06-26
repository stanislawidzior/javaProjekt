import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooksList {
    private Map<Book, ArrayList<Book>> books;
    public BooksList() {
        books = new HashMap<>();
    }
    public void addBook(Book book) {
        if (!books.containsKey(book)) {
            books.put(book, new ArrayList<>());
            books.get(book).add(book);
        }
        else{
            books.get(book).add(book);
        }
    }
    public void debug(Book book){
        System.out.println(books.get(book).size());
        return;
    }
    public void removeBook(Book book) throws BookNotFoundException {

        if (books.containsKey(book)) {
            if(books.get(book).size() == 1) {
                books.remove(book);
            }
            else if(!books.get(book).isEmpty()) {
                int index = getExactBookIndex(book);
                books.get(book).remove(index);
            }
        }else throw new BookNotFoundException();
    }
    public boolean containsBook(Book book){
        return books.containsKey(book);
    }
    private int getExactBookIndex(Book lookupBook) {
        for(int i = 0; i < books.get(lookupBook).size();i++){
            if(books.get(lookupBook).get(i).isExactly(lookupBook)){
                return i;
            }
        }
        return -1;
    }
    public int getAmount(Book book){
        try {
            return this.books.get(book).size();
        }catch (Exception e){
            return 0;
        }
    }
    public int getSize(Book book){
        return this.books.get(book).size();
    }
    public HashMap<Book,Integer> getBooksAmountTable(){
        HashMap<Book,Integer> table = new HashMap<>();
        for(ArrayList<Book> book : books.values()){
            table.put(book.get(0),book.size());
        }
        return table;
    }
    //public boolean containsBook(String title)
    public String getBooksAmountString(){
        HashMap<Book,Integer> table = getBooksAmountTable();
        StringBuilder string = new StringBuilder();
        for(Map.Entry<Book,Integer> book : table.entrySet()){
            string.append(book.getKey().getTitle());
            string.append("\t");
            string.append(book.getKey().getAuthor());
            string.append("\tAll: ");
            string.append(book.getValue());
            string.append("\tAvailable: ");
            string.append(getAmount(book.getKey()));
            string.append("\n");
        }
        if(string.isEmpty()){
            return "Pusta lista";
        }
        return string.toString();
    }
}
