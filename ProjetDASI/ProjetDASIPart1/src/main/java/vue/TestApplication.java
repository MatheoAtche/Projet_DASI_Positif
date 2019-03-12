package vue;

import dao.JpaUtil;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import metier.modele.Client;
import metier.modele.Employe;
import metier.service.Service;
import util.AstroTest;
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

    public static void main(String[] args) throws ParseException, IOException {
        JpaUtil.init();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(new Date());
        Date d = format.parse("1988-11-05");
        Client c = new Client("sophie", "lab","m","mme",d,"adresse","tel","courriel");
        //Employe e = new Employe("mathéo", "atcb","tel","c","mdp");
        
        Service.initialiserApplication();
        Service.inscrireClient(c);
        
        String courriel = Saisie.lireChaine("Entrez votre courriel : ");
        String mdp = Saisie.lireChaine("Entrez votre mdp : ");
        Service.connexionUtilisateur(courriel,mdp);
        
        System.out.println(Service.getProfilConnecte());
        /*AstroTest astroTest = new AstroTest();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        String dateString =format.format(new Date());
        Date d = format.parse("1988-11-05");
        List<String>profil=astroTest.getProfil("Sophie",d);
        System.out.println(profil.get(0));
        System.out.println(profil.get(1));
        System.out.println(profil.get(2));
        System.out.println(profil.get(3));*/

        JpaUtil.destroy();

    }

}
