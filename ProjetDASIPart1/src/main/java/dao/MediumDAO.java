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
import metier.modele.Voyant;

public class MediumDAO {
    
    public static List<Medium> listerMediums() {
        String jpql = "select m from Medium m";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        return (List<Medium>) query.getResultList();
    }
    
    public static void ajouterMedium(Medium medium) {
        JpaUtil.obtenirEntityManager().persist(medium);
    }
    
    public static List<Medium> chercherMedium (String idMedium) {
        String jpql = "select m from Medium m where m.id= :idMedium";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("idMedium", Integer.parseInt(idMedium));
        List<Medium> resultat = (List<Medium>) query.getResultList();
        return resultat;
    }
    
    public static void majMedium(Medium medium) {
        JpaUtil.obtenirEntityManager().merge(medium);
    }
    
    public static List<Medium> rechercherMedium (Client client, Employe employe) {
        String jpql = "select m from Medium m join m.employes e, m.voyances v, v.client c where e.idEmploye= :idEmploye and c.idClient= :idClient";
        long idClient = client.getId();
        long idEmploye = employe.getId();
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("idClient",idClient);
        query.setParameter("idEmploye",idEmploye);
        List<Medium> resultat = (List<Medium>) query.getResultList();
        return resultat;
    }
    
}
