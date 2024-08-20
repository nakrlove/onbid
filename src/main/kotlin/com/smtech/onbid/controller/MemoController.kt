package com.smtech.onbid.controller

import com.smtech.onbid.data.dto.MemoDTO
import com.smtech.onbid.service.MemoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value=["/api/onbid"])
class MemoController(@Autowired val memoService: MemoService) {

    /**
     * 메모 목록조회
     */
    @RequestMapping(value=["/findMemos"])
    fun findMemos( @RequestBody memoDTO: MemoDTO): ResponseEntity<out Any> {
        println("================memoUpdate=============")
        val resultMemo = memoService.findMemos(memoDTO)
        return ResponseEntity.status(HttpStatus.OK).body(resultMemo)
    }

    @RequestMapping(value=["/momeSave"])
    fun momeSave( @RequestBody memoDTO: MemoDTO): ResponseEntity<out Any> {
        println("================memoUpdate=============")
        val resultMemo = memoService.memoSave(memoDTO)
        return ResponseEntity.status(HttpStatus.OK).body(resultMemo)
    }

    @RequestMapping(value=["/memoUpdate"])
    fun memoUpdate( @RequestBody memoDTO: MemoDTO): ResponseEntity<out Any> {
        println("================memoUpdate=============")
        val resultMemo = memoService.statusUpdate(memoDTO)
        return ResponseEntity.status(HttpStatus.OK).body(resultMemo)
    }

    @RequestMapping(value=["/memoDelete"])
    fun memoDelete( @RequestBody memoDTO: MemoDTO): ResponseEntity<out Any> {
        println("================memoDelete=============")
        val resultMemo = memoService.daleteMemos(memoDTO)
        return ResponseEntity.status(HttpStatus.OK).body(resultMemo)
    }
}