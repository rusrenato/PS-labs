package ro.utcluj.mapper;

import org.springframework.stereotype.Component;
import ro.utcluj.dto.ReviewDTO;
import ro.utcluj.enitity.Review;

@Component
public class ReviewMapper {
    public ReviewMapper() {
    }

    public ReviewDTO map(Review review) {
        UserMapper userMapper = new UserMapper();

        ReviewDTO dto = new ReviewDTO();

        dto.setId(review.getId());
        dto.setNote(review.getNote());
        dto.setReview(review.getReview());
        dto.setWorker(userMapper.map(review.getWorkerReviewed()));

        return dto;
    }
}
