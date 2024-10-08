package com.smtech.onbid.service

import com.smtech.onbid.data.dto.OnBidDTO
import com.smtech.onbid.data.dto.OnBidDayDTO
import com.smtech.onbid.data.dto.OnBidMapDTO
import com.smtech.onbid.data.entity.OnBid
import com.smtech.onbid.data.entity.wapper.BidWrapper
import org.springframework.data.domain.PageRequest
import org.springframework.web.multipart.MultipartFile
import java.io.File

interface OnBidService {

    /* 입찰일목록 */
    fun findDaysQuery(bididx: Int):List<OnBidDayDTO>
//    fun findDaysQuery(bididx: Int):List<OnBidDays>

    fun findAll(onBid: OnBidDTO,page: PageRequest): BidWrapper

    /** 목록조회 */
    fun findOnBidLists(searchTerm: Int?, limit: Int, offset: Int): List<OnBidMapDTO>

    fun findOnBidCount(searchTerm: Int?): Long


    fun findDetail(onBid: OnBidDTO ):OnBidMapDTO?

    /**
     * @parm onBid 기본정보
     * @parm file 파산공매공고
     * @parm additionalFiles 기타파일
     * 파일등록
     */
//    fun saveOnBid(onBid: OnBidDTO, file: MultipartFile?, additionalFiles: List<MultipartFile>?): OnBid
    /**
     * @parm onBid 기본정보
     * @parm file 파산공매공고
     * @parm additionalFiles 기타파일
     * 파일등록
     */
    fun saveOnBid(onBid: OnBidDTO,  options: List<String>? ,additionalFiles: List<MultipartFile>?,onbidDays: List<OnBidDayDTO>?): OnBid

    fun saveOnBid(onBid: OnBidDTO, file: File?, additionalFiles: List<File>?): OnBid

}