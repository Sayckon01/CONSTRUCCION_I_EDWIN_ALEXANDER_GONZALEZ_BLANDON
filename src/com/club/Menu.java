/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.club;
import javax.swing.JOptionPane;
/**
 *
 * @author Alex
 */
public class Menu {
    private String mainMenu = """
                              Bienvenido
                              1. Afiliar un socio al club
                              2. Registrar una persona autorizada para un socio.
                              3. Pagar una factura
                              4. Registrar un consumo en la cuenta de un socio.
                              5. Aumentar fondos de la cuenta de un socio
                              6. Eliminar un socio del club.
                              7. Eliminar una persona autorizada de un socio
                              8. Salir.
                              """;
    
    public Menu(){

    }
    
    public int mainMenu(){
        return Integer.parseInt(JOptionPane.showInputDialog(mainMenu));
    }
}
