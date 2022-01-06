/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matus.asos.insurancecompanyws.pojo;

/**
 *
 * @author Matus
 */
public class Contract {

    private String id;
    private Person owner;
    private Person insuredPerson;

    public Contract() {
    }

    public Contract(String id, Person owner, Person insuredPerson) {
        this.id = id;
        this.owner = owner;
        this.insuredPerson = insuredPerson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Person getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(Person insuredPerson) {
        this.insuredPerson = insuredPerson;
    }
}
