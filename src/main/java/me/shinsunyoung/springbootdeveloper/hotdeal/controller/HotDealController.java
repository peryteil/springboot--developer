package me.shinsunyoung.springbootdeveloper.hotdeal.controller;

import me.shinsunyoung.springbootdeveloper.dealcomment.dto.DealCommentDto;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.HotDealAllDto;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.HotDealDto;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.HotDealMain;
import me.shinsunyoung.springbootdeveloper.hotdeal.service.HotDealService;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import me.shinsunyoung.springbootdeveloper.service.CustomUserDetails;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/hotDeal")
public class HotDealController {
    private final HotDealService hotDealService;
    private final UserRepository userRepository;

    public HotDealController(HotDealService hotDealService, UserRepository userRepository1) {
        this.hotDealService = hotDealService;
        this.userRepository = userRepository1;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> insertHotDeal(
            @RequestPart("dto") HotDealDto dto,
            @RequestPart(value = "files", required = false) List<MultipartFile> files,
            @AuthenticationPrincipal CustomUserDetails userDetails
            ) {
        Long userId = userDetails.getUserId();
        User user = userRepository.findById(userId).orElse(null);
        System.out.printf(user.getEmail() + "==========================================");
        hotDealService.createHotDeal(dto, files, userId );
        return ResponseEntity.ok().build();
    }
    //전체 불러오기 핫딜의 모든것 굳이 안쓸듯
    @GetMapping("/findAll")
    public ResponseEntity<List<HotDealDto>> getAllHotDeal(){
        List<HotDealDto> hotDealDtos = hotDealService.findAllHotDeal();
        return ResponseEntity.ok(hotDealDtos);
    }
    @GetMapping("/findAllList")
    public ResponseEntity<List<HotDealAllDto>> allHotDeal(){
        List<HotDealAllDto> hotDealAllDtos = hotDealService.findAll();
        return ResponseEntity.ok(hotDealAllDtos);
    }



    @PatchMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateHotDeal(
            @PathVariable("id") Long id,
            @RequestPart("dto") HotDealDto dto,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
        hotDealService.updateHotDeal(id, dto, files);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        hotDealService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<HotDealDto> dealCommentFindById(@PathVariable("id") Long id) {
        HotDealDto dto = hotDealService.commentFindById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping("/increaseViewCount/{id}")
    public ResponseEntity<Void> increaseViewCount(@PathVariable("id") Long id) {
        hotDealService.increaseViewCount(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/increaseLikeCount/{id}")
    public ResponseEntity<Void> increaseLikeCount(@PathVariable("id") Long id) {
        hotDealService.increaseLikeCount(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/topmain")
    public ResponseEntity<List<HotDealMain>> getTop3() {
        List<HotDealMain> mains = hotDealService.getTop4Like();
        return ResponseEntity.ok().body(mains);
    }
    @GetMapping("/top")
    public ResponseEntity<List<HotDealMain>> getTop5() {
        List<HotDealMain> mains = hotDealService.getTop5Like();
        return ResponseEntity.ok().body(mains);
    }
}
