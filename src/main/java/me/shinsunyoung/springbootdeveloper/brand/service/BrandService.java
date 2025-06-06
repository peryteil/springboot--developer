package me.shinsunyoung.springbootdeveloper.brand.service;

import me.shinsunyoung.springbootdeveloper.brand.dto.BrandMain;
import me.shinsunyoung.springbootdeveloper.brand.dto.BrandResDto;
import me.shinsunyoung.springbootdeveloper.brand.dto.BrandTitle;
import me.shinsunyoung.springbootdeveloper.brand.entity.Brand;
import me.shinsunyoung.springbootdeveloper.brand.repository.BrandRepository;
import me.shinsunyoung.springbootdeveloper.config.s3.Image;
import me.shinsunyoung.springbootdeveloper.config.s3.S3Service;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;
import me.shinsunyoung.springbootdeveloper.product.dto.ProductFindAll;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BrandService {
    private final S3Service s3Service;
    private final BrandRepository repository;

    public BrandService(S3Service s3Service, BrandRepository repository) {
        this.s3Service = s3Service;
        this.repository = repository;
    }

    @Transactional
    public void saveBrand(ProductFindAll.BrandDto dto, List<MultipartFile> files) {
        Brand brand = new Brand();
        brand.setTitle(dto.getTitle());
        brand.setContent(dto.getContent());
        brand.setFounded(dto.getFounded());
        brand.setOffice(dto.getOffice());
        brand.setRepresentative(dto.getRepresentative());
        brand.setWebSite(dto.getWebSite());
        brand.setCountry(dto.getCountry());
        brand.setIntroduction(dto.getIntroduction());
        brand.setHistory(dto.getHistory());
        brand.setCreatedAt(LocalDateTime.now());
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                String fileUrl = s3Service.updateFiler(file);
                Image image = new Image();
                image.setFileUrl(fileUrl);
                brand.addImage(image);
                dto.getImages().add(new ImageDto(fileUrl));
            }
        } else {
            System.out.println("임미지 없이 저장");
        }
        repository.save(brand);
    }
    @Transactional
    public List<ProductFindAll.BrandDto> findAll() {
        return repository.findAll().stream().map(ProductFindAll.BrandDto::new).toList();
    }
    @Transactional
    public ProductFindAll.BrandDto findByid(Long id) {
        Brand brand = repository.findById(id).orElse(null);
        return ProductFindAll.BrandDto.fromEntity(brand);
    }
    @Transactional
    public void updateBrand(Long id, ProductFindAll.BrandDto dto, List<MultipartFile> files) {
        Brand brand = repository.findById(id).orElse(null);
        if (dto.getTitle() != null) {
            brand.setTitle(dto.getTitle());
        }
        if (dto.getContent() != null) {
            brand.setContent(dto.getContent());
        }
        if (dto.getCountry() != null) {
            brand.setCountry(dto.getCountry());
        }
        if (dto.getOffice() != null) {
            brand.setRepresentative(dto.getRepresentative());
        }
        if (dto.getWebSite() != null) {
            brand.setWebSite(dto.getWebSite());
        }
        if (dto.getIntroduction() != null) {
            brand.setIntroduction(dto.getIntroduction());
        }
        if (dto.getHistory() != null) {
            brand.setHistory(dto.getHistory());
        }
        if (files != null && !files.isEmpty()) {
            s3Service.patchBrandFile(brand.getId(), files, brand);
        }
        brand.setUpdatedAt(LocalDateTime.now());
        repository.save(brand);
    }
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<BrandResDto> findAllList() {
        return repository.findAll().stream().map(BrandResDto::fromEntity).toList();
    }

    public List<BrandResDto> findBycountry(String country) {
        List<Brand> brands = repository.findByCountry(country);
        return brands.stream().map(BrandResDto::fromEntity).toList();
    }

    public List<BrandMain> findMaun() {
        List<Brand> brands = repository.findRandom3Brands();
        return brands.stream().map(BrandMain::fromEntity).toList();
    }

    public List<BrandTitle> findAllTitle() {
        List<Brand> brands = repository.findAll();
        return brands.stream().map(x -> BrandTitle.fromEntity(x)).toList();
    }
}
