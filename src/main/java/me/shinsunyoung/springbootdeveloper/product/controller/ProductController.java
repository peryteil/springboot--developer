package me.shinsunyoung.springbootdeveloper.product.controller;

import me.shinsunyoung.springbootdeveloper.product.dto.ProductDto;
import me.shinsunyoung.springbootdeveloper.product.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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
    public ResponseEntity<List<ProductDto>> getFindAll() {
        List<ProductDto> productDtos = productService.findAllProduct();
        return ResponseEntity.ok().body(productDtos);
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

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteByid(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ProductDto> findByProductId(@PathVariable("id") Long id) {
        ProductDto productDto = productService.findById(id);
        return ResponseEntity.ok().body(productDto);
    }
}
