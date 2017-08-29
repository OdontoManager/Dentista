/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.Principal;
import Model.Consulta;
import Model.Data;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author lucas
 */
public class FrameConsulta extends JPanel {
    private Consulta consulta;
    public String localPath = "/media/lucas/OS/Users/lucas/Documents/NetBeansProjects/Dentista";
    private int y;
    public FrameConsulta(Consulta consulta, int y){
        this.consulta=consulta;
        this.y =y;
        this.setLayout(null);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (getBackground()==Color.LIGHT_GRAY)
                    setBackground(Color.GRAY);
                else
                    setBackground(Color.getHSBColor(121, 66, 24));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (getBackground()==Color.GRAY)
                    setBackground(Color.LIGHT_GRAY);
                else
                    setBackground(Color.getHSBColor(121, 66, 43));
            }
        });
    }

    public void mostrar(){
       setBounds(20, y, 610, 100);
       if (isPast())
            setBackground(Color.LIGHT_GRAY);
       else
           setBackground(Color.getHSBColor(121, 66, 43));
       setVisible(true);
       
       JLabel labelHora = new JLabel("Horário: ");
       labelHora.setFont(new Font("Calibri", 0, 11));
       labelHora.setBounds(10, 5, 90, 15);
       add(labelHora);
       
       JLabel hora = new JLabel(consulta.getHora());
       hora.setFont(new Font("Calibri", 0, 30));
       hora.setBounds(10, 10, 90, 50);
       add(hora);
       
       JLabel labelCompareceu = new JLabel();
       if (consulta.getCompareceu()==1)
           labelCompareceu.setText("Compareceu!");
       else if (consulta.getCompareceu()==0)
           labelCompareceu.setText("Não Compareceu!");
       else{
           JButton verde = new JButton("Compareceu");   
           verde.setBounds(250,40,90,15);
           add(verde);
           
           JButton vermelho = new JButton("Não Compareceu");   
           verde.setBounds(250,70,90,15);
           add(vermelho);
       }
       labelCompareceu.setFont(new Font("Calibri", 0, 11));
       labelCompareceu.setBounds(250, 60, 90, 15);
       add(labelCompareceu);
       
       JLabel icone= new JLabel();
       icone.setBounds(150, 10, 60, 80);
       File imagem = new File(localPath+"/Perfis/"+consulta.getIdPaciente()+".jpg");
       if (imagem.exists()){
            ImageIcon img = new ImageIcon(imagem.getAbsoluteFile().toString());
            img.setImage(img.getImage().getScaledInstance(60, 80, 100));
            icone.setIcon(img);
       }
       else{
            imagem = new File(localPath+"/src/View/perfil.png");
            ImageIcon img = new ImageIcon(imagem.getAbsoluteFile().toString());
            img.setImage(img.getImage().getScaledInstance(60, 80, 100));
            icone.setIcon(img);
       }
       add(icone);
       
       JLabel paciente = new JLabel("Paciente: "+Principal.controladorPaciente.getPacienteID(consulta.getIdPaciente()).getNome());
       paciente.setFont(new Font("Calibri", 0, 12));
       paciente.setBounds(250, 10, 300, 30);
       add(paciente);
       
       updateUI();
    }
    
    public boolean isPast(){
        Data data = consulta.getData();
        
        LocalDate dataAtual = LocalDate.now();
        int tAno = dataAtual.getYear();
        int tMes = dataAtual.getMonthValue();
        int tDia = dataAtual.getDayOfMonth();

        if ((data.ano < tAno) || ((data.mes < tMes) && (data.ano == tAno)) || ((data.dia < tDia) && (data.mes == tMes) && (data.ano == tAno)) )
            return true;
        else
            return false;
    }
}
/*
        
        JPanel temp = new JPanel();
        temp.setBounds(10, 10, 1500,200);
        temp.setBackground(Color.blue);
        jPanel1.add(temp);
        JLabel label = new JLabel("Digite um número:");
        label.setFont(new Font("Tahoma", 0, 11));
        label.setBounds(10, 10, 50, 20);
        temp.add(label);
        
        temp = new JPanel();
        temp.setBounds(10, 2000, 1500,200);
        temp.setBackground(Color.blue);
        jPanel1.add(temp);
        
        jPanel1.setPreferredSize(new Dimension(jPanel1.getWidth(), 9000));
        jPanel1.updateUI();
        jPanel1.repaint();
        jScrollPane1.setViewportView(jPanel1);
        jScrollPane1.updateUI();
        jScrollPane1.repaint();
        
*/