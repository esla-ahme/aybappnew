package com.example.mainapp;


public class model {
    private String post;
    private String content;
    private String image;
    private String address;
    private String name;
    private String phone;
    private String type;



    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }

    public model(String address, String name, String phone, String type) {
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.type = type;
    }

    public model(String post, String content, String image) {
        this.post = post;
        this.content = content;
        this.image = image;
    }



    public model(String post, String image) {
        this.post = post;
        this.image = image;
    }





    public String getPost() {
        return post;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }
}