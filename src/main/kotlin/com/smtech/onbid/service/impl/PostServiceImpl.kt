package com.smtech.onbid.service.impl

import com.smtech.onbid.data.entity.Post
import com.smtech.onbid.handler.PostDataHandler
import com.smtech.onbid.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(@Autowired val postHandler: PostDataHandler): PostService {
//    @Transactional
    override fun findPosts(addr1: String,totalPage: Long): List<Post> {
        return postHandler.findPosts(addr1,totalPage)
    }
    override fun findByCount(addr1: String): Long {
        return postHandler.findByCount(addr1)
    }

}