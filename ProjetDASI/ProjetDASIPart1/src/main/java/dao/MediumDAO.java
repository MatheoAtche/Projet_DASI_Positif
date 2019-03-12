/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author slabouchei
 */

import java.util.List;
import javax.persistence.Query;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;

public class MediumDAO {
    
    public static List<Medium> listerMediums() {
        String jpql = "select m from Medium";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        List<Medium> resultat = (List<Medium>) query.getResultList();
        return resultat;
    }
    
}
