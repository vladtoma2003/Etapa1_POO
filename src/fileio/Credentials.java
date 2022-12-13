package fileio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({"intBalance"})
public final class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;
    private Integer intBalance;

    public Credentials() {

    }

    public Credentials(final String name, final String password, final String accountType, final String country, final String balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }

    public Credentials(final String name, final String password) {
        this.name = name;
        this.password = password;
    }
}
