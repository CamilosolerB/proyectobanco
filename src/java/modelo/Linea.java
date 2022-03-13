/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author PC
 */
public class Linea {
    private String codigo;
    private String numero;
    private String monto;
    private int plazo;

    public Linea(String codigo, String numero, String monto, int plazo) {
        this.codigo = codigo;
        this.numero = numero;
        this.monto = monto;
        this.plazo = plazo;
    }

    public Linea(String codigo) {
        this.codigo = codigo;
    }

    public Linea() {
    }
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }
    
    
}
