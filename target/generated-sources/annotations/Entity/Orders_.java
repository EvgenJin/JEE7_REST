package Entity;

import Entity.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.0.v20150309-rNA", date="2019-09-30T02:24:41")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Long> orderid;
    public static volatile SingularAttribute<Orders, Long> price;
    public static volatile SingularAttribute<Orders, String> description;
    public static volatile SingularAttribute<Orders, Person> personId;
    public static volatile SingularAttribute<Orders, Long> id;
    public static volatile SingularAttribute<Orders, String> title;

}