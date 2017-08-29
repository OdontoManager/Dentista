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
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Data.db");
            c.setAutoCommit(false) ;

            stmt = c.createStatement();
            try{
                String sql = String.format(" ALTER TABLE Agenda ADD '%s' INT ", consulta.getHora());
                stmt.executeUpdate(sql) ;
                stmt.close();
                c.commit();
                c.close(); 
                horarios = horariosDisponiveis();
            }
            catch(Exception e){
                
            }
  
            
            c = DriverManager.getConnection("jdbc:sqlite:Data.db");
            c.setAutoCommit(false) ;
            stmt = c.createStatement();
            int count=0;
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM Agenda WHERE Data='%s';",consulta.getData().toString())) ;
            while(rs.next())
                count++;
            stmt.close();
            c.close();
            
            c = DriverManager.getConnection("jdbc:sqlite:Data.db");
            c.setAutoCommit(false) ;
            stmt = c.createStatement();
            String sql2;
            if (count>0)
                sql2 = String.format("UPDATE Agenda SET '%s' = '%s' WHERE Data = '%s' ;", consulta.getHora(),consulta.getIdPaciente(), consulta.getData().toString());
            else
                sql2 = String.format("INSERT INTO Agenda ('Data', '%s') VALUES ('%s', '%s') ;", consulta.getHora(), consulta.getData().toString(), consulta.getIdPaciente());
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
            c = DriverManager.getConnection("jdbc:sqlite:Data.db") ;
            c.setAutoCommit(false) ;

            stmt = c.createStatement() ;
            ResultSet rs = stmt.executeQuery("SELECT max(ID) from Agenda;");
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
            Class.forName("org.sqlite.JDBC") ;
            c = DriverManager.getConnection("jdbc:sqlite:Data.db") ;
            c.setAutoCommit(false) ;

            stmt = c.createStatement() ;
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM Agenda WHERE Data='%s';",dt)) ;
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
        try {
            ArrayList<Consulta> retorno = new ArrayList<>();
            Class.forName("org.sqlite.JDBC") ;
            c = DriverManager.getConnection("jdbc:sqlite:Data.db") ;
            c.setAutoCommit(false) ;

            stmt = c.createStatement() ;
           
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM Agenda")) ;
            ArrayList<String> lista= new ArrayList<>();
            int h=0, m=0;
            while(h<24){
                try{
                    String _h = String.valueOf(h);
                    String _m = String.valueOf(m);
                    if (h<10)
                        _h = "0"+_h;
                    if (m<10)
                        _m = "0"+_m;
                    rs.findColumn(_h+":"+_m);
                    lista.add(_h+":"+_m);
                }
                catch(Exception e){
                    
                }
                m++;
                if (m==60){
                    h++;
                    m=0;
                }
            }
            stmt.close();
            c.close();
            return lista;
        } catch ( Exception e ) {
            System.err.println( e.getClass() .getName() + ": " + e.getMessage() );
        }
        return null;
    }
    public void deletar(int id){//CHANGE MEMU LEKOTE
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC") ;
            c = DriverManager.getConnection("jdbc:sqlite:Data.db") ;
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