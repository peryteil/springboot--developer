package me.shinsunyoung.springbootdeveloper.product.controller;

import me.shinsunyoung.springbootdeveloper.product.dto.ProductDto;
import me.shinsunyoung.springbootdeveloper.product.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
            @RequestPart("dto")ProductDto dto,
            @RequestPart(value = "files" , required = false)List<MultipartFile>files
            ){
        productService.save(dto, files);
        return ResponseEntity.ok().build();
    }
}
