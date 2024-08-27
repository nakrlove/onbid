package com.smtech.onbid.data.dao.impl

import com.smtech.onbid.data.dao.CategoryDAO
import com.smtech.onbid.data.entity.Category
import com.smtech.onbid.data.repository.CategroyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryDAOImpl( @Value("\${smtech.com.user.key}") val userKey: String,@Autowired val categoryRepository: CategroyRepository): CategoryDAO {

    /**
     * 관심목록 조회
     */
    override fun cateGoryList(content: String?): List<Category> {


        return if (content != null) {
            categoryRepository.findOptionalByUserAndContent(userKey, content)
                .map { listOf(it) } // 1건 조회 결과를 리스트로 변환
                .orElse(emptyList()) // 없으면 빈 리스트 반환
        } else {
            categoryRepository.findByUser(userKey)
        }


    }

    /**
     * 관심목록 저장
     */
    override fun cateGroySave(category: Category): Category {

//        val result: Category? = category.content?.let { categoryRepository.findByUserAndContent(userKey, it); }
//        val result: Category = categoryRepository.findByUserAndContent(userKey, category?.content!!).orElseThrow {
//            Exception("결과가 없습니다. 새로운 데이터를 저장합니다.")
//        }

        val existingCategory: Optional<Category> = categoryRepository.findByUserAndContent(userKey, category?.content!!)
        return if (existingCategory.isPresent) {
            // 조회된 결과가 있으면 예외를 던짐
            throw Exception("이미 관심목록이 존재합니다.")
        } else {
            category.user = userKey
            // 조회된 결과가 없으면 새로 등록
            categoryRepository.save(category)
        }
//        return existingCategory.orElseGet(
//            categoryRepository.save(category)
//        ).also { exiting ->
//            if (exiting !== category) {
//                throw Exception("결과가 없습니다. 새로운 데이터를 저장합니다.")
//            }
//        }
    }

    /**
     * 관심목록 수정
     */
    override fun cateGroyUpdate(category: Category): Category? {

        val result = categoryRepository.findById(category.idx!!);
        return if(result.isPresent) {
            // 2. 엔티티 존재 시, 수정 작업 수행
            val categoryObj = result.get()
            categoryObj.content = category.content
            // 3. save 메서드로 엔티티 저장 (업데이트)
            categoryRepository.save(categoryObj)
        } else {
            throw Exception("작업에 실패하였습니다..")
        }

    }

    /**
     * 관심목록 삭제
     */
    override fun cateGroyDelete(idx: Int): Boolean {
        categoryRepository.deleteById(idx)
        return categoryRepository.existsById(idx)
    }
}