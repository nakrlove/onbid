package com.smtech.onbid.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.smtech.onbid.data.dto.MemoDTO
import com.smtech.onbid.data.dto.OnBidDTO
import com.smtech.onbid.data.dto.OnBidDayDTO
import com.smtech.onbid.data.dto.wrapper.OnBidWrapper
import com.smtech.onbid.data.entity.Memos
import com.smtech.onbid.data.entity.wapper.BidAllWrapper
import com.smtech.onbid.service.MemoService
import com.smtech.onbid.service.OnBidService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(value=["/api/onbid"])
class OnBidController( @Autowired val onbid: OnBidService,@Autowired val onbidMemo: MemoService) {

    @Value("\${file.upload-dir}")
    lateinit var uploadDirPath: String


    @RequestMapping(value=["/onBidDetil"])
    fun onBidDetail( @RequestBody onbidDTO: OnBidDTO ): ResponseEntity<out Any>{
        println("================onbidLDetil=============")
        /* 상세조회 */
        val resultOnBid = onbid.findDetail(onbidDTO)
        /* 입찰일자 조회 */
        val resultDays = onbidDTO.bididx?.let { onbid.findDaysQuery(it) }
        /* memo 목록조회 */
        val resultMemos = onbidDTO.bididx?.let { onbidMemo.findMemos(MemoDTO(bididx = it)) }
        return ResponseEntity.status(HttpStatus.OK).body(OnBidWrapper(resultOnBid,resultDays,resultMemos))
    }



    @PostMapping("/onbidList")
    fun onBidList( @RequestBody onbidDTO: OnBidDTO): ResponseEntity<out Any>{
        /* 조회를 역순으로 정렬요청 */
        val sort = Sort.by(Sort.Order.desc("bididx"))
        /* 페이지 요청 */
        val pageable = PageRequest.of(onbidDTO.page, onbidDTO.size,sort)

        val result = onbid.findAll(onbidDTO,pageable)
        println(" pageable = [${result}]")
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    @PostMapping("/onbidAllList")
    fun onBidAllList( @RequestBody onbidDTO: OnBidDTO): ResponseEntity<out Any>{
        /* 조회를 역순으로 정렬요청 */
//        val sort = Sort.by(Sort.Order.desc("bididx"))
        /* 페이지 요청 */
//        val pageable = PageRequest.of(onbidDTO.page, onbidDTO.size,sort)
        val count = onbid.countOnBidWithDetails("")
        val result = onbid.findOnBidLists("",onbidDTO.page,onbidDTO.size)
        println(" pageable = [${result}]")
        val bidResult = BidAllWrapper(count,result)
        return ResponseEntity.status(HttpStatus.OK).body(bidResult)
    }

    /* 특정경로에 파일을 저장 */
    /*
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

        // 추가 파일들 저장
        val savedAdditionalFiles = additionalFiles?.map { Utils.saveFile(it, uploadDir) } ?: emptyList()
        onbid.saveOnBid(onbidDTO,savedFile,savedAdditionalFiles)
        return ResponseEntity.status(HttpStatus.OK).body(onbidDTO)
    }
    */


    /*
     * BLOB 컬럼에 파일저장
     */
    @PostMapping("/onBidRegist", consumes = ["multipart/form-data"])
    fun onRegistBid( @Valid @RequestPart onbidDTO: OnBidDTO
                 , @RequestParam("additionalFiles") additionalFiles: List<MultipartFile>?/* 파일첨부 */
                 , @RequestParam("additionalFileOptions") options: List<String>?
                 , @RequestPart("onbidDays") onbidDaysJson: String /* 입찰일자\감정가\보증금 */
    ): ResponseEntity<out Any>{
        println("================onbidL=============")

        // additionalFiles와 options를 함께 처리하는 로직
        if (additionalFiles != null && options != null && additionalFiles.size == options.size) {
            additionalFiles.forEachIndexed { index, file ->
                val option = options[index]
                // 파일과 옵션을 함께 저장하는 로직
                println("File: ${file.originalFilename}, Option: $option , ${file.contentType}")
            }
        }

        /* 입찰일자 / 감정가/보증금 */
        val objectMapper = jacksonObjectMapper()
        val onbidDays: List<OnBidDayDTO> = objectMapper.readValue(onbidDaysJson)
        if (onbidDays != null ) {
            onbidDays.forEachIndexed { index, file ->
                val option = onbidDays[index]
                // 파일과 옵션을 함께 저장하는 로직
                println(" onbidDays:  , edate=${option.edate} ,evalue=${option.evalue}")
            }
        }

        onbid.saveOnBid(onbidDTO, options, additionalFiles,onbidDays)
        return ResponseEntity.status(HttpStatus.OK).body(onbidDTO)
    }

}