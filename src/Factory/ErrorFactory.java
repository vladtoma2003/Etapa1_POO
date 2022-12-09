package Factory;

import Data.OutputError;
import Data.User;

public class ErrorFactory {
    public static OutputError standardError(String message) {
        OutputError newError = new OutputError();
        newError.setError(message);
        return newError;
    }

    public static OutputError error(User currentUser) {
        OutputError newError = new OutputError();
        newError.setError(null);
        newError.setCurrentUser(currentUser);
        return newError;
    }
}
