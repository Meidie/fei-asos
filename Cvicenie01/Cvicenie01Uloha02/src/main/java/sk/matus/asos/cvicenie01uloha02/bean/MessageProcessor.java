/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie01uloha02.bean;

import sk.matus.asos.cvicenie01uloha02.bean.service.MessageService;

/**
 *
 * @author Meidie
 */
public class MessageProcessor {
    
    private MessageService service;
    
    public void setService(MessageService messageService) {
        this.service = messageService;
    }
    
    public void printMessage() {
        System.out.println(service.getMessage());
    }
}
