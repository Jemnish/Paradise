package com.example.paradise.repo;

import com.example.paradise.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

    Optional<Feedback> findFeedbackByEmail(String email);
}
