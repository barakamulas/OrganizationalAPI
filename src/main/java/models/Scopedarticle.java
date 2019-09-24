package models;

import java.util.Objects;

public class Scopedarticle extends Article {


    public static final String TYPE = "Scoped";

    public Scopedarticle(String title, String content) {
        super(title, content);
        this.type = TYPE;
    }
}
