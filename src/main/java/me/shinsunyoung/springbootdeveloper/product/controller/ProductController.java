package me.shinsunyoung.springbootdeveloper.product.controller;

import me.shinsunyoung.springbootdeveloper.brand.entity.Brand;
import me.shinsunyoung.springbootdeveloper.brand.repository.BrandRepository;
import me.shinsunyoung.springbootdeveloper.product.dto.*;
import me.shinsunyoung.springbootdeveloper.product.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/product") // ✅ 여기를 반드시 "/api/product"로 수정
public class ProductController {

    private final ProductService productService;
    private final BrandRepository brandRepository;

    public ProductController(ProductService productService,  BrandRepository brandRepository) {
        this.productService = productService;
        this.brandRepository = brandRepository;
    }
    @GetMapping("/brands")
    public ResponseEntity<List<String>> getBrandTitles() {
        List<String> brandTitles = brandRepository.findAll()
                .stream()
                .map(brand -> brand.getTitle())
                .collect(Collectors.toList());
        return ResponseEntity.ok(brandTitles);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createProduct(
            @RequestPart("dto") ProductDto dto,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
        productService.save(dto, files);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductFindAll>> getFindAll() {
        List<ProductFindAll> product = productService.findAllProduct();
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/findAllSimple")
    public ResponseEntity<List<ProductFindAll>> findAllSimple() {
        List<ProductFindAll> productFindAlls = productService.findAllSimple();
        return ResponseEntity.ok().body(productFindAlls);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable("id") Long id,
            @RequestPart("dto") ProductDto dto,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
        productService.updatePrduct(id, dto, files);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteByid(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ProductDto> findByProductId(@PathVariable("id") Long id) {
        ProductDto productDto = productService.findById(id);
        return ResponseEntity.ok().body(productDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchBrandAndCategory(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String category
    ) {
        List<ProductDto> result = productService.findByBrandAndCategory(brand, category);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findByMain")
    public ResponseEntity<List<ProductMain>> findByMain() {
        List<ProductMain> list = productService.findByMain();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/findRelate/{id}")
    public ResponseEntity<List<ProductRelate>> findRelate(@PathVariable("id") Long id) {
        List<ProductRelate> productRelates = productService.findRelatedProduct(id);
        return ResponseEntity.ok().body(productRelates);
    }

    @GetMapping("/bestReview")
    public ResponseEntity<List<ProBest>> bestReview() {
        List<ProBest> mains = productService.findBestProducts();
        return ResponseEntity.ok().body(mains);
    }

    @GetMapping("/findBest")
    public ResponseEntity<List<ProductRelate>> findTop5() {
        List<ProductRelate> productRelates = productService.findBestpro();
        return ResponseEntity.ok().body(productRelates);
    }
    @GetMapping("/all")
    public ResponseEntity<List<String>> findAllBrandNames() {
        List<String> brandNames = brandRepository.findAll()
                .stream()
                .map(Brand::getTitle)
                .toList();
        return ResponseEntity.ok(brandNames);
    }
}
