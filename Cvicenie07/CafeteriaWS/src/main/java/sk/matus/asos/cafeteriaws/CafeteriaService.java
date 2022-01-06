/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cafeteriaws;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;
import sk.matus.asos.cafeteriaws.pojo.Day;
import sk.matus.asos.cafeteriaws.pojo.Food;
import sk.matus.asos.cafeteriaws.pojo.Menu;

/**
 *
 * @author Matus
 */
@WebService(serviceName = "CafeteriaService")
@Stateless()
public class CafeteriaService {

   Map<Day, Menu> menuOffers = new HashMap<>();

    public CafeteriaService() {
        for(Day d : Day.values()){
            menuOffers.put(d, new Menu(d));
        }
    }
    
    @WebMethod
    public Menu getMenu(Day day) throws Exception {
        
        if(day == null){
            throw  new Exception("Day is null");
        }
        
        return menuOffers.get(day);        
    }
    
    @WebMethod
    public void addFood(Food food, Day day) throws Exception {
        
        if(food == null || day == null){
            throw  new Exception("Input values can not be null");
        }
        
        List<Food> offers = menuOffers.get(day).getFoodOffers(); 
        for (Food f : offers) {
             if(f.getName().equals(food.getName())){
                throw new Exception("Food already exists");
            }
        }
        
        offers.add(food);
        System.out.println("Success"+ " food " + food.getName() + " was added.");
    }
}
