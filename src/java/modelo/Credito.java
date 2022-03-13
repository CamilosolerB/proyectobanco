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
public class Credito {
    private String codigo;
    private String documento;
    private String codlinea;
    private int  monto;
    private String fecha;
    private int plazo;

    public Credito(String codigo, String documento, String codlinea, int monto, String fecha, int plazo) {
        this.codigo = codigo;
        this.documento = documento;
        this.codlinea = codlinea;
        this.monto = monto;
        this.fecha = fecha;
        this.plazo = plazo;
    }

    public Credito() {
    
    }

    public Credito(String codigo) {
        this.codigo = codigo;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCodlinea() {
        return codlinea;
    }

    public void setCodlinea(String codlinea) {
        this.codlinea = codlinea;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }
    
    
}
