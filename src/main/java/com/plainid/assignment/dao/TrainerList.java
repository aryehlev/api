package com.plainid.assignment.dao;


import java.util.Collections;
import java.util.List;


public class TrainerList {
    List<Trainer> trainers;

    public List<Trainer> getTrainers() {

        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public void sortTrainers() {
        Collections.sort(this.trainers);
    }
}

