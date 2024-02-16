package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.dto.BarReviewDTO;
import com.Ohsul.Ohsul.entity.*;
import com.Ohsul.Ohsul.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
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

    // 특정 바 전체 리뷰 조회
    public List<BarReviewDTO> getBarReviewAll(Integer barId) {
        List<ReviewEntity> searchResult = reviewRepository.findAllByBar_BarId(barId);
        List<BarReviewDTO> reviews = new ArrayList<>();

        for (ReviewEntity i : searchResult) {
            BarReviewDTO barReviewDTO = BarReviewDTO.builder()
                    .content(i.getContent())
                    .score(i.getScore())
                    .reviewImg(i.getReviewImg()).build();
            reviews.add(barReviewDTO);
        }
        return reviews;
    }

    // 리뷰 등록
    public Boolean createReview(Integer barId, BarReviewDTO barReviewDTO, Integer userNumber) {
        BarEntity bar = barRepository.findById(barId)
                .orElseThrow(()-> new RuntimeException("가게 정보 없음"));

        ReviewEntity review;
        // 회원 리뷰 저장
        if (userNumber != null) {
            UserEntity user = userRepository.findByUserNumber(userNumber)
                    .orElseThrow(() -> new RuntimeException("유저 정보 없음"));

            review = ReviewEntity.builder()
                    .content(barReviewDTO.getContent())
                    .score(barReviewDTO.getScore())
                    .reviewImg(barReviewDTO.getReviewImg())
                    .user(user)
                    .bar(bar)
                    .build();
        } else {
            // 비회원 리뷰 저장
            review = ReviewEntity.builder()
                    .content(barReviewDTO.getContent())
                    .score(barReviewDTO.getScore())
                    .reviewImg(barReviewDTO.getReviewImg())
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

        for(Integer tag : moodTags) {
            MoodEntity mood = moodRepository.findById(tag)
                    .orElseThrow(() -> new RuntimeException(tag + " 태그 정보 없음"));
            BarMoodEntity barMood = new BarMoodEntity(bar, mood, review);
            barMoodRepository.save(barMood);
        }
        return true;
    }

    // 리뷰 수정 (비회원) 인증
    public Boolean editUserCheck(Integer reviewId, BarReviewDTO barReviewDTO) {
        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰 정보 없음"));

        return Objects.equals(review.getNickname(), barReviewDTO.getNickname()) && Objects.equals(review.getReviewPw(), barReviewDTO.getReviewPw());
    }

    // 리뷰 수정 (트랜잭션 적용 예정)
    public Boolean editReview(Integer barId, Integer reviewId, BarReviewDTO barReviewDTO, Integer userNumber) {
        BarEntity bar = barRepository.findById(barId)
                .orElseThrow(() -> new RuntimeException("가게 정보 없음"));

        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰 정보 없음"));

        // 업데이트를 위한 setter 사용
        review.setContent(barReviewDTO.getContent());
        review.setScore(barReviewDTO.getScore());
        review.setReviewImg(barReviewDTO.getReviewImg());

        review = reviewRepository.save(review);

        // 기존 태그 삭제
        barAlcoholRepository.deleteByReview_reviewId(reviewId);
        barMusicRepository.deleteByReview_reviewId(reviewId);
        barMoodRepository.deleteByReview_reviewId(reviewId);

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

        for(Integer tag : moodTags) {
            MoodEntity mood = moodRepository.findById(tag)
                    .orElseThrow(() -> new RuntimeException(tag + " 태그 정보 없음"));
            BarMoodEntity barMood = new BarMoodEntity(bar, mood, review);
            barMoodRepository.save(barMood);
        }
        return true;
    }

    // 리뷰 삭제
    public Boolean deleteReview(Integer reviewId) {
        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰 정보 없음"));
        reviewRepository.deleteById(reviewId);
        return true;
    }

    // 특정 리뷰 조회
    public ReviewEntity getBarReview(Integer reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰 정보 없음"));
    }
}
