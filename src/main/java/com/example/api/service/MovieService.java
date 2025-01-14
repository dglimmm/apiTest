package com.example.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.api.mapper.MovieMapper;
import com.example.api.vo.dao.MovieDao;
import com.example.api.vo.dto.MovieDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {
	
	private final MovieMapper movieMapper;
	
    // 영화목록 조회
    public List<MovieDto> getMovie() {
        // DAO 리스트 가져오기
        List<MovieDao> daoList = movieMapper.getMovieList();
        // DAO -> DTO 변환
        List<MovieDto> movieDtoList = daoList.stream()
                .map(MovieDto::listfromDao) // 변환 메서드 호출
                .collect(Collectors.toList());
        return movieDtoList;
    }

    
    //좋아요 업데이트
    public void updateLikes(List<MovieDto> updateList){
        // MovieDto를 MovieDao로 변환
        List<MovieDao> mDaoList = updateList.stream()
                .map(MovieDao::updateFromDao)
                .collect(Collectors.toList());
        // MyBatis 매퍼 호출하여 업데이트 수행
        movieMapper.updateLikes(mDaoList); 	
    }
    
    // 영화 삭제
    public void deleteMovie(Long movieId) {
        movieMapper.deleteMovieById(movieId);
    }
    //영화 삽입
    public void insertMovie(MovieDto insertList) {
    	movieMapper.insertMovie(insertList);
    }
    
}
