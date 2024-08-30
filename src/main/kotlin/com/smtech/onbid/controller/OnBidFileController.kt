package com.smtech.onbid.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

import com.smtech.onbid.service.OnBidFileService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*
import java.net.URLEncoder

data class BidRequest(val bididx: Int,val modify:Boolean)

@RestController
@RequestMapping(value=["/api/onbid"])
class OnBidFileController( @Autowired val onfile: OnBidFileService) {


    /** 파일 다운로드 */
    @GetMapping(value=["/{id}"])
    fun onGetFile(@PathVariable id: Int, response: HttpServletResponse): ResponseEntity<Unit> {
        println("================onGetFile=============")
        val file = onfile.getFileById(id) ?: return ResponseEntity.notFound().build()

        val fileObj = file.get()
        response.contentType = fileObj.fileType

        val encodedFileName = URLEncoder.encode(fileObj.fileName, "UTF-8").replace("+", "%20")
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"$encodedFileName\"")
//        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=${fileObj.fileName}")
        fileObj.file?.inputStream()?.copyTo(response.outputStream)
        response.outputStream.flush()

        return ResponseEntity(HttpStatus.OK)
    }



    /** 저장된 파일 카테고리 목록조회 */
    @RequestMapping(value=["/category"])
    fun onFileCategory(@RequestBody param: String): ResponseEntity<out Any> {
        println("================onFileCategory=============")
        println("${param}")
        println("================onFileCategory=============")
        /* 입찰일자 / 감정가/보증금 */
        val objectMapper = jacksonObjectMapper()
        val value: BidRequest = objectMapper.readValue(param)
        val bididx = value.bididx
        println("Extracted bididx: $bididx")

        var result =  onfile.getFileCategory(bididx)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }
}