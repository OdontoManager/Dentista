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
public class Paciente {
    private int id;
    private String nome;
    private String rg;
    private Data data;
    private Convenio convenio;
    private String telefone;
    private String celular;
    private Endereco endereco;

    public Paciente(int id, String nome, String rg, Data data, Convenio convenio, String telefone, String celular, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.data = data;
        this.convenio = convenio;
        this.telefone = telefone;
        this.celular = celular;
        this.endereco = endereco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRg() {
        return rg;
    }

    public Data getData() {
        return data;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCelular() {
        return celular;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    
}
