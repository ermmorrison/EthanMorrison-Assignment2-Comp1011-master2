package com.example.ethanmorrisonassignment2comp1011.Utilities;

import com.example.ethanmorrisonassignment2comp1011.Models.MealRecipe;
import com.example.ethanmorrisonassignment2comp1011.Models.Response;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class APIUtility {


    public static Response getRecipeFromJSON(){
        Gson gson = new Gson();
        Response result = null;
        try(
                FileReader fileReader = new FileReader("apiResponse.json");
                JsonReader jsonReader = new JsonReader(fileReader);
        )
        {
            result = gson.fromJson(jsonReader, Response.class);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Response getMealFromAPI(String searchText) throws IOException, InterruptedException {
        Response result = null;
        searchText = searchText.replace(" ","+");
        String uri = "https://www.themealdb.com/api/json/v1/1/search.php?s="+searchText;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get("apiResponse.json")));
        result = getRecipeFromJSON();
        return result;
    }

    public static Response getMealFromAPICategory(String searchText) throws IOException, InterruptedException {
        Response result = null;
        searchText = searchText.replace(" ","+");
        String uri = "https://www.themealdb.com/api/json/v1/1/filter.php?c="+searchText;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get("apiResponse.json")));
        result = getRecipeFromJSON();
        return result;
    }

    public static Response getRandomMealFromAPI() throws IOException, InterruptedException {
        Response result = null;
        String uri = "https://www.themealdb.com/api/json/v1/1/random.php";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get("apiResponse.json")));
        result = getRecipeFromJSON();
        return result;
    }


    public static MealRecipe getMealRecipe(String mealID) throws IOException, InterruptedException {
        mealID = mealID.trim();

        String uri = "https://www.themealdb.com/api/json/v1/1/lookup.php?i="+mealID;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);

        Gson gson = new Gson();
        return gson.fromJson(response.body(), MealRecipe.class);
    }
}
