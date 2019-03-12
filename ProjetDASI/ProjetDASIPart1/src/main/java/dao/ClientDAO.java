/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.*;
import metier.modele.Client;

/**
 *
 * @author slabouchei
 */
public class ClientDAO {

    public static void ajouterClient(Client client) {
        JpaUtil.obtenirEntityManager().persist(client);
    }

    public static List<Client> estPresentClient(Client client) {
        String jpql = "select c from Client c where c.courriel = :mail";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("mail", client.getCourriel());
        List<Client> resultat = (List<Client>) query.getResultList();

        return resultat;
    }
    
    public static List<Client> rechercherClient (String courriel, String mdpasse){
        String jpql = "select c from Client c where c.courriel = :courriel and c.mdp = :mdpasse";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("courriel", courriel);
        query.setParameter("mdpasse", mdpasse);
        List<Client> resultat = (List<Client>) query.getResultList();
        return resultat;
    }

}
