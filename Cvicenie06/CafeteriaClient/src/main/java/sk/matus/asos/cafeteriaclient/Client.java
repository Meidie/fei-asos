/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cafeteriaclient;

import java.util.Collections;
import java.util.List;
import sk.matus.asos.cafeteriawa.NoSuchFieldException_Exception;

/**
 *
 * @author Matus
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        addFood("Halusky", "jedlo", 8.5);
        createFood("Gulas", "polievka", 5.5);
        getMenu();
        removeFood("Gulas");
        getMenu();
        getInfo("Halusky");
        changeDescription("Halusky", "Narodne jedlo");
        changePrice("Halusky", 12);
        getInfo("Halusky");
        orderFood("Halusky");
        orderFood("Halusky");
        ordersCount("Halusky");
        addFood("Spenat", "jedlo", 4);
        addFood("Svieckova", "jedlo", 2);
        String name = orderCheapestFood();
        System.out.println(name);
        
        changeDescription("xxx", "zzz");
        changePrice("xxx", 2);
        orderFood("xxx");
    }
    
    private static String orderCheapestFood(){
        List<String> foods = getMenu();
        
        String cheapestFoodName = "";
        double lowestPrice = Double.MAX_VALUE;
        
        for(String name : foods){
           sk.matus.asos.cafeteriawa.Food food = getInfo(name);
           if(food != null && food.getPrice() < lowestPrice){
               lowestPrice = food.getPrice();
               cheapestFoodName =  food.getName();
           }
        }
        
        return cheapestFoodName;
    }
    
    private static void createFood(String name , String description, double price){
        
        try {
            sk.matus.asos.cafeteriawa.CafeteriaService_Service service = new sk.matus.asos.cafeteriawa.CafeteriaService_Service();
            sk.matus.asos.cafeteriawa.CafeteriaService port = service.getCafeteriaServicePort();
            port.vytvorJedlo(name, description, price);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

    }
    
    private static void addFood(String name, String description, double price){
        
        try { // Call Web Service Operation
            sk.matus.asos.cafeteriawa.CafeteriaService_Service service = new sk.matus.asos.cafeteriawa.CafeteriaService_Service();
            sk.matus.asos.cafeteriawa.CafeteriaService port = service.getCafeteriaServicePort();
            sk.matus.asos.cafeteriawa.Food food = new sk.matus.asos.cafeteriawa.Food();
            food.setName(name);
            food.setDescription(description);
            food.setPrice(price);
            port.pridajJedlo(food);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

    }
    
    private static List<String> getMenu(){
        
        try { // Call Web Service Operation
            sk.matus.asos.cafeteriawa.CafeteriaService_Service service = new sk.matus.asos.cafeteriawa.CafeteriaService_Service();
            sk.matus.asos.cafeteriawa.CafeteriaService port = service.getCafeteriaServicePort();
            java.util.List<java.lang.String> result = port.ponuka();
            System.out.println("Result = " + result);
            return result;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }
    
    private static void changeDescription(String name, String description){
        
        try { // Call Web Service Operation
            sk.matus.asos.cafeteriawa.CafeteriaService_Service service = new sk.matus.asos.cafeteriawa.CafeteriaService_Service();
            sk.matus.asos.cafeteriawa.CafeteriaService port = service.getCafeteriaServicePort();
            port.zmenPopis(name, description);
        } catch (NoSuchFieldException_Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    private static void removeFood(String name){
        
        try { // Call Web Service Operation
            sk.matus.asos.cafeteriawa.CafeteriaService_Service service = new sk.matus.asos.cafeteriawa.CafeteriaService_Service();
            sk.matus.asos.cafeteriawa.CafeteriaService port = service.getCafeteriaServicePort();
            port.odstranJedlo(name);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

    }
    
    private static void changePrice(String name, double price){
        
        try { // Call Web Service Operation
            sk.matus.asos.cafeteriawa.CafeteriaService_Service service = new sk.matus.asos.cafeteriawa.CafeteriaService_Service();
            sk.matus.asos.cafeteriawa.CafeteriaService port = service.getCafeteriaServicePort();
            port.zmenCenu(name, price);
        } catch (NoSuchFieldException_Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    private static void orderFood(String name){
        
        try { // Call Web Service Operation
            sk.matus.asos.cafeteriawa.CafeteriaService_Service service = new sk.matus.asos.cafeteriawa.CafeteriaService_Service();
            sk.matus.asos.cafeteriawa.CafeteriaService port = service.getCafeteriaServicePort();
            port.objednaj(name);
        } catch (NoSuchFieldException_Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    private static void ordersCount(String name){
        
        try { // Call Web Service Operation
            sk.matus.asos.cafeteriawa.CafeteriaService_Service service = new sk.matus.asos.cafeteriawa.CafeteriaService_Service();
            sk.matus.asos.cafeteriawa.CafeteriaService port = service.getCafeteriaServicePort();
            int result = port.pocetObjednavok(name);
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

    }
    
    private static sk.matus.asos.cafeteriawa.Food getInfo(String name){
        
        try { // Call Web Service Operation
            sk.matus.asos.cafeteriawa.CafeteriaService_Service service = new sk.matus.asos.cafeteriawa.CafeteriaService_Service();
            sk.matus.asos.cafeteriawa.CafeteriaService port = service.getCafeteriaServicePort();
            sk.matus.asos.cafeteriawa.Food result = port.info(name);
            System.out.println("Name: " + result.getName() + ", Description: " + result.getDescription() + ", Price: " + result.getPrice());
            return result;
            
        } catch (Exception ex) {
            return null;
        }
    }
    
}
