package PageVisitor;

import Data.*;
import Factory.ErrorFactory;
import Factory.MovieFactory;
import Factory.UserFactory;
import Pages.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Actionio;


public class VisitPagesAction implements VisitorAction {
    @Override
    public void visit(Page page, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {

    }

    @Override
    public void visit(Start start, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        if(!start.canDoAction(action.getFeature())) {
            OutputError stdError = ErrorFactory.standardError(dataBase);
            output.addPOJO(stdError);
            currentPage.setName("start");
            return;
        }
    }

    @Override
    public void visit(Login login, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        User us = UserFactory.newUser(action.getCredentials());
        if (!dataBase.existsUser(us)) {
            // error, user doesn't exist
            OutputError stdError = ErrorFactory.standardError(dataBase);
            output.addPOJO(stdError);
            currentPage.setName("start");
            return;
        }
        dataBase.setLoggedUser(UserFactory.newUser(dataBase.getCurrentUser(us)));
        currentPage.setAuth(true);
        currentPage.setName("home auth");
        OutputError err = ErrorFactory.success(dataBase);
        output.addPOJO(err);
    }

    @Override
    public void visit(Register register, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        User usr = UserFactory.newUser(action.getCredentials());
        if (dataBase.existsUser(usr)) {
            // error: user already exists
            OutputError stdError = ErrorFactory.standardError(dataBase);
            output.addPOJO(stdError);
            currentPage.setName("start");
            return;
        }
        dataBase.getUsers().add(usr);
        OutputError success = ErrorFactory.success(dataBase, usr);
        output.addPOJO(success);
        currentPage.setName("home auth");
        currentPage.setAuth(true);
        dataBase.setLoggedUser(usr);
    }

    @Override
    public void visit(Home home, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        if(!home.canDoAction(action.getFeature())) {
            OutputError stdError = ErrorFactory.standardError(dataBase);
            output.addPOJO(stdError);
            currentPage.setName("home auth");
        }
    }

    @Override
    public void visit(Movies movies, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        if(!movies.canDoAction(action.getFeature())) {
            OutputError stdError = ErrorFactory.standardError(dataBase);
            output.addPOJO(stdError);
            currentPage.setName("movies");
            return;
        }
        if(action.getFeature().equals("search")) {
            movies.search(dataBase, action.getStartsWith());
        } else if(action.getFeature().equals("filter")) {
            movies.filter(dataBase, action.getFilters());
        }
        OutputError err = ErrorFactory.success(dataBase);
        output.addPOJO(err);

    }

    @Override
    public void visit(Details details, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        int price = 10;
        if(!details.canDoAction(action.getFeature())) {
            OutputError stdError = ErrorFactory.standardError(dataBase);
            output.addPOJO(stdError);
            currentPage.setName("details");
            return;
        }
        if(action.getFeature().equals("purchase")) {
            Movie movie = dataBase.getMovieFromCurrentList(action.getMovie());
            if(movie == null) {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                currentPage.setName("details");
                return;
            }
            if(dataBase.getLoggedUser().getNumFreePremiumMovies() > 0) {
                dataBase.getLoggedUser().setNumFreePremiumMovies(dataBase.getLoggedUser().getNumFreePremiumMovies() - 1);
            } else if(dataBase.getLoggedUser().getTokensCount() < price) {
                dataBase.getLoggedUser().setTokensCount(dataBase.getLoggedUser().getTokensCount() - price);
            } else {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                currentPage.setName("details");
                return;
            }
            dataBase.getLoggedUser().getPurchasedMovies().add(movie);
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        } else if(action.getFeature().equals("watch")) {
            if(!dataBase.getLoggedUser().getPurchasedMovies().stream()
                    .anyMatch(o ->o.getName().startsWith(action.getMovie()))) {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                currentPage.setName("details");
                return;
            }
            Movie movie = dataBase.getPurchasedMovies(action.getMovie());
            dataBase.getLoggedUser().getWatchedMovies().add(movie);
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        } else if(action.getFeature().equals("like")) {
            if(!dataBase.getLoggedUser().getWatchedMovies().stream()
                    .anyMatch(o -> o.getName().startsWith(action.getMovie()))) {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                currentPage.setName("details");
                return;
            }
            Movie movie = dataBase.getWatchedMovies(action.getMovie());
            movie.setNumLikes(movie.getNumLikes() + 1);
            dataBase.getLoggedUser().getWatchedMovies().add(MovieFactory.newMovie(movie));
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        } else if(action.getFeature().equals("rate")) {
            if(!dataBase.getLoggedUser().getWatchedMovies().stream()
                    .anyMatch(o -> o.getName().startsWith(action.getMovie()))) {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                currentPage.setName("details");
                return;
            }
            Movie movie = dataBase.getWatchedMovies(action.getMovie());
            movie.setNumRatings(movie.getNumRatings() + 1);
            movie.setTotalRatin(movie.getTotalRatin() + action.getRate());
            movie.setRating((movie.getTotalRatin()/movie.getNumRatings()));
            dataBase.getLoggedUser().getRatedMovies().add(movie);

        }
    }

    @Override
    public void visit(Upgrades upgrades, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        if(!upgrades.canDoAction(action.getFeature())) {
            OutputError stdError = ErrorFactory.standardError(dataBase);
            output.addPOJO(stdError);
            return;
        }
        FilterCountryOut.filterCountry(dataBase);
        if(action.getFeature().equals("buy tokens")) {
            if(dataBase.getLoggedUser().getCredentials().getIntBalance() < Integer.parseInt(action.getCount())) {
                OutputError err = ErrorFactory.success(dataBase);
                output.addPOJO(err);
                return;
            }
            dataBase.getLoggedUser().setTokensCount(dataBase.getLoggedUser().getTokensCount() + Integer.parseInt(action.getCount()));
            dataBase.getLoggedUser().getCredentials().setIntBalance(dataBase.getLoggedUser().getCredentials().getIntBalance() - Integer.parseInt(action.getCount()));
            dataBase.getLoggedUser().getCredentials().setBalance(Integer.toString(dataBase.getLoggedUser().getCredentials().getIntBalance()));
            OutputError err = ErrorFactory.success(dataBase);
            output.addPOJO(err);
        } else {
            if(dataBase.getLoggedUser().getTokensCount() < 10) {
                OutputError stdError = ErrorFactory.standardError(dataBase);
                output.addPOJO(stdError);
                return;
            }
            dataBase.getLoggedUser().setTokensCount(dataBase.getLoggedUser().getTokensCount() - 10);
            dataBase.getLoggedUser().getCredentials().setAccountType("premium");
            System.out.println(dataBase.getLoggedUser());
            OutputError err = ErrorFactory.success(dataBase);
            System.out.println(err.getCurrentUser().getTokensCount() + err.getCurrentUser().getCredentials().getAccountType());
            output.addPOJO(err);
        }
    }

    @Override
    public void visit(Logout logout, DataBase dataBase, Page currentPage, Actionio action, ArrayNode output) {
        dataBase.setLoggedUser(null);
        currentPage.setName("start");
        currentPage.setAuth(false);
    }
}
