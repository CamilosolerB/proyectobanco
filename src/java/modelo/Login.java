/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author SENA
 */
public class Login {
    private String documento;
    private String usuario;
    private String password;
    private String rol;
    private String estado;
    private String foto;
    private String documentoc;
    private String nombre;
    private String apellido;
    private String correo;
    private String celular;
    private String sexo;
    private String fechanac;

    public Login(String documento, String usuario, String password, String rol, String estado, String foto, String documentoc, String nombre, String apellido, String correo, String celular, String sexo, String fechanac) {
        this.documento = documento;
        this.usuario = usuario;
        this.password = password;
        this.rol = rol;
        this.estado = estado;
        this.foto = foto;
        this.documentoc = documentoc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.celular = celular;
        this.sexo = sexo;
        this.fechanac = fechanac;
    }
    
    

    public Login(String usuario, String password, String rol, String foto,String nombre, String apellido) {
        this.usuario = usuario;
        this.password = password;
        this.rol = rol;
        this.foto = foto;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Login() {
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDocumentoc() {
        return documentoc;
    }

    public void setDocumentoc(String documentoc) {
        this.documentoc = documentoc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }
    
    
    
    public Login(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
}
