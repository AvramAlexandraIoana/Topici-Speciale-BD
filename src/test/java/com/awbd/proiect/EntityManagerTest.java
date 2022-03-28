package com.awbd.proiect;

import com.awbd.proiect.domain.Tourist;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Rollback(false)
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;

    @Test
    public void findTourist() {
        Tourist touristFound = entityManager.find(Tourist.class, 1l);
        assertEquals(touristFound.getFirstName(), "Ioana");
    }

    @Test
    public void updateTourist() {
        Tourist touristFound = entityManager.find(Tourist.class, 1l);
        touristFound.setLastName("Popa");
        entityManager.persist(touristFound);
        entityManager.flush();
    }

    @Test
    public void findTouristByLastName() {
        Tourist touristFound = entityManager.find(Tourist.class, 1l);
        assertEquals(touristFound.getLastName(), "Popa");
    }


}