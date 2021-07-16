package com.musafi.bookslist;

public class Book {
    private String Name;
    private String author;
    private float rating;
    private MyRBG placeholderColor;
    private String Cover;

    public Book() {
    }

    public Book(String name, String author, float rating, MyRBG placeholderColor, String cover) {
        Name = name;
        this.author = author;
        this.rating = rating;
        this.placeholderColor = placeholderColor;
        Cover = cover;
    }

    public String getName() {
        return Name;
    }

    public Book setName(String name) {
        Name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public float getRating() {
        return rating;
    }

    public Book setRating(float rating) {
        this.rating = rating;
        return this;
    }

    public MyRBG getPlaceholderColor() {
        return placeholderColor;
    }

    public Book setPlaceholderColor(MyRBG placeholderColor) {
        this.placeholderColor = placeholderColor;
        return this;
    }

    public String getCover() {
        return Cover;
    }

    public Book setCover(String cover) {
        Cover = cover;
        return this;
    }
}
