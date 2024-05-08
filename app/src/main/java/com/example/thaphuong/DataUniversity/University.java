package com.example.thaphuong.DataUniversity;

public class University {
    private int image;
    private String name;
    // Constructor default
    public University() {
    }
    public University(int image, String name) {
        this.image = image;
        this.name = name;
    }
    // Getter
    public int getImage() {
        return image;
    }
    public String getName() {
        return name;
    }
    // Setter
    public void setImage(int image) {
        this.image = image;
    }
    public void setName(String name) {
        this.name = name;
    }
}
