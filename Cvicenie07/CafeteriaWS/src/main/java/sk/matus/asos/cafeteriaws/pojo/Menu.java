/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cafeteriaws.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matus
 */
public class Menu {
    
    private Day day;
    private List<Food> foodOffers = new ArrayList<>();

    public Menu() {
    }

    public Menu(Day day) {
        this.day = day;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public List<Food> getFoodOffers() {
        return foodOffers;
    }

    public void setFoodOffers(List<Food> foodOffers) {
        this.foodOffers = foodOffers;
    }
}
