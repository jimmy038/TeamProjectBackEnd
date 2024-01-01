
package com.example.PipiShrimp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PipiShrimp.entity.Feedback;

@Repository
public interface FeedbackDao extends JpaRepository<Feedback, Integer>{


	
}
