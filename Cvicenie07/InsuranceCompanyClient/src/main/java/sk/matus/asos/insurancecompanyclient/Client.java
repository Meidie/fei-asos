/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.insurancecompanyclient;

import sk.matus.asos.insurancecompanyws.Contract;
import sk.matus.asos.insurancecompanyws.Exception_Exception;
import sk.matus.asos.insurancecompanyws.InsuranceService;
import sk.matus.asos.insurancecompanyws.InsuranceService_Service;
import sk.matus.asos.insurancecompanyws.Person;

/**
 *
 * @author Matus
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            InsuranceService_Service service = new InsuranceService_Service();
            InsuranceService port = service.getInsuranceServicePort();
           
            Person person = new Person();
            person.setName("Jozko");
            person.setSurname("Mrkvicka");
            
            Contract contact = new Contract();
            contact.setInsuredPerson(person);
            contact.setOwner(person);

            Contract result = port.chcekContract(contact);
            System.out.println("Contract id: " + result.getId());
            System.out.println("Owner: " + result.getOwner().getName() + " " + result.getOwner().getSurname());
            System.out.println("Insured person: " + result.getInsuredPerson().getName() + " " + result.getInsuredPerson().getSurname());
        } catch (Exception_Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
