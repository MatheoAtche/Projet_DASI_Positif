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
import metier.modele.Medium;
import metier.modele.Voyance;

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
     
    public static List<Employe> trouverEmployeCompetent(String medium) {
        String jpql = "select e from Employe e join e.mediums m where m.id= :medium and e.etat = :etat order by e.nbConsultations";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("medium", Integer.parseInt(medium));
        query.setParameter("etat", "Disponible");
        List<Employe> resultat = (List<Employe>) query.getResultList();
        return resultat;
    }
    
    public static void ajouterEmploye(Employe employe) {
        JpaUtil.obtenirEntityManager().persist(employe);
    }
    
    public static void majEmploye(Employe employe) {
        JpaUtil.obtenirEntityManager().merge(employe);
    }
    /*
    public static List<Employe> recevoirNotificationClient (Client client) {
        String jpql = "select e from Employe e join e.voyances v, v.client c where e.etat='Disponible' and v.etat='Non débutée' and c.idClient= :idClient";
        long idClient = client.getId();
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("idClient",idClient);
        List<Employe> resultat = (List<Employe>) query.getResultList();
        return resultat;
    }
    */
}
