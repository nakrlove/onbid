package com.smtech.onbid

import com.smtech.onbid.data.repository.PostRepository
import com.smtech.onbid.data.entity.Post
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class PostApplicationTests {


    @Test
    fun contextLoads() {
    }

    @Autowired
    private lateinit var postRepository: PostRepository

    @Test
    fun `test findPost Post entity`() {
       val list: List<Post> = postRepository.findBySigunguLikeDolomyeongLikeBeobjeongdongmyeong("은평구",10)
        //list
        //assert(list.size)
//        assertEquals(1, list.size)
    }
}