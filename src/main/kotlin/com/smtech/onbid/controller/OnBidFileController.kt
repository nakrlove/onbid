package com.smtech.onbid.controller

import com.smtech.onbid.service.FileStorageService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value=["/api/onbid"])
class OnBidFileController( @Autowired val onfile: FileStorageService) {


    /** 파일 다운로드 */
    @GetMapping(value=["/{id}"])
    fun onGetFile(@PathVariable id: Int, response: HttpServletResponse): ResponseEntity<Unit> {
        println("================onbidLDetil=============")
        val file = onfile.getFileById(id) ?: return ResponseEntity.notFound().build()

        response.contentType = file.get().fileType
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=${file.get().fileName}")
        file.get().file?.inputStream()?.copyTo(response.outputStream)
        response.outputStream.flush()

        return ResponseEntity(HttpStatus.OK)
    }
}