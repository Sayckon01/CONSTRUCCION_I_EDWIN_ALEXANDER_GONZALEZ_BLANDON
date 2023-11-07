
package com.club;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class Member extends Person {
    //Attributes
    private String membershipType;
    private int fundsAvailable;
    private List<Invoice> invoices = new ArrayList<>();
    private List<Person> associatedPeople = new ArrayList<>();
    
    //Contructor method
    public Member(String name, String id, String membershipType,int fundsAvailable) {
        super(name,id);
        this.fundsAvailable = fundsAvailable;
        this.membershipType = membershipType.toUpperCase();
    }
    
    //Getters and Setters

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public int getFundsAvailable() {
        return fundsAvailable;
    }

    public void setFundsAvailable(int fundsAvailable) {
        this.fundsAvailable = fundsAvailable;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
    
    public List<Person> getAssociatedPeople() {
        return associatedPeople;
    }

    public void setAssociatedPeople(List<Person> associatedPeople) {
        this.associatedPeople = associatedPeople;
    }
    
    
    //method to associate authorized people
    public boolean addAssociatedPerson(Person person) {
        //maximum 10 authorized people
        if(associatedPeople.size() < 10){
            //validate duplicate ids
            if (!validateDuplicateIds(person)){
                if(fundsAvailable>0){
                    associatedPeople.add(person);
                    JOptionPane.showMessageDialog(null,"¡Agregación exitosa!\nSe ha agregado " + person.getName() + " como persona autorizada del socio: " + getName());
                    return true;
                }
                else{
                    JOptionPane.showMessageDialog(null,"ERROR!\nEl socio tiene" + fundsAvailable + " fondos, no puede agregar autorizados, debe recargar.");
                    return false;
                }
                
            }
            
            else{
                return false;
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"ERROR!\nLa lista esta llena, hay " + associatedPeople.size() + " personas autorizadas.");
            return false;
        }
    }

    //method to remove authorized people
    public boolean removeAssociatedPerson(Person person) {
        
        if (associatedPeople.contains(person)) {
            if (invoices.isEmpty()){
                associatedPeople.remove(person);
                JOptionPane.showMessageDialog(null,"La persona " + person.getName() + " con identificacion " + person.getId() + " fue retirada exitosamente");
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null,"ERROR!\nDebes pagar " + invoices.size() + " facturas pendientes para poder eliminar autorizados" );
                return false;
            }
            
        } 
        else {
            JOptionPane.showMessageDialog(null,"ERROR!\nEsta persona no esta autorizada por el socio, o ya fue removida");
            return false;
            }
    }
    
    //validate duplicate ids
    public boolean validateDuplicateIds(Person person){
        for(Person auxPerson: associatedPeople){
            if(person.getId().equals(auxPerson.getId())){
                JOptionPane.showMessageDialog(null, "ERROR!\nEsta identifación ya esta registrada: " + person.getId());
                return true;
            }
        }
        return false;
    }
    
    //Method to add invoices
    public boolean addInvoice(Invoice invoice){
        if(invoices.size() < 20){
            invoices.add(invoice);
            JOptionPane.showMessageDialog(null,"Factura agregada exitosamente");
        }
        else{
            JOptionPane.showMessageDialog(null,"ERROR!\nNo puede agregar mas facturas tienes: " + invoices.size());
        }
        return false;
    }
    
    //Method to remove invoices
    public boolean removeInvoice(Invoice invoice){
        if(invoice.getValue() <= fundsAvailable){
            fundsAvailable -= invoice.getValue();
            invoices.remove(invoice);
            JOptionPane.showMessageDialog(null,"Factura pagada exitosamente, saldo disponible: " + fundsAvailable);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null,"ERROR!\nNo tiene fondos suficientes para pagar esta factura");
            return false;
        }
    }
 
    //found person
    public Person findPerson(){
        String id = JOptionPane.showInputDialog("Ingrese el numero de identificacion de la persona\nautorizada por el(a) socio(a) " + getName());
        for(Person person: associatedPeople){
            if(person.getId().equals(id)){
                return person;
            }
        }
        JOptionPane.showMessageDialog(null, "Esta identificacion: " + id + " NO pertenece a ninguna persona autorizada por " + getName());
        return null;
    }
    
}
