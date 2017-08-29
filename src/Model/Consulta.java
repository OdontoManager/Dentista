/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author lucas
 */
public class Consulta {
    private int idPaciente;
    private Data data;
    private String hora;
    private int compareceu;
    private String tipo;

    public Consulta(int idPaciente, Data data,String hora, int compareceu, String tipo) {
        this.idPaciente = idPaciente;
        this.data = data;
        this.tipo = tipo;
        this.compareceu = compareceu;
        this.hora = hora;
    }

    public void setCompareceu(int compareceu) {
        this.compareceu = compareceu;
    }

    public String getHora() {
        return hora;
    }

    public int getCompareceu() {
        return compareceu;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public Data getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }
    
    
}
