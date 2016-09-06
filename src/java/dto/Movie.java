package dto;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;
public class Movie implements Serializable
{
    private int movieId;
    private String movieName;
    private String category;
    private String movieCast;
    private String director;
    private Time duration;
    private String cover;
    private String description;
    private Date releaseDate;

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(String movieCast) {
        this.movieCast = movieCast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    
    
}
