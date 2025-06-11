package org.scoula.domain;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Co
mponent
public class Parrot {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void init() {
        this.name = "Kiki";
    }
}