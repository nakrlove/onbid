package com.smtech.onbid.service.impl

import com.smtech.onbid.data.dao.OnBidFileDAO
import com.smtech.onbid.data.dto.OnBidFileDTO
import com.smtech.onbid.data.entity.OnBidFile
import com.smtech.onbid.service.OnBidFileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.*

@Service
class OnBidFileServiceImpl(@Autowired val fileDAO: OnBidFileDAO): OnBidFileService {

    override fun getFileById(id: Int): Optional<OnBidFile> {
        return fileDAO.findById(id)
    }

    /**
     * 파일다운로드
     * @id: Int
     */
    override fun getFileInputStream(id: Int): InputStream? {
        val file = getFileById(id) ?: return null
        return file?.let {
                if(it.isPresent){
                    val attachFiles = it.get()
                    ByteArrayInputStream(attachFiles.file)
                } else {
                    null
                }
        }

    }

    /** 등록된 파일카테고리 목록조회 */
    override fun getFileCategory(bididx: Int): List<OnBidFileDTO> {
        return fileDAO.getFileCategory(bididx)
    }
}