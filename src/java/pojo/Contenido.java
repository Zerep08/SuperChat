/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "contenido", catalog = "whatsApp")
public class Contenido {
@Id @GeneratedValue    
 @Column(name = "idContenido")
private int idContenido;

@Column(name = "texto")
private String texto;

@Column(name = "emoji")
private String emoji;

public Contenido(){
    
}

    /**
     * @return the idContenido
     */
    public int getIdContenido() {
        return idContenido;
    }

    /**
     * @param idContenido the idContenido to set
     */
    public void setIdContenido(int idContenido) {
        this.idContenido = idContenido;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the emoji
     */
    public String getEmoji() {
        return emoji;
    }

    /**
     * @param emoji the emoji to set
     */
    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
}

