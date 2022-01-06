/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.cvicenie01uloha04b.bean.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Meidie
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Override
    public String getMessage(){
        return "Hello World";
    }
}
