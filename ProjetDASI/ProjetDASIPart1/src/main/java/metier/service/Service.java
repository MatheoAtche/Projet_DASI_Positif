package metier.service;

import dao.ClientDAO;
import dao.EmployeDAO;
import dao.JpaUtil;
import dao.MediumDAO;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.RollbackException;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import util.AstroTest;
import util.Message;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author slabouchei
 */
public class Service {

    private static String profilConnecte;

    public static String getProfilConnecte() {
        return profilConnecte;
    }
    
    public static List<Medium> afficherMediums(){
        //if(profilConnecte.equals("Client")) {
            
            JpaUtil.creerEntityManager();
            List<Medium> mediums = MediumDAO.listerMediums();
            Iterator<Medium> iter = mediums.iterator(); 
            while (iter.hasNext()) { 
                System.out.println( iter.next());
            }
            JpaUtil.fermerEntityManager();
            return mediums;
        //}
    }
    
    public static void initialiserApplication () {
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        Employe e1 = new Employe("Nom1","Prenom1","Tel1","Courriel1","mdp1");
        Employe e2 = new Employe("Nom2","Prenom2","Tel1","Courriel2","mdp2");
        Employe e3 = new Employe("Nom3","Prenom3","Tel1","Courriel3","mdp2");
        Employe e4 = new Employe("Nom4","Prenom4","Tel1","Courriel4","mdp3");
        Employe e5 = new Employe("Nom5","Prenom5","Tel1","Courriel5","mdp4");
        
        try {
            EmployeDAO.ajouterEmploye(e1);
            EmployeDAO.ajouterEmploye(e2);
            EmployeDAO.ajouterEmploye(e3);
            EmployeDAO.ajouterEmploye(e4);
            EmployeDAO.ajouterEmploye(e5);
        } catch (RollbackException ex) {
                JpaUtil.fermerEntityManager();
        }
    
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        
    }
    
    public static void inscrireClient(Client client) {
        JpaUtil.creerEntityManager();

        if (ClientDAO.estPresentClient(client).isEmpty()) {

            JpaUtil.ouvrirTransaction();
            try {
                AstroTest astroTest = new AstroTest();
                List<String> profil = astroTest.getProfil(client.getPrenom(), client.getDateNaissance());
                client.setSigneZodiaque(profil.get(0));
                client.setSigneChinois(profil.get(1));
                client.setCouleurPB(profil.get(2));
                client.setAnimalTotem(profil.get(3));
            } catch (IOException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                JpaUtil.fermerEntityManager();
            }

            try {
                ClientDAO.ajouterClient(client);
                Message.envoyerMail(
                        "posit-if@contact.com",
                        client.getCourriel(),
                        "Validation de l'inscription",
                        "Félécitations, vous êtes inscrit sur Posit'if ! Vous pouvez dès à présent vous connecter sur la plateforme");

                JpaUtil.validerTransaction();
                JpaUtil.fermerEntityManager();
            } catch (RollbackException ex) {
                Message.envoyerMail(
                        "posit-if@contact.com",
                        client.getCourriel(),
                        "Erreur lors de l'inscription",
                        "Désolé, votre inscription sur la plateforme posit'if à échoué, veuillez réessayer ultérieurement");
                JpaUtil.fermerEntityManager();
            }

        }

    }

    public static boolean connexionUtilisateur(String courriel, String motDePasse) {

        JpaUtil.creerEntityManager();
        if (ClientDAO.rechercherClient(courriel, motDePasse).isEmpty()) {
            if (EmployeDAO.rechercherEmploye(courriel, motDePasse).isEmpty()) {
                profilConnecte = "Aucun";
                JpaUtil.fermerEntityManager();
                return false;
            } else {
                profilConnecte = "Employe";
            }
        } else {
            profilConnecte = "Client";
        }
    
        JpaUtil.fermerEntityManager();
        return true;
    }
    

}
