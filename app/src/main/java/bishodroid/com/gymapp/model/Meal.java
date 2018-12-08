package bishodroid.com.gymapp.model;

import java.io.Serializable;

public class Meal implements Serializable{

    private String name;
    private String recipe;
    private String steps;
    private int protein;
    private int calories;
    private int  carbs;
    private String imgUrl;

    public Meal() {
    }

    public Meal(String name, String recipe, int calories, String imgUrl, String steps, int protein, int carbs) {
        this.name = name;
        this.recipe = recipe;
        this.calories = calories;
        this.imgUrl = imgUrl;
        this.steps = steps;
        this.protein = protein;
        this.carbs = carbs;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
