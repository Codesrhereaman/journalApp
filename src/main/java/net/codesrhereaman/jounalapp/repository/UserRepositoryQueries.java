package net.codesrhereaman.jounalapp.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.codesrhereaman.jounalapp.journalentry.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

//we are using this class for creating custom query in mongodb
@RequiredArgsConstructor
@Component
@Slf4j
public class UserRepositoryQueries {

    private final MongoTemplate mongoTemplate;

    public List<User> getUsersWithSA() {
        Query query = new Query();
            query.addCriteria(
                    Criteria.where("email")
                            .regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
            );
        query.addCriteria(
                Criteria.where("sentimentalAnalysis").is(true)
        );

        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
