package com.example.ethanmorrisonassignment2comp1011.Controllers;

import com.example.ethanmorrisonassignment2comp1011.InitializeRecipe;
import com.example.ethanmorrisonassignment2comp1011.Models.MealRecipe;
import com.example.ethanmorrisonassignment2comp1011.Models.Response;
import com.example.ethanmorrisonassignment2comp1011.Utilities.APIUtility;
import com.example.ethanmorrisonassignment2comp1011.Utilities.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecipeDetailsController implements Initializable, InitializeRecipe {

    @FXML
    private Label nameLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label areaLabel;

    @FXML
    private ListView<String> ingredientsListView;

    @FXML
    private Label instructionsLabel;

    @FXML
    private ImageView mealImageView;

    @FXML
    private void showSearchView(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event,"recipe-lookup-view.fxml","Search Recipes");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getMealRecipe(String mealID)  {
        try {
            MealRecipe mealRecipe = APIUtility.getMealRecipe(mealID);
            nameLabel.setText(mealRecipe.getMealName());
            categoryLabel.setText(mealRecipe.getCategory());
            areaLabel.setText(mealRecipe.getArea());
            ingredientsListView.getItems().addAll(mealRecipe.getIngredients());
            instructionsLabel.setText(mealRecipe.getInstructions());
            try{
                mealImageView.setImage(new Image(mealRecipe.getThumbnail()));
            } catch (Exception e){
                mealImageView.setImage(new Image(getClass().getResourceAsStream("image-not-found.png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

