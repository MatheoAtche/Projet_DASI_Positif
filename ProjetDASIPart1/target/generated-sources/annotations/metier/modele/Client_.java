package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Voyance;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-19T14:10:57")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> signeZodiaque;
    public static volatile SingularAttribute<Client, Long> idClient;
    public static volatile SingularAttribute<Client, Date> dateNaissance;
    public static volatile SingularAttribute<Client, String> animalTotem;
    public static volatile ListAttribute<Client, Voyance> voyances;
    public static volatile SingularAttribute<Client, String> telephone;
    public static volatile SingularAttribute<Client, String> nom;
    public static volatile SingularAttribute<Client, String> courriel;
    public static volatile SingularAttribute<Client, String> couleurPB;
    public static volatile SingularAttribute<Client, String> mdp;
    public static volatile SingularAttribute<Client, String> adresse;
    public static volatile SingularAttribute<Client, String> prenom;
    public static volatile SingularAttribute<Client, String> signeChinois;
    public static volatile SingularAttribute<Client, String> civilite;

}