package com.example.paradise;

import com.example.paradise.entity.Feedback;
import com.example.paradise.repo.FeedbackRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FeedbackRepositoryTest {

    @Autowired
    private FeedbackRepo feedbackRepo;

    @Test
    @Order(1)
    public void shouldSaveFeedback() {
        Feedback feedback = createFeedback();

        feedbackRepo.save(feedback);

        Assertions.assertThat(feedback.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void shouldGetFeedback() {
        Feedback feedback = createFeedback();
        feedbackRepo.save(feedback);

        Feedback feedbackCreated = feedbackRepo.findById(feedback.getId()).get();

        Assertions.assertThat(feedbackCreated.getId()).isEqualTo(feedback.getId());
    }

    @Test
    @Order(3)
    public void shouldGetListOfFeedback() {
        Feedback feedback = createFeedback();
        feedbackRepo.save(feedback);

        List<Feedback> feedbackList = feedbackRepo.findAll();

        Assertions.assertThat(feedbackList).isNotEmpty();
    }

    @Test
    @Order(4)
    public void shouldUpdateFeedback() {
        Feedback feedback = createFeedback();
        feedbackRepo.save(feedback);

        Feedback feedback1 = feedbackRepo.findById(feedback.getId()).get();
        feedback1.setEmail("newemail@gmail.com");
        Feedback feedbackUpdated = feedbackRepo.save(feedback1);

        Assertions.assertThat(feedbackUpdated.getEmail()).isEqualTo("newemail@gmail.com");
    }

    @Test
    @Order(5)
    public void shouldDeleteFeedback() {
        Feedback feedback = createFeedback();
        feedbackRepo.save(feedback);

        Feedback feedback1 = feedbackRepo.findById(feedback.getId()).get();
        feedbackRepo.delete(feedback1);

        Optional<Feedback> optionalFeedback = feedbackRepo.findFeedbackByEmail("jenish@gmail.com");

        Assertions.assertThat(optionalFeedback).isEmpty();
    }

    private Feedback createFeedback() {
        return Feedback.builder()
                .email("jenish@gmail.com")
                .message("wow")
                .build();
    }
}
