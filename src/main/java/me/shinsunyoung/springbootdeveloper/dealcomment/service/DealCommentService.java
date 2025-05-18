package me.shinsunyoung.springbootdeveloper.dealcomment.service;

import me.shinsunyoung.springbootdeveloper.dealcomment.entity.DealComment;
import me.shinsunyoung.springbootdeveloper.dealcomment.repository.DealCommentRepository;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.hotdeal.entity.HotDeal;
import me.shinsunyoung.springbootdeveloper.hotdeal.service.HotDealService;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DealCommentService {
    private final DealCommentRepository dealCommentRepository;
    private final HotDealService hotDealService;
    private final UserRepository userRepository;


    public DealCommentService(DealCommentRepository dealCommentRepository, HotDealService hotDealService, UserRepository userRepository) {
        this.dealCommentRepository = dealCommentRepository;
        this.hotDealService = hotDealService;
        this.userRepository = userRepository;
    }


    public void createComment(Long id, String content, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        HotDeal hotDeal = hotDealService.findById(id);
        DealComment comment = new DealComment();
        comment.setUser(user);
        comment.setHotDeal(hotDeal);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());
        dealCommentRepository.save(comment);
    }

    public void updateComment(Long id, String content) {
        DealComment comment = dealCommentRepository.findById(id).orElse(null);
        comment.setContent(content);
        comment.setUpdatedAt(LocalDateTime.now());
        dealCommentRepository.save(comment);
    }

    public List<DealComment> getCommentByHotDealId(Long id) {
        return dealCommentRepository.findByHotDealId(id);
    }
}
