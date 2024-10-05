package org.example.model;

public class VideoGame {
    private String category;
    private String name;
    private String rating;
    private String releaseDate;
    private Integer reviewScore;


    public VideoGame(String category, String name, String rating, String releaseDate, Integer reviewScore) {
        this.category = category;
        this.name = name;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.reviewScore = reviewScore;
    }


    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getReviewScore()
    {
        return this.reviewScore;
    }

    public void setReviewScore(Integer reviewScore)
    {
        this.reviewScore = reviewScore;
    }
}

