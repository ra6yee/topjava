package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;

@Controller
public class JspMealController {
    private static final Logger log= LoggerFactory.getLogger(JspMealController.class);

    @Autowired
    private UserService service;
    @Autowired
    private MealService mealService;












}


