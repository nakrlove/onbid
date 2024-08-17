package com.smtech.onbid.controller

import com.smtech.onbid.data.repository.CodeRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CodeControllerTests {
    @Autowired
    private lateinit var codeRepository: CodeRepository
    @Test
    fun contextLoads()
    {
        codeRepository.findCodeQuery()
    }
//    @BeforeEach
//    fun setUp() {
//        codeRepository.saveAll(
//            listOf(
//                Codes(code = "CODE1", name = "Name1", scode = "000"),
//                Codes(code = "CODE2", name = "Name2", scode = "001")
//            )
//        )
//    }

    @Test
    fun testFindCodeQuery() {
        val results = codeRepository.findCodeQuery()
        assertNotNull(results)
        assertTrue(results.isNotEmpty())
        // 추가적인 검증 로직을 여기에 추가합니다.
        // 예: assertTrue(results.any { it.code == "CODE1" })
    }
}