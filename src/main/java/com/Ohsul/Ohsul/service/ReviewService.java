package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.BarReviewDTO;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.repository.*;
import io.awspring.cloud.s3.S3Template;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BarRepository barRepository;
    @Autowired
    AlcoholRepository alcoholRepository;
    @Autowired
    MusicRepository musicRepository;
    @Autowired
    MoodRepository moodRepository;
    @Autowired
    BarAlcoholRepository barAlcoholRepository;
    @Autowired
    BarMusicRepository barMusicRepository;
    @Autowired
    BarMoodRepository barMoodRepository;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    private S3Template s3Template;

    @Autowired
    S3Service s3Service;

    @PersistenceContext
    private EntityManager entityManager;

    // 특정 바 전체 리뷰 조회
    public List<BarReviewDTO> getBarReviewAll(Integer barId) {
        List<ReviewEntity> searchResult = reviewRepository.findAllByBar_BarId(barId);
        List<BarReviewDTO> reviews = new ArrayList<>();

        for (ReviewEntity i : searchResult) {
            List<BarAlcoholEntity> barAlcoholEntityList = barAlcoholRepository.findAllByReview_reviewId(i.getReviewId());
            List<BarMusicEntity> barMusicEntityList = barMusicRepository.findAllByReview_reviewId(i.getReviewId());
            List<BarMoodEntity> barMoodEntityList = barMoodRepository.findAllByReview_reviewId(i.getReviewId());

            BarReviewDTO barReviewDTO = BarReviewDTO.builder()
                    .reviewId(i.getReviewId())
                    .content(i.getContent())
                    .score(i.getScore())
                    .reviewImg(i.getReviewImg())
                    .nickname(i.getNickname())
                    .date(i.getDate())
                    .alcoholTags(barAlcoholEntityList.stream().map(BarAlcoholEntity::getAlcohol).map(AlcoholEntity::getAlcoholId).collect(Collectors.toList()))
                    .musicTags(barMusicEntityList.stream().map(BarMusicEntity::getMusic).map(MusicEntity::getMusicId).collect(Collectors.toList()))
                    .moodTags(barMoodEntityList.stream().map(BarMoodEntity::getMood).map(MoodEntity::getMoodId).collect(Collectors.toList()))
                    .build();
            reviews.add(barReviewDTO);
        }
        return reviews;
    }

    // 리뷰 등록
    @Transactional
    public BarReviewDTO createReview(Integer barId, MultipartFile reviewImg, BarReviewDTO barReviewDTO, String userId) {
        BarEntity bar = barRepository.findById(barId)
                .orElseThrow(() -> new RuntimeException("가게 정보 없음"));

        Optional<UserEntity> userSearch = userRepository.findByUserId(userId);
        ReviewEntity review;
        String reviewImgUrl = null;

        if (reviewImg != null) reviewImgUrl = s3Service.uploadReviewImg(reviewImg);

        // 회원 리뷰 저장
        if (userSearch.isPresent()) {
            UserEntity user = userSearch.get();

            review = ReviewEntity.builder()
                    .content(barReviewDTO.getContent())
                    .score(barReviewDTO.getScore())
                    .reviewImg(reviewImgUrl)
                    .nickname(barReviewDTO.getNickname())
                    .user(user)
                    .bar(bar)
                    .build();
        } else {
            // 비회원 리뷰 저장
            review = ReviewEntity.builder()
                    .content(barReviewDTO.getContent())
                    .score(barReviewDTO.getScore())
                    .reviewImg(reviewImgUrl)
                    .nickname(barReviewDTO.getNickname())
                    .reviewPw(barReviewDTO.getReviewPw())
                    .bar(bar)
                    .build();
        }
        review = reviewRepository.save(review);

        List<Integer> alcoholTags = barReviewDTO.getAlcoholTags();
        List<Integer> musicTags = barReviewDTO.getMusicTags();
        List<Integer> moodTags = barReviewDTO.getMoodTags();

        for (Integer tag : alcoholTags) {
            AlcoholEntity alcohol = alcoholRepository.findById(tag)
                    .orElseThrow(() -> new RuntimeException(tag + " 태그 정보 없음"));
            BarAlcoholEntity barAlcohol = new BarAlcoholEntity(bar, alcohol, review);
            barAlcoholRepository.save(barAlcohol);
        }

        for (Integer tag : musicTags) {
            MusicEntity music = musicRepository.findById(tag)
                    .orElseThrow(() -> new RuntimeException(tag + " 태그 정보 없음"));
            BarMusicEntity barMusic = new BarMusicEntity(bar, music, review);
            barMusicRepository.save(barMusic);
        }

        for (Integer tag : moodTags) {
            MoodEntity mood = moodRepository.findById(tag)
                    .orElseThrow(() -> new RuntimeException(tag + " 태그 정보 없음"));
            BarMoodEntity barMood = new BarMoodEntity(bar, mood, review);
            barMoodRepository.save(barMood);
        }
        return BarReviewDTO.builder()
                .content(review.getContent())
                .score(review.getScore())
                .reviewImg(review.getReviewImg())
                .nickname(review.getNickname())
                .alcoholTags(alcoholTags)
                .musicTags(musicTags)
                .moodTags(moodTags)
                .build();
    }

    // 리뷰 수정 (비회원) 인증 (비번 일치 여부 확인)
    public Boolean userCheck(Integer reviewId, BarReviewDTO barReviewDTO) {
        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰 정보 없음"));
        return Objects.equals(review.getReviewPw(), barReviewDTO.getReviewPw());
    }

    // 리뷰 수정
    @Transactional
    public Boolean editReview(Integer barId, Integer reviewId, MultipartFile reviewImg, BarReviewDTO barReviewDTO, String userId) {
        BarEntity bar = barRepository.findById(barId)
                .orElseThrow(() -> new RuntimeException("가게 정보 없음"));

        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰 정보 없음"));

        // 회원 여부 확인
        Optional<UserEntity> userSearch = userRepository.findByUserId(userId);

//        String reviewImgUrl = null;
        String newReviewImgUrl = null;

        if (reviewImg != null) {
            // 기존 리뷰 이미지 s3 삭제
            s3Service.deleteReviewImg(review.getReviewImg());

            // 수정 리뷰 이미지 s3 등록
            newReviewImgUrl = s3Service.uploadReviewImg(reviewImg);
        } else {
            newReviewImgUrl = review.getReviewImg();
        }

        review.setContent(barReviewDTO.getContent());
        review.setScore(barReviewDTO.getScore());
        review.setReviewImg(newReviewImgUrl);

//        if (userSearch.isPresent()) {
//            UserEntity user = userSearch.get();
//
//            review = ReviewEntity.builder()
//                    .content(barReviewDTO.getContent())
//                    .score(barReviewDTO.getScore())
//                    .reviewImg(newReviewImgUrl)
//                    .nickname(review.getNickname())
//                    .user(user)
//                    .bar(bar)
//                    .build();
//        } else {
//            // 비회원 리뷰 저장
//            review = ReviewEntity.builder()
//                    .content(barReviewDTO.getContent())
//                    .score(barReviewDTO.getScore())
//                    .reviewImg(newReviewImgUrl)
//                    .nickname(review.getNickname())
//                    .reviewPw(review.getReviewPw())
//                    .bar(bar)
//                    .build();
//        }
        review = reviewRepository.save(review);

        // 새 태그 생성
        List<Integer> alcoholTags = barReviewDTO.getAlcoholTags();
        List<Integer> musicTags = barReviewDTO.getMusicTags();
        List<Integer> moodTags = barReviewDTO.getMoodTags();

        for (Integer tag : alcoholTags) {
            AlcoholEntity alcohol = alcoholRepository.findById(tag)
                    .orElseThrow(() -> new RuntimeException(tag + " 태그 정보 없음"));
            BarAlcoholEntity barAlcohol = new BarAlcoholEntity(bar, alcohol, review);
            barAlcoholRepository.save(barAlcohol);
        }

        for (Integer tag : musicTags) {
            MusicEntity music = musicRepository.findById(tag)
                    .orElseThrow(() -> new RuntimeException(tag + " 태그 정보 없음"));
            BarMusicEntity barMusic = new BarMusicEntity(bar, music, review);
            barMusicRepository.save(barMusic);
        }

        for (Integer tag : moodTags) {
            MoodEntity mood = moodRepository.findById(tag)
                    .orElseThrow(() -> new RuntimeException(tag + " 태그 정보 없음"));
            BarMoodEntity barMood = new BarMoodEntity(bar, mood, review);
            barMoodRepository.save(barMood);
        }
        deleteAssociateTags(reviewId);

        BarReviewDTO.builder()
                .content(review.getContent())
                .score(review.getScore())
                .reviewImg(review.getReviewImg())
                .nickname(review.getNickname())
                .alcoholTags(alcoholTags)
                .musicTags(musicTags)
                .moodTags(moodTags)
                .build();

        return true;
    }

    public void deleteAssociateTags(Integer reviewId) {
        barAlcoholRepository.deleteByReview_reviewId(reviewId);
        barMusicRepository.deleteByReview_reviewId(reviewId);
        barMoodRepository.deleteByReview_reviewId(reviewId);
    }

    // 리뷰 삭제
    @Transactional
    public Boolean deleteReview(Integer reviewId) {
        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰 정보 없음"));

        List<BarAlcoholEntity> barAlcoholEntityList = barAlcoholRepository.findAllByReview_reviewId(reviewId);
        List<BarMusicEntity>  barMusicEntityList = barMusicRepository.findAllByReview_reviewId(reviewId);
        List<BarMoodEntity> barMoodEntityList = barMoodRepository.findAllByReview_reviewId(reviewId);

        barAlcoholRepository.deleteAll(barAlcoholEntityList);
        barMusicRepository.deleteAll(barMusicEntityList);
        barMoodRepository.deleteAll(barMoodEntityList);

        s3Service.deleteReviewImg(review.getReviewImg());
        reviewRepository.deleteById(reviewId);

        entityManager.flush();

        return true;
    }

    // 리뷰 상세 조회
    public BarReviewDTO getBarReview(Integer reviewId) {
        ReviewEntity barReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰 정보 없음"));

        List<BarAlcoholEntity> barAlcoholEntityList = barAlcoholRepository.findAllByReview_reviewId(barReview.getReviewId());
        List<BarMusicEntity> barMusicEntityList = barMusicRepository.findAllByReview_reviewId(barReview.getReviewId());
        List<BarMoodEntity> barMoodEntityList = barMoodRepository.findAllByReview_reviewId(barReview.getReviewId());

        return BarReviewDTO.builder()
                .content(barReview.getContent())
                .score(barReview.getScore())
                .reviewImg(barReview.getReviewImg())
                .nickname(barReview.getNickname())
                .date(barReview.getDate())
                .alcoholTags(barAlcoholEntityList.stream().map(BarAlcoholEntity::getAlcohol)
                        .map(AlcoholEntity::getAlcoholId).collect(Collectors.toList()))
                .musicTags(barMusicEntityList.stream().map(BarMusicEntity::getMusic)
                        .map(MusicEntity::getMusicId).collect(Collectors.toList()))
                .moodTags(barMoodEntityList.stream().map(BarMoodEntity::getMood)
                        .map(MoodEntity::getMoodId).collect(Collectors.toList()))
                .build();
    }
}
