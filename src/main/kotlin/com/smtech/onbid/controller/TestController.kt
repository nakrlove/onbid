package com.smtech.onbid.controller

import com.smtech.onbid.data.dto.MemberDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/server")
class TestController {
    private val LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)

    @RequestMapping(value = ["/hello"], method = [RequestMethod.GET])
    fun hello(): String = "Hello World"


    @GetMapping(value = ["/around-hub"])
    fun getTest1(): String {
        LOGGER.info("getTest1 호출!")
        return "Hello, Around Hub Studio!"
    }

    @GetMapping(value = ["/name"])
    fun getTest2(@RequestParam name: String): String {
        LOGGER.info("getTest2 호출")
        return "Hello. $name!"
    }

    @GetMapping(value = ["/path-variable/{name}"])
    fun getTest3(@PathVariable name: String): String {
        LOGGER.info("getTest3 호출")
        return "Hello. $name!"
    }

    @PostMapping(value = ["/member"])
    fun getMember(
        @RequestBody memberDTO: MemberDTO?,
        @RequestParam name: String?,
        @RequestParam email: String?,
        @RequestParam organization: String?
    ): ResponseEntity<MemberDTO> {
        LOGGER.info("getMember 호출!")

        //        MemberDTO memberDTO1=new MemberDTO();
//        memberDTO1.setName(name);
//        memberDTO1.setEmail(email);
//        memberDTO1.setOrganization(organization);
        return ResponseEntity.status(HttpStatus.OK).body(memberDTO)
    }

    @PostMapping(value=["/add-header"])
    fun addHeader(@RequestHeader("around-header") header: String, @RequestBody memberDTO: MemberDTO): ResponseEntity<MemberDTO> {
        LOGGER.info("add-header 호출!")
        LOGGER.info("header 값: {}",header)

        return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
    }
}