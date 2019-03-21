/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.*;
import metier.modele.Client;
import metier.modele.Employe;

/**
 *
 * @author slabouchei
 */
public class ClientDAO {

    /*
    ** Rôle : Ajouter un client à la base
    ** Entrée : Un client
    ** Sortie : aucune
    */
    public static void ajouterClient(Client client) {
        JpaUtil.obtenirEntityManager().persist(client);
    }

    /*
    ** Rôle : Savoir si un client est déjà présent
    ** Entrée : Le courriel du client
    ** Sortie : Le client (s'il existe)
    */
    public static Client estPresentClient(String courriel) {
        
        Client resultat;
        String jpql = "select c from Client c where c.courriel = :courriel";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("courriel", courriel);
        try {
            resultat = (Client) query.getSingleResult();
        } catch(NoResultException e) {
            resultat = null;
        }
        
        return resultat;
        
    }
        
    /*
    ** Rôle : Mettre à jour un client
    ** Entrée : Un client
    ** Sortie : aucune
    */
    public static void majClient(Client client) {
        JpaUtil.obtenirEntityManager().merge(client);
    }
    
    /*
    ** Rôle : Recherche un client pour un employé donné
    ** Entrée : L'employé 
    ** Sortie : Le client
    */
    public static Client rechercherClientPourUnEmploye(Employe employe) {
        String jpql = "select c from Client c join c.voyances v where v.employe.idEmploye= :idEmploye and (v.etat='Non débutée' or v.etat='Débutée')";
        long idEmploye = employe.getId();
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        Client resultat = null;
        query.setParameter("idEmploye",idEmploye);
        try {
            resultat = (Client) query.getSingleResult();
        } catch(NoResultException e) {
            resultat = null;
        }
        return resultat;
    }
    
    
}
