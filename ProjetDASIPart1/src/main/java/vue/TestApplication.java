package vue;

import dao.JpaUtil;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.Voyance;
import metier.service.Service;
import util.Saisie;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author slabouchei
 */
public class TestApplication {

    private static Client clientConnecte;
    private static Employe employeConnecte;
    private static String profilConnecte;

    public static void main(String[] args) throws ParseException, IOException {

        JpaUtil.init();
        Service.initialiserApplication();
       
        clientConnecte = new Client();
        employeConnecte = new Employe();
        profilConnecte = "Aucun";

        /**
         * ******* On teste le client *********
         */
        
        //Inscription
        /*
        System.out.println("*********** Inscription client ***********");
        String civiliteInscription = Saisie.lireChaine("Entrez votre civilité : ");
        String nomInscription = Saisie.lireChaine("Entrez votre nom : ");
        String prenomInscription = Saisie.lireChaine("Entrez votre prénom : ");
        String courrielInscription = Saisie.lireChaine("Entrez votre courriel : ");
        String mdpInscription = Saisie.lireChaine("Entrez votre mot de passe : ");
        String adresseInscription = Saisie.lireChaine("Entrez votre adresse : ");
        String telInscription = Saisie.lireChaine("Entrez votre téléphone : ");
        int anneeInscription = Saisie.lireInteger("Entrez votre année de naissance au format YYYY : ");
        int moisInscription = Saisie.lireInteger("Entrez votre mois de naissance au format MM : ");
        int jourInscription = Saisie.lireInteger("Entrez votre jour de naissance au format JJ : ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(new Date());
        Date date = format.parse(Integer.toString(anneeInscription)+"-"
                                    +Integer.toString(moisInscription)+"-"
                                    +Integer.toString(jourInscription)); 
        Client clientInscription = new Client(prenomInscription,nomInscription,mdpInscription,
                                                civiliteInscription,date,adresseInscription,telInscription,
                                                courrielInscription);
        
        Service.inscrireClient(clientInscription);
        */
         
        
        //Connexion client
        
        System.out.println("*********** Connection client ***********");
        String courrielConnexion = Saisie.lireChaine("Courriel : ");
        String mdpConnexion = Saisie.lireChaine("Mot de passe : ");
        
        clientConnecte = Service.connexionClient(courrielConnexion,mdpConnexion);
        employeConnecte = Service.connexionEmploye(courrielConnexion,mdpConnexion);
        if (clientConnecte != null) {
            profilConnecte = "Client";
        } else if ( employeConnecte != null) {
            profilConnecte = "Employe";
        } else {
            System.out.println("Login inconnu");
        }
        
        
        //Affichage du profil
        
        System.out.println("*********** Affichage du profil client ***********");
        if(profilConnecte.equals("Client")) {
            List<String> profilClient = Service.afficherProfilClient(clientConnecte);
            Iterator<String> iter = profilClient.iterator(); 
            while (iter.hasNext()) { 
                System.out.println(iter.next());
            }
        }
        
       
        //Affichage de l'historique
        
        System.out.println("*********** Affichage de l'historique client ***********");
        if(profilConnecte.equals("Client")) {
            List<Voyance> historiqueClient = Service.afficherHistoriqueClient(clientConnecte);
            Iterator<Voyance> iter3 = historiqueClient.iterator(); 
            while (iter3.hasNext()) { 
                Voyance v = iter3.next();
                System.out.println(v.getDateDebut()+ " " + v.getHeureFin() + " " + v.getMedium().getNom()+ " " + v.getCommentaire() );
            }
        }
        
        
        //Affichage des médiums et en choisir un
        
        System.out.println("*********** Choix du médium ***********");
        if(profilConnecte.equals("Client")) {    
            
            List<Medium> mediumsVoyant = Service.afficherMediums();
            Iterator<Medium> iter = mediumsVoyant.iterator(); 
            while (iter.hasNext()) { 
                System.out.println( iter.next());
            }
            
            int mediumSouhaite = Saisie.lireInteger("Indiquez l'ID  du médium souhaité : ");
            Service.creerVoyance(clientConnecte,mediumSouhaite);
      
        }
        profilConnecte = Service.deconnecterUtilisateur();
        
        /**
         * ******* On teste l'employé *********
         */
        
         //Connexion
        
        System.out.println("*********** Connection employé ***********");
        String courrielConnexion2 = Saisie.lireChaine("Courriel : ");
        String mdpConnexion2 = Saisie.lireChaine("Mot de passe : ");
        
        clientConnecte = Service.connexionClient(courrielConnexion2,mdpConnexion2);
        employeConnecte = Service.connexionEmploye(courrielConnexion2,mdpConnexion2);
        if (clientConnecte != null) {
            profilConnecte = "Client";
        } else if ( employeConnecte != null) {
            profilConnecte = "Employe";
        } else {
            System.out.println("Login inconnu");
        }
        
       
        //Acceptation d'une notification + Générateur de phrases + terminer la voyance
        // + Consultation du profil client
        
        if(profilConnecte.equals("Employe")) {
            
            System.out.println("*********** Rechercher une voyance ***********");
            Voyance voyanceAFaire = Service.trouverVoyanceAFaireDunEmploye(employeConnecte);
            
            
            if(voyanceAFaire != null) {
 
                //Profil client
                
                System.out.println("*********** Afficher profil client ***********");
                List<String> profilClient = Service.afficherProfilClient(voyanceAFaire.getClient());
                Iterator<String> iter = profilClient.iterator(); 
                while (iter.hasNext()) { 
                    System.out.println(iter.next());
                }
                
           
                //Historique client
                
                System.out.println("*********** Afficher historique client ***********");
                List<Voyance> historiqueClient = Service.afficherHistoriqueClient(voyanceAFaire.getClient());
                Iterator<Voyance> iter3 = historiqueClient.iterator(); 
                while (iter3.hasNext()) { 
                    Voyance v = iter3.next();
                    System.out.println(v.getDateDebut()+ " " + v.getHeureFin() + " " + v.getMedium().getNom()+ " " + v.getCommentaire() );
                }
                
                
                //On accepte la demande de voyance
                System.out.println("*********** Démarrer la voyance ***********");
                voyanceAFaire = Service.demarrerVoyance(employeConnecte);
            
                //Générateur
                
                System.out.println("*********** Générer phrases ***********");
                int amour = Saisie.lireInteger("Amour : ",Arrays.asList(1,2,3,4));
                int sante = Saisie.lireInteger("Santé : ",Arrays.asList(1,2,3,4));
                int travail = Saisie.lireInteger("Travail : ",Arrays.asList(1,2,3,4));

                List<String> phraseGenerees = Service.generePhrases(voyanceAFaire.getClient().getCouleurPB(),voyanceAFaire.getClient().getAnimalTotem(),amour,sante,travail);
                Iterator<String> iter2 = phraseGenerees.iterator(); 
                while (iter2.hasNext()) { 
                    System.out.println( iter2.next());
                }
                
                
                
                //Terminer la voyance
                
                System.out.println("*********** Terminer voyance ***********");
                String commentaire = Saisie.lireChaine("Indiquez votre commentaire : ");
                String heureFin = Saisie.lireChaine("Indiquez l'heure de fin : ");
                Service.terminerVoyance(voyanceAFaire,commentaire,heureFin,employeConnecte);
                

            }
              
            
            //Afficher les statistiques
            
            System.out.println("*********** Afficher statistiques ***********");
            List<Object[]> resultats = Service.statsNbVoyancesParMedium();
            for (int i=0; i<resultats.size(); i++) {
                Object[] arr= resultats.get(i);
                for (Object arr1 : arr) {
                    System.out.print(arr1 + " ");
                }
                System.out.println("");
            }  
            
            List<Object[]> res = Service.statsNbClientsDistinctsEmploye();
            for (int i=0; i<res.size(); i++) {
                Object[] r= res.get(i);
                for (Object r1 : r) {
                    System.out.print(r1 + " ");
                }
                System.out.println("");
            }
            
            List<Object[]> res2 = Service.statsNbClientsTotalEmploye();
            for (int i=0; i<res2.size(); i++) {
                Object[] r= res2.get(i);
                for (Object r1 : r) {
                    System.out.print(r1 + " ");
                }
                System.out.println("");
            }
            
            
        }
        
        profilConnecte = Service.deconnecterUtilisateur();
        
        JpaUtil.destroy();

    }
    
}
