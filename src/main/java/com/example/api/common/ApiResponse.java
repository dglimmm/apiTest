package com.example.api.common;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class ApiResponse<T> {
	
	  @Schema(description = "타임스탬프")
	  private final String timestamp = LocalDateTime.now(ZoneId.of("Asia/Seoul")).toString();

	  @Schema(description = "트랜잭션 아이디")
	  private final UUID txId = UUID.randomUUID();

	  @Schema(description = "에러")
	  private String error;

	  @Schema(description = "메시지")
	  private String message;

	  @Schema(description = "데이터")
	  private T data;

	  // 에러응답과 형식을 맞추기 위한 변수
	  @Schema(description = "입력값 에러")
	  private List<ParamError> paramErrors;


}
