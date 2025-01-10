package com.example.api.vo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieDto {
	
	private int id;
	private String title;
	private int year;
	private String category;
	private int likeCnt;
	private String imgUrl;
	
}
