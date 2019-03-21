/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author slabouchei
 */
@Entity
public class Client implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idClient;
    private String prenom;
    private String nom;
    private String mdp;
    private String civilite;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private String adresse;
    private String telephone;
    private String courriel;
    private String signeZodiaque;
    private String signeChinois;
    private String couleurPB;
    private String animalTotem;
    @OneToMany(mappedBy="client")
    private List<Voyance> voyances;

    public Client() {
    }

    public Client(String prenom, String nom, String mdp, String civilite, Date dateNaissance, String adresse, String telephone, String courriel) {
        this.prenom = prenom;
        this.nom = nom;
        this.mdp = mdp;
        this.civilite = civilite;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.telephone = telephone;
        this.courriel = courriel;
        this.voyances = new ArrayList<Voyance>();
    }

    public void ajouteNouvelleVoyance(Voyance voyance) {
        voyances.add(voyance);
    }
        
    public Long getId() {
        return idClient;
    }

    public String getMdp() {
        return mdp;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getCivilite() {
        return civilite;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getCouleurPB() {
        return couleurPB;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }

    public void setId(Long id) {
        this.idClient = id;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public void setCouleurPB(String couleurPB) {
        this.couleurPB = couleurPB;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }
    
    

    
}
