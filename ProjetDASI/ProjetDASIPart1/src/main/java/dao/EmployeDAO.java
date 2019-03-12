/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import metier.modele.Client;
import metier.modele.Employe;

/**
 *
 * @author slabouchei
 */
public class EmployeDAO {
    
     public static List<Employe> rechercherEmploye (String courriel, String mdpasse){
         
        String jpql = "select e from Employe e where e.courriel = :courriel and e.mdp = :mdpasse";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("courriel", courriel);
        query.setParameter("mdpasse", mdpasse);
        List<Employe> resultat = (List<Employe>) query.getResultList();
        return resultat;
    }
     
    public static void ajouterEmploye(Employe employe) {
        JpaUtil.obtenirEntityManager().persist(employe);
    }
}
