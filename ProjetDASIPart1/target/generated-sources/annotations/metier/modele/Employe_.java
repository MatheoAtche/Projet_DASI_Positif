package metier.modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Medium;
import metier.modele.Voyance;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-19T14:10:57")
@StaticMetamodel(Employe.class)
public class Employe_ { 

    public static volatile SingularAttribute<Employe, Integer> nbConsultations;
    public static volatile ListAttribute<Employe, Voyance> voyances;
    public static volatile SingularAttribute<Employe, String> mdp;
    public static volatile SingularAttribute<Employe, String> telephone;
    public static volatile SingularAttribute<Employe, String> nom;
    public static volatile SingularAttribute<Employe, String> prenom;
    public static volatile SingularAttribute<Employe, String> courriel;
    public static volatile SingularAttribute<Employe, String> etat;
    public static volatile ListAttribute<Employe, Medium> mediums;
    public static volatile SingularAttribute<Employe, Long> idEmploye;

}