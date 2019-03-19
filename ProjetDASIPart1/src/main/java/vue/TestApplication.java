package vue;

import dao.JpaUtil;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.Voyance;
import metier.modele.Voyant;
import metier.service.Service;
import util.AstroTest;
import util.Message;
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

        /**
         * ******* On teste le client *********
         */
        //Inscription
        /*
        String civiliteInscription = Saisie.lireChaine("Entrez votre civilité : ");
        String nomInscription = Saisie.lireChaine("Entrez votre nom : ");
        String prenomInscription = Saisie.lireChaine("Entrez votre prénom : ");
        String courrielInscription = Saisie.lireChaine("Entrez votre courriel : ");
        String mdpInscription = Saisie.lireChaine("Entrez votre mot de passe : ");
        String adresseInscription = Saisie.lireChaine("Entrez votre adresse : ");
        String telInscription = Saisie.lireChaine("Entrez votre téléphone : ");
        String dateInscription = Saisie.lireChaine("Entrez votre date de naissance au format YYYY-MM-JJ : ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(new Date());
        Date date = format.parse(dateInscription); // au format "1988-11-05"
        Client clientInscription = new Client(prenomInscription,nomInscription,mdpInscription,
                                                civiliteInscription,date,adresseInscription,telInscription,
                                                courrielInscription);
        
        if(Service.inscrireClient(clientInscription)) {
            Message.envoyerMail(
                "contact@posit.if",
                clientInscription.getCourriel(),
                "Validation de l'inscription",
                "Félicitations, vous êtes inscrit sur Posit'if ! Vous pouvez dès à présent vous connecter sur la plateforme");
        } else {
            Message.envoyerMail(
                "contact@posit.if",
                clientInscription.getCourriel(),
                "Erreur lors de l'inscription",
                "Désolé, votre inscription sur la plateforme posit'if a échoué, veuillez réessayer ultérieurement");
        }
         */
        
        //Connexion
        
        String courrielConnexion = Saisie.lireChaine("Entrez votre courriel : ");
        String mdpConnexion = Saisie.lireChaine("Entrez votre mot de passe : ");
        profilConnecte = Service.connexionUtilisateur(courrielConnexion, mdpConnexion);
        if (profilConnecte.equals("Aucun")) {
            System.out.println("Login ou mot de passe incorrects.");
        } else if (profilConnecte.equals("Client")) {
            clientConnecte = Service.chercheClientConnecte(courrielConnexion, mdpConnexion);
        } else {
            employeConnecte = Service.chercheEmployeConnecte(courrielConnexion, mdpConnexion);
        }
        
        
        
        //Affichage du profil
        /*
        if(profilConnecte.equals("Client")) {
            System.out.println(Service.afficherProfilClient(clientConnecte));
        }
        */
        
         //Affichage de l'historique
        /*
        if(profilConnecte.equals("Client")) {
            System.out.println(Service.afficherHistoriqueClient(clientConnecte));
        }
        */
        
        //Affichage des médiums et en choisir un
        
        if(profilConnecte.equals("Client")) {
            
            List<Medium> mediumsVoyant = Service.afficherMediums();
            Iterator<Medium> iter = mediumsVoyant.iterator(); 
            while (iter.hasNext()) { 
                System.out.println( iter.next());
            }
            
            String mediumSouhaite = Saisie.lireChaine("Indiquez l'ID du méduim souhaité : ");
            List<Medium> medium = Service.choisirMedium(mediumSouhaite);
            List<Employe> employesDispos = Service.rechercheEmployePourMedium(clientConnecte,mediumSouhaite);
            
            if(employesDispos.isEmpty()) {
                System.out.println("Le médium souhaité ne peut pas travailler aujourd'hui. Veuillez réessayer ultérieurement.");
            } else {
                Voyance voyance = Service.validerVoyance(clientConnecte,medium.get(0),employesDispos.get(0));
                Message.envoyerNotification(employesDispos.get(0).getTelephone(),
                        "Pour : " + employesDispos.get(0).getPrenom() + " " + employesDispos.get(0).getNom() + "\n" +
                        "Tel : " + employesDispos.get(0).getTelephone() + "\n" +
                        "Message : Voyance demandée le " + voyance.getDateDebut() +
                            " pour " + clientConnecte.getPrenom() + " " + clientConnecte.getNom() +
                            "(#" + clientConnecte.getId() + "). Médium à incarner : " + medium.get(0).getNom());
            }
            
        }
        
        //Réception d'une notifiction client -- INUTILE
        /*
        if(profilConnecte.equals("Client")) {
            List<Employe> employe = Service.recevoirNotification(clientConnecte);
            if(!employe.isEmpty()){
                List<Medium> medium = Service.trouverMedium(clientConnecte,employe.get(0));
                Message.envoyerNotification(clientConnecte.getTelephone(),
                        "Pour : " + clientConnecte.getPrenom() + " " + clientConnecte.getNom() + "\n" +
                        "Tel : " + clientConnecte.getTelephone() + "\n" +
                        "Message : Votre demande de voyance du a bien été enregistrée."
                            + "Vous pouvez dès à présent me contacter au " + employe.get(0).getTelephone() +
                            ". A tout de suite ! Posit'ifement vôtre, " + medium.get(0).getNom());
            } 
        }
        */
        
        /**
         * ******* On teste l'employé *********
         */
         //Connexion
        
        String courrielConnexion2 = Saisie.lireChaine("Entrez votre courriel : ");
        String mdpConnexion2 = Saisie.lireChaine("Entrez votre mot de passe : ");
        profilConnecte = Service.connexionUtilisateur(courrielConnexion2, mdpConnexion2);
        if (profilConnecte.equals("Aucun")) {
            System.out.println("Login ou mot de passe incorrects.");
        } else if (profilConnecte.equals("Client")) {
            clientConnecte = Service.chercheClientConnecte(courrielConnexion2, mdpConnexion2);
        } else {
            employeConnecte = Service.chercheEmployeConnecte(courrielConnexion2, mdpConnexion2);
        }
        
        
        //Acceptation d'une notification + Générateur de phrases + terminer la voyance
        // + Consultation du profil client
        if(profilConnecte.equals("Employe")) {
            List<Client> client = Service.rechercherVoyance(employeConnecte);
            if(!client.isEmpty()){
                //Acceptation d'une notification
                List<Voyance> voyances = Service.accepterVoyance(employeConnecte,client.get(0));
                List<Medium> medium = Service.trouverMedium(client.get(0),employeConnecte);
                Message.envoyerNotification(client.get(0).getTelephone(),
                        "Pour : " + client.get(0).getPrenom() + " " + client.get(0).getNom() + "\n" +
                        "Tel : " + client.get(0).getTelephone() + "\n" +
                        "Message : Votre demande de voyance du "+ voyances.get(0).getDateDebut()+ " a bien été enregistrée."
                            + "Vous pouvez dès à présent me contacter au " + employeConnecte.getTelephone() +
                            ". A tout de suite ! Posit'ifement vôtre, " + medium.get(0).getNom());
                
                
                //Profil client
                System.out.println(Service.afficherProfilClient(client.get(0)));
                
                //Générateur
                String amour = Saisie.lireChaine("Amour : ");
                String sante = Saisie.lireChaine("Santé : ");
                String travail = Saisie.lireChaine("Travail : ");
                
                List<String> phraseGenerees = Service.generePhrases(client.get(0).getCouleurPB(),client.get(0).getAnimalTotem(),amour,sante,travail);
                Iterator<String> iter = phraseGenerees.iterator(); 
                while (iter.hasNext()) { 
                    System.out.println( iter.next());
                }
                
                //Terminer la voyance
                String commentaire = Saisie.lireChaine("Indiquez votre commentaire : ");
                String heureFin = Saisie.lireChaine("Indiquez l'heure de fin : ");
                Service.terminerVoyance(voyances.get(0),commentaire,heureFin,employeConnecte);
                
            } 
        }
        
        JpaUtil.destroy();

    }

}
