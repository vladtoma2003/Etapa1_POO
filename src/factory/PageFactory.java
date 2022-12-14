package factory;

import pages.Page;
import pages.Logout;
import pages.Details;
import pages.Movies;
import pages.Home;
import pages.Start;
import pages.Login;
import pages.Register;
import pages.Upgrades;

public final class PageFactory {
    /**
     * creates a page based on the name
     *
     * @param page
     * @return
     */
    public static Page newPage(final Page page) {
        return switch (page.getName()) {
            case "start" -> new Start();
            case "login" -> new Login();
            case "register" -> new Register();
            case "home auth" -> new Home();
            case "movies" -> new Movies();
            case "see details" -> new Details();
            case "upgrades" -> new Upgrades();
            default -> new Logout();
        };
    }

    private PageFactory() {

    }
}
