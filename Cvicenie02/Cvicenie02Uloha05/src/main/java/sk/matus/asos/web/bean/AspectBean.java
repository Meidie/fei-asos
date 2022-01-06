/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.web.bean;

import org.aspectj.lang.ProceedingJoinPoint;
import sk.matus.asos.web.cipher.CaesarCipher;

/**
 *
 * @author Meidie
 */
public class AspectBean {

    public Object myAroundAdvice(ProceedingJoinPoint pjp, String name) throws Throwable {

        CaesarCipher cipher = new CaesarCipher(5);
        String ct = cipher.encrypt(name);
        Object returnVal = pjp.proceed(new Object[]{ct});

        if (returnVal instanceof String) {
            return cipher.decrypt((String) returnVal);
        } else {
            return returnVal;
        }
    }
}
