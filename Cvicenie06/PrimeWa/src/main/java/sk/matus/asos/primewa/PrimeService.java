/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.primewa;

import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author Matus
 */
@WebService(serviceName = "PrimeService")
public class PrimeService {

    @WebMethod
    public Long findNextPrime(Long num) {

        long start = System.currentTimeMillis();

        num++;

        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                num++;
                i = 2;
            }
        }

        long finish = System.currentTimeMillis();
        System.out.println("\nElapsed: " + (finish-start) + " ms");

        return num;
    }
}
