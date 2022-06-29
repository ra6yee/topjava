package ru.javawebinar.topjava.repository.jpa;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Meal save(Meal meal, int userId) {
meal.setUser(em.getReference(User.class,userId));

        if(meal.isNew()){

            em.persist(meal);
            return meal;
        }
        else{
            if((get(meal.id(),userId)==null)){
            return null;
        }
        }

        em.merge(meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.Delete)
                .setParameter("id",id)
                .setParameter("userId",userId)
                .executeUpdate()!=0;
    }

    @Override
    public Meal get(int id, int userId) {

        return (Meal)em.createNamedQuery(Meal.Get,Meal.class)
                .setParameter("id",id)
                .setParameter("userId",userId)
                .getSingleResult();
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.GetAll,Meal.class)
                .setParameter("id",userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {

        return em.createNamedQuery(Meal.GetBetween,Meal.class)
                .setParameter("startDate",startDateTime)
                .setParameter("finishDate",endDateTime)
                .setParameter("userId",userId)
                .getResultList();
    }
}