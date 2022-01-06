/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie02uloha04.bean;

import sk.matus.asos.cvicenie02uloha04.bean.consumer.MessageConsumer;
import sk.matus.asos.cvicenie02uloha04.bean.service.MessageService;

/**
 *
 * @author Meidie
 */
public class MessageProcessor {

    private MessageService service;
    private MessageConsumer consumer;

    public MessageProcessor(MessageService messageService, MessageConsumer messageConsumer) {
        this.service = messageService;
        this.consumer = messageConsumer;
    }

    public void processMessage() {
        String msg = service.getMessage();
        consumer.putMessage(msg);
    }
}
