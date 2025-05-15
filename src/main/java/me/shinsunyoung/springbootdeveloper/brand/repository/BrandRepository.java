package me.shinsunyoung.springbootdeveloper.brand.repository;

import me.shinsunyoung.springbootdeveloper.brand.dto.BrandResDto;
import me.shinsunyoung.springbootdeveloper.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findByTitle(String brand);

    List<Brand> findByCountry(String country);
}
