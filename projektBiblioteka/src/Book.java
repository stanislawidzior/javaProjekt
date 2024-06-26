public class Book {
    private String title;
    private String author;
    private int pages;
    private String isbn;
    private String publisher;
    private int releaseYear;

    public Book(String title, String author, int pages, String isbn, String publisher, int releaseYear) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.isbn = isbn;
        this.publisher = publisher;
        this.releaseYear = releaseYear;
    }
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.pages = 0;
        this.isbn = " ";
        this.publisher = " ";
        this.releaseYear = 0;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public boolean equals(Object object){
        if(object instanceof Book){
            Book book = (Book) object;
            return this.title.equals(book.getTitle()) && this.author.equals(book.getAuthor());
        }
        return false;
    }
    public int hashCode() {
        String name = getTitle() + getAuthor();
        return name.hashCode();
    }
    public boolean isExactly(Book book){
        return this.title.equals(book.getTitle()) && this.author.equals(book.getAuthor());
    }
    @Override
    public String toString() {
        return String.format("Tytuł: %s, Autor: %s)", this.title, this.author);
    }
}