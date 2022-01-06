/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie01uloha05a.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.matus.asos.cvicenie01uloha05a.bean.MessageProcessor;
import sk.matus.asos.cvicenie01uloha05a.bean.service.MessageService;
import sk.matus.asos.cvicenie01uloha05a.bean.service.MessageServiceImpl;

/**
 *
 * @author Meidie
 */
@Configuration
public class AppConfig {
    
    @Bean
    public MessageProcessor processor(){
        return new MessageProcessor();
    }
    
    @Bean
    public MessageService service(){
        return new MessageServiceImpl();
    }
}
