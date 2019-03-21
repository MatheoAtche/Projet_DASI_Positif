package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-19T14:10:57")
@StaticMetamodel(Voyance.class)
public class Voyance_ { 

    public static volatile SingularAttribute<Voyance, Date> dateDebut;
    public static volatile SingularAttribute<Voyance, Employe> employe;
    public static volatile SingularAttribute<Voyance, Client> client;
    public static volatile SingularAttribute<Voyance, Long> id;
    public static volatile SingularAttribute<Voyance, Medium> medium;
    public static volatile SingularAttribute<Voyance, String> heureFin;
    public static volatile SingularAttribute<Voyance, String> etat;
    public static volatile SingularAttribute<Voyance, String> commentaire;

}