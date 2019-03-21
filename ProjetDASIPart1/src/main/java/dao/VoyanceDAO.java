/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Voyance;


/**
 *
 * @author slabouchei
 */

public class VoyanceDAO {

    /*
    ** Rôle : Créer une voyance
    **        dans la base de données
    ** Entrée : La voyance
    ** Sortie : aucun
    */
    public static void creerVoyance(Voyance voyance) {
        
        JpaUtil.obtenirEntityManager().persist(voyance);
    
    }
    
    /*
    ** Rôle : Mettre à jour une voyance
    **        dans la base de données
    ** Entrée : La voyance
    ** Sortie : aucun
    */
    public static void majVoyance(Voyance voyance) {
        
        JpaUtil.obtenirEntityManager().merge(voyance);
    }
   
    /*
    ** Rôle : Chercher une voyance en attente d'un client
    **        et d'un employé donnés
    ** Entrée : Le client et l'employé
    ** Sortie : La voyance trouvée (si elle existe)
    */
    public static Voyance rechercherVoyanceDunClientPourUnEmploye(Client client, Employe employe) {
        
        String jpql = "select v from Voyance v where v.employe.idEmploye= :idEmploye and v.client.idClient= :idClient and (v.etat='Non débutée' or v.etat='Débutée')";
        long idClient = client.getId();
        long idEmploye = employe.getId();
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("idClient",idClient);
        query.setParameter("idEmploye",idEmploye);
        Voyance resultat = null;
        try {
            resultat = (Voyance) query.getSingleResult();
        }catch(NoResultException e) {
            resultat = null;
        }
        return resultat;
    }
    
    /*
    ** Rôle : Rechercher les voyances faites 
    **        par un client
    ** Entrée : Le client
    ** Sortie : La liste des voyances faites
    */
    public static List<Voyance> rechercheVoyancesFaitesClient (Client client) {
        
        String jpql = "select v from Voyance v where v.client.idClient= :idClient and v.etat = 'Terminée' order by v.dateDebut";
        System.out.println(client.getId());
        long idClient = client.getId();
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("idClient",idClient);
        List<Voyance> resultat = (List<Voyance>) query.getResultList();
        return resultat;    
    }
    
    /*
    ** Rôle : Le nombre de voyances faites
    **        pour chaque médium
    ** Entrée : aucun
    ** Sortie : La liste des résultats
    */
    public static List<Object[]> statsNbVoyancesParMedium () {
        
        String jpql = "select v.medium, COUNT(v.id) from Voyance v group by v.medium";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        List<Object[]> resultat = (List<Object[]>) query.getResultList();
        return resultat;    
    }
    
    /*
    ** Rôle : Parcourt des voyances pour retourner
    **        le nombre de clients distincts par employé
    ** Entrée : aucun
    ** Sortie : La liste des résultats
    */
    public static List<Object[]> statsNbClientsDistinctsEmploye () {
        
        String jpql = "select v.employe, COUNT(distinct(v.client)) from Voyance v group by v.employe";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        List<Object[]> resultat = (List<Object[]>) query.getResultList();
        return resultat;    
    }
    
    /*
    ** Rôle : Parcourt des voyances pour retourner
    **        le nombre de clients distincts par employé
    ** Entrée : aucun
    ** Sortie : La liste des résultats
    */
    public static List<Object[]> statsNbClientsTotalEmploye () {
        
        String jpql = "select v.employe, COUNT(v.client) from Voyance v group by v.employe";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        List<Object[]> resultat = (List<Object[]>) query.getResultList();
        return resultat;    
    }
   
}
