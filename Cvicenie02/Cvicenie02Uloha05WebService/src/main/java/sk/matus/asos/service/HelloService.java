/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import sk.matus.asos.cipher.CaesarCipher;

/**
 *
 * @author Meidie
 */
@WebService(serviceName = "HelloService")
public class HelloService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        CaesarCipher cipher = new CaesarCipher(5);
        return cipher.encrypt("Hello ") + txt + " !";
    }
}
