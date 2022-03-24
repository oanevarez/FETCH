package com.example.fetch_json_excercise;

public class List_Model {
    int id;
    int listID;
    String name;

    public List_Model(int id, int listID, String name) {
        this.id = id;
        this.listID = listID;
        this.name = name;
    }

    public List_Model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
