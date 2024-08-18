package com.smtech.onbid.controller

import com.smtech.onbid.data.dto.OnBidDayDTO
import com.smtech.onbid.service.OnBidDaysService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value=["/api/onbid"])
class OnBidDaysController(@Autowired val onBidDaysService: OnBidDaysService) {

    @PutMapping(value=["/statusUpdate"])
    fun onBidUpdateDays( @RequestBody onbidDTO: OnBidDayDTO): ResponseEntity<out Any> {
        println("================onbidLDetil=============")
        val resultOnBid = onBidDaysService.statusUpdate(onbidDTO)
        val result = resultOnBid?.let{
            onBidDaysService.findBidDaysQuery(onbidDTO.bididx!!)
        }

        return ResponseEntity.status(HttpStatus.OK).body(result)
    }
}