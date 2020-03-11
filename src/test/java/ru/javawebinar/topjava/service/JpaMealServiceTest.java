package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.repository.MealRepository;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@ActiveProfiles(Profiles.JPA)
public class JpaMealServiceTest extends MealServiceTest {

    @Autowired
    private static MealService service;
    @Autowired
    private static MealRepository repository;

    public JpaMealServiceTest() {
        super(service, repository);
    }


}
