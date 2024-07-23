package com.smtech.onbid.controller

import com.smtech.onbid.data.dto.OnBidDTO
import com.smtech.onbid.entity.OnBid
import com.smtech.onbid.service.OnBidService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException

@RestController
@RequestMapping(value=["/api/onbid"])
class OnBidController( @Autowired val onbid: OnBidService) {


//    @RequestMapping(value=["/onbidL"])
//    fun onBidList(   @RequestBody onbidDTO: OnBidDTO
//                   , @RequestParam("file") file: MultipartFile
//                   , @RequestParam("additionalFiles") additionalFiles: List<MultipartFile>): ResponseEntity<out Any>{
//        println("================onbidL=============")
////        val onBidData = OnBid(null,contents = onbidDTO.contents,contphone = onbidDTO.contphone)
//        val fileBytes = file.bytes
//        val additionalFilesBytes = additionalFiles.map{ it.bytes }
//        onbid.saveOnBid(onbidDTO,fileBytes,additionalFilesBytes)
//        return ResponseEntity.status(HttpStatus.OK).body(onbidDTO)
//    }

    @Value("\${file.upload-dir}")
    lateinit var uploadDirPath: String

    @PostMapping("/onbidL", consumes = ["multipart/form-data"])
    fun onBidList(   @RequestPart onbidDTO: OnBidDTO
                     , @RequestPart("file") file: MultipartFile
                     , @RequestPart("additionalFiles") additionalFiles: List<MultipartFile>): ResponseEntity<out Any>{
        println("================onbidL=============")
//        val onBidData = OnBid(null,contents = onbidDTO.contents,contphone = onbidDTO.contphone)

        // 파일 저장 디렉토리
        val uploadDir = File(uploadDirPath)
        if (!uploadDir.exists()) {
            uploadDir.mkdirs()
        }
// 메인 파일 저장
        val savedFile = saveFile(file, uploadDir)

        /*
        val fileBytes = file.bytes
        val additionalFilesBytes = additionalFiles.map{ it.bytes }
        */

        // 추가 파일들 저장
        val savedAdditionalFiles = additionalFiles?.map { saveFile(it, uploadDir) } ?: emptyList()
//        val additionalFilesBytes = additionalFiles.map{ it.bytes }
//        onbid.saveOnBid(onbidDTO,fileBytes,additionalFilesBytes)
        onbid.saveOnBid(onbidDTO,null,null)
        return ResponseEntity.status(HttpStatus.OK).body(onbidDTO)
    }



    @Throws(IOException::class)
    private fun saveFile(file: MultipartFile, uploadDir: File): File {
        val filePath = File(uploadDir, file.originalFilename ?: file.name)
        file.transferTo(filePath)
        return filePath
    }
}