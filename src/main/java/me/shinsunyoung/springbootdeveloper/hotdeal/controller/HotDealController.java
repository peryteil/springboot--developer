package me.shinsunyoung.springbootdeveloper.hotdeal.controller;

import me.shinsunyoung.springbootdeveloper.hotdeal.dto.HotDealDto;
import me.shinsunyoung.springbootdeveloper.hotdeal.service.HotDealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/hotDeal")
public class HotDealController {
    private final HotDealService hotDealService;

    public HotDealController(HotDealService hotDealService) {
        this.hotDealService = hotDealService;
    }

    @PostMapping("create")
    public ResponseEntity<HotDealDto> insertHotDeal(
            @RequestPart("dto")HotDealDto dto,
            @RequestPart("file")List<MultipartFile> files
            ) {
        hotDealService.createHotDeal(dto, files);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<HotDealDto>> getAllHotDeal(){
        List<HotDealDto> hotDealDtos = hotDealService.findAllHotDeal();
        return ResponseEntity.ok(hotDealDtos);
    }
}
