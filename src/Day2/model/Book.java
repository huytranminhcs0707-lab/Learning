package Day2.model;

public class Book {
    private String name;
    private String author;
    private boolean available = true;

    public Book(String name, String author, boolean available) {
        this.name = name;
        this.author = author;
        this.available = available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }


    public boolean isAvailable() {
        return available;
    }

    public void borrowBook(){
        if (available == true){
            available = false;
        }
    }

    public void returnBook(){
        available = true;
    }
}
