package com.example.va3prog2.models;

public class Gift {

    private String category;
    private String description;
    private Double price;

    private String nickname;

    public Gift(String category, String description, Double price, String nickname) {
        this.category = category;
        this.description = description;
        this.price = price;
        this.nickname = nickname;
    }
    public String getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }
    public Double getPrice() {
        return price;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "nickname='" + nickname + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

}

