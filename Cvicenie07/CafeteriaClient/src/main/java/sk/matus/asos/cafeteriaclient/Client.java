/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cafeteriaclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import sk.matus.asos.cafeteriaWS.CafeteriaService;
import sk.matus.asos.cafeteriaWS.CafeteriaService_Service;
import sk.matus.asos.cafeteriaWS.Day;
import sk.matus.asos.cafeteriaWS.Exception_Exception;
import sk.matus.asos.cafeteriaWS.Food;
import sk.matus.asos.cafeteriaWS.Menu;

/**
 *
 * @author Matus
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         GetMenu(Day.MONDAY);
         AddFood(Day.MONDAY, "Halusky", "Narodne jedlo", 12.5);
         AddFood(Day.MONDAY, "Gulas", "Polievka", 12.5);
         GetMenu(Day.MONDAY);
    }
    
    public static void GetMenu(Day day){
        
        try {
            CafeteriaService_Service service = new CafeteriaService_Service();
            CafeteriaService port = service.getCafeteriaServicePort();
            Menu result = port.getMenu(day);
            
            System.out.println("Day: " + result.getDay() + " Food: ");
            result.getFoodOffers().forEach(f -> {
                System.out.println(f.getName());
            });
            
        } catch (Exception_Exception ex) {
            System.out.println(ex.getMessage());
        }
 
    }
    
    public static void AddFood(Day day, String name, String description, Double price){
        
        try { // Call Web Service Operation
            CafeteriaService_Service service = new CafeteriaService_Service();
            CafeteriaService port = service.getCafeteriaServicePort();
            // TODO initialize WS operation arguments here
            Food food = new Food();
            food.setName(name);
            food.setDescription(description);
            food.setPrice(price);
            port.addFood(food, day);
        } catch (Exception_Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
