package com.azizi.mohamed.kata.tennis;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player implements Observable {

    private String name; // Must be unique
    private List<Observer> observers;

    public Player(String name) {
        this.name = name;
        this.observers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void winPoint() {
        notifyObservers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public void notifyObservers() {
        this.observers.forEach((observer -> observer.onEvent(this)));
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }
}
