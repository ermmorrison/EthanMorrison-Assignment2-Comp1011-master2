package com.example.ethanmorrisonassignment2comp1011.Controllers;

import com.example.ethanmorrisonassignment2comp1011.Models.Meal;
import com.example.ethanmorrisonassignment2comp1011.Utilities.APIUtility;
import com.example.ethanmorrisonassignment2comp1011.Utilities.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecipeLookupViewController implements Initializable {

    @FXML
    private Label header;

    @FXML
    private ListView<Meal> mealsListView;

    @FXML
    private TextField searchTextField;

    @FXML
    private ImageView mealImageView;

    @FXML
    private TextField categoryTextField;

    @FXML
    private Button viewRecipeButton;

    @FXML
    void getSearchResults(ActionEvent event) throws IOException, InterruptedException {
        mealsListView.getItems().clear();
        mealsListView.getItems().addAll(APIUtility.getMealFromAPI(searchTextField.getText()).getSearch());
    }

    @FXML
    void getSearchResults2(ActionEvent event) throws IOException, InterruptedException {
        mealsListView.getItems().clear();
        mealsListView.getItems().addAll(APIUtility.getMealFromAPI(categoryTextField.getText()).getSearch());
    }

    @FXML
    void getSearchResults3(ActionEvent event) throws IOException, InterruptedException {
        mealsListView.getItems().clear();
        mealsListView.getItems().addAll(APIUtility.getRandomMealFromAPI().getSearch());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mealsListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, old, selectedMeal) -> {
                    try {
                        mealImageView.setImage(new Image(selectedMeal.getThumbnail()));
                    } catch (Exception e) {
                        mealImageView.setImage(new Image(getClass().getResourceAsStream("image-not-found.png")));
                    }
                }
        );
    }

    @FXML
    private void getMealDetails(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event,"recipe-details.fxml","View Recipe",
                mealsListView.getSelectionModel().getSelectedItem().getMealID());
    }
}

