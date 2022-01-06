/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie02uloha03.bean;

import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Meidie
 */
public class AspectLoggerBean {

    private int messageCallCount;
    private int messagesLength;
    
    public int getMessageCallCount(){
        return messageCallCount;
    }
    
        public int getMessagesLength(){
        return messagesLength;
    }
    
    public void myBeforeAdvice(JoinPoint jp) {
        messageCallCount++;
    }
    
    public void myAfterReturningAdvice(JoinPoint jp, String message){
        messagesLength += message.length();
    }
}
