package com.smtech.onbid.controller

import com.smtech.onbid.data.dto.OnBidDTO
import com.smtech.onbid.entity.OnBid
import com.smtech.onbid.service.OnBidService
import jakarta.validation.Valid
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

    /* 특정경로에 파일을 저장
    @PostMapping("/onbidL", consumes = ["multipart/form-data"])
    fun onBidList( @RequestPart onbidDTO: OnBidDTO
                   , @RequestPart("file") file: MultipartFile
                   , @RequestPart("additionalFiles") additionalFiles: List<MultipartFile>): ResponseEntity<out Any>{
        println("================onbidL=============")
        
        // 파일 저장 디렉토리
        val uploadDir = File(uploadDirPath)
        if (!uploadDir.exists()) {
            uploadDir.mkdirs()
        }
        // 메인 파일 저장
        val savedFile = Utils.saveFile(file, uploadDir)

        /*
        val fileBytes = file.bytes
        val additionalFilesBytes = additionalFiles.map{ it.bytes }
        */

        // 추가 파일들 저장
        val savedAdditionalFiles = additionalFiles?.map { Utils.saveFile(it, uploadDir) } ?: emptyList()
//        val additionalFilesBytes = additionalFiles.map{ it.bytes }
//        onbid.saveOnBid(onbidDTO,fileBytes,additionalFilesBytes)
        onbid.saveOnBid(onbidDTO,savedFile,savedAdditionalFiles)
        return ResponseEntity.status(HttpStatus.OK).body(onbidDTO)
    }
    */

    /*
     * BLOB 컬럼에 파일저장
     */
    @PostMapping("/onbidL", consumes = ["multipart/form-data"])
    fun onBidList( @Valid @RequestPart onbidDTO: OnBidDTO
                 , @RequestParam("additionalFiles") additionalFiles: List<MultipartFile>?
                 , @RequestParam("additionalFileOptions") options: List<String>?
    ): ResponseEntity<out Any>{
        println("================onbidL=============")

        // additionalFiles와 options를 함께 처리하는 로직
        if (additionalFiles != null && options != null && additionalFiles.size == options.size) {
            additionalFiles.forEachIndexed { index, file ->
                val option = options[index]
                // 파일과 옵션을 함께 저장하는 로직
                println("File: ${file.originalFilename}, Option: $option")
            }
        }

        onbid.saveOnBid(onbidDTO, null, additionalFiles)
        println("=====additionalFiles count[${additionalFiles?.size}]")
        return ResponseEntity.status(HttpStatus.OK).body(onbidDTO)
    }

}