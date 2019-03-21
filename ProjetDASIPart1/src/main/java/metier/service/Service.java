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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import metier.modele.Astrologue;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.Tarologue;
import metier.modele.Voyance;
import metier.modele.Voyant;
import util.AstroTest;
import util.DebugLogger;
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
    
    /*
    ** Rôle : Renvoyer la liste des médiums
    ** Entrée : aucune
    ** Sortie : Renvoie la liste des médiums présents dans la base
    */
    public static List<Medium> afficherMediums(){
        
        List<Medium> mediumsVoyants = null;
        JpaUtil.creerEntityManager();
        try {
            mediumsVoyants = MediumDAO.listerMediums();
        } catch (Exception e) {
            DebugLogger.log("Erreur afficherMediums",e);
        }
        finally {
            JpaUtil.fermerEntityManager();
        }
        return mediumsVoyants;
    }
    
    /*
    ** Rôle : Initialiser la base de données avec des clients, 
    **        des médiums et des employés 
    ** Entrée : aucune
    ** Sortie : aucune
    */
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
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerEntityManager();
        }
    
        Service.inscrireClient(c1);
        Service.inscrireClient(c2);
        Service.inscrireClient(c3);
        
    }
    
    /*
    ** Rôle : Trouver les employés compétents pouvant
    **        incarner le médium souhaité
    ** Entrée : L'identifiant du médium
    ** Sortie : La liste des employés compétents
    */
    public static List<Employe> trouverEmployeCompetent (int medium) {
        
        List<Employe> employesCapables = null;
        try {
             employesCapables = EmployeDAO.trouverEmployeCompetent(medium);
        } catch(Exception e) {
            DebugLogger.log("Erreur trouverEmployeCompetent",e);
        } 
        return employesCapables;
    }
    
    
    /*
    ** Rôle : Trouver les employés compétents pouvant
    **        incarner le médium souhaité
    ** Entrée : L'identifiant du médium
    ** Sortie : La liste des employés compétents
    */
    public static List<Employe> rechercheEmployePourMedium(int mediumSouhaite){
        
        List<Employe> employesChoisis = null;
        try {
            JpaUtil.creerEntityManager();
            employesChoisis= Service.trouverEmployeCompetent(mediumSouhaite);
        } catch (Exception e) {
            DebugLogger.log("Erreur rechercheEmployePourMedium",e);
        } finally {
            JpaUtil.fermerEntityManager();
        }
        
        return employesChoisis;
        
    }
    
    /*
    ** Rôle : Valider la voyance par l'employé concerné 
    ** Entrée : Le client, l'employé et le médium incarné
    ** Sortie : La voyance validée
    */
    public static Voyance validerVoyance(Client client,Medium medium,Employe employe) {
        
        Date dateActuelle = new Date();
        String commentaire = "";
        Voyance voyance = new Voyance(commentaire,dateActuelle,client,medium,employe);
        
        employe.ajouteNouvelleVoyance(voyance);
        client.ajouteNouvelleVoyance(voyance);
        medium.ajouteNouvelleVoyance(voyance);
        employe.setNbConsultations(employe.getNbConsultations()+1);
        employe.setEtat("Non disponible");
        
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();

            VoyanceDAO.creerVoyance(voyance);
            EmployeDAO.majEmploye(employe);
            ClientDAO.majClient(client);
            MediumDAO.majMedium(medium);

            JpaUtil.validerTransaction();
            
        } catch(RollbackException ex) {
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerEntityManager();
        }
        
        return voyance;
        
    }
    
    /*
    ** Rôle : Trouver un médium particulier 
    ** Entrée : L'identifiant d'un médium
    ** Sortie : Le médium trouvé
    */
    public static Medium choisirMedium(int idMedium) {
        
        JpaUtil.creerEntityManager();
        Medium medium = null;
        try {
            medium = MediumDAO.chercherMedium(idMedium);
        } catch(NoResultException e) {
            DebugLogger.log("Erreur choisirMedium",e);
        } finally {
            JpaUtil.fermerEntityManager();
        }

        return medium;
    }
    
    /*
    ** Rôle : Trouver un client pour un employé
    ** Entrée : L'employé
    ** Sortie : Le client trouvé (ou pas)
    */
    public static Client rechercherClientPourUnEmploye (Employe employe) {
        
        JpaUtil.creerEntityManager();
        Client retour = null;
        try {
            retour = ClientDAO.rechercherClientPourUnEmploye(employe);
        } catch(Exception e) {
            retour= null;
            DebugLogger.log("Erreur rechercherVoyanceClientEmploye",e);
        } finally {
            JpaUtil.fermerEntityManager();
        }
        return retour;
    }
    
    /*
    ** Rôle : Inscrire un client
    ** Entrée : Un client
    ** Sortie : Si l'inscription s'est bien passée
    */
    public static boolean inscrireClient(Client client) {
        
        JpaUtil.creerEntityManager();
        boolean retour = false;
        
        try {
            if (ClientDAO.estPresentClient(client.getCourriel())== null) {

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

                    ClientDAO.ajouterClient(client);
                    JpaUtil.validerTransaction();
                    retour = true;
            }
            
        }  catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerEntityManager();
        }
        
        if(retour) {
            Message.envoyerMail(
                "contact@posit.if",
                client.getCourriel(),
                "Validation de l'inscription",
                "Félicitations, vous êtes inscrit sur Posit'if ! Vous pouvez dès à présent vous connecter sur la plateforme");
        } else {
            Message.envoyerMail(
                "contact@posit.if",
                client.getCourriel(),
                "Erreur lors de l'inscription",
                "Désolé, votre inscription sur la plateforme posit'if a échoué, veuillez réessayer ultérieurement");
        }
        
        return retour;
    }

    /*
    ** Rôle : Savoir si c'est un client qui veut se connecter
    **        et dans ce cas le connecter
    ** Entrée : Le courriel et le mot de passe
    ** Sortie : Le client (s'il est trouvé) ou null
    */
    public static Client connexionClient(String courriel, String motDePasse) {
        
        Client retour = null;
        Client clientRecupere = null;
        JpaUtil.creerEntityManager();
        try {
            clientRecupere = ClientDAO.estPresentClient(courriel);
            if (clientRecupere!=null) {
                if(motDePasse.equals(clientRecupere.getMdp())) {
                    retour = clientRecupere;
                } 
            } 
        } catch(Exception e) {
            DebugLogger.log("Erreur connexionClient",e);
        } finally {
            JpaUtil.fermerEntityManager();
        }
        
        return retour;
    }
    
    /*
    ** Rôle : Savoir si c'est un employé qui veut se connecter
    **        et dans ce cas le connecter
    ** Entrée : Le courriel et le mot de passe
    ** Sortie : L'employé (s'il est trouvé) ou null
    */
    public static Employe connexionEmploye(String courriel, String motDePasse) {
        
        Employe retour = null;
        Employe employeRecupere = null;
                
        JpaUtil.creerEntityManager();
        try {
            employeRecupere = EmployeDAO.estPresentEmploye(courriel);
            if (employeRecupere!=null) {
                if(motDePasse.equals(employeRecupere.getMdp())) {
                    retour = employeRecupere;
                }
            } 
        } catch (Exception e) {
            DebugLogger.log("Erreur connexionEmploye",e);
        } finally {
            JpaUtil.fermerEntityManager();
        }
        
        return retour;
    }
    
    /*
    ** Rôle : Trouver le médium incarné par un employé
    **        pour une voyance 'Non débutée' ou 'Débutée'
    ** Entrée : Le client et l'employé d'une même voyance
    ** Sortie : Le médium (s'il est trouvé) ou null
    */
    public static Medium trouverMediumIncarneParLemploye (Client client,Employe employe) {
        
        JpaUtil.creerEntityManager();
        Medium mediums = null;
        
        try {
            mediums = MediumDAO.trouverMediumIncarneParLemploye(client, employe);
        } catch (NoResultException e) {
            DebugLogger.log("Erreur trouverMedium",e);
        } finally {
            JpaUtil.fermerEntityManager();
        }
        
        return mediums;
    }
    
    /*
    ** Rôle : Permet à un employé d'accepter la voyance
    ** Entrée : L'employé et le client
    ** Sortie : La voyance (si elle est trouvée) ou null
    */
    public static Voyance accepterVoyance(Employe employe, Client client) {
        
        JpaUtil.creerEntityManager();
        Voyance voyance = null;
        
        try {
            voyance = VoyanceDAO.rechercherVoyanceDunClientPourUnEmploye(client, employe);
            voyance.setEtat("Débutée");

            JpaUtil.ouvrirTransaction();
            VoyanceDAO.majVoyance(voyance);
            JpaUtil.validerTransaction();
            
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerEntityManager();
        }

        return voyance;
    }
    
    public static Voyance chercherVoyance (Employe employe, Client client) {
        
        JpaUtil.creerEntityManager();
        Voyance voyance = null;
        
        try {
            voyance = VoyanceDAO.rechercherVoyanceDunClientPourUnEmploye(client, employe);
        } catch (RollbackException ex) {
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerEntityManager();
        }

        return voyance;
    }
    
    /*
    ** Rôle : Générer des phrases de vooyance 
    **        en fonction de paramètres
    ** Entrée : La couleur porte-bonheur, l'animal-totem, les indices
    **          d'amour, de santé et de travail indiqués
    ** Sortie : Les prases générées
    */
    public static List<String> generePhrases(String couleur, String animal, int amour, int sante, int travail) {
      
        AstroTest astroTest = new AstroTest();
        List<String> phrases = null;
        try {
            phrases = astroTest.getPredictions(couleur, animal, amour, sante, travail);
        } catch (IOException | NumberFormatException e) {
            DebugLogger.log("Erreur generePhrases",e);
        }
        
        return phrases;
    }
    
    /*
    ** Rôle : Permet à un employé de terminer la voyance
    **        commencée
    ** Entrée : La voyance, le commentaire, l'heure de fin et l'employé
    **          faisant la voyance
    ** Sortie : aucune
    */
    public static void terminerVoyance (Voyance voyance, String commentaire, String heureFin, Employe employe) {
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        try {
            voyance.setHeureFin(heureFin);
            voyance.setCommentaire(commentaire);
            voyance.setEtat("Terminée");
            employe.setEtat("Disponible");

            VoyanceDAO.majVoyance(voyance);
            EmployeDAO.majEmploye(employe);

            JpaUtil.validerTransaction();
        } catch (RollbackException e) {
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerEntityManager();
        }
       
        
    }
    
    /*
    ** Rôle : Afficher le profil d'un client ->
    **        nom, prénom, données de voyances, ...
    ** Entrée : Le client
    ** Sortie : Le profil généré
    */
    public static List<String> afficherProfilClient (Client client) {
        
        List<String> profil = new ArrayList();
        profil.add(client.getNom());
        profil.add(client.getPrenom());
        profil.add(client.getSigneZodiaque());
        profil.add(client.getSigneChinois());
        profil.add(client.getCouleurPB());
        profil.add(client.getAnimalTotem());
        return profil;
    }
    
    /*
    ** Rôle : Afficher l'historique d'un client ->
    **        les médiums, les dates de début/fin, ...
    ** Entrée : Le client
    ** Sortie : L'historique généré
    */
    public static List<Voyance> afficherHistoriqueClient (Client client) {

        JpaUtil.creerEntityManager();
        List<Voyance> voyance = null;
        try {
            voyance = VoyanceDAO.rechercheVoyancesFaitesClient(client);
        } catch (Exception e) {
            DebugLogger.log("Erreur afficherHistoriqueClient",e);
        } finally {
            JpaUtil.fermerEntityManager();
        }

        return voyance;
    }
    
    /*
    ** Rôle : Création de voyance lors d'une demande 
    **        par un client
    ** Entrée : Le client, l'identifiant du médium souhaité
    ** Sortie : aucune
    */
    public static void creerVoyance(Client client, int mediumSouhaite) {
        
        Medium medium = Service.choisirMedium(mediumSouhaite);
        List<Employe> employesDispos = Service.rechercheEmployePourMedium(mediumSouhaite);
        try {
            if(employesDispos.isEmpty()) {

                Message.envoyerNotification(client.getTelephone()," Le médium choisi n'est pas disponible");

            } else {
                Voyance voyance = Service.validerVoyance(client,medium,employesDispos.get(0));
                Message.envoyerNotification(employesDispos.get(0).getTelephone()," Voyance demandée le " + voyance.getDateDebut() +
                            " pour " + client.getPrenom() + " " + client.getNom() +
                            "(#" + client.getId() + "). Médium à incarner : " + medium.getNom());

            }
            
        }  catch (Exception e) {
                DebugLogger.log("Erreur creerVoyance",e);
        }
    }
    
    /*
    ** Rôle : Démarrer une voyance, demande faite 
    **        par un employé
    ** Entrée : Le client et l'employé
    ** Sortie : La voyance démarrée
    */
    public static Voyance demarrerVoyance(Employe employe) {
        
        Voyance voyance = null;
        
        try {
            Client client = Service.rechercherClientPourUnEmploye(employe);
            if(client !=null){
                voyance = Service.accepterVoyance(employe,client);
                Medium medium = Service.trouverMediumIncarneParLemploye(client,employe);
                Message.envoyerNotification(client.getTelephone(),
                        "Votre demande de voyance du "+ voyance.getDateDebut()+ " a bien été enregistrée."
                            + "Vous pouvez dès à présent me contacter au " + employe.getTelephone() +
                            ". A tout de suite ! Posit'ifement vôtre, " + medium.getNom());

            } 
        } catch (Exception e) {
                DebugLogger.log("Erreur demarrerVoyance",e);
        }
        
        return voyance;
    }
    
    /*
    ** Rôle : Trouver la voyance (s'il elle existe)
    **        que doit faire un employé
    ** Entrée : L'employé considéré
    ** Sortie : La voyance trouvée
    */
    public static Voyance trouverVoyanceAFaireDunEmploye (Employe employe) {
        
        Voyance voyance = null;
        
        try {
            Client client = Service.rechercherClientPourUnEmploye(employe);
            if(client !=null){
                voyance = Service.chercherVoyance(employe,client);
            } 
        } catch (Exception e) {
                DebugLogger.log("Erreur demarrerVoyance",e);
        }
        
        return voyance;
    }
    
    /*
    ** Rôle : Statistique du nombre de voyances
    **        pour chaque médium
    ** Entrée : aucun
    ** Sortie : Les statistiques trouvées
    */
    public static List<Object[]> statsNbVoyancesParMedium () {
        
        JpaUtil.creerEntityManager();
        List<Object[]> resultat = null;
        
        try {
            resultat = VoyanceDAO.statsNbVoyancesParMedium();
        } catch (Exception e) {
            DebugLogger.log("Erreur statsNbVoyancesMedium",e);
        } finally {
            JpaUtil.fermerEntityManager();
        }
        return resultat;
    }
    
    /*
    ** Rôle : Statistique du nombre de clients différents
    **        pour chaque employé
    ** Entrée : aucun
    ** Sortie : Les statistiques trouvées
    */
    public static List<Object[]> statsNbClientsDistinctsEmploye () {
        
        JpaUtil.creerEntityManager();
        List<Object[]> resultat = null;
        
        try {
            resultat = VoyanceDAO.statsNbClientsDistinctsEmploye();
        } catch (Exception e) {
            DebugLogger.log("Erreur statsNbClientsEmploye",e);
        } finally {
            JpaUtil.fermerEntityManager();
        }

        return resultat;
    }
    
    /*
    ** Rôle : Statistique du nombre de clients différents
    **        pour chaque employé
    ** Entrée : aucun
    ** Sortie : Les statistiques trouvées
    */
    public static List<Object[]> statsNbClientsTotalEmploye () {
        
        JpaUtil.creerEntityManager();
        List<Object[]> resultat = null;
        
        try {
            resultat = VoyanceDAO.statsNbClientsTotalEmploye();
        } catch (Exception e) {
            DebugLogger.log("Erreur statsNbClientsTotalEmploye",e);
        } finally {
            JpaUtil.fermerEntityManager();
        }

        return resultat;
    }
    
    public static String deconnecterUtilisateur () {
        return "Aucun";
    }
}
