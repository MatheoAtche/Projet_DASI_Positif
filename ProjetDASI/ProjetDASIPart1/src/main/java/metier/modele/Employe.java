/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author slabouchei
 */
@Entity
public class Employe implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEmploye;
    private String nom;
    private String prenom;
    private String telephone;
    private String courriel;
    private String mdp;
    private String etat;
    @OneToMany(mappedBy="employe")
    private List<Voyance> voyances;
    @ManyToMany(mappedBy="employes")
    private List<Medium> mediums;

    public Employe() {
    }

    public Employe(String nom, String prenom, String telephone, String courriel, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.courriel = courriel;
        this.mdp = mdp;
    }

    public void setIdEmploye(Long idEmploye) {
        this.idEmploye = idEmploye;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Long getIdEmploye() {
        return idEmploye;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getMdp() {
        return mdp;
    }

    public String getEtat() {
        return etat;
    }


    
    public Long getId() {
        return idEmploye;
    }



}
