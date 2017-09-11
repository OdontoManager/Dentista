/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Consulta;
import Model.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class ControladorAgenda {
    public ArrayList<String> horarios;
    
    public ControladorAgenda(){
        horarios = horariosDisponiveis();
    }
    
    public void adicionarConsulta(Consulta consulta){
         
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OdontoManager","postgres", "123");
            c.setAutoCommit(false) ;

            stmt = c.createStatement();
            
            String sql = String.format("SELECT COUNT(COLUMN_NAME) AS resultado FROM INFORMATION_SCHEMA.COLUMNS "+
            "WHERE TABLE_NAME = 'agenda' AND  COLUMN_NAME = '%s'",consulta.getHora());
            
            ResultSet checkHour = stmt.executeQuery(sql);
            while ( checkHour.next()) {
                if (checkHour.getInt("resultado")==0){
                    sql = String.format(" ALTER TABLE agenda ADD COLUMN \"%s\" INT ", consulta.getHora());
                    stmt.executeUpdate(sql) ;
                    break;
                }
            }

            stmt.close();
            c.commit();
            c.close(); 
            
            
            horarios = horariosDisponiveis();
 
            
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OdontoManager","postgres", "123");
            c.setAutoCommit(false) ;
            stmt = c.createStatement();
            int count=0;
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM agenda WHERE Data='%s';",consulta.getData().toString())) ;
            while(rs.next())
                count++;
            stmt.close();
            c.close();
            
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OdontoManager","postgres", "123");
            c.setAutoCommit(false) ;
            stmt = c.createStatement();
            String sql2;
            if (count>0)
                sql2 = String.format("UPDATE agenda SET \"%s\" = '%s' WHERE \"data\" = '%s' ;", consulta.getHora(),consulta.getIdPaciente(), consulta.getData().toString());
            else
                sql2 = String.format("INSERT INTO agenda (\"data\", \"%s\") VALUES ('%s', '%s') ;", consulta.getHora(), consulta.getData().toString(), consulta.getIdPaciente());
            stmt.executeUpdate(sql2) ;
            stmt.close();
            c.commit();
            c.close(); 
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    
     public int ultimoID(){
        Connection c = null;
        Statement stmt = null;
        try{
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OdontoManager","postgres", "123");
            c.setAutoCommit(false) ;

            stmt = c.createStatement() ;
            ResultSet rs = stmt.executeQuery("SELECT max(ID) from agenda;");
            int value = Integer.valueOf(rs.getString(1));
            stmt.close();
            c.close(); 
            return value;
        }
        catch(Exception e){
            
        }

        return -1;
    }
    
    public ArrayList<Consulta> getConsultaDia(Data data){
        Connection c = null;
        Statement stmt = null;
        String dt = data.dia+"/"+data.mes+"/"+data.ano;
        try {
            ArrayList<Consulta> retorno = new ArrayList<>();
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OdontoManager","postgres", "123");
            c.setAutoCommit(false) ;

            stmt = c.createStatement() ;
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM agenda WHERE Data='%s';",dt)) ;
            while ( rs.next() ) {
                for (String hora: horarios){
                    if (rs.getString(hora)!=null){
                        String _idP = rs.getString(hora).replace("-", "");
                        _idP = _idP.replace("+","");
                        int compareceu;
                        if (rs.getString(hora).contains("-"))
                            compareceu = 0;
                        else if (rs.getString(hora).contains("+"))
                            compareceu = 1;
                        else
                            compareceu = -1;
                        retorno.add(new Consulta(Integer.valueOf(_idP),data,hora,compareceu,""));
                    }
                }
            }
            stmt.close();
            c.close();
            return retorno;
        } catch ( Exception e ) {
            System.err.println( e.getClass() .getName() + ": " + e.getMessage() );
        }
        return null;
    }
   
    public ArrayList<String> horariosDisponiveis(){
        Connection c = null;
        Statement stmt = null;
        System.out.println("j");
        try {
            ArrayList<String> lista= new ArrayList<>();
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OdontoManager","postgres", "123");
            c.setAutoCommit(false) ;

            stmt = c.createStatement() ;
           
            ResultSet rs = stmt.executeQuery("SELECT COLUMN_NAME AS resultado FROM "+
            "INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'agenda'") ;
            System.out.println("j");
            while(rs.next()){
                if (!rs.getString("resultado").equals("id") && !rs.getString("resultado").equals("data")){
                    lista.add(rs.getString("resultado"));
                }
            }
            stmt.close();
            c.close();
            return lista;
        }catch(Exception e){
                
        }
        return null;
    }
    
    public void deletar(int id){//CHANGE MEMU LEKOTE
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OdontoManager","postgres", "123");
            c.setAutoCommit(false) ;

            stmt = c.createStatement() ;
            stmt.executeUpdate("DELETE FROM  WHERE ID="+id+";") ;
            stmt.close();
            c.commit();
            c.close(); 
        } catch ( Exception e ) {
            System.err.println( e.getClass() .getName() + ": " + e.getMessage() );
        }
    }
    
}