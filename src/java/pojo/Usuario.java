/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;


import com.google.gson.annotations.Expose;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Francisco
 */

@Entity
@Table(name = "usuario", catalog = "whatsApp")
public class Usuario {
    @Expose
    @Id @GeneratedValue
    @Column(name = "idUsuario", nullable = false)
    private int idUsuario;
    
    @Expose
    @Column(name = "nombre",nullable = false)
    private String nombre;
    
    @Expose
    @Column(name = "telefono", nullable = false)
    private String telefono;
    
    @Expose
    @Column(name = "conectado", nullable = false)
    private boolean conectado;
    
    @Expose
    @Column(name = "fotografia", nullable = false)
    private String fotografia;
    
    @Expose
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
    
    @Expose
    @Column(name = "eliminado", nullable = false)
    private boolean eliminado;
    
    public Usuario(){
        
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the conectado
     */
    public boolean isConectado() {
        return conectado;
    }

    /**
     * @param conectado the conectado to set
     */
    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    /**
     * @return the fotografia
     */
    public String getFotografia() {
        return fotografia;
    }

    /**
     * @param fotografia the fotografia to set
     */
    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the eliminado
     */
    public boolean isEliminado() {
        return eliminado;
    }

    /**
     * @param eliminado the eliminado to set
     */
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    
    
    


}
