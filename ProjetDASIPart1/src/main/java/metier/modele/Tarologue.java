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
public class Tarologue extends Medium {

    public Tarologue() {
    }

    public Tarologue(String nom, String descriptif) {
        super(nom, descriptif);
    }
    
    
}
