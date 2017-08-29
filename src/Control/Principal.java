/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Consulta;
import Model.Data;
import View.Agenda;
import View.MenuProvisorio;
import View.TelaPacientes;
import java.util.Scanner;

/**
 *
 * @author lucas
 */
public class Principal {
    public static ControladorPaciente controladorPaciente = new ControladorPaciente();
    public static ControladorAgenda controladorAgenda = new ControladorAgenda();
    public static Agenda agenda = new Agenda();
    public static TelaPacientes telaPacientes = new TelaPacientes();
    
    public static void main(String args[]){
       /* Scanner teclado = new Scanner(System.in);
        int opcao = -1;
        while (opcao!=0){
            System.out.println("Sistema Gerenciamento Dentista");
            System.out.println("1 - Gerenciar Pacientes");
            System.out.println("2 - Gerenciar Agenda");
            System.out.println("0 - Sair");
            System.out.print("\tOpção: ");
            opcao = Integer.parseInt(teclado.next());
            System.out.println("");
            switch(opcao){
                case 1: {telaPacientes.setVisible(true); break;}
                case 2: {agenda.setVisible(true); break;}
            }*/
        new MenuProvisorio().setVisible(true);
        //    break;
        //}
    }
}
