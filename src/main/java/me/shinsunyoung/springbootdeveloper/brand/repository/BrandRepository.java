package me.shinsunyoung.springbootdeveloper.brand.repository;

import me.shinsunyoung.springbootdeveloper.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findByTitle(String brand);
}
