package fileio;

import java.util.ArrayList;
//@JsonIgnoreProperties({"users", "movies", "actions"})
public final class Input {
    private ArrayList<Userio> users;
    private ArrayList<Movieio> movies;
    private ArrayList<Action> actions;

    public Input() {
    }

    public ArrayList<Userio> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Userio> users) {
        this.users = users;
    }

    public ArrayList<Movieio> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movieio> movies) {
        this.movies = movies;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }
}
