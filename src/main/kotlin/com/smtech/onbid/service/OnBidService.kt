package com.smtech.onbid.service

import com.smtech.onbid.data.dto.OnBidDTO
import com.smtech.onbid.entity.OnBid
import org.springframework.web.multipart.MultipartFile
import java.io.File

interface OnBidService {

    /**
     * @parm onBid 기본정보
     * @parm file 파산공매공고
     * @parm additionalFiles 기타파일
     * 파일등록
     */
    fun saveOnBid(onBid: OnBidDTO, file: MultipartFile?, additionalFiles: List<MultipartFile>?):  OnBid

    fun saveOnBid(onBid: OnBidDTO, file: File?, additionalFiles: List<File>?):  OnBid

}