package com.smtech.onbid.data.dao.impl

import com.smtech.onbid.data.dao.OnBidDAO
import com.smtech.onbid.data.dto.OnBidMapDTO
import com.smtech.onbid.data.repository.OnBidRepository
import com.smtech.onbid.data.entity.OnBid
import com.smtech.onbid.data.repository.OnBidFileRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class OnBidDAOImpl(@Autowired val onbidDRepository: OnBidRepository,private val onbidFileRepository: OnBidFileRepository):OnBidDAO {

    val  LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)


    /**
     * 파산공매공고 신규등록/수정
     * Array형식의 메모수정
     * Array형식의 파일첨부수정
     * Array형식의 입찰일정보수정
     * @param: OnBid
     */
    @Transactional
    override fun saveOnBid(onBid: OnBid): OnBid {

        var findBid = onBid.bididx?.let { onbidDRepository.findById(it) }

        findBid?.let { findData ->
            if(findBid.isPresent){
                val modifyBid = findData.get()

                var isUpdated: Boolean = false
                if(onBid.onBidFiles.isNullOrEmpty().not()) {
                    println(" files is not empty ##################")
                    // 기존 파일과 새 파일을 구분하기 위한 리스트
                    val existingFiles = modifyBid.onBidFiles?.toMutableList() ?: mutableListOf()
                    val newFiles = onBid.onBidFiles ?: mutableListOf()

                    // 삭제 처리
                    val filesToRemove = existingFiles.filterNot { existingFile ->
                        newFiles.any { newFile -> existingFile.idx == newFile.idx }
                    }
                    filesToRemove.forEach { fileToRemove ->
                        modifyBid.onBidFiles?.remove(fileToRemove)
                        onbidFileRepository.delete(fileToRemove) // 데이터베이스에서 파일 삭제
                    }

                    // 수정 및 추가 처리
                    newFiles.forEach { newFile ->
                        val existingFile = existingFiles.find { it.idx == newFile.idx }
                        if (existingFile != null) {
                            // 파일 내용이 변경된 경우에만 업데이트
                            if (existingFile.file != newFile.file ||
                                existingFile.fileName != newFile.fileName ||
                                existingFile.fileSize != newFile.fileSize ||
                                existingFile.fileType != newFile.fileType
                            ) {

                                existingFile.file = newFile.file
                                existingFile.fileName = newFile.fileName
                                existingFile.fileSize = newFile.fileSize
                                existingFile.fileType = newFile.fileType
                            }
                        } else {
                            // 새 파일 추가
                            newFile.onBidFiles = modifyBid
                            modifyBid.onBidFiles?.add(newFile)
                        }
                    }
                }


                // onBidDays 컬렉션 관리
                onBid.onBidDays?.let{ onbidDays ->

                    if(onbidDays.size == 0){
                        return@let
                    }

                    // 크기가 같은 경우 각 요소의 필드 값 비교 (순서 중요)
                    for (i in onbidDays.indices) {
                        val onBidDay = onbidDays[i]
                        val modifyBidFile = modifyBid.onBidDays[i]

                        // 요소의 필드 비교: equals() 메서드를 재정의하여 사용할 수도 있음
                        if (onBidDay != modifyBidFile) {
                            isUpdated = true
                            break
                        }
                    }

                    if(isUpdated){
                        modifyBid.onBidDays.clear()  // 기존 컬렉션 비우기
                        modifyBid.onBidDays.addAll(onbidDays)// 새 컬렉션 추가
                        isUpdated = false
                    }
                }


                onBid.onMemo?.let{ memo ->

                    if(memo.size == 0){
                        return@let
                    }

                    for (i in memo.indices) {
                        val onBidMemo = memo[i]
                        val modifyBidFile = modifyBid.onMemo!![i]
                        // 요소의 필드 비교: equals() 메서드를 재정의하여 사용할 수도 있음
                        if (onBidMemo != modifyBidFile) {
                            isUpdated = true
                            break
                        }
                    }

                    if(isUpdated){
                        modifyBid.onMemo?.clear()
                        modifyBid.onMemo?.addAll(memo)
                        isUpdated = false
                    }
                }

                modifyBid.onbid_status        = onBid.onbid_status
                modifyBid.addr1               = onBid.addr1
                modifyBid.addr2               = onBid.addr2
                modifyBid.bruptcy_admin_name  = onBid.bruptcy_admin_name
                modifyBid.bruptcy_admin_phone = onBid.bruptcy_admin_phone
                modifyBid.debtor              = onBid.debtor
                modifyBid.disposal_type       = onBid.disposal_type
                modifyBid.enforcement_decree  = onBid.enforcement_decree
                modifyBid.estateType          = onBid.estateType
                modifyBid.idx                 = onBid.idx
                modifyBid.items               = onBid.items
                modifyBid.land_classification = onBid.land_classification
                modifyBid.national_land_planning_use_laws = onBid.national_land_planning_use_laws
                modifyBid.note                = onBid.note
                modifyBid.other_laws          = onBid.other_laws
                modifyBid.ld_area             = onBid.ld_area
                modifyBid.ld_area_memo        = onBid.ld_area_memo
                modifyBid.build_area          = onBid.build_area
                modifyBid.build_area_memo     = onBid.build_area_memo
                modifyBid.rd_addr             = onBid.rd_addr
                modifyBid.progress_status     = onBid.progress_status
                modifyBid.regdate             = onBid.regdate
                modifyBid.renter              = onBid.renter

                println("insert data ${modifyBid}")
                onbidDRepository.save(modifyBid)
            } else {
                null
            }
        } ?: run{
            onbidDRepository.save(onBid)
        }
        return onBid
    }

    /** 상세보기 */
    override fun findDetail(onBid: OnBid): OnBidMapDTO? {
        return onBid.bididx?.let { onbidDRepository.onBidDetails(it) }
    }

//    @Transactional(readOnly = true)
    override fun findAlls(page: PageRequest): Page<OnBid>? {
//        return onbidDRepository.findAll(page)
        return null
    }

    /** 목록조회 */
    override fun findOnBidLists(searchTerm: Int?, limit: Int, offset: Int): List<OnBidMapDTO> {
        return onbidDRepository.findOnBidLists(searchTerm,limit,offset)
    }

    override fun findOnBidCount(searchTerm: Int?): Long {
        return onbidDRepository.findOnBidCount(searchTerm)
    }
}