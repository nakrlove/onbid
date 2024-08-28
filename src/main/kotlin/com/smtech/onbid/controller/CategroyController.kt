package com.smtech.onbid.controller

import com.smtech.onbid.data.dto.CategoryDTO
import com.smtech.onbid.data.entity.Category
import com.smtech.onbid.exception.DuplicateCategoryException
import com.smtech.onbid.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


/**
 * 관심목록
 */
@RestController
@RequestMapping(value=["/api/onbid"])
class CategroyController(@Autowired val categoryService: CategoryService) {

    /*
     * 관심목록 조회
     */
    @PostMapping("/categroyList")
    fun cateGoryList(@RequestBody categoryDTO: CategoryDTO?): ResponseEntity<out Any>{

        println("============= CategoryDTO =============")
        val category: Category? = Category(content = categoryDTO?.content, user = categoryDTO?.user)
        val result =  categoryService.cateGoryList(category?.content)

        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    /**
     * 관심목록 저장
     */
    @PostMapping("/categroySave")
    fun cateGroySave(@RequestBody categoryDTO: CategoryDTO?): ResponseEntity<out Any>{
        val category: Category? = Category(content = categoryDTO?.content)
//        val category: Category? = Category(content = categoryDTO?.content, user = categoryDTO?.user)
        val result = category?.let{ categoryService.cateGroySave(category) } ?: null
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }



    /**
     * 관심목록 수정
     */
    @PostMapping("/categroyUpdate")
    fun cateGroyUpdate(@RequestBody categoryDTO: CategoryDTO): ResponseEntity<out Any>{

        val category: Category? = Category(content = categoryDTO.content, user = categoryDTO.user)
        val result = category?.let{  categoryService.cateGroyUpdate(category) }
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    /**
     * 관심목록 삭제
     */
    @PostMapping("/categroyDelete")
    fun cateGroyDelete(@RequestBody categoryDTO: CategoryDTO):ResponseEntity<out Any>{
        val result = categoryService.cateGroyDelete(categoryDTO?.idx!!)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }


    // 예외 핸들러 추가
    @ExceptionHandler(DuplicateCategoryException::class)
    fun handleDuplicateCategoryException(ex: DuplicateCategoryException): ResponseEntity<Map<String, String?>> {
        val errorResponse = mapOf("message" to ex.message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)  // 400 상태 코드와 메시지 반환
    }

}