package me.shinsunyoung.springbootdeveloper.config.s3;

import org.springframework.data.jpa.repository.JpaRepository;

public interface S3Repository extends JpaRepository<Image, Long> {
    void deleteByProductId(Long productId);
}
