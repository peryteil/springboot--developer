package me.shinsunyoung.springbootdeveloper.brand.controller;

import me.shinsunyoung.springbootdeveloper.brand.dto.BrandDto;
import me.shinsunyoung.springbootdeveloper.brand.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createBrand(
            @RequestPart("dto") BrandDto dto,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
        brandService.saveBrand(dto, files);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<BrandDto>> findAllBrand() {
        List<BrandDto> brandDtos = brandService.findAll();
        return ResponseEntity.ok().body(brandDtos);
    }

    @GetMapping("/findBy/{id}")
    public ResponseEntity<BrandDto> findById(@PathVariable("id") Long id) {
        BrandDto dto = brandService.findByid(id);
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> updateBrand(
            @PathVariable("id") Long id,
            @RequestPart("dto") BrandDto dto,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
        brandService.updateBrand(id, dto, files);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        brandService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
