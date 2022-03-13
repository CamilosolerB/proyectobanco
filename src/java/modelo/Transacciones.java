/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author SENA
 */
public class Transacciones {
    private String documentome;
    private String documentores;
    private int valor;

    public Transacciones(String documentome, String documentores, int valor) {
        this.documentome = documentome;
        this.documentores = documentores;
        this.valor = valor;
    }

    public Transacciones(String documentome, int valor) {
        this.documentome = documentome;
        this.valor = valor;
    }


    public String getDocumentome() {
        return documentome;
    }

    public void setDocumentome(String documentome) {
        this.documentome = documentome;
    }

    public String getDocumentores() {
        return documentores;
    }

    public void setDocumentores(String documentores) {
        this.documentores = documentores;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
}
