package com.example.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.api.vo.dao.MovieDao;

@Mapper
public interface MovieMapper {
	
	//모든 영화 정보 조회
	List<MovieDao> getMovieList();
	
	 // 좋아요 업데이트 (여러 개의 영화 업데이트)
	int updateLikes(List<MovieDao> movieList);
	
	//영화 삭제
	int deleteMovieById(int movieId);
}
