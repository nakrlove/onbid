package com.smtech.onbid.service.impl

import com.smtech.onbid.data.dto.OnBidDTO
import com.smtech.onbid.data.dto.OnBidDayDTO
import com.smtech.onbid.entity.Memos
import com.smtech.onbid.entity.OnBid
import com.smtech.onbid.entity.OnBidDays
import com.smtech.onbid.entity.OnBidFile
import com.smtech.onbid.handler.OnBidDataHandler
import com.smtech.onbid.service.OnBidService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.math.BigDecimal

@Service
class OnBidServiceImpl(@Autowired val onBidHandler: OnBidDataHandler): OnBidService{

//    @Transactional
//    override fun saveOnBid(onBidDTO: OnBidDTO, fileBytes: MultipartFile?, additionalFilesBytes: List<MultipartFile>?): OnBid {
//        //onBid.regdate = LocalDateTime.now() // Set the current date/time
//        println(" saveOnBid =============== 1")
//        val onbid  = OnBid( addr1 = onBidDTO.addr1
//                          , addr2 = onBidDTO.detailAddress
//                          , bankruptcyName = onBidDTO.bankruptcyName
//                          , bankruptcyphone = onBidDTO.bankruptcyPhone)

//        val mainFile = fileBytes?.let {
//            OnBidFile(
//                fileName = it.originalFilename ?: "main_file",
//                fileSize = it.size,
//                file     = it.bytes,
//                onBidFiles    = onbid
//            )
//        }
//
//
//        if (mainFile != null) {
//            onbid.onBidFiles?.add(mainFile)
//        }
//
//        additionalFilesBytes?.forEach {  additionalFile ->
//            val onBidFile = OnBidFile(
//                fileName = additionalFile.originalFilename ?: "additional_file_${additionalFilesBytes.indexOf(additionalFile)}",
//                fileSize = additionalFile.size,
//                file = additionalFile.bytes,
//                onBidFiles = onbid
//            )
//            onbid.onBidFiles?.add(onBidFile)
//        }
//        return onBidHandler.saveOnBidEntity(onbid)
//        return onbid
//    }

    @Transactional
    override fun saveOnBid(  onBidDTO: OnBidDTO
                           , options: List<String>?
                           , additionalFiles: List<MultipartFile>?
                           , onbidDays: List<OnBidDayDTO>?): OnBid {
        println(" saveOnBid =============== 1")

        /**
         * 기본정보 Entity
         *
         *
         *    , val note: String? /* 유의사항 주소 */
         *       , val memo: String? /* 메모 주소 */
         *       , val renter:String? /* 임차여부 */
         *       , val land: String? /* 토지 */
         *       , val build: String? /* 건축물 */
         *       , val estateType:String?  /* 부동산종류 */
         *       , val disposal:String?
         */
        val onbid  = OnBid( addr1 = onBidDTO.addr1
            , addr2 = onBidDTO.detailAddress
            , rd_addr = onBidDTO.rd_addr
//            , streeaddr2 = onBidDTO.s
            , bruptcy_admin_name = onBidDTO.bruptcy_admin_name
            , bruptcy_admin_phone = onBidDTO.bruptcy_admin_phone
            , renter = onBidDTO.renter
            , ld_area = onBidDTO.ld_area
            , build_area = onBidDTO.build_area
            , estateType = onBidDTO.estateType
            , disposal_type = onBidDTO.disposal_type
        )

        /**
         * 입찰일자 / 감정가/보증금 등록
         */
        onbidDays?.forEachIndexed { index, onBidDayDTO ->
            val onbidDate = onBidDayDTO?.let{
                OnBidDays(  sdate    = onBidDayDTO.sdate
                    ,edate = onBidDayDTO.edate
                    ,evalue  = onBidDayDTO.evalue
                    ,deposit = onBidDayDTO.deposit
                    ,onBid = onbid
                )
            }

            if (onbidDate != null) {
//                onbid.onBidDays?.add(onbidDate)
                onbid.addOnBidDay(onbidDate)
            }
        }

        /**
         * 첨부파일 저장
         */
        additionalFiles?.forEachIndexed { index, additionalFile ->
            val codeOption = options?.get(index)
            val onBidFile = codeOption?.let {
                OnBidFile(
                    fileName = additionalFile.originalFilename ?: "additional_file_${additionalFiles.indexOf(additionalFile)}",
                    fileSize = additionalFile.size,
                    file = additionalFile.bytes,
                    code = it,
                    onBidFiles = onbid
                )
            }

            if (onBidFile != null) {
//                onbid.onBidFiles?.add(onBidFile)
                onbid.addOnBidFiles(onBidFile)
            }
        }


        //=========================================================//
        val memos: MutableList<Memos>  = mutableListOf()
        val note = onBidDTO.note?.let{
            /* 035: 유의사항 */
            Memos(code="035",memo_contents = onBidDTO.note, onNote = onbid)
        }
        note?.let{
            memos.add(note)
        }
        val memo = onBidDTO.memo?.let{
            /* 035: 메모 */
            Memos(code="036",memo_contents = onBidDTO.memo, onNote = onbid)
        }
        memo?.let{
            memos.add(memo)
        }
        /**
         * 유의사항/메모 등록
         */
        memos?.let{
            it.forEach{ item ->
                onbid.addNoteDay(item)
            }
        }
        //=========================================================//

        /* 기본정보 저장 */
        return onBidHandler.saveOnBidEntity(onbid)
    }

    override fun saveOnBid(onBidDTO: OnBidDTO, file: File?, additionalFiles: List<File>?): OnBid {
        println(" saveOnBid =============== 2")
        //onBid.regdate = LocalDateTime.now() // Set the current date/time
        val onbid  = OnBid(
            addr1 = onBidDTO.addr1,
            addr2 = onBidDTO.detailAddress, bruptcy_admin_name = onBidDTO.bruptcy_admin_name, bruptcy_admin_phone = onBidDTO.bruptcy_admin_phone)
        return onBidHandler.saveOnBidEntity(onbid)
    }
}

