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
import metier.modele.Voyance;


/**
 *
 * @author slabouchei
 */

public class VoyanceDAO {

    public static void creerVoyance(Voyance voyance) {
        JpaUtil.obtenirEntityManager().persist(voyance);
    
    }
    
    public static void majVoyance(Voyance voyance) {
        JpaUtil.obtenirEntityManager().merge(voyance);
    }
   
    public static List<Voyance> recevoirNotificationClient (Client client) {
        String jpql = "select v from Voyance v join v.employe e, v.client c where e.etat='Disponible' and c.idClient= :idClient";
        long idClient = client.getId();
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("idClient",idClient);
        List<Voyance> resultat = (List<Voyance>) query.getResultList();
        return resultat;
   }
    
    public static List<Voyance>rechercherVoyance(Client client, Employe employe) {
        String jpql = "select v from Voyance v join v.employe e, v.client c where e.idEmploye= :idEmploye and c.idClient= :idClient";
        long idClient = client.getId();
        long idEmploye = employe.getId();
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("idClient",idClient);
        query.setParameter("idEmploye",idEmploye);
        List<Voyance> resultat = (List<Voyance>) query.getResultList();
        return resultat;    
    }
   
}
