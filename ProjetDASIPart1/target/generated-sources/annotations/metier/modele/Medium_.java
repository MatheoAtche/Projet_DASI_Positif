package metier.modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Employe;
import metier.modele.Voyance;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-19T14:10:57")
@StaticMetamodel(Medium.class)
public class Medium_ { 

    public static volatile ListAttribute<Medium, Voyance> voyances;
    public static volatile SingularAttribute<Medium, Long> id;
    public static volatile SingularAttribute<Medium, String> nom;
    public static volatile SingularAttribute<Medium, String> descriptif;
    public static volatile ListAttribute<Medium, Employe> employes;

}