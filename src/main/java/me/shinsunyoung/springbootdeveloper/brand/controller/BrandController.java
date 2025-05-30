package me.shinsunyoung.springbootdeveloper.brand.controller;

import me.shinsunyoung.springbootdeveloper.brand.dto.BrandMain;
import me.shinsunyoung.springbootdeveloper.brand.dto.BrandResDto;
import me.shinsunyoung.springbootdeveloper.brand.dto.BrandTitle;
import me.shinsunyoung.springbootdeveloper.brand.service.BrandService;
import me.shinsunyoung.springbootdeveloper.product.dto.ProductFindAll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createBrand(
            @RequestPart("dto") ProductFindAll.BrandDto dto,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
        brandService.saveBrand(dto, files);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findTitle")
    public ResponseEntity<List<BrandTitle>> findBrandTitle() {
        List<BrandTitle> brandTitles = brandService.findAllTitle();
        return ResponseEntity.ok().body(brandTitles);
    }
    @GetMapping("/findAllList")
    public ResponseEntity<List<BrandResDto>> findAllListBrand() {
        List<BrandResDto> dtos = brandService.findAllList();
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/findByCountry/{country}")
    public ResponseEntity<List<BrandResDto>> findByCountry(@PathVariable("country") String country) {
        List<BrandResDto> dtos = brandService.findBycountry(country);
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductFindAll.BrandDto>> findAllBrand() {
        List<ProductFindAll.BrandDto> brandDtos = brandService.findAll();
        return ResponseEntity.ok().body(brandDtos);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ProductFindAll.BrandDto> findById(@PathVariable("id") Long id) {
        ProductFindAll.BrandDto dto = brandService.findByid(id);
        return ResponseEntity.ok().body(dto);
    }
    @GetMapping("/findByMain")
    public ResponseEntity<List<BrandMain>> findByMain() {
        List<BrandMain> list = brandService.findMaun();
        return ResponseEntity.ok().body(list);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> updateBrand(
            @PathVariable("id") Long id,
            @RequestPart("dto") ProductFindAll.BrandDto dto,
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