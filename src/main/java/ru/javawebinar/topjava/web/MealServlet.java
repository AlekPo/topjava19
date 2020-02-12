package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.MemoryStorageMeal;
import ru.javawebinar.topjava.storage.StorageMeal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private StorageMeal storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = new MemoryStorageMeal();
        MealsUtil.testData().forEach(storage::save);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalTime time = LocalTime.parse(request.getParameter("time"));
        String description = request.getParameter("description");
        int calories = Integer.valueOf(request.getParameter("calories"));
        if (id.isEmpty()) {
            Meal meal = new Meal(MealsUtil.newId(), LocalDateTime.of(date, time), description, calories);
            storage.save(meal);
        } else {
            Meal meal = new Meal(id, LocalDateTime.of(date, time), description, calories);
            storage.update(meal);
        }
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to users");

        String id = request.getParameter("id");
        String action = Objects.nonNull(request.getParameter("action")) ? request.getParameter("action") : "list";
        Meal meal;
        switch (action) {
            case "delete":
                storage.delete(id);
                response.sendRedirect("meals");
                return;
            case "add":
                meal = new Meal("", LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 0);
                break;
            case "edit":
                meal = storage.get(id);
                break;
            case "list":
            default:
                int caloriesPerDay = 2000;
                List<MealTo> mealsTo = MealsUtil.filteredByStreams((List<Meal>) storage.getAll().values(), LocalTime.MIN, LocalTime.MAX, caloriesPerDay);

                request.setAttribute("mealsTo", mealsTo);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                return;
        }
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }
}
