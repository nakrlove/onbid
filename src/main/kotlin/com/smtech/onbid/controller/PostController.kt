package com.smtech.onbid.controller

import com.smtech.onbid.data.dto.PostDTO
import com.smtech.onbid.data.dto.PostWrapper
import com.smtech.onbid.service.PostService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    val LOGGER: Lazy<Logger> = lazy { LoggerFactory.getLogger(PostController::class.java) }
    val sido:Map<String,String> = mutableMapOf(
         Pair("강원도","강원도")
        ,Pair("강원특별자치도","강원특별자치도")
        , Pair("경기","경기도")
        , Pair("경기도","경기도")
        , Pair("경남","경상남도"), Pair("경북","경상북도")
        , Pair("경상남도","경상남도"), Pair("경상북도","경상북도")
        , Pair("전남","전라남도"), Pair("전북","전라북도")
        , Pair("전라남도","전라남도"), Pair("전라북도","전라북도")
        , Pair("충남","충청남도"), Pair("충북","충청북도")
        , Pair("충청남도","충청남도"), Pair("충청북도","충청북도")
        , Pair("광주시","광주광역시")
        , Pair("광주광역시","광주광역시")
        , Pair("대구시","대구광역시"), Pair("대전시","대전광역시")
        , Pair("대구광역시","대구광역시"), Pair("대전광역시","대전광역시")
        , Pair("부산","부산광역시")
        , Pair("부산시","부산광역시")
        , Pair("부산광역시","부산광역시")
        , Pair("서울","서울특별시")
        , Pair("서울시","서울특별시")
        , Pair("서울특별시","서울특별시")
        , Pair("세종시","세종특별자치시")
        , Pair("세종특별자치시","세종특별자치시")
        , Pair("울산시","울산광역시")
        , Pair("울산광역시","울산광역시")
        , Pair("인천시","인천광역시"), Pair("제주시","제주특별자치도")
        , Pair("인천광역시","인천광역시"), Pair("제주특별자치도","제주특별자치도"));


    fun getAddr(addr1: String): String {
        val words = addr1.split(" ").toMutableList()
        val findWords = words[0]
        words[0] = sido[findWords].let { it } ?: findWords
        return  words.joinToString(" ")
    }

    @PostMapping("/findZipCode")
    fun findZipCode( @RequestBody param: PostDTO ): ResponseEntity<out PostWrapper> {

        val (addr1,addr2,addr3,totalPage,reload) = param
        val addr = getAddr(addr1)
        var fcount: Long = 0


        reload?.let {
            if(it == 0) {
                fcount = addr?.let { postService.findByCount(it) } ?: 0

                LOGGER.value.trace(" findPosts count #${fcount}")

            }
        }
        val postList = addr?.let { postService.findPosts(it,totalPage!!) }
        val post = PostWrapper(fcount,postList)
        return ResponseEntity.status(HttpStatus.OK).body(post)
    }

    @PostMapping("/findCount")
    fun findCount(  @RequestBody param: PostDTO ): ResponseEntity<out Any> {
        val (addr1, addr2, addr3) = param
        val addr = getAddr(addr1)
        val fcount = addr?.let { postService.findByCount(it) } ?: 0
        return ResponseEntity.status(HttpStatus.OK).body(fcount)
    }
}