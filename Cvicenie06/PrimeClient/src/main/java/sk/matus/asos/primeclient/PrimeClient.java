/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.primeclient;

import java.util.concurrent.ExecutionException;

/**
 *
 * @author Matus
 */
public class PrimeClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        findNextPrime(1000000000l);
        findNextPrimePooling(1000000000l);
        findNextPrimeCallback(1000000000l);
    }
    
    public static void findNextPrime(Long num){
        
        try { // Call Web Service Operation
            sk.matus.asos.primeclient.PrimeService_Service service = new sk.matus.asos.primeclient.PrimeService_Service();
            sk.matus.asos.primeclient.PrimeService port = service.getPrimeServicePort();
            java.lang.Long result = port.findNextPrime(num);
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public static void findNextPrimePooling(Long num){
        
        try { // Call Web Service Operation(async. polling)
            sk.matus.asos.primeclient.PrimeService_Service service = new sk.matus.asos.primeclient.PrimeService_Service();
            sk.matus.asos.primeclient.PrimeService port = service.getPrimeServicePort();
            javax.xml.ws.Response<sk.matus.asos.primeclient.FindNextPrimeResponse> resp = port.findNextPrimeAsync(num);
            
            int poolCount = 0;
            while(!resp.isDone()) {
                Thread.sleep(100);
                poolCount++;
                System.out.println(poolCount);
            }
            System.out.println("Result = "+resp.get().getReturn());
        } catch (InterruptedException | ExecutionException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public static void findNextPrimeCallback(Long num){
        
        try { // Call Web Service Operation(async. callback)
            sk.matus.asos.primeclient.PrimeService_Service service = new sk.matus.asos.primeclient.PrimeService_Service();
            sk.matus.asos.primeclient.PrimeService port = service.getPrimeServicePort();
            javax.xml.ws.AsyncHandler<sk.matus.asos.primeclient.FindNextPrimeResponse> asyncHandler = (javax.xml.ws.Response<sk.matus.asos.primeclient.FindNextPrimeResponse> response) -> {
                try {
                    System.out.println("Result = "+ response.get().getReturn());
                } catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
            };
            java.util.concurrent.Future<? extends java.lang.Object> result = port.findNextPrimeAsync(num, asyncHandler);
            
             int poolCount = 0;
            while(!result.isDone()) {
                // do something
                Thread.sleep(100);
                 poolCount++;
                System.out.println(poolCount);
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

    }
    
}
