/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie02uloha04.bean;

import org.aspectj.lang.ProceedingJoinPoint;
import sk.matus.asos.cvicenie02uloha04.bean.cipher.CaesarCipher;

/**
 *
 * @author Meidie
 */
public class AspectLoggerBean {

    private CaesarCipher cipher;

    public AspectLoggerBean(CaesarCipher cipher) {
        this.cipher = cipher;
    }

    public Object myAroundAdviceService(ProceedingJoinPoint pjp) throws Throwable {

        Object obj = pjp.proceed();

        if (obj instanceof String) {
            return cipher.encrypt((String) obj);
        } else {
            return obj;
        }
    }

    public Object myAroundAdviceConsumer(ProceedingJoinPoint pjp, String message) throws Throwable {
        String plainText = cipher.decrypt(message);
        return pjp.proceed(new Object[]{plainText});
    }
}
