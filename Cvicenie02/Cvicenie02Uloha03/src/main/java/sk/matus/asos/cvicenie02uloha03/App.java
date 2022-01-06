/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie02uloha03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sk.matus.asos.cvicenie02uloha03.bean.AspectLoggerBean;
import sk.matus.asos.cvicenie02uloha03.bean.MessageProcessor;

/**
 *
 * @author Meidie
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        MessageProcessor mp = ctx.getBean("processor", MessageProcessor.class);
        AspectLoggerBean alb = ctx.getBean("aspectLoggerBean", AspectLoggerBean.class);
        
        for(int i = 0; i < 10; i++){
             mp.processMessage();
        }
        
        System.out.println("messageCallCount: " + alb.getMessageCallCount());
        System.out.println("messagesLength: " + alb.getMessagesLength());
    }
}
