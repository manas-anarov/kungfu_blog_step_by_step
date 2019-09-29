package com.example.samuray.myapplication.model;


public class PostModel {

    private Integer id;
    private String title;
    private String text;
    private Integer category;


    public PostModel(int id,
                     String title,
                     String text,
                     Integer category
    ) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.category = category;
    }


    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }

    public String getText(){
        return text;
    }


}
