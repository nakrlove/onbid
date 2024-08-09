package com.smtech.onbid.controller

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
    * 코드등록
    */
    @PostMapping("/regstcode")
    fun onRegstCode( @RequestBody attachmentDTO: AttachCodeDTO): ResponseEntity<out Any> {
        println("================onRegstCode===${attachmentDTO.codename}")
        val result = codeservice.saveCode(attachmentDTO)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    /*
     * 코드수정
    */
    @PutMapping("/updatecode")
    fun onUpdateCode( @RequestBody attachmentDTO: AttachCodeDTO): ResponseEntity<out Any> {
        println("================onUpdateCode===${attachmentDTO.codename}")
        val result = codeservice.updateCode(attachmentDTO)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }


    /*
   * 코드삭제
  */
    @DeleteMapping("/deletecode")
    fun onDeleteCode( @RequestBody attachmentDTO: AttachCodeDTO): ResponseEntity<out Any> {
        println("================onDeleteCode===${attachmentDTO.codename}")
        val result = codeservice.deleteCode(attachmentDTO)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    /*
   * 코드목록 조회
   */
    @PostMapping("/codelist")
    fun onRegstCodeList( @RequestBody attachmentDTO: AttachCodeDTO): ResponseEntity<out Any> {
        println("================onRegstCodeList===${attachmentDTO.codename}")
        /* 페이지 요청 */
        val pageable = PageRequest.of(attachmentDTO.page, attachmentDTO.size)
        println(" pageable = [${attachmentDTO.codename}]")
        val result = codeservice.findListsEntity(attachmentDTO,pageable)
        println("=attachmentDTO result=${result}")
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }
}