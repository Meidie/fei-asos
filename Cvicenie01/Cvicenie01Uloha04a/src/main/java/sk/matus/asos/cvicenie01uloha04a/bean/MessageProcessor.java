/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie01uloha04a.bean;

import org.springframework.beans.factory.annotation.Autowired;
import sk.matus.asos.cvicenie01uloha04a.bean.service.MessageService;

/**
 *
 * @author Meidie
 */
public class MessageProcessor {
    
    @Autowired
    private MessageService service;
    
//    public MessageProcessor(MessageService messageService) {
//        this.service = messageService;
//    }
    
    public void printMessage() {
        System.out.println(service.getMessage());
    }
}
