/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author slabouchei
 */
@Entity
@Inheritance (strategy=InheritanceType.JOINED)
public class Medium implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String descriptif;
    @OneToMany(mappedBy="medium")
    private List<Voyance> voyances;
    @ManyToMany
    private List<Employe> employes;

    public Medium() {
    }

    public Medium(String nom, String descriptif) {
        this.nom = nom;
        this.descriptif = descriptif;
        this.voyances = new ArrayList<Voyance>();
        this.employes = new ArrayList<Employe>();
    }

    public void ajouteNouvelleVoyance(Voyance voyance) {
        voyances.add(voyance);
    }
    public void ajouteEmploye (Employe e) {
        employes.add(e);
    }
    
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString () {
        String chaine = "ID : " + id + " - Nom : " + nom + " - Description : " + descriptif;
        return chaine;
    }
    
}
