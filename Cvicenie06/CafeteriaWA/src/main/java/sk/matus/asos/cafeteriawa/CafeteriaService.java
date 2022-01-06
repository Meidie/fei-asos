/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cafeteriawa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import sk.matus.asos.cafeteriawa.pojo.Food;

/**
 *
 * @author Matus
 */
@WebService(serviceName = "CafeteriaService")
@Stateless()
public class CafeteriaService {

    private final Map<String, Food> menu = new HashMap<>();
    private final List<Food> orders = new ArrayList<>();

    // vytvorenie a pridanie noveho jedla
    @WebMethod(operationName = "vytvorJedlo")
    public void createFood(@WebParam(name = "meno") String name, 
            @WebParam(name = "popis") String description, @WebParam(name = "cena") double price) {

        if (!menu.containsKey(name)) {
            menu.put(name, new Food(name, description, price));
        }
    }
    
    // blizsie informacie o jedle
    @WebMethod
    public Food info(@WebParam(name = "meno") String name){
        return menu.getOrDefault(name, null);
    }   
    
    // nazvy jedal v ponuke
    @WebMethod(operationName = "ponuka")
    public String[] getMenu(){
        return menu.keySet().toArray(new String[0]);
    }
    
    // pridanie noveho jedla
    @WebMethod(operationName = "pridajJedlo")
    public void addFood(@WebParam(name = "jedlo") Food food){
        menu.put(food.getName(), food);
    }
    
    // odstranenie jedla z ponuky
    @Oneway
    @WebMethod(operationName = "odstranJedlo")
    public void removeFood(@WebParam(name = "nazov") String name){
        menu.remove(name);
    }
    
    // zmena ceny jedla
    @WebMethod(operationName = "zmenCenu")
    public void changePrice (@WebParam(name = "nazov") String name, @WebParam(name = "cena") double price ) throws NoSuchFieldException{
        Food food = menu.get(name);
        
        if(food != null){
            food.setPrice(price);
        } else{
            throw new NoSuchFieldException("Jedlo neexistuje");        
        }
    } 
    
    // zmena popisu jedla
    @WebMethod(operationName = "zmenPopis")
    public void changeDescription (@WebParam(name = "nazov") String name, @WebParam(name = "popis") String description ) throws NoSuchFieldException{
        Food food = menu.get(name);
        if(food != null){
            food.setDescription(description);
        } else {
            throw new NoSuchFieldException("Jedlo neexistuje");  
        }
    }
    
     // objednanie jedla
    @WebMethod(operationName = "objednaj")
    public void order (@WebParam(name = "nazov") String name) throws NoSuchFieldException{
        Food food = menu.get(name);
        
        if(food != null){
            orders.add(food);
        } else {
            throw new NoSuchFieldException("Jedlo neexistuje");  
        }
    }
    
    // zistenie poctu objednavok jedla
    @WebMethod(operationName = "pocetObjednavok")
    public int numberOfOrders (@WebParam(name = "nazov") String name){
        return orders.size();
    }
}
