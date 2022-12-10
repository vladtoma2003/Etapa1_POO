package Data;

import java.util.ArrayList;

public class Movie {
    private String name;
    private Integer year;
    private Integer duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;

    private Integer numLikes;
    private Integer numRatings;
    private Double rating;

    public boolean equals(Movie obj) {
        boolean a = true;
        a &= obj.getName().equals(this.name);
        a &= obj.getYear().equals(this.year);
        a &= obj.getDuration().equals(this.duration);
        return a;
    }

    public int compareTo(Movie o) {
        if(this.compareRating(o) == 0) {
            return this.compareDuration(o);
        }
        return this.compareRating(o);
    }

    public int compareRating(Movie o) {
        if(rating > o.getRating()) {
            return 1;
        } else if(o.getRating() < rating) {
            return -1;
        } else {
            return 0;
        }
    }
    public int compareDuration(Movie o) {
        if(duration > o.getDuration()) {
            return 1;
        } else if(o.getDuration() > duration) {
            return -1;
        } else {
            return 0;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public Integer getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(Integer numLikes) {
        this.numLikes = numLikes;
    }

    public Integer getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(Integer numRatings) {
        this.numRatings = numRatings;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", genres=" + genres +
                ", actors=" + actors +
                ", countriesBanned=" + countriesBanned +
                ", numLikes=" + numLikes +
                ", numRatings=" + numRatings +
                ", rating=" + rating +
                '}';
    }
}
