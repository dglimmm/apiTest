package com.example.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.common.ApiResponse;
import com.example.api.service.MovieService;
import com.example.api.vo.dto.MovieDto;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor //final 필드 자동생성
public class MovieController {
	
	private final MovieService movieService;
	
	//memberList 반환 api
	@Operation(summary = "모든 영화 조회")
	@GetMapping
	public ResponseEntity<ApiResponse<List<MovieDto>>> getMovieList(){	
		List<MovieDto> movieList = movieService.getMovie();
        // 로그 확인
        log.info("가져온 movie 리스트: {}", movieList);
        ApiResponse<List<MovieDto>> res = new ApiResponse<>();
        res.setData(movieList);
        // ResponseEntity로 반환departments);
        return ResponseEntity.ok(res);
		
	}
	
	@Operation(summary = "좋아요 업데이트")
	@PatchMapping
	public ResponseEntity<ApiResponse<String>> updateMovielike(@RequestBody List<MovieDto> updateList) {
	    // 요청 데이터 로그 출력
	    log.info("받은 업데이트 요청 데이터: {}", updateList);
	    // 업데이트 수행 및 결과 확인
	    movieService.updateLikes(updateList);
	    
	    ApiResponse<String> res = new ApiResponse<>();
	    res.setMessage("데이터가 업데이트되었습니다.");
	    // 성공 응답 생성 및 반환
	    return ResponseEntity.ok(res);
	}
	
	@Operation(summary = "영화 데이터 삭제")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> deleteMovie(@Valid @PathVariable Long id) {
	    // 요청 데이터 로그 출력
	    log.info("받은 삭제 요청 데이터: {}", id);
        // 영화 삭제 수행
        movieService.deleteMovie(id);
        
	    ApiResponse<String> res = new ApiResponse<>();
	    res.setMessage("정상적으로 영화데이터가 삭제되었습니다.");
        return ResponseEntity.ok(res);
	}
	
	@Operation(summary = "영화 데이터 삽입")
	@PostMapping
	public ResponseEntity<ApiResponse<String>> insertMovie(@Valid @RequestBody MovieDto insertList) {
	    // 요청 데이터 로그 출력
	    log.info("받은 삽입 요청 데이터: {}", insertList);
	    // 영화 삽입 수행
	    movieService.insertMovie(insertList);
	    // Assert를 사용하여 결과 확인
	    ApiResponse<String> res = new ApiResponse<>();
	    res.setMessage("영화 삽입 성공");
        return ResponseEntity.ok(res);

	}
}
