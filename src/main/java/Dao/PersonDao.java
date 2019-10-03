/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Person;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author admin
 */
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
    
    public List<Person> findByFio(String firstName, String surname, String thirdname) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root<Person> cust = query.from(Person.class);
        query.select(cust);

        List<Predicate> predicateList = new ArrayList<>();

        Predicate firstNamePredicate, surnamePredicate;

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
            surnamePredicate = builder.like(
                builder.upper(cust.<String>get("tname")), "%"+thirdname.toUpperCase()+"%");
            predicateList.add(surnamePredicate);
        }            

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        query.where(predicates);
//        System.err.println(query.toString());
        return em.createQuery(query).getResultList();
//            System.out.println(query);
//            return null;
    }         
    
}
