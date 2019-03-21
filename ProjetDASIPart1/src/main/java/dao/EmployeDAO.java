/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import metier.modele.Employe;

/**
 *
 * @author slabouchei
 */
public class EmployeDAO {
    
    /*
    ** Rôle : Savoir si un employé est présent
    **        dans la base
    ** Entrée : Le courriel
    ** Sortie : L'employe (s'il existe)
    */
    public static Employe estPresentEmploye(String courriel) {
        
        String jpql = "select e from Employe e where e.courriel = :mail";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("mail", courriel);
        Employe resultat = null;
        try {
            resultat = (Employe) query.getSingleResult();
        } catch(NoResultException e) {
            resultat = null;
        }
        return resultat;
    }
       
    
    /*
    ** Rôle : Trouver l'employé compétent
    **        pour un médium donné
    ** Entrée : L'identifiant du médium
    ** Sortie : La liste des employés possibles
    **          triées par ordre de consultations
    **          déjà faites
    */
    public static List<Employe> trouverEmployeCompetent(int medium) {
        
        String jpql = "select e from Employe e join e.mediums m where m.id= :medium and e.etat = :etat order by e.nbConsultations";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("medium",medium);
        query.setParameter("etat", "Disponible");
        List<Employe> resultat = (List<Employe>) query.getResultList();
        return resultat;
    }
    
    /*
    ** Rôle : Ajouter un employé dans la base
    ** Entrée : L'employé
    ** Sortie : aucune
    */
    public static void ajouterEmploye(Employe employe) {
        
        JpaUtil.obtenirEntityManager().persist(employe);
    }
    
    /*
    ** Rôle : Mettre à jour un employé dans la base
    ** Entrée : L'employé
    ** Sortie : aucune
    */
    public static void majEmploye(Employe employe) {
        
        JpaUtil.obtenirEntityManager().merge(employe);
    }
    
    
}
