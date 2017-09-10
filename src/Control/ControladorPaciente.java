    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Convenio;
import Model.Data;
import Model.Endereco;
import Model.Paciente;
import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author lucas
 */
public class ControladorPaciente {
    
//    private ArrayList<Paciente> pacientes = new ArrayList<>();
    
    
    public int adicionarPaciente(Paciente p){
        String nome = p.getNome();
        String rg = p.getRg();
        Data data = p.getData();
        Convenio convenio = p.getConvenio();
        String telefone = p.getTelefone();
        String celular = p.getCelular();
        String endEstado = p.getEndereco().getEstado();
        String endCidade = p.getEndereco().getCidade();
        String endBairro = p.getEndereco().getBairro();
        String endRua = p.getEndereco().getRua();
        String endNumero = p.getEndereco().getNumero();
        String endComplemento = p.getEndereco().getComplemento();
        //Endereco endereco = new Endereco(endEstado, endCidade, endBairro,endRua, endNumero, endComplemento);
        
        //Paciente p = new Paciente(nome,rg,data,convenio,telefone,celular,endereco);
        
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OdontoManager",
            "postgres", "123");
            c.setAutoCommit(false) ;

            stmt = c.createStatement();
            String sql = "INSERT INTO Pacientes (Nome, RG, DataNascimento, Telefone, Celular,"
                    + "Estado, Cidade, Bairro, Rua, Número, Complemento) " +
                       String.format("VALUES ('%s', '%s', '%s', '%s', '%s', '%s','%s','%s','%s','%s','%s') ;",nome, rg,
                               data.toString(),telefone,celular, endEstado, endCidade,endBairro,endRua,endNumero,endComplemento); 
            stmt.executeUpdate(sql) ;
            stmt.close();
            c.commit();
            c.close(); 
            return ultimoID();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return -1;
    }
    public int atualizarPaciente(Paciente p){
        String nome = p.getNome();
        String rg = p.getRg();
        Data data = p.getData();
        Convenio convenio = p.getConvenio();
        String telefone = p.getTelefone();
        String celular = p.getCelular();
        String endEstado = p.getEndereco().getEstado();
        String endCidade = p.getEndereco().getCidade();
        String endBairro = p.getEndereco().getBairro();
        String endRua = p.getEndereco().getRua();
        String endNumero = p.getEndereco().getNumero();
        String endComplemento = p.getEndereco().getComplemento();
        
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OdontoManager",
            "postgres", "123");
            c.setAutoCommit(false) ;

            stmt = c.createStatement();
            String sql = String.format("UPDATE Pacientes SET Nome = '%s', RG='%s', DataNascimento='%s', Telefone='%s', Celular='%s',"
                    + "Estado='%s', Cidade='%s', Bairro='%s', Rua='%s', Número='%s', Complemento='%s' WHERE ID = %s;",
                    nome, rg, data.toString(),telefone,celular, endEstado, endCidade,endBairro,endRua,endNumero,endComplemento,p.getId()); 
            stmt.executeUpdate(sql) ;
            stmt.close();
            c.commit();
            c.close();
            return p.getId();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return -1;
    }
    
    public ArrayList<Paciente> getPacientes(){
        Connection c = null;
        Statement stmt = null;
        try {
            ArrayList<Paciente> retorno = new ArrayList<>();
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OdontoManager",
            "postgres", "123");
            c.setAutoCommit(false) ;

            stmt = c.createStatement() ;
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Pacientes;" ) ;
            while ( rs.next() ) {
               String dt[] = rs.getString("DataNascimento").split("/");
               Paciente p = new Paciente(rs.getInt("ID"), rs.getString("Nome"),rs.getString("RG"),
                       new Data(Integer.valueOf(dt[0]),Integer.valueOf(dt[1]),Integer.valueOf(dt[2])),new Convenio("SUS","xxxxx") ,rs.getString("Telefone"),rs.getString("Celular"),
                       new Endereco(rs.getString("Estado"),rs.getString("Cidade"),rs.getString("Bairro"),rs.getString("Rua"),
                               rs.getString("Número"),rs.getString("Complemento")));
               retorno.add(p);
            }
            stmt.close();
            c.close();
            return retorno;
        } catch ( Exception e ) {
            System.err.println( e.getClass() .getName() + ": " + e.getMessage() );
        }
        return null;
    }
    
    public Paciente getPacienteID(int id){
        Connection c = null;
         Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OdontoManager",
            "postgres", "123");
            c.setAutoCommit(false) ;

            stmt = c.createStatement() ;
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Pacientes WHERE ID="+id+";") ;
            while ( rs.next() ) {
               String dt[] = rs.getString("DataNascimento").split("/");
               Paciente p = new Paciente(rs.getInt("ID"), rs.getString("Nome"),rs.getString("RG"),
                       new Data(Integer.valueOf(dt[0]),Integer.valueOf(dt[1]),Integer.valueOf(dt[2])),new Convenio("SUS","xxxxx") ,rs.getString("Telefone"),rs.getString("Celular"),
                       new Endereco(rs.getString("Estado"),rs.getString("Cidade"),rs.getString("Bairro"),rs.getString("Rua"),
                               rs.getString("Número"),rs.getString("Complemento")));
                stmt.close();
                c.close(); 
               return p;
            }
            stmt.close();
            c.close(); 
        } catch ( Exception e ) {
            System.err.println( e.getClass() .getName() + ": " + e.getMessage() );
        }
        return null;
    }
    public int ultimoID(){
         Connection c = null;
         Statement stmt = null;
        try{
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OdontoManager",
            "postgres", "123");
            c.setAutoCommit(false) ;

            stmt = c.createStatement() ;
            ResultSet rs = stmt.executeQuery("SELECT max(ID) from Pacientes;");
            int value = Integer.valueOf(rs.getString(1));
            stmt.close();
            c.close(); 
            return value;
        }
        catch(Exception e){
            
        }

        return -1;
    }
    
    
    
    public void deletar(int id){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OdontoManager",
            "postgres", "123");
            c.setAutoCommit(false) ;

            stmt = c.createStatement() ;
            stmt.executeUpdate("DELETE FROM Pacientes WHERE ID="+id+";") ;
            stmt.close();
            c.commit();
            c.close(); 
        } catch ( Exception e ) {
            System.err.println( e.getClass() .getName() + ": " + e.getMessage() );
        }
    }
}
