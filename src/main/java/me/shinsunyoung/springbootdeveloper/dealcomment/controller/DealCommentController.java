package me.shinsunyoung.springbootdeveloper.dealcomment.controller;

import me.shinsunyoung.springbootdeveloper.dealcomment.entity.DealComment;
import me.shinsunyoung.springbootdeveloper.dealcomment.service.DealCommentService;
import me.shinsunyoung.springbootdeveloper.hotdeal.entity.HotDeal;
import me.shinsunyoung.springbootdeveloper.hotdeal.service.HotDealService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class DealCommentController {
    private final DealCommentService dealCommentService;
    private final HotDealService hotDealService;

    public DealCommentController(DealCommentService dealCommentService, HotDealService hotDealService) {
        this.dealCommentService = dealCommentService;
        this.hotDealService = hotDealService;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<Void> createComment(
            @PathVariable("id") Long id,
            @RequestParam("comment") String content
    ) {
        dealCommentService.createComment(id, content);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> updateComment(
            @PathVariable("id") Long id,
            @RequestParam("comment") String content
    ) {
        dealCommentService.updateComment(id, content);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<DealComment>> getCommentByHotDealId(@PathVariable("id")Long id) {
        return ResponseEntity.ok(dealCommentService.getCommentByHotDealId(id));
    }
}
