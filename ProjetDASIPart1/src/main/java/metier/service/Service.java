package metier.service;

import dao.ClientDAO;
import dao.EmployeDAO;
import dao.JpaUtil;
import dao.MediumDAO;
import dao.VoyanceDAO;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.RollbackException;
import metier.modele.Astrologue;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.Tarologue;
import metier.modele.Voyance;
import metier.modele.Voyant;
import util.AstroTest;

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
    
    public static List<Medium> afficherMediums(){

        JpaUtil.creerEntityManager();
        List<Medium> mediumsVoyants = MediumDAO.listerMediums();
        JpaUtil.fermerEntityManager();
        return mediumsVoyants;

    }
    
    public static void initialiserApplication () throws ParseException {
        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format1.format(new Date());
        Date date1 = format1.parse("1984-08-16"); 
        Date date2 = format1.parse("1996-02-17"); 
        Date date3 = format1.parse("1988-08-13"); 
       
        Client c1 = new Client("Moez", "WOAGNER", "mdp1", "M.", date1, "6 Rue Camille Koechlin, Villeurbanne", "0832205629", "moez.woagner@laposte.net");
        Client c2 = new Client("Matteo", "HONRY", "mdp2", "M.", date2, "9 Impasse Guillet, Villeurbanne	", "0482381862", "matteo.honry@yahoo.com");
        Client c3 = new Client("Alice", "VOYRET", "mdp3", "Mme", date3, "1 Rue d'Alsace, Villeurbanne", "0486856520", "alice.voyret@hotmail.com");
        
        Employe e1 = new Employe("BORROTI MATIAS DANTAS","Raphaël","0328178508","rborrotimatiasdantas4171@free.fr","mdp1");
        Employe e2 = new Employe("OLMEADA MARAIS","Nor","0418932546","nolmeadamarais1551@gmail.com","mdp2");
        Employe e3 = new Employe("RAYES GEMEZ","Olena","0532731620","orayesgemez5313@outlook.com","mdp3");
        Employe e4 = new Employe("SING","Ainhoa","0705224200","asing8183@free.fr","mdp4");
        Employe e5 = new Employe("ABDIULLINA","David Alexander","0590232772","david-alexander.abdiullina@laposte.net","mdp5");
       
        Medium m1 = new Voyant("Gwenael","Spécialiste des grandes conversations au-delà de TOUTES les frontières","Boule de Cristal");
        Medium m2 = new Voyant("Professeur Maxwell","Votre avenir est devant vous : regardons-le ensemble !","Marc de Café");
        Medium m3 = new Tarologue("Mme Irma","Comprenez votre entourage grâce à mes cartes ! Résultats rapides.");
        Medium m4 = new Tarologue("Endora","Mes cartes répondront à toutes vos questions personnelles");
        Medium m5 = new Astrologue("Serena","Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.",
                                    "Ecole Normale Supérieure d'Astrologie (ENS-Astro)","2006");
        
        Medium m6 = new Astrologue("M. M.Histaire-Hyeux","Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter !",
                                    "Institut des Nouveaux Savoirs Astrologiques","2010");
        
        e1.ajouteMediumsIncarnes(m1);
        e1.ajouteMediumsIncarnes(m2);
        e1.ajouteMediumsIncarnes(m3);
        m1.ajouteEmploye(e1);
        m2.ajouteEmploye(e1);
        m3.ajouteEmploye(e1);
        
        e2.ajouteMediumsIncarnes(m1);
        e2.ajouteMediumsIncarnes(m2);
        e2.ajouteMediumsIncarnes(m3);
        m1.ajouteEmploye(e2);
        m2.ajouteEmploye(e2);
        m3.ajouteEmploye(e2);
        
        e3.ajouteMediumsIncarnes(m4);
        e3.ajouteMediumsIncarnes(m5);
        e3.ajouteMediumsIncarnes(m6);
        m4.ajouteEmploye(e3);
        m5.ajouteEmploye(e3);
        m6.ajouteEmploye(e3);
        
        e4.ajouteMediumsIncarnes(m4);
        e4.ajouteMediumsIncarnes(m5);
        e4.ajouteMediumsIncarnes(m6);
        m4.ajouteEmploye(e4);
        m5.ajouteEmploye(e4);
        m6.ajouteEmploye(e4);
        
        e5.ajouteMediumsIncarnes(m4);
        e5.ajouteMediumsIncarnes(m5);
        e5.ajouteMediumsIncarnes(m6);
        m4.ajouteEmploye(e5);
        m5.ajouteEmploye(e5);
        m6.ajouteEmploye(e5);
        
        try {
            
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            
            MediumDAO.ajouterMedium(m1);
            MediumDAO.ajouterMedium(m2);
            MediumDAO.ajouterMedium(m3);
            MediumDAO.ajouterMedium(m4);
            MediumDAO.ajouterMedium(m5);
            MediumDAO.ajouterMedium(m6);
            
            EmployeDAO.ajouterEmploye(e1);
            EmployeDAO.ajouterEmploye(e2);
            EmployeDAO.ajouterEmploye(e3);
            EmployeDAO.ajouterEmploye(e4);
            EmployeDAO.ajouterEmploye(e5);
            
            JpaUtil.validerTransaction();
            JpaUtil.fermerEntityManager();
            
             
        } catch (RollbackException ex) {
            JpaUtil.fermerEntityManager();
        }
    
        Service.inscrireClient(c1);
        Service.inscrireClient(c2);
        Service.inscrireClient(c3);
        
    }
    
    public static List<Employe> trouverEmployeCompetent (String medium) {
        List<Employe> employesCapables = EmployeDAO.trouverEmployeCompetent(medium);
        return employesCapables;
    }
    
    
    public static List<Employe> rechercheEmployePourMedium(Client client,String mediumSouhaite){
        JpaUtil.creerEntityManager();
        List<Employe> employesChoisis = Service.trouverEmployeCompetent(mediumSouhaite);
        JpaUtil.fermerEntityManager();
        return employesChoisis;
        
    }
    
    public static Voyance validerVoyance(Client client,Medium medium,Employe employe) throws ParseException {
        
        Date dateActuelle = new Date();
        String commentaire = "";
        /*SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateString1 = format1.format(new Date());
        Date date = format1.parse("1998-10-04"); // au format "1988-11-05"*/
        
        Voyance voyance = new Voyance(commentaire,dateActuelle,client,medium,employe);
        employe.ajouteNouvelleVoyance(voyance);
        client.ajouteNouvelleVoyance(voyance);
        medium.ajouteNouvelleVoyance(voyance);
        employe.setNbConsultations(employe.getNbConsultations()+1);
        employe.setEtat("Non disponible");
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        VoyanceDAO.majVoyance(voyance);
        EmployeDAO.majEmploye(employe);
        ClientDAO.majClient(client);
        MediumDAO.majMedium(medium);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        
        return voyance;
        
    }
    
    public static List<Medium> choisirMedium(String idMedium) {
        JpaUtil.creerEntityManager();
        List<Medium> medium = new ArrayList<Medium>();
        try {
            medium = MediumDAO.chercherMedium(idMedium);
        } catch(RollbackException ex) {
            System.out.println("Nexiste aps");
        }
        
        JpaUtil.fermerEntityManager();
        return medium;
    }
    
    /*
    public static List<Employe> recevoirNotification (Client client) {
        
        JpaUtil.creerEntityManager();
        List <Employe> retour = EmployeDAO.recevoirNotificationClient(client);
        JpaUtil.fermerEntityManager();
        return retour;
    }
    */
    
    public static List<Client> rechercherVoyance (Employe employe) {
        
        JpaUtil.creerEntityManager();
        List <Client> retour = ClientDAO.rechercherVoyance(employe);
        JpaUtil.fermerEntityManager();
        return retour;
    }
    
    public static boolean inscrireClient(Client client) {
        
        JpaUtil.creerEntityManager();
        boolean retour = false;
        
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
                JpaUtil.validerTransaction();
                JpaUtil.fermerEntityManager();
                retour = true;
                
            } catch (RollbackException ex) {
                JpaUtil.fermerEntityManager();
                return retour;
            }

        }
        return retour;
    }

    public static String connexionUtilisateur(String courriel, String motDePasse) {
        String retour = "Aucun";
        JpaUtil.creerEntityManager();
        if (ClientDAO.rechercherClient(courriel, motDePasse).isEmpty()) {
            if (EmployeDAO.rechercherEmploye(courriel, motDePasse).isEmpty()) {
            } else {
                retour = "Employe";
            }
        } else {
           retour = "Client";
        }
        JpaUtil.fermerEntityManager();
        return retour;
    }
    
    public static Client chercheClientConnecte (String courriel, String motDePasse) {
        JpaUtil.creerEntityManager();
        Client c = ClientDAO.rechercherClient(courriel, motDePasse).get(0);
        JpaUtil.fermerEntityManager();
        return c;
    }

    public static Employe chercheEmployeConnecte (String courriel, String motDePasse) {
        JpaUtil.creerEntityManager();
        Employe e = EmployeDAO.rechercherEmploye(courriel, motDePasse).get(0);
        JpaUtil.fermerEntityManager();
        return e;
    }
    
    public static List<Medium> trouverMedium (Client client,Employe employe) {
        JpaUtil.creerEntityManager();
        List<Medium> mediums = MediumDAO.rechercherMedium(client, employe);
        JpaUtil.fermerEntityManager();
        return mediums;
    }
    
    public static List<Voyance> accepterVoyance(Employe employe, Client client) {
        JpaUtil.creerEntityManager();
        List<Voyance> voyances = VoyanceDAO.rechercherVoyance(client, employe);
        Voyance voyanceTraitee = voyances.get(0);
        voyanceTraitee.setEtat("Débutée");
        
        JpaUtil.ouvrirTransaction();
        
        VoyanceDAO.creerVoyance(voyanceTraitee);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        
        return voyances;
    }
    
    public static List<String> generePhrases(String couleur, String animal, String amour, String sante, String travail) throws IOException {
        AstroTest astroTest = new AstroTest();
        return astroTest.getPredictions(couleur, animal, Integer.parseInt(amour), Integer.parseInt(sante), Integer.parseInt(travail));
    }
    
    public static void terminerVoyance (Voyance voyance, String commentaire, String heureFin, Employe employe) {
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        voyance.setHeureFin(heureFin);
        voyance.setCommentaire(commentaire);
        voyance.setEtat("Terminée");
        employe.setEtat("Disponible");
        
        VoyanceDAO.majVoyance(voyance);
        EmployeDAO.majEmploye(employe);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        
    }
    
    public static String afficherProfilClient (Client client) {
        String profil = "Signe du zodiaque : ";
        profil += client.getSigneZodiaque();
        profil += "\n";
        profil += "Signe chinois : ";
        profil += client.getSigneChinois();
        profil += "\n";
        profil += "Couleur porte-bonheur : ";
        profil += client.getCouleurPB();
        profil += "\n";
        profil += "Animal totem : ";
        profil += client.getAnimalTotem();
        return profil;
    }
    
    public static String afficherHistoriqueClient (Client client) {
        String historique = "Historique du client  : \n";
        //On recupere sa liste des voyances 
        //List<voyance> voyance = VoyanceDAO.rechercheVoyanceClient(client);
        return historique;
    }
    
}
