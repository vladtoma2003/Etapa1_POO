package data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class OutputError {
    private String error;
    private ArrayList<Movie> currentMoviesList;
    private User currentUser;
}
