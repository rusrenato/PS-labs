package ro.utcluj.api;

import ro.utcluj.dto.ReviewDTO;

import java.util.List;

public interface ReviewServiceInterface {

    List<ReviewDTO> findAllByWorkerName(String name);

    void writeReview(String message, Integer grade, String workerName);
}
