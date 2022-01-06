/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.insurancecompanyws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;
import sk.matus.asos.insurancecompanyws.pojo.Contract;

/**
 *
 * @author Matus
 */
@WebService(serviceName = "InsuranceService")
@Stateless()
public class InsuranceService {

    @WebMethod
    public Contract chcekContract(Contract contract) throws Exception {
        
        if(contract.getOwner() == null){
            throw new Exception("No owner");
        } else if (contract.getInsuredPerson() == null){
            throw new Exception("No insured person");
        } else {
          contract.setId("digestor");
          return contract;
        }
    }
}
