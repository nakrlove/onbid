package com.smtech.onbid.controller

import com.smtech.onbid.data.dto.CodeDTO
import com.smtech.onbid.data.entity.Codes
import com.smtech.onbid.service.CodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.data.domain.Sort

@RestController
@RequestMapping(value=["/api/onbid"])
class CodeController(@Autowired val codeservice: CodeService) {
    /*
    * 코드등록
    */
    @PostMapping("/regstcode")
    fun onRegstCode( @RequestBody codeDTO: CodeDTO): ResponseEntity<out Any> {
        println("================onRegstCode===${codeDTO.codename}")
        val result = codeservice.saveCode(codeDTO)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    /*
     * 코드수정
    */
    @PutMapping("/updatecode")
    fun onUpdateCode( @RequestBody codeDTO: CodeDTO): ResponseEntity<out Any> {
        println("================onUpdateCode===${codeDTO.codename}")
        val result = codeservice.updateCode(codeDTO)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    /*
     * 코드삭제
    */
    @DeleteMapping("/deletecode")
    fun onDeleteCode( @RequestBody codeDTO: CodeDTO): ResponseEntity<out Any> {
        println("================onDeleteCode===${codeDTO.codename}")
        val result = codeservice.deleteCode(codeDTO)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    /*
   * 코드목록 조회
   */
    @PostMapping("/codelist")
    fun onRegstCodeList( @RequestBody codeDTO: CodeDTO): ResponseEntity<out Any> {
        println("====onRegstCodeList, attachmentDTO.page:${codeDTO.page}, attachmentDTO.codename:${codeDTO.codename}")
        /* 조회를 역순으로 정렬요청 */
        val sort = Sort.by(Sort.Order.desc("idx"))
        /* 페이지 요청 */
        val pageable = PageRequest.of(codeDTO.page, codeDTO.size,sort)
        println(" pageable = [${codeDTO.codename}]")
        val result = codeservice.findListsEntity(codeDTO,pageable)
        println("=attachmentDTO result=${result}")
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }


    /*
    * 마스트 코드목록 조회
    */
    @PostMapping("/groupcode")
    fun onMaskCodeList(@RequestBody request: Map<String, String>): ResponseEntity<out Any> {
        val subcode = request["subcode"] ?: ""
        println("================onMaskCodeList===${subcode}")
        /* 페이지 요청 */
        val result = codeservice.findMastCode(subcode)
        println("=attachmentDTO result=${result}")
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    /*
   * 파일첨부 구분코드
   */
    @PostMapping("/file-code")
    fun onFileCodeList(@RequestParam("codes") codes: List<String>?): ResponseEntity<out Any> {
        var result: List<Codes>? = null
        codes?.let{
            println(" === 1) onFileCodeList ===========")
            result = codeservice.findCodeQuery(codes)
        } ?: run {
            println(" === 2) onFileCodeList ===========")
            result = codeservice.findCodeQuery()
        }
//        val result = codeservice.findCodeQuery()
        println("=attachmentDTO result=${result}")
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }


}