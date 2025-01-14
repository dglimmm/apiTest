package com.example.api.vo.dao;

import com.example.api.vo.dto.MovieDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MovieDao {
	
	private Long id;
	
	@NotBlank(message = "title 은(는) 필수 입력사항입니다.")
	private String title;
	
	@NotNull(message = "year 은(는) 필수 입력사항입니다.")
	private int year;
	
	@NotBlank(message = "category 은(는) 필수 입력사항입니다.")
	private String category;
	
	private int likeCnt;
	
	@NotBlank(message = "imgUrl 은(는) 필수 입력사항입니다.")
	private String imgUrl;
	
    // 좋아요 업데이트를 위한 생성자
	@Builder
    public MovieDao(Long id, int likeCnt) {
        this.id = id;
        this.likeCnt = likeCnt;
    }
	
	//좋아요 업데이트를 위한 Dto -> Dao
	public static MovieDao updateFromDao(MovieDto dto) {
		return MovieDao.builder()
				.id(dto.getId())
				.likeCnt(dto.getLikeCnt())
				.build();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
    // toString() 메서드 재정의
    @Override
    public String toString() {
        return String.format("MovieDao{id=%d, title='%s', year=%d, category='%s', likeCnt=%d, imgUrl='%s'}", 
                             id, title, year, category, likeCnt, imgUrl);
    }
	
}
