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
 * @author Yhair
 */
@Entity
@Table(name="mensaje",catalog="whatsApp")
public class Mensaje {
    
    @Id @GeneratedValue
    @Column(name="idMensaje")
    private  int idMensaje;
    
    @Column(name="leido",nullable=false)
    private boolean leido;
    
    @Column(name="enviado",nullable=false)
    private boolean enviado;
    
    @Column(name="recibido",nullable=false)
    private boolean recibido;
    
    @Column(name="dt_leido",nullable=false)
    private Date dt_leido;
    
    @Column(name="dt_enviado",nullable=false)
    private Date dt_enviado;
    
    @Column(name="dt_recibido",nullable=false)
    private Date dt_recibido;
    
    @ManyToOne
    @Column(name="idContenido",nullable=false)
    private Contenido idContenido;
    
    @ManyToOne
    @Column(name="idUsuario",nullable=false)
    private Usuario idUsuario;
    
    @ManyToOne
    @JoinColumn(name="idContacto",nullable=false)
    private Contacto idContacto;
    
    public Mensaje(){
        
    }
    
    /**
     * @return the idMensaje
     */
    public int getIdMensaje() {
        return idMensaje;
    }

    /**
     * @param idMensaje the idMensaje to set
     */
    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    /**
     * @return the leido
     */
    public boolean isLeido() {
        return leido;
    }

    /**
     * @param leido the leido to set
     */
    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    /**
     * @return the enviado
     */
    public boolean isEnviado() {
        return enviado;
    }

    /**
     * @param enviado the enviado to set
     */
    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    /**
     * @return the recibido
     */
    public boolean isRecibido() {
        return recibido;
    }

    /**
     * @param recibido the recibido to set
     */
    public void setRecibido(boolean recibido) {
        this.recibido = recibido;
    }

    /**
     * @return the dt_leido
     */
    public Date getDt_leido() {
        return dt_leido;
    }

    /**
     * @param dt_leido the dt_leido to set
     */
    public void setDt_leido(Date dt_leido) {
        this.dt_leido = dt_leido;
    }

    /**
     * @return the dt_enviado
     */
    public Date getDt_enviado() {
        return dt_enviado;
    }

    /**
     * @param dt_enviado the dt_enviado to set
     */
    public void setDt_enviado(Date dt_enviado) {
        this.dt_enviado = dt_enviado;
    }

    /**
     * @return the dt_recibido
     */
    public Date getDt_recibido() {
        return dt_recibido;
    }

    /**
     * @param dt_recibido the dt_recibido to set
     */
    public void setDt_recibido(Date dt_recibido) {
        this.dt_recibido = dt_recibido;
    }

    /**
     * @return the idContenido
     */
    public Contenido getIdContenido() {
        return idContenido;
    }

    /**
     * @param idContenido the idContenido to set
     */
    public void setIdContenido(Contenido idContenido) {
        this.idContenido = idContenido;
    }

    /**
     * @return the idUsuario
     */
    public Usuario getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the idContacto
     */
    public Contacto getIdContacto() {
        return idContacto;
    }

    /**
     * @param idContacto the idContacto to set
     */
    public void setIdContacto(Contacto idContacto) {
        this.idContacto = idContacto;
    }
    
    
    
    
}
