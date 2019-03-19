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
public class Voyant extends Medium {

    private String specialite;

    public Voyant() {
    }

    public Voyant(String nom, String descriptif,String specialite) {
        super(nom, descriptif);
        this.specialite = specialite;
    }
    
    public String toString () {
        String chaine = super.toString();
        chaine += " - Specialité : " + specialite;
        return chaine;
    }
    
    
}
