package com.example.api.vo.dto;


import com.example.api.vo.dao.MovieDao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MovieDto {
	
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
	
	@Builder
	public MovieDto(Long id, String title, int year, String category, int likeCnt, String imgUrl) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.category = category;
		this.likeCnt = likeCnt;
		this.imgUrl = imgUrl;
	}
	
	//목록 조회를 위한 Dao -> Dto
	public static MovieDto listfromDao(MovieDao dao) {
		return MovieDto.builder()
				.id(dao.getId())
    	        .title(dao.getTitle())
    	        .year(dao.getYear())
    	        .category(dao.getCategory())
    	        .likeCnt(dao.getLikeCnt())
    	        .imgUrl(dao.getImgUrl())
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
        return String.format("MovieDto{id=%d, title='%s', year=%d, category='%s', likeCnt=%d, imgUrl='%s'}", 
                             id, title, year, category, likeCnt, imgUrl);
    }
	
	
	
}
