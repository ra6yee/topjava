package ru.javawebinar.topjava.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries(
        {@NamedQuery(name=Meal.GetAll, query = "SELECT m from Meal m where  m.user.id=:id"),
                @NamedQuery(name=Meal.GetMealById, query= "select u from Meal u where u.id=:id"),
                @NamedQuery(name = Meal.Delete, query = "delete  from Meal m where m.id=:id and m.user.id=:userId"),
                @NamedQuery(name=Meal.Get,query="select m from Meal m where m.id=:id and m.user.id=:userId"),
                @NamedQuery(name=Meal.GetBetween,query="select m from Meal m where  m.user.id=:userId and m.dateTime>=:startDate and m.dateTime<:finishDate")


        }
)

@Entity
@Table(name="meals")
public class Meal extends AbstractBaseEntity {

    public static final String GetAll="Meal.getAll";
    public static final String GetMealById="Meal.getMealById";
    public static final String Delete="Meal.delete";
    public static final String Get="Meal.get";
    public static final String GetBetween="Meal.getBetween";

   @Column(name = "date_time", nullable = false, unique = true)
    private LocalDateTime dateTime;
@Column(name="description",nullable = false)
    private String description;
@Column(name="calories",nullable = false)
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id")


    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
