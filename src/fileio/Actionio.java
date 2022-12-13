package fileio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Actionio {
    private String type;
    private String page;
    private String feature;
    private Credentials credentials;
    private String startsWith;
    private String count;
    private Integer rate;
    private Filterio filters;
    private String movie;
}
