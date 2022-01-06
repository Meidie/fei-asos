/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie02uloha02.bean;

import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Meidie
 */
public class LoggerBean {
    
    public void myBeforeAdvice(JoinPoint jp) {
        System.out.println("Method Signature: " + jp.getSignature());
    }
}
