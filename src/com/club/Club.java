
package com.club;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class Club {
    private String name;
    private int counterVips = 0;
    private List<Member> associateMembers = new ArrayList<>();

    public Club(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getAssociateMembers() {
        return associateMembers;
    }

    public void setAssociateMembers(List<Member> associateMembers) {
        this.associateMembers = associateMembers;
    }
    
    //method to associate authorized people
    public boolean addAssociateMember(Member member) {
        //maximum 10 authorized people
        if(associateMembers.size() < 35){
            //validate duplicate ids
            if (!validateDuplicateIds(member)){
                if(member.getMembershipType().equals("VIP") && counterVips < 3){
                    if(validateFunds(member)){
                        associateMembers.add(member);
                        counterVips++;
                        JOptionPane.showMessageDialog(null,"¡Agregación exitosa!\nSe ha agregado " + member.getName() + " como socio del club: " + getName());
                        return true;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"ERROR!\nEl socio tiene: " + member.getFundsAvailable() + " fondos!\ndebe tener minimo 100000 | maximo 5000000");
                        return false;
                    }
                }
                
                else if(member.getMembershipType().equals("REGULAR")){
                    if(validateFunds(member)){
                        associateMembers.add(member);
                        JOptionPane.showMessageDialog(null,"¡Agregación exitosa!\nSe ha agregado " + member.getName() + " como socio del club: " + getName());
                            return true;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"ERROR!\nEl socio tiene: " + member.getFundsAvailable() + " fondos!\ndebe tener minimo 50000 | maximo 1000000");
                        return false;
                    }
                }
                
                else {
                    JOptionPane.showMessageDialog(null,"ERROR! El club solo puede tener 3 socios VIP, actualmente hay " + counterVips + " socios VIP");
                    return false;
                }   
            }
            
            else{
                return false;
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"ERROR!\n El club esta lleno, hay " + associateMembers.size() + " socios registrados.");
            return false;
        }
    }
    
     //method to remove associate members
    public boolean removeAssociateMember(String idMember) {
        for(Member member: associateMembers){
            if(member.getId().equals(idMember)){
                if(member.getMembershipType().equals("REGULAR")){
                    if(member.getAssociatedPeople().size()<=1){
                        if(member.getInvoices().isEmpty()){
                            associateMembers.remove(member);
                            JOptionPane.showMessageDialog(null,"El socio(a) " + member.getName() + " con identificacion " + member.getId() + " fue retirado(a) exitosamente");
                            return true;
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"ERROR!\nEl socio(a) " + member.getName() + " con identificacion " + member.getId() + " tiene " + member.getInvoices().size() + " facturas sin pagar, no se puede eliminar.");
                            return false;
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"ERROR!\nEl socio(a) " + member.getName() + " con identificacion " + member.getId() + " tiene " + member.getAssociatedPeople().size() + " personas asociadas, no se puede eliminar.");
                        return false;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"ERROR!\nEl socio(a) " + member.getName() + " con identificacion " + member.getId() + " es un socio " + member.getMembershipType() + ", no se puede eliminar.");
                    return false;
                }
                
            }
        } 
        JOptionPane.showMessageDialog(null,"ERROR!\nNo se encontro un socio con el numero de identificacion: " + idMember);
        return false;
    }
    
    //validate duplicate ids
    public boolean validateDuplicateIds(Member member){
        for(Member auxMember: associateMembers){
            if(member.getId().equals(auxMember.getId())){
                JOptionPane.showMessageDialog(null, "ERROR!\nEsta identifación ya esta registrada: " + member.getId());
                return true;
            }
        }
        return false;
    }
    
    //Method to recharge funds
    public boolean rechageFunds(Member member){
        int funds = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad a recargar"));
        if(funds>0){
            if(member.getMembershipType().equals("VIP") && funds+member.getFundsAvailable() <= 5000000){
                member.setFundsAvailable(member.getFundsAvailable()+funds);
                JOptionPane.showMessageDialog(null, "Recarga exitosa por $"+funds+"!\nNuevo saldo disponible: $" + member.getFundsAvailable());
                return true;
            }
            else if(member.getMembershipType().equals("REGULAR") && funds+member.getFundsAvailable() <= 1000000){
                member.setFundsAvailable(member.getFundsAvailable()+funds);
                JOptionPane.showMessageDialog(null, "Recarga exitosa por $"+funds+"!\nNuevo saldo disponible: $" + member.getFundsAvailable());
                return true;
            }
            else if(member.getMembershipType().equals("REGULAR") && funds+member.getFundsAvailable() > 1000000){
                JOptionPane.showMessageDialog(null, "ERROR! Los fondos maximos para un socio REGULAR son: 1'000.000");
                return false;
            }
            else{
                JOptionPane.showMessageDialog(null, "ERROR! Los fondos maximos para un socio VIP son: 5'000.000");
                return false;
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "ERROR! Los fondos a recargar deben ser mayores a $0");
            return false;
        }
    }
    
    
    
    //Validate founds
    public boolean validateFunds(Member member){
        if(member.getMembershipType().equals("VIP")){
            return member.getFundsAvailable() >= 100000 && member.getFundsAvailable() <= 5000000;
        }
        else{
            return member.getFundsAvailable() >= 50000 && member.getFundsAvailable() <= 1000000;
        }
    }
    
    //found member
    public Member findMember(){
        String id = JOptionPane.showInputDialog("Ingrese el numero de identificacion de el socio registrado");
        for(Member member: associateMembers){
            if(member.getId().equals(id)){
                return member;
            }
        }
        JOptionPane.showMessageDialog(null, "Esta identificacion: " + id + " NO pertenece a ningun socio registrado");
        return null;
    }
}
