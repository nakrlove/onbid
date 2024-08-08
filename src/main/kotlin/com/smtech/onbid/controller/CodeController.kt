package com.smtech.onbid.controller

import com.smtech.onbid.data.dao.AttachCodeDAO
import com.smtech.onbid.data.dto.AttachCodeDTO
import com.smtech.onbid.service.CodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value=["/api/onbid"])
class CodeController(@Autowired val codeservice: CodeService) {
    /*
    * BLOB 컬럼에 파일저장
    */
    @PostMapping("/regstcode")
    fun onRegstCode( @RequestBody attachmentDTO: AttachCodeDTO): ResponseEntity<out Any> {
        println("================attachmentDTO===${attachmentDTO.codename}")
        val result = codeservice.saveCode(attachmentDTO)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    /*
   * BLOB 컬럼에 파일저장
   */
    @PostMapping("/codelist")
    fun onRegstCodeList( @RequestBody attachmentDTO: AttachCodeDTO): ResponseEntity<out Any> {
        println("================attachmentDTO===${attachmentDTO.codename}")
        /* 페이지 요청 */
        val pageable = PageRequest.of(attachmentDTO.page, attachmentDTO.size)

        val result = codeservice.findListsEntity(attachmentDTO,pageable)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }
}