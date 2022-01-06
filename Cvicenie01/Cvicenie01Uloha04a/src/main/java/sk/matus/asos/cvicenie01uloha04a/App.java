/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie01uloha04a;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sk.matus.asos.cvicenie01uloha04a.bean.MessageProcessor;

/**
 *
 * @author Meidie
 */
public class App {
    
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        MessageProcessor mp = ctx.getBean("processor", MessageProcessor.class);
        mp.printMessage();
    }
}
