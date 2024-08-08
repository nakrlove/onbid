package com.smtech.onbid

import com.smtech.onbid.data.repository.OnBidDaysRepository
import com.smtech.onbid.data.repository.OnBidRepository
import com.smtech.onbid.entity.OnBid
import com.smtech.onbid.entity.OnBidDays
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


//import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime

@SpringBootTest
class OnbidApplicationTests {

	@Test
	fun contextLoads() {

	}

	@Autowired
	private lateinit var onBidRepository: OnBidRepository
	@Autowired
	private lateinit var onBidDaysRepository: OnBidDaysRepository

	@Test
	fun `test saving OnBid entity`() {
		// Given
		val onBid = OnBid(
			addr1 = "Some CONTENTS",
			addr2 = "test 1",
			regdate = LocalDateTime.now()
		)


		// When
		val savedOnBid = onBidRepository.save(onBid)

		val onBidDays = OnBidDays(
			startDays = LocalDateTime.now(),
			endDays = LocalDateTime.now().plusDays(1),
			regdate = LocalDateTime.now(),
			onBid = savedOnBid // OnBid 엔티티와 관계 설정
		)
		savedOnBid.addOnBidDay(onBidDays)
		onBidRepository.save(savedOnBid)

//		val savedOnBidDays = onBidDaysRepository.save(onBidDays)
		// Then
//		assertNotNull(savedOnBid.idx) // Ensure that ID is generated
		assertEquals(onBid.addr1, savedOnBid.addr1)
		assertEquals(onBid.regdate, savedOnBid.regdate)
//		assertEquals(onBid.zcode, savedOnBid.zcode)
	}
}
