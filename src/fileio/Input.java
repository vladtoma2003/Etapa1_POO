package fileio;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public final class Input {
    private ArrayList<Userio> users;
    private ArrayList<Movieio> movies;
    private ArrayList<Actionio> actions;

    public Input() {
    }
}
