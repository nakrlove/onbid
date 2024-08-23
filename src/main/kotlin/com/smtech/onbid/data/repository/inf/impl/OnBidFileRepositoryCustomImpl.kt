package com.smtech.onbid.data.repository.inf.impl

import com.smtech.onbid.data.dto.OnBidFileDTO
import com.smtech.onbid.data.repository.inf.OnBidFileRepositoryCustom
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class OnBidFileRepositoryCustomImpl: OnBidFileRepositoryCustom {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun getFileCategory(bididx: Int): List<OnBidFileDTO> {
        val query = entityManager.createNamedQuery("getFileCategory", OnBidFileDTO::class.java)
        query.setParameter("bididx", bididx)
        return query.resultList
    }
}