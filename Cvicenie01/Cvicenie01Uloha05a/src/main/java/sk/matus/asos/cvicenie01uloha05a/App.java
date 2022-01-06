/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie01uloha05a;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sk.matus.asos.cvicenie01uloha05a.bean.MessageProcessor;
import sk.matus.asos.cvicenie01uloha05a.config.AppConfig;

/**
 *
 * @author Meidie
 */
public class App {
    
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        MessageProcessor mp = ctx.getBean(MessageProcessor.class);
        mp.printMessage();
    }
}
