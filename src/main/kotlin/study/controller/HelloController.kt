package study.controller

import com.smtech.onbid.data.dto.MemberDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/server")
class HelloController {
    @RequestMapping(value = ["/hello1"], method = [RequestMethod.GET])
    fun hello(): String = "Hello World"

}