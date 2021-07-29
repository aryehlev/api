package com.plainid.assignment.dao;

import java.util.ArrayList;


public class Trainer implements Comparable<Trainer> {
    private String name;
    private int level;
    ArrayList<String> bag;
    Pokemon[] pokemons;

    public  Pokemon[] Pokemons(){
        return pokemons;
    }
    public  void setPokemons(){
         pokemons  = new Pokemon[3];;
    }

    public  ArrayList<String> getBag() {
        return bag;
    }


    public  void setBag() {
        bag = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            if (pokemons[i] != null) {
                bag.add(pokemons[i].getName());
            }
        }
    }


    public int NextPokemon() {
        if (pokemons[0] == null) return 1;
        else if (pokemons[1] == null) return 2;
        else return  3;
    }

    public int NumOfPokemons(){
        int ans = 0;
        for(int i = 0; i < 3; i++){
            if (pokemons[i] != null){
                ans++;
            }
        }

        return ans;
    }

    public void setFirstPokemon(Pokemon pokemon) {
        this.pokemons[0] = pokemon;
    }

    public void setSecondPokemon(Pokemon pokemon) {
        this.pokemons[1] = pokemon;
    }

    public void setThirdPokemon(Pokemon pokemon) {
        this.pokemons[2] = pokemon;
    }



    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Trainer other) {
        return this.getLevel() - other.getLevel();
    }


}
