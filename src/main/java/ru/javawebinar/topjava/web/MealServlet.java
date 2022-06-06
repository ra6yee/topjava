package ru.javawebinar.topjava.web;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MealServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doGet(req, resp);
//        System.out.println("sdfsdhfksdjhfkjsdhf");
//        System.out.println(MealsUtil.mealsTo.size());
//        MealsUtil.mealsTo.forEach(System.out::println);
             req.setAttribute("list", MealsUtil.DataList());
        req.getRequestDispatcher("meal.jsp").forward(req, resp);
      //  resp.sendRedirect("/meal.jsp");
    }
}
