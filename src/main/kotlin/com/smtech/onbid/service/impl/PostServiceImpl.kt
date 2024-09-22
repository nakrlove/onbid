package com.smtech.onbid.service.impl

import com.smtech.onbid.data.dto.PostDTO
import com.smtech.onbid.data.entity.Post
import com.smtech.onbid.handler.PostDataHandler
import com.smtech.onbid.service.PostService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.net.URL
import java.net.URLEncoder

@Service
class PostServiceImpl(@Autowired val postHandler: PostDataHandler): PostService {
//    @Transactional

    private val LOGGER: Logger = LoggerFactory.getLogger(PostServiceImpl::class.java)
    override fun findPosts(addr1: String,totalPage: Long): List<Post> {
        return postHandler.findPosts(addr1,totalPage)
    }
    override fun findByCount(addr1: String): Long {
        return postHandler.findByCount(addr1)
    }

    /*
    fun getAddrApi(param: PostDTO): String {

        // OPEN API 호출 URL 정보 설정
        val apiUrl =
//            "/addrlink/addrLinkApi.do?currentPage=" + currentPage + "&countPerPage=" + countPerPage + "&keyword=" + URLEncoder.encode(
            "/addrlink/addrLinkApi.do?currentPage=" + currentPage + "&countPerPage=" + param.totalPage + "&keyword=" + URLEncoder.encode(
                keyword,
                "UTF-8"
            ) + "&confmKey=" + confmKey + "&resultType=" + resultType
        val url = URL(apiUrl)

        val uri: URI = UriComponentsBuilder
            .fromUriString("https://business.juso.go.kr")
            .path(apiUrl)
//            .path("/api/server/around-hub")
            .encode() //기본적 utf-8로
            .build() //return 값 uricomponent 이기때문에
            .toUri() //uri로 바꿔줌
        val restTemplate: RestTemplate = RestTemplate()
        val responseEntity = restTemplate.getForEntity(uri, String::class.java)
        LOGGER.info("status code: {}", responseEntity.statusCode);
        LOGGER.info("body : {}", responseEntity.body);

        return responseEntity.body!!
    }
*/
}