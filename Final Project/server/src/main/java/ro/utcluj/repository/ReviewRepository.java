package ro.utcluj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcluj.enitity.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByWorkerReviewedName(String name);

    List<Review> findAll();
}
