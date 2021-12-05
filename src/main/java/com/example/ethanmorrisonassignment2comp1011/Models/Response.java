package com.example.ethanmorrisonassignment2comp1011.Models;

import com.example.ethanmorrisonassignment2comp1011.Models.Meal;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("meals")
    private Meal[] search;

    public Meal[] getSearch() {
        return search;
    }

    public void setSearch(Meal[] search) {
        this.search = search;
    }
}
