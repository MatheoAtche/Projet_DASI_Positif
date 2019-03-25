/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author slabouchei
 */
@Entity
public class Voyance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String etat;
    private String commentaire;
    private String heureFin;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Medium medium;
    @ManyToOne
    private Employe employe;

    public Voyance() {
    }

    public Voyance(String commentaire, Date dateDebut, Client client, Medium medium, Employe employe) {
        this.etat = "Non débutée";
        this.commentaire = commentaire;
        this.dateDebut = dateDebut;
        this.client = client;
        this.medium = medium;
        this.employe = employe;
    }

    public Long getId() {
        return id;
    }

    public String getEtat() {
        return etat;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Client getClient() {
        return client;
    }

    public Medium getMedium() {
        return medium;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

}
