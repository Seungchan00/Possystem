package PosSys.PosSys.controller;

import PosSys.PosSys.domain.Restaurant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;

import static org.junit.Assert.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class apiControllerTest {
    @Autowired EntityManager em;
    @Test

    private void create() throws Exception
    {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1111L);
        restaurant.setName("My Restaurant");
        restaurant.setLocation("Seoul, Korea");
        em.persist(restaurant);

    }
}