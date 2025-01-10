package com.example.api.service;


import java.util.ArrayList;
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

        // DAO 데이터가 잘 넘어왔는지 로그 확인
        log.info("DAO 데이터: {}", daoList);
        
        
     // DAO 리스트가 비어있지 않으면 다음 작업 수행
        if (daoList != null && !daoList.isEmpty()) {
            
        	// DAO 데이터를 DTO로 변환
        	List<MovieDto> movieDtoList = daoList.stream()
        	    .map(dao -> new MovieDto(
        	        dao.getId(),
        	        dao.getTitle(),
        	        dao.getYear(),
        	        dao.getCategory(),
        	        dao.getLikeCnt(),
        	        dao.getImgUrl()))
        	    .collect(Collectors.toCollection(ArrayList::new));

	        // DTO 데이터 로그 확인
	        log.info("DTO 데이터: {}", movieDtoList);
	
	        return movieDtoList;
        } else {
            // daoList가 비어 있을 때 처리할 작업
            log.warn("DAO 리스트에 데이터가 없습니다.");
            throw new RuntimeException("No movies found");  
        }

    }
    
    //좋아요 업데이트
    public int updateLikes(List<MovieDto> updateList){
    	
        // MovieDto를 MovieDao로 변환
        List<MovieDao> mDaoList = updateList.stream()
                .map(dto -> new MovieDao(dto.getId(), dto.getLikeCnt()))
                .collect(Collectors.toList());

        // MyBatis 매퍼 호출하여 업데이트 수행
        int result = movieMapper.updateLikes(mDaoList);

        // 업데이트 결과 로그 확인
        log.info("좋아요 업데이트 결과: {}", result);

        return result;
    	
    }
    
    // 영화 삭제
    public int deleteMovie(int movieId) {
        log.info("영화 삭제 요청 ID: {}", movieId);
        
        // 삭제 쿼리 호출
        int result = movieMapper.deleteMovieById(movieId);
        
        if (result > 0) {
            log.info("영화 삭제 성공 ID: {}", movieId);
        } else {
            log.warn("영화 삭제 실패 ID: {}", movieId);
        }
        
        return result;
    }
    
}
