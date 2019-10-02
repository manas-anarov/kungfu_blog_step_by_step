package com.example.samuray.myapplication.model;

public class CategoryModel {

    private int id;
    private int id_category;
    private String name;


    public CategoryModel(int id,
                         int id_category,
                         String name
    ) {
        this.id = id;
        this.id_category = id_category;
        this.name = name;
    }


    public int getId(){
        return id;
    }
    public int getIdCategory(){
        return id_category;
    }
    public String getName(){
        return name;
    }


}