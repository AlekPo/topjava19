package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.repository.UserRepository;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@ActiveProfiles(Profiles.JDBC)
public class JdbcUserServiceTest extends UserServiceTest {

    @Autowired
    private static UserService service;
    @Autowired
    private static UserRepository repository;

    public JdbcUserServiceTest() {
        super(service, repository);
    }
}
