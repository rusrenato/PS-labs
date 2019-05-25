package ro.utcluj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcluj.api.ReviewServiceInterface;
import ro.utcluj.dto.ReviewDTO;
import ro.utcluj.enitity.Review;
import ro.utcluj.enitity.User;
import ro.utcluj.mapper.ReviewMapper;
import ro.utcluj.repository.ReviewRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReviewService implements ReviewServiceInterface {

    private ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;

    @Autowired
    private UserService userService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    public List<ReviewDTO> findAllByWorkerName(String name) {
        List<ReviewDTO> list = new ArrayList<>();
        reviewRepository.findAllByWorkerReviewedName(name).forEach(review -> list.add(reviewMapper.map(review)));
        return list;
    }

    public void writeReview(String message, Integer grade, String workerName) {
        Review review = new Review(message, grade, userService.getUserbyUsername(workerName));
        reviewRepository.save(review);
    }

}
