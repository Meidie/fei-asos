/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie01uloha05b.bean;

import sk.matus.asos.cvicenie01uloha05b.bean.service.MessageService;

/**
 *
 * @author Meidie
 */
public class MessageProcessor {
    
    private MessageService service;
    
    public MessageProcessor(MessageService service) {
        this.service = service;
    }
    
    public void printMessage() {
        System.out.println(service.getMessage());
    }
}
