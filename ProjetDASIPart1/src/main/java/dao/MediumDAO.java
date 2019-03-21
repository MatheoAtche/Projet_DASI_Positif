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
import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.Voyance;

public class MediumDAO {

    /*
    ** Rôle : Avoir la liste de tous les médiums
    ** Entrée : aucun
    ** Sortie : La liste de tous les médiums
    */
    public static List<Medium> listerMediums() {

        String jpql = "select m from Medium m";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        return (List<Medium>) query.getResultList();
    }

    /*
    ** Rôle : Ajouter un médium à la base
    ** Entrée : Le médium à ajouter
    ** Sortie : aucun
    */
    public static void ajouterMedium(Medium medium) {
        
        JpaUtil.obtenirEntityManager().persist(medium);
    }

    /*
    ** Rôle : Chercher un médium particulier
    ** Entrée : L'identifiant du médium
    ** Sortie : Le médium trouvé
    */
    
    public static Medium chercherMedium(int idMedium) {
        
        String jpql = "select m from Medium m where m.id= :idMedium";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("idMedium", idMedium);
        Medium resultat = (Medium) query.getSingleResult();
        return resultat;
    }

    /*
    ** Rôle : Mettre à jour un médium
    ** Entrée : Le médium
    ** Sortie : aucun
    */
    public static void majMedium(Medium medium) {
        
        JpaUtil.obtenirEntityManager().merge(medium);
    }

    /*
    ** Rôle : Trouver le médium incarné par un employé
    **        pour la voyance courrante
    ** Entrée : Le client et l'employé
    ** Sortie : Le médium trouvé
    */
    public static Medium trouverMediumIncarneParLemploye(Client client, Employe employe) {
        
        String jpql = "select m from Medium m join m.voyances v where v.employe.idEmploye= :idEmploye and v.etat='Débutée' and v.client.idClient= :idClient";
        long idClient = client.getId();
        long idEmploye = employe.getId();
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("idClient", idClient);
        query.setParameter("idEmploye", idEmploye);
        Medium resultat = (Medium) query.getSingleResult();
        return resultat;
    }

}
