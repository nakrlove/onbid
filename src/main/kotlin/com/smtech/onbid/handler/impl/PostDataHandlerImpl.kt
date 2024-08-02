package com.smtech.onbid.handler.impl

import com.smtech.onbid.data.dao.PostDAO
import com.smtech.onbid.entity.Post
import com.smtech.onbid.handler.PostDataHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostDataHandlerImpl(@Autowired val postDAO: PostDAO) : PostDataHandler {
    override fun findPosts(addr1: String, totalPage: Long): List<Post> {
        println("Address = $addr1")
        return postDAO.findSigunguLikeDolomyongLikeBeobjeongdongmyeong(addr1,totalPage)
    }

    override fun findByCount(addr1: String) = postDAO.findByCount(addr1)

}