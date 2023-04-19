public class Anime {
    private String title;
    private String genre;
    private int year;
    private int rating;
    private int id;

    public Anime(String title, String genre, int year, int rating, int id) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return this.title;
    }

}
