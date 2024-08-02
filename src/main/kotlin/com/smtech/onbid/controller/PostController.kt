package com.smtech.onbid.controller

import com.smtech.onbid.data.dto.PostDTO
import com.smtech.onbid.entity.PostWrapper
import com.smtech.onbid.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value=["/api/post"])
class PostController(@Autowired val postService: PostService) {

//    @PostMapping("/find")
//    fun findPosts(  @RequestParam("addr1") addr1: String
//                  , @RequestParam("addr2") addr2: String
//                  , @RequestParam("addr3") addr3: String
//                  , @RequestParam("pageNum") pageNum: Long
//    ): ResponseEntity<out Any> {
//        val list = postService.findPosts(addr1,addr2,addr3,pageNum)
//        return ResponseEntity.status(HttpStatus.OK).body(list)
//    }
//
//    @PostMapping("/findCount")
//    fun findCount(  @RequestParam("addr1") addr1: String
//                    , @RequestParam("addr2") addr2: String
//                    , @RequestParam("addr3") addr3: String): ResponseEntity<out Any> {
//        val fcount = postService.findByCount(addr1,addr2,addr3)
//        return ResponseEntity.status(HttpStatus.OK).body(fcount)
//    }



    @PostMapping("/find")
    fun findPosts( @RequestBody param: PostDTO ): ResponseEntity<out Any> {
        val (addr1,addr2,addr3,totalPage,reload) = param
        var fcount: Long = 0

        reload?.let {
            if(it == 0) {
                fcount = addr1?.let { postService.findByCount(it) } ?: 0
            }
        }
        val postList = addr1?.let { postService.findPosts(it,totalPage!!) }
        val post = PostWrapper(fcount,postList)
        return ResponseEntity.status(HttpStatus.OK).body(post)
    }

    @PostMapping("/findCount")
    fun findCount(  @RequestBody param: PostDTO ): ResponseEntity<out Any> {
        val (addr1, addr2, addr3) = param
        val fcount = addr1?.let { postService.findByCount(it) } ?: 0
        return ResponseEntity.status(HttpStatus.OK).body(fcount)
    }
}