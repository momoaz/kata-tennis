package com.azizi.mohamed.kata.tennis;

public interface Observable {
    void notifyObservers();
    void registerObserver(Observer observer);
}
