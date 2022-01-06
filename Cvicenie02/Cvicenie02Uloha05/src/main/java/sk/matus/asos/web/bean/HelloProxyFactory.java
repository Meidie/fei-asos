/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.web.bean;

import sk.matus.asos.web.service.HelloService;
import sk.matus.asos.web.service.HelloService_Service;

/**
 *
 * @author Meidie
 */
public class HelloProxyFactory {

    public static HelloService getPort() {
        HelloService_Service service = new HelloService_Service();
        return service.getHelloServicePort();
    }
}
