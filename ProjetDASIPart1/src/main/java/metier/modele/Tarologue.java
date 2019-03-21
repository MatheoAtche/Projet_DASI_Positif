/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;


import javax.persistence.Entity;


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
