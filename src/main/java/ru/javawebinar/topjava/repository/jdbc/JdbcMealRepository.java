package ru.javawebinar.topjava.repository.jdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcMealRepository implements MealRepository {

    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

   private final JdbcTemplate jdbcTemplate;


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;
    @Autowired
    public JdbcMealRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate).withTableName("meals").usingGeneratedKeyColumns("id");
    }
    @Override
    public Meal save(Meal meal, int userId) {
        MapSqlParameterSource map=new MapSqlParameterSource()
                .addValue("id",meal.getId())
                .addValue("dateTime",meal.getDateTime())
                .addValue("description",meal.getDescription())
                .addValue("calories",meal.getCalories())
                .addValue("userid",userId);

if(meal.isNew()){
    meal.setId(insertUser.executeAndReturnKey(map).intValue());

}else {
    if (namedParameterJdbcTemplate.update("UPDATE meals SET  date_time=:dateTime, description=:description WHERE id=:id AND userid=:userid", map) == 0)
        return null;
}
return meal;
    }



    @Override
    public boolean delete(int id, int userId) {

        return jdbcTemplate.update("delete from meals where id=? and userid=?",id,userId) !=0;
    }

    @Override
    public Meal get(int id, int userId) {
        return (Meal) jdbcTemplate.query("Select * from meals where id=? and userid=userId",ROW_MAPPER,userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return jdbcTemplate.query("Select * from meals where userid=? order by date_time ",ROW_MAPPER,userId);
       // return list;
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {

        List<Meal> list=jdbcTemplate.query("select*from meals where date_time >=? and date_time<? and userid=?",ROW_MAPPER,startDateTime, endDateTime, userId);
        return list;
    }
}
