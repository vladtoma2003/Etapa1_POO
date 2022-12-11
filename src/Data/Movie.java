package Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties({"totalRatin"})

public class Movie {
    private String name;
    private Integer year;
    private Integer duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private int numLikes;
    private int numRatings;
    private double rating;
    private int totalRatin;

    public boolean equals(Movie obj) {
        boolean a = true;
        a &= obj.getName().equals(this.name);
        a &= obj.getYear().equals(this.year);
        a &= obj.getDuration().equals(this.duration);
        return a;
    }

    public int compareTo(Movie o) {
        if (this.compareRating(o) == 0) {
            return this.compareDuration(o);
        }
        return this.compareRating(o);
    }

    public int compareRating(Movie o) {
        if (rating > o.getRating()) {
            return 1;
        } else if (o.getRating() < rating) {
            return -1;
        } else {
            return 0;
        }
    }

    public int compareDuration(Movie o) {
        if (duration > o.getDuration()) {
            return 1;
        } else if (o.getDuration() > duration) {
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

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getTotalRatin() {
        return totalRatin;
    }

    public void setTotalRatin(int totalRatin) {
        this.totalRatin = totalRatin;
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
