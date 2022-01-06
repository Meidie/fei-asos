/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie01uloha04b.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.matus.asos.cvicenie01uloha04b.bean.service.MessageService;

/**
 *
 * @author Meidie
 */
@Component("processor")
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
