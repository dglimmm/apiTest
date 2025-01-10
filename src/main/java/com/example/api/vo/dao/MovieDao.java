package com.example.api.vo.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class MovieDao {
	private int id;
	private String title;
	private int year;
	private String category;
	private int likeCnt;
	private String imgUrl;
	
    // 좋아요 업데이트를 위한 생성자
    public MovieDao(int id, int likeCnt) {
        this.id = id;
        this.likeCnt = likeCnt;
    }
	
	
}
