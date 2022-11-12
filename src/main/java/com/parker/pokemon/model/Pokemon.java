package com.parker.pokemon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    public Pokemon(){

    }
    @Id
    private int id;
    private String name;
    private int height;
    private int weight;
    private String imageURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    //    // Want official artwork
//    private String imageURL;
//    private String characteristics;
//    private String description;
//
//    private String habitat;
//    private String[] types;
//
//    // Stats
//    private int hp;
//    private int attack;
//    private int defense;
//    private int specialAttack;
//    private int specialDefense;
//    private int speed;
}
