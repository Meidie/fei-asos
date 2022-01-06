/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie02uloha04.bean.consumer;

/**
 *
 * @author Meidie
 */
public class MessageConsumerImpl implements MessageConsumer {

    @Override
    public void putMessage(String msg) {
        System.out.println(msg);
    }
}
