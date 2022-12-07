package Data;

import java.util.ArrayList;

public class DataBase {
    private ArrayList<User> users;
    private ArrayList<Movie> movies;

    public boolean existsUser(User user) {
        return users.stream()
                .anyMatch(o -> o.equals(user));
    }

    public User getCurrentUser(User user) {
        for(var u:users) {
            if(u.equals(user)) {
                return u;
            }
        }
        return null;
    }

    public boolean existsMovie(Movie movie) {
        return movies.stream()
                .anyMatch(o ->o.equals(movie));
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
