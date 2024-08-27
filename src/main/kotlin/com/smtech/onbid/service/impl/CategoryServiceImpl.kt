package com.smtech.onbid.service.impl

import com.smtech.onbid.data.dao.CategoryDAO
import com.smtech.onbid.data.entity.Category
import com.smtech.onbid.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryServiceImpl(@Autowired val categoryDAO: CategoryDAO) : CategoryService {

    @Value("\${smtech.com.user.key}")
    lateinit var userKey: String

    /**
     * 관심목록 조회
     */
    override fun cateGoryList(name: String?): List<Category> {
        return categoryDAO.cateGoryList(name)
    }

    /**
     * 관심목록 저장
     */
    override fun cateGroySave(category: Category): Category? {
        return categoryDAO.cateGroySave(category)
    }

    /**
     * 관심목록 수정
     */
    override fun cateGroyUpdate(category: Category): Category? {
        return  categoryDAO.cateGroyUpdate(category)
    }

    /**
     * 관심목록 삭제
     */
    override fun cateGroyDelete(idx: Int): Boolean {
        return categoryDAO.cateGroyDelete(idx)
    }
}