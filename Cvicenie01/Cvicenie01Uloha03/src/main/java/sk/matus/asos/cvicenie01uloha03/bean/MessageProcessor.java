/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie01uloha03.bean;

import org.springframework.beans.factory.annotation.Autowired;
import sk.matus.asos.cvicenie01uloha03.bean.service.MessageService;

/**
 *
 * @author Meidie
 */
public class MessageProcessor {
    
    private MessageService service;
    
    public MessageProcessor() {
    }
    
    public MessageProcessor(MessageService service) {
         this.service = service;
    }
     
     public void setService(MessageService service){
         this.service = service;
     }
    
    public void printMessage() {
        System.out.println(service.getMessage());
    }
}
