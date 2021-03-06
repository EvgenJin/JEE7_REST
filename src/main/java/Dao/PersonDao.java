package Dao;

import Entity.Person;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class PersonDao {
    
    @PersistenceContext(unitName = "pers")
    EntityManager em;
    
    public List getAll() {
        return em.createNamedQuery("Person.findAll", Person.class).getResultList();
    }

    public Person findById(Long id) {
        return em.find(Person.class, id);
    }

    public List<Person> findByAdres(String address) {
        return em.createNamedQuery("Person.findByAddres").setParameter("addres","%" + address + "%").getResultList();
    }
    
    public Person findByInn(BigInteger inn) {
        return (Person) em.createNamedQuery("Person.findByInn").setParameter("inn", inn).getSingleResult();
    }    

    public List<Person> findByRecs(String firstName, String surname, String thirdname, BigInteger inn, String addres, Date dateofbirth) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root<Person> cust = query.from(Person.class);
        query.select(cust);

        List<Predicate> predicateList = new ArrayList<>();

        Predicate firstNamePredicate, surnamePredicate, thirdnamePredicate, innPredicate, addresPredicate, dateofbirthPredicate;

//      имя не нулл и не пусто  
        if ((firstName != null) && (!(firstName.isEmpty()))) {
            firstNamePredicate = builder.like(
                builder.upper(cust.<String>get("fname")), "%"+firstName.toUpperCase()+"%");
            predicateList.add(firstNamePredicate);
        }

        if ((surname != null) && (!(surname.isEmpty()))) {
            surnamePredicate = builder.like(
                builder.upper(cust.<String>get("sname")), "%"+surname.toUpperCase()+"%");
            predicateList.add(surnamePredicate);
        }

        if ((thirdname != null) && (!(thirdname.isEmpty()))) {
            thirdnamePredicate = builder.like(
                builder.upper(cust.<String>get("tname")), "%"+thirdname.toUpperCase()+"%");
            predicateList.add(thirdnamePredicate);
        }
        
        if (inn != null) {
            innPredicate = builder.equal(cust.get("inn"), inn);
            predicateList.add(innPredicate);
        }
        
        if ((addres != null) && (!(addres.isEmpty()))) {
            addresPredicate = builder.like(
                builder.upper(cust.<String>get("addres")), "%"+addres.toUpperCase()+"%");
            predicateList.add(addresPredicate);
        }   
        
        if (dateofbirth != null) {
            dateofbirthPredicate = builder.equal(cust.get("dateOfBirth"), dateofbirth);
            predicateList.add(dateofbirthPredicate);
        }        
        
       

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        query.where(predicates);
        return em.createQuery(query).getResultList();
    }
    
    public void update(Person person) {
        em.merge(person);
    }

    public void create(Person person) {
        em.persist(person);
    }

    public void delete(Person person) {
        if (!em.contains(person)) {
            person = em.merge(person);
        }
        em.remove(person);
    }    
    
}
