package com.azizi.mohamed.kata.tennis;

import java.util.Objects;

public class Player implements Observable {

    private String name; // Must be unique
    private Observer observer;

    public Player(String name) {
        this.name = name;
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
        if(observer != null) {
            observer.onEvent(this);
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observer = observer;
    }
}
