package Data;

import java.util.ArrayList;

public class DataBase {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Movie> availableMovies = new ArrayList<>();
    private ArrayList<Movie> currentMoviesList = new ArrayList<>();
    private User loggedUser = null;

    public Movie getCurrentMovie() {
        return currentMovie;
    }

    public void setCurrentMovie(Movie currentMovie) {
        this.currentMovie = currentMovie;
    }

    private Movie currentMovie;

    public boolean existsUser(User user) {
        return users.stream()
                .anyMatch(o -> o.equals(user));
    }

    public User getCurrentUser(User user) {
        if(!existsUser(user)) {
            return null;
        }
        for(var u:users) {
            if(u.equals(user)) {
                return u;
            }
        }
        return null;
    }

    public boolean existsMovie(Movie movie) {
        return currentMoviesList.stream()
                .anyMatch(o ->o.equals(movie));
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movie> getAvailableMovies() {
        return availableMovies;
    }

    public void setAvailableMovies(ArrayList<Movie> availableMovies) {
        this.availableMovies = availableMovies;
    }

    public ArrayList<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(ArrayList<Movie> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
