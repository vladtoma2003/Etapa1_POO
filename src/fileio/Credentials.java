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
}
