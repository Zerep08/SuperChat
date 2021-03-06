/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 *
 * @author Francisco
 */
@Entity
@Table(name="contacto", catalog="whatsApp")
public class Contacto {
    
 @Id @GeneratedValue
 @Column(name="idContacto", nullable = false)
 private int idUsuario;
 
 @ManyToOne
 @JoinColumn(name="idUsuarioA", nullable = false)
 private Usuario idUsuarioA;
 
 @ManyToOne
 @JoinColumn(name = "idUsuarioB", nullable=false)
 private Usuario idUsuarioB;

 public Contacto(){
     
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
     * @return the idUsuarioA
     */
    public Usuario getIdUsuarioA() {
        return idUsuarioA;
    }

    /**
     * @param idUsuarioA the idUsuarioA to set
     */
    public void setIdUsuarioA(Usuario idUsuarioA) {
        this.idUsuarioA = idUsuarioA;
    }

    /**
     * @return the idUsuarioB
     */
    public Usuario getIdUsuarioB() {
        return idUsuarioB;
    }

    /**
     * @param idUsuarioB the idUsuarioB to set
     */
    public void setIdUsuarioB(Usuario idUsuarioB) {
        this.idUsuarioB = idUsuarioB;
    }
 
}
