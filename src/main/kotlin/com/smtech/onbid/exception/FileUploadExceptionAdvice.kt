package com.smtech.onbid.exception

import org.springframework.http.HttpHeaders
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@ControllerAdvice
class FileUploadExceptionAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException::class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    fun handleMaxSizeException(e: MaxUploadSizeExceededException): ResponseEntity<String> {
        // CORS 헤더를 설정합니다.
        val headers = HttpHeaders()
        headers.add("Access-Control-Allow-Origin", "*") // 모든 도메인 허용. 필요시 특정 도메인으로 변경 가능
        headers.add("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization")

        return ResponseEntity("File size exceeds limit!", headers, HttpStatus.EXPECTATION_FAILED)
    }


}