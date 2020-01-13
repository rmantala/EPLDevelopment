package id.ac.poliban.roman.epldevelopment.domain;

import java.io.Serializable;

public class Club implements Serializable {
    private int id;
    private String clubName;
    private String founded;
    private String grounded;
    private String manager;
    private String description;
    private String urlLogo;

    public Club(int id, String clubName, String founded, String grounded, String manager, String description, String urlLogo) {
        this.id = id;
        this.clubName = clubName;
        this.founded = founded;
        this.grounded = grounded;
        this.manager = manager;
        this.description = description;
        this.urlLogo = urlLogo;
    }

    public Club(String clubName, String founded, String grounded, String manager, String description, String urlLogo) {
        this.clubName = clubName;
        this.founded = founded;
        this.grounded = grounded;
        this.manager = manager;
        this.description = description;
        this.urlLogo = urlLogo;
    }

    public int getId() {
        return id;
    }

    public String getClubName() {
        return clubName;
    }

    public String getFounded() {
        return founded;
    }

    public String getGrounded() {
        return grounded;
    }

    public String getManager() {
        return manager;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    @Override
    public String toString() {
        return String.format("%3d %30s", getId(), getClubName());
    }
}
