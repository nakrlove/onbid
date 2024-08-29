package com.smtech.onbid.service.impl

import com.smtech.onbid.data.dao.OnBidDAO
import com.smtech.onbid.data.dto.OnBidDTO
import com.smtech.onbid.data.dto.OnBidDayDTO
import com.smtech.onbid.data.dto.OnBidMapDTO
import com.smtech.onbid.data.entity.*
import com.smtech.onbid.data.repository.OnBidRepository
import com.smtech.onbid.data.entity.wapper.BidWrapper
import com.smtech.onbid.data.repository.OnBidDaysRepository
import com.smtech.onbid.handler.OnBidDataHandler
import com.smtech.onbid.service.OnBidService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.time.LocalDateTime

@Service
class OnBidServiceImpl(@Autowired val onBidHandler: OnBidDAO ,@Autowired val onbidRepository: OnBidRepository,@Autowired val onBidDaysRepository: OnBidDaysRepository): OnBidService{


    override fun findDetail( onBid: OnBidDTO ):OnBidMapDTO? {
        return onBidHandler.findDetail(OnBid(bididx = onBid.bididx))
    }

    override fun findDaysQuery(bididx: Int): List<OnBidDayDTO> {
        return onbidRepository.findDaysQuery(bididx)
    }

    override fun findAll(onBid: OnBidDTO, page: PageRequest): BidWrapper {

        val data = OnBid( addr1 = onBid.addr1
            , addr2 = onBid.detailAddress
            , rd_addr = onBid.rd_addr
            , bruptcy_admin_name = onBid.bruptcy_admin_name
            , bruptcy_admin_phone = onBid.bruptcy_admin_phone
        )

        val count = onbidRepository.count()
        val resultList =  onBidHandler.findAlls(page)


        println("(@) ====== findAll =count [${count}]")
        return BidWrapper(count,resultList)
    }

    override fun findOnBidLists(searchTerm: Int?, limit: Int, offset: Int): List<OnBidMapDTO> {
        return onBidHandler.findOnBidLists(searchTerm,limit,offset)
    }

    override fun findOnBidCount(searchTerm: Int?): Long {
        return onBidHandler.findOnBidCount(searchTerm)
    }


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


//    @Transactional
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
            , bruptcy_admin_name = onBidDTO.bruptcy_admin_name
            , bruptcy_admin_phone = onBidDTO.bruptcy_admin_phone
            , renter = onBidDTO.renter
            , ld_area = onBidDTO.ld_area
            , ld_area_memo = onBidDTO.ld_area_memo      //토지면적 메모
            , build_area = onBidDTO.build_area
            , build_area_memo = onBidDTO.build_area_memo //건물면적 메모
            , estateType = onBidDTO.estateType
            , disposal_type = onBidDTO.disposal_type
            , note = onBidDTO.note
            , land_classification = onBidDTO.land_classification
            , progress_status = onBidDTO.progress_status
            , onbid_status =  onBidDTO.onbid_status
            , debtor =  onBidDTO.debtor
            , national_land_planning_use_laws = onBidDTO.national_land_planning_use_laws
            , other_laws = onBidDTO.other_laws
            , enforcement_decree = onBidDTO.enforcement_decree
            , idx = onBidDTO.idx  //관심종목
        )


        /**
         * 입찰일자 / 감정가/보증금 등록
         */
        onbidDays?.forEachIndexed { index, onBidDayDTO ->
            val onbidDate = onBidDayDTO?.let{
                OnBidDays(
//                     sdate        = it.sdate ?: LocalDateTime.now().toString()
                     edate        = it.edate ?: LocalDateTime.now().toString()
                    ,evalue       = it.evalue
                    ,deposit      = it.deposit
                    ,onbid_status = it.onbid_status
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
                    fileType = additionalFile.contentType,
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
        /**
         * 메모 등록
         */

        if (onBidDTO.memo.isNullOrEmpty().not()) {
            onbid.addMemo(Memos(memo_contents = onBidDTO.memo, onMemo = onbid))
        }
       //================================================//

        /* 기본정보 저장 */
//        return onBidHandler.saveOnBidEntity(onbid)
        return onBidHandler.saveOnBid(onbid)
    }

    override fun saveOnBid(onBidDTO: OnBidDTO, file: File?, additionalFiles: List<File>?): OnBid {
        println(" saveOnBid =============== 2")
        //onBid.regdate = LocalDateTime.now() // Set the current date/time
        val onbid  = OnBid(
            addr1 = onBidDTO.addr1,
            addr2 = onBidDTO.detailAddress, bruptcy_admin_name = onBidDTO.bruptcy_admin_name, bruptcy_admin_phone = onBidDTO.bruptcy_admin_phone)
//        return onBidHandler.saveOnBidEntity(onbid)
        return onBidHandler.saveOnBid(onbid)
    }
}

