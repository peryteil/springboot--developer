package me.shinsunyoung.springbootdeveloper.hotdeal.service;

import me.shinsunyoung.springbootdeveloper.config.s3.Image;
import me.shinsunyoung.springbootdeveloper.config.s3.S3Service;
import me.shinsunyoung.springbootdeveloper.dealcomment.dto.DealCommentDto;
import me.shinsunyoung.springbootdeveloper.dealcomment.entity.DealComment;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.HotDealAllDto;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.HotDealDto;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.HotDealMain;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;
import me.shinsunyoung.springbootdeveloper.hotdeal.entity.HotDeal;
import me.shinsunyoung.springbootdeveloper.hotdeal.repository.HotDealRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class HotDealService {
    private final S3Service s3Service;
    private final HotDealRepository hotDealRepository;

    public HotDealService(S3Service s3Service, HotDealRepository hotDealRepository) {
        this.s3Service = s3Service;
        this.hotDealRepository = hotDealRepository;
    }
    @Transactional
    public void createHotDeal(HotDealDto dto, List<MultipartFile> files) {
        HotDeal hotDeal = new HotDeal();
        hotDeal.setTitle(dto.getTitle());
        hotDeal.setContent(dto.getContent());
        hotDeal.setShopName(dto.getShopName());
        hotDeal.setShopLink(dto.getShopLink());
        hotDeal.setPrice(dto.getPrice());
        hotDeal.setCreatedAt(LocalDateTime.now());
        hotDeal.setCategory(dto.getCategory());
        hotDeal.setLikeCount(dto.getLikeCount());
        hotDeal.setViewCount(dto.getViewCount());

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                String fileUrl = s3Service.updateFiler(file);
                Image image = new Image();
                image.setFileUrl(fileUrl);
                image.setHotDeal(hotDeal);
                hotDeal.addImage(image);
                dto.getImageDtos().add(new ImageDto(fileUrl));
            }
        } else {
            System.out.println("이미지 없이 저장됨");
        }
        hotDealRepository.save(hotDeal);
    }
    @Transactional
    public List<HotDealDto> findAllHotDeal() {
        return hotDealRepository.findAll().stream()
                .map(HotDealDto::new).toList();
    }
    @Transactional
    public void updateHotDeal(Long id, HotDealDto dto, List<MultipartFile> files) {
        HotDeal hotDeal = hotDealRepository.findById(id).orElse(null);

        if (dto.getTitle() != null) {
            hotDeal.setTitle(dto.getTitle());
        }
        if (dto.getContent() != null) {
            hotDeal.setContent(dto.getContent());
        }
        if (dto.getPrice() != null) {
            hotDeal.setPrice(dto.getPrice());
        }
        if (dto.getShopName() != null) {
            hotDeal.setShopName(dto.getShopName());
        }
        if (dto.getShopLink() != null) {
            hotDeal.setShopLink(dto.getShopLink());
        }
        if (files != null && !files.isEmpty()) {
            s3Service.pacthFile(hotDeal.getId(), files);
        }
        hotDeal.setUpdatedAt(LocalDateTime.now());

        hotDealRepository.save(hotDeal);
    }
    @Transactional
    public void deleteById(Long id) {
        hotDealRepository.deleteById(id);

    }

    @Transactional
    public HotDealDto commentFindById(Long id) {
        HotDeal hotDeal = hotDealRepository.findById(id).orElse(null);
        return HotDealDto.fromEntity(hotDeal);
    }

    public HotDeal findById(Long id) {
        return hotDealRepository.findById(id).orElse(null);
    }
    @Transactional(readOnly = true)
    public List<HotDealAllDto> findAll() {
        return hotDealRepository.findAll().stream().map(HotDealAllDto::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public List<HotDealAllDto> findTop50() {
        Pageable pageable = PageRequest.of(0, 50);
        return hotDealRepository.findTop50Dto(pageable);
    }

    public void increaseViewCount(Long id) {
        HotDeal hotDeal = hotDealRepository.findById(id).orElse(null);
        hotDeal.setViewCount(hotDeal.getViewCount() + 1);
        hotDealRepository.save(hotDeal);

    }

    public void increaseLikeCount(Long id) {
        HotDeal hotDeal = hotDealRepository.findById(id).orElse(null);
        hotDeal.setLikeCount(hotDeal.getLikeCount() + 1);
        hotDealRepository.save(hotDeal);
    }

    public List<HotDealMain> getTop4Like() {
        List<HotDeal> hotDeals = hotDealRepository.findTop4ByOrderByLikeCountDesc();
        return hotDeals.stream().map(HotDealMain::fromEntity).toList();
    }

    public List<HotDealMain> getTop5Like() {
        List<HotDeal> hotDeals = hotDealRepository.findTop5ByOrderByLikeCountDesc();
        return hotDeals.stream().map(HotDealMain::fromEntity).toList();
    }
}
