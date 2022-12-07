package fileio;

public class Userio {
    private Credentials credentials;

    public Userio() {

    }

    public Userio(Credentials credentials) {
        this.credentials = credentials;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
