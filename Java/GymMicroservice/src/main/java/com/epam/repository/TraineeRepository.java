package com.epam.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.epam.entity.Trainer;
import com.epam.entity.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.epam.entity.Trainee;
import com.epam.entity.Training;
import org.springframework.data.repository.query.Param;

public interface TraineeRepository extends JpaRepository<Trainee,Integer>{
	
	Optional<Trainee> findByUserUsername(String username);

	@Query("SELECT t FROM Training t WHERE (:from IS NULL OR t.date >= :from) AND (:to IS NULL OR t.date < :to) AND (:trainer IS NULL OR t.trainer = :trainer) AND (:trainingType IS NULL OR t.trainingType = :trainingType) AND (:trainee IS NULL OR t.trainee = :trainee)")
	List<Training> findAllTrainingInBetween(@Param("from") LocalDate from, @Param("to") LocalDate to, @Param("trainer") Trainer trainer, @Param("trainingType") TrainingType trainingType, @Param("trainee") Trainee trainee);


}
