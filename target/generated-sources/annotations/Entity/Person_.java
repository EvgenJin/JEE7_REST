package Entity;

import java.math.BigInteger;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.0.v20150309-rNA", date="2019-09-30T02:24:41")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, String> sex;
    public static volatile SingularAttribute<Person, BigInteger> inn;
    public static volatile SingularAttribute<Person, String> addres;
    public static volatile SingularAttribute<Person, Date> dateOfBirth;
    public static volatile SingularAttribute<Person, Integer> id;
    public static volatile SingularAttribute<Person, String> fullname;
    public static volatile SingularAttribute<Person, String> pfr;

}