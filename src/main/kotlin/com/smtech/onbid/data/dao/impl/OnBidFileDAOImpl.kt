package com.smtech.onbid.data.dao.impl

import com.smtech.onbid.data.dao.OnBidFileDAO
import com.smtech.onbid.data.dto.OnBidFileDTO
import com.smtech.onbid.data.entity.OnBidFile
import com.smtech.onbid.data.repository.OnBidFileRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class OnBidFileDAOImpl(@Autowired val onbidfileRepository: OnBidFileRepository): OnBidFileDAO {

    override fun findById(id: Int): Optional<OnBidFile>{
        return onbidfileRepository.findById(id)
    }

    override fun getFileCategory(bididx: Int): List<OnBidFileDTO> {
        return onbidfileRepository.getFileCategory(bididx)
    }
}