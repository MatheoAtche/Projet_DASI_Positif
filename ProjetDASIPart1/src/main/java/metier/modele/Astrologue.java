/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author slabouchei
 */
@Entity
public class Astrologue extends Medium {

    private String formation;
    private String promotion;
    
    public Astrologue() {
    }

    public Astrologue(String nom, String descriptif, String formation,String promotion) {
        super(nom, descriptif);
        this.formation = formation;
        this.promotion = promotion;
    }
    
    public String toString () {
        String chaine = super.toString();
        chaine += " - Formation : " + formation + " - Promotion : " + promotion;
        return chaine;
    }
}
