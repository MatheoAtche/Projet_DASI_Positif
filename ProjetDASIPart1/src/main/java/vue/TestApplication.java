package vue;

import dao.JpaUtil;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public static void Inscription (){
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
        Date date;
        try {
            date = format.parse(Integer.toString(anneeInscription)+"-" 
                    +Integer.toString(moisInscription)+"-"
                    +Integer.toString(jourInscription));
            Client clientInscription = new Client(prenomInscription,nomInscription,mdpInscription,
                                                civiliteInscription,date,adresseInscription,telInscription,
                                                courrielInscription);
            Service.inscrireClient(clientInscription);
        } catch (ParseException ex) {
            Logger.getLogger(TestApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Connexion(){
        System.out.println("*********** Connexion ***********");
        String courrielConnexion = Saisie.lireChaine("Courriel : ");
        String mdpConnexion = Saisie.lireChaine("Mot de passe : ");
        clientConnecte = Service.connexionClient(courrielConnexion,mdpConnexion);
        employeConnecte = Service.connexionEmploye(courrielConnexion,mdpConnexion);
        if (clientConnecte != null) {
            profilConnecte = "Client";
        } else if ( employeConnecte != null) {
            profilConnecte = "Employe";
        } else {
            System.out.println("Identifiants inconnus");
        }
    }
    
    public static void Deconnexion(){
        profilConnecte = Service.deconnecterUtilisateur();
    }
    
    public static void AfficherProfil(){
        System.out.println("*********** Affichage du profil client ***********");
        if(profilConnecte.equals("Client")) {
            List<String> profilClient = Service.afficherProfilClient(clientConnecte);
            Iterator<String> iter = profilClient.iterator(); 
            while (iter.hasNext()) { 
                System.out.println(iter.next());
            }
        }
    }
    
    public static void AfficherHistorique(){
        System.out.println("*********** Affichage de l'historique client ***********");
        if(profilConnecte.equals("Client")) {
            List<Voyance> historiqueClient = Service.afficherHistoriqueClient(clientConnecte);
            Iterator<Voyance> iter3 = historiqueClient.iterator(); 
            while (iter3.hasNext()) { 
                Voyance v = iter3.next();
                System.out.println(v.getDateDebut()+ " " + v.getHeureFin() + " " + v.getMedium().getNom()+ " " + v.getCommentaire() );
            }
        }
    }
    
    public static void DemanderVoyance(){
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
    }
    
    public static Voyance VerifierVoyance(){
        System.out.println("*********** Rechercher une voyance ***********");
        Voyance voyanceAFaire = Service.trouverVoyanceAFaireDunEmploye(employeConnecte);
        if(voyanceAFaire != null) {
            System.out.println("*********** Afficher profil client ***********");
            List<String> profilClient = Service.afficherProfilClient(voyanceAFaire.getClient());
            Iterator<String> iter = profilClient.iterator(); 
            while (iter.hasNext()) { 
                System.out.println(iter.next());
            }
            System.out.println("*********** Afficher historique client ***********");
            List<Voyance> historiqueClient = Service.afficherHistoriqueClient(voyanceAFaire.getClient());
            Iterator<Voyance> iter3 = historiqueClient.iterator(); 
            while (iter3.hasNext()) { 
                Voyance v = iter3.next();
                System.out.println(v.getDateDebut()+ " " + v.getHeureFin() + " " + v.getMedium().getNom()+ " " + v.getCommentaire() );
            }
        }
        return voyanceAFaire;
    }
    
    public static Voyance DemarrerUneVoyance(Voyance v){
        System.out.println("*********** Démarrer la voyance ***********");
        v = Service.demarrerVoyance(employeConnecte);
        System.out.println("*********** Générer phrases ***********");
        int amour = Saisie.lireInteger("Amour : ",Arrays.asList(1,2,3,4));
        int sante = Saisie.lireInteger("Santé : ",Arrays.asList(1,2,3,4));
        int travail = Saisie.lireInteger("Travail : ",Arrays.asList(1,2,3,4));

        List<String> phraseGenerees = Service.generePhrases(v.getClient().getCouleurPB(),v.getClient().getAnimalTotem(),amour,sante,travail);
        Iterator<String> iter2 = phraseGenerees.iterator(); 
        while (iter2.hasNext()) { 
            System.out.println( iter2.next());
        }
        return v;
    }
    
    public static void TerminerVoyance(Voyance v){
        System.out.println("*********** Terminer voyance ***********");
        String commentaire = Saisie.lireChaine("Indiquez votre commentaire : ");
        String heureFin = Saisie.lireChaine("Indiquez l'heure de fin : ");
        Service.terminerVoyance(v,commentaire,heureFin,employeConnecte);
    }
    
    public static void AfficherStats (){
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

    public static void main(String[] args) throws ParseException, IOException {

        JpaUtil.init();
        //Service.initialiserApplication();
        clientConnecte = new Client();
        employeConnecte = new Employe();
        profilConnecte = "Aucun";
        String choix = "";
        System.out.println("Bienvenue sur la plateforme Posit'if");
        
        while (!choix.equals("Q")){
            System.out.println("Que voulez vous faire?");
            if (profilConnecte.equals("Aucun")){
                System.out.println("C : Se connecter");
                System.out.println("I : S'inscrire");
                System.out.println("Q : Quitter");
                choix=Saisie.lireChaine("");
                switch(choix){
                    case "C":
                        Connexion();
                        break;
                    case "I":
                        Inscription();
                        break;
                    case "Q":
                        break;
                    default:
                        System.out.println("Entrée inconnue");
                        break;
                }
            }
            if (profilConnecte.equals("Employe")){
                System.out.println("A : Afficher les statistiques");
                System.out.println("V : Vérifier les demandes de voyance");
                System.out.println("D : Se déconnecter");
                System.out.println("Q : Quitter l'application");
                choix = Saisie.lireChaine("");
                switch (choix) {
                    case "A":
                        AfficherStats();
                        break;
                    case "V":
                        Voyance v = VerifierVoyance();
                        if (v!=null){
                            if (v.getEtat().equals("Non débutée")){
                                System.out.println("Entrez 'D' pour démarrer la voyance");
                                choix = Saisie.lireChaine("");
                                if (choix.equals("D")){
                                    v=DemarrerUneVoyance(v);
                                }
                            }
                            if (v.getEtat().equals("Débutée")){
                                System.out.println("Entrer 'T' pour terminer la voyance");
                                choix = Saisie.lireChaine("");
                                if (choix.equals("T")){
                                    TerminerVoyance(v);
                                }
                            }
                        }else{
                            System.out.println("Aucune demande de voyance en cours");
                        }
                        break;
                    case "D":
                        Deconnexion();
                        break;
                    case "Q":
                        break;
                    default:
                        System.out.println("choix inconnu");
                        break;
                }
            } else if (profilConnecte.equals("Client")){
                System.out.println("V : Demander une voyance");
                System.out.println("P : Afficher le profil");
                System.out.println("H : Afficher l'historique des voyances");
                System.out.println("D : Se déconnecter");
                System.out.println("Q : Quitter");
                choix = Saisie.lireChaine("");
                switch(choix){
                    case "V":
                        DemanderVoyance();
                        break;
                    case "P":
                        AfficherProfil();
                        break;
                    case "H":
                        AfficherHistorique();
                        break;
                    case "D":
                        Deconnexion();
                        break;
                    case "Q":
                        break;
                    default:
                        System.out.println("Entrez inconnue");
                        break;
                }
            }
        }
        JpaUtil.destroy();
    }
}
