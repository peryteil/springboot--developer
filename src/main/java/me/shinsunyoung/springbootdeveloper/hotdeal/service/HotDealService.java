package me.shinsunyoung.springbootdeveloper.hotdeal.service;

import me.shinsunyoung.springbootdeveloper.config.s3.Image;
import me.shinsunyoung.springbootdeveloper.config.s3.S3Service;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.HotDealDto;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;
import me.shinsunyoung.springbootdeveloper.hotdeal.entity.HotDeal;
import me.shinsunyoung.springbootdeveloper.hotdeal.repository.HotDealRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
}
