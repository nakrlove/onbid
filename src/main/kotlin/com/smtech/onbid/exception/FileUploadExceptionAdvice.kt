package com.smtech.onbid.exception

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
        return ResponseEntity("File size exceeds limit!", HttpStatus.EXPECTATION_FAILED)
    }



}