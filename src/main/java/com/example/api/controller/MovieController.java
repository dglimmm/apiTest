package com.example.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.service.MovieService;
import com.example.api.vo.dto.MovieDto;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor //final 필드 자동생성
public class MovieController {
	
	private final MovieService movieService;
	
	//memberList 반환 api
	@Operation(summary = "모든 영화 조회")
	@GetMapping("/movies")
	public ResponseEntity<List<MovieDto>> getMovieList(){
		
		log.info("movie 리스트 조회 요청");
		
		List<MovieDto> movieList = movieService.getMovie();
		
        // 로그 확인
        log.info("가져온 movie 리스트: {}", movieList);

        // ResponseEntity로 반환departments);
        return ResponseEntity.ok(movieList);
		
	}
	
	@Operation(summary = "좋아요 업데이트")
	@PostMapping("/movies/updateLike")
	public ResponseEntity<String> updateMovielike(@RequestBody List<MovieDto> updateList) {
	    // 요청 데이터 로그 출력
	    log.info("받은 업데이트 요청 데이터: {}", updateList);

	    // 데이터 검증
	    if (updateList.isEmpty()) {
	        log.warn("요청 데이터가 비어있습니다.");
	        return ResponseEntity.badRequest().body("요청 데이터가 비어있습니다.");
	    }

	    try {
	        // 업데이트 수행
	        int result = movieService.updateLikes(updateList);
	        if (result > 0) {
	            log.info("데이터 업데이트 성공: {}", updateList);
	            return ResponseEntity.ok("업데이트 성공");
	        } else {
	            log.warn("업데이트 실패");
	            return ResponseEntity.status(500).body("업데이트 실패");
	        }
	    } catch (Exception e) {
	        log.error("업데이트 실패: {}", e.getMessage());
	        return ResponseEntity.status(500).body("업데이트 실패");
	    }
	}
	
	@Operation(summary = "영화 데이터 삭제")
	@DeleteMapping("/movies/deleteMovie")
	public ResponseEntity<String> deleteMovie(@RequestBody MovieDto deleteDto) {
	    // 요청 데이터 로그 출력
	    log.info("받은 삭제 요청 데이터: {}", deleteDto);

	    // 데이터 검증
	    if (deleteDto == null || deleteDto.getId() == 0) {
	        log.warn("영화 ID가 유효하지 않거나 비어있습니다.");
	        return ResponseEntity.badRequest().body("영화 ID가 유효하지 않거나 비어있습니다.");
	    }
	    try {
	        // 영화 삭제 수행
	        int result = movieService.deleteMovie(deleteDto.getId());

	        if (result > 0) {
	            return ResponseEntity.ok("영화 삭제 성공");
	        } else {
	            log.warn("영화 삭제 실패");
	            return ResponseEntity.status(500).body("영화 삭제 실패");
	        }
	    } catch (Exception e) {
	        log.error("영화 삭제 실패: {}", e.getMessage());
	        return ResponseEntity.status(500).body("영화 삭제 실패");
	    }
	}
	

}
