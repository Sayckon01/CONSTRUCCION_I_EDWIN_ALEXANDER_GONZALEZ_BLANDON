
package com.club;
import javax.swing.JOptionPane;
public class Main {

    public static void main(String[] args) {
        
        boolean exit = false;
        Menu menu = new Menu();
        String name,id,type;
        Invoice invoice;
        Member foundMember;
        Person foundPerson;
        int funds;
        int optionMain; 
        Club club = new Club("Prestige");
        Product product1 = new Product("Margarita", 32000);
        Product product2 = new Product("Waffle", 28000);
        Product product3 = new Product("Baby beff", 43000);
        
        
        
        //Bucle Main
        while(!exit){
            optionMain = menu.mainMenu();
            switch(optionMain){
                case 1: 
                      name = JOptionPane.showInputDialog("Ingrese el nombre de la persona que desea registrar como socio");
                      id = JOptionPane.showInputDialog("Ingrese el numero de identificacion de la persona que desea registrar como socio");
                      type = JOptionPane.showInputDialog("Ingrese el numero del tipo de socio\n1. Regular\n2. VIP");
                      while(Integer.parseInt(type) < 1 || Integer.parseInt(type) > 2){
                          type = JOptionPane.showInputDialog("ERROR! Opcion invalida\nIngrese el numero del tipo de socio\n1. Regular\n2. VIP");
                      }
                      if(type.equals("1")){
                          type = "REGULAR";
                      }
                      else{
                          type = "VIP";
                      }
                      funds = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de fondos iniciales para el socio"));
                      Member member = new Member(name, id, type, funds);
                      club.addAssociateMember(member);
                      break;
                      
                case 2:
                      foundMember =  club.findMember();
                      if(foundMember == null){
                          break;
                      }
                      name = JOptionPane.showInputDialog("Ingrese el nombre de la persona que desea autorizar por el(la) socio(a) " + foundMember.getName());
                      id = JOptionPane.showInputDialog("Ingrese el numero de identificacion de la persona que desea autorizar por el(la) socio(a) " + foundMember.getName());
                      Person person = new Person(name, id);
                      foundMember.addAssociatedPerson(person);
                      break;
                
                case 3:
                    JOptionPane.showMessageDialog(null, "PAGAR FACTURAS");
                    foundMember =  club.findMember();
                    if(foundMember == null){
                          break;
                    }
                    if(foundMember.getInvoices().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Tiene " + foundMember.getInvoices().size() + " facturas pendientes por pagar");
                        break;
                    }
                    String bills = "FACTURAS\n";
                    int index = 0;
                    for(Invoice bill: foundMember.getInvoices()){
                        index++;
                        bills += "Factura #" + index + ".Producto " + bill.getConcept() + " $" + bill.getValue() + "\n" ;
                    }
                    int auxI = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de la factura que desea pagar?\n"+bills));
                    if(auxI > foundMember.getInvoices().size() || auxI <= 0){
                        JOptionPane.showMessageDialog(null,auxI + " No es una factura valida");
                        break;
                    }
                    foundMember.removeInvoice(foundMember.getInvoices().get(auxI-1));
                    break;
                case 4:
                    String optionP = JOptionPane.showInputDialog( "Ingrese el numero del producto que desea consumir\n1. " + product1.getName() + " $" + product1.getValue() + "\n2. " + product2.getName() + " $" + product2.getValue() +"\n3. " + product3.getName() + " $" + product2.getValue());
                    foundMember =  club.findMember();
                    if(foundMember == null){
                          break;
                      }
                    else{
                        switch(optionP){
                            case "1":
                                invoice = new Invoice(product1.getName(), foundMember.getName(), product1.getValue());
                                if(invoice.getValue() <= foundMember.getFundsAvailable()){
                                    foundMember.addInvoice(invoice);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "No tienes suficientes fondos disponibles para consumir esto, \ndebes recargar fondo. Saldo actual:" + foundMember.getFundsAvailable());
                                }
                                break;
                            case "2":
                                invoice = new Invoice(product2.getName(), foundMember.getName(), product2.getValue());
                                if(invoice.getValue() <= foundMember.getFundsAvailable()){
                                    foundMember.addInvoice(invoice);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "No tienes suficientes fondos disponibles para consumir esto, \ndebes recargar fondo. Saldo actual:" + foundMember.getFundsAvailable());
                                }
                                break;
                            case "3":
                                invoice = new Invoice(product3.getName(), foundMember.getName(), product3.getValue());
                                if(invoice.getValue() <= foundMember.getFundsAvailable()){
                                    foundMember.addInvoice(invoice);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "No tienes suficientes fondos disponibles para consumir esto, \ndebes recargar fondos. Saldo actual:" + foundMember.getFundsAvailable());
                                }
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, optionP + " no es una opcion valida");
                                break;
                        }
                        break;
                    }
                case 5:
                    JOptionPane.showMessageDialog(null, "RECARGAR FONDOS");
                    foundMember =  club.findMember();
                    if(foundMember == null){
                          break;
                      }
                    club.rechageFunds(foundMember);
                    break;
                case 6:
                    id = JOptionPane.showInputDialog("Ingrese la identificacion del socio que desea eliminar");
                    club.removeAssociateMember(id);
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Eliminar una persona autorizada por un socio");
                    foundMember =  club.findMember();
                    if(foundMember == null){
                          break;
                      }
                    foundPerson = foundMember.findPerson();
                    if(foundPerson == null){
                          break;
                    }
                    foundMember.removeAssociatedPerson(foundPerson);
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, "Gracias por usar este programa.");
                    exit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, optionMain + " No es una opcion valida");
                    break;     
            }
            
        }
   
        
    }
    
}
