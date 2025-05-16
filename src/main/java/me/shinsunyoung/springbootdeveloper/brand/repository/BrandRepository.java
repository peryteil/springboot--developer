package me.shinsunyoung.springbootdeveloper.brand.repository;

import me.shinsunyoung.springbootdeveloper.brand.dto.BrandResDto;
import me.shinsunyoung.springbootdeveloper.brand.entity.Brand;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findByTitle(String brand);

    List<Brand> findByCountry(String country);
    @Query(value = "SELECT * FROM brand ORDER BY RAND() LIMIT 4", nativeQuery = true)
    List<Brand> findRandom3Brands();

}
