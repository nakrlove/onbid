package com.smtech.onbid.service.impl

import com.smtech.onbid.data.dao.MemoDAO
import com.smtech.onbid.data.dto.MemoDTO
import com.smtech.onbid.data.entity.Memos
import com.smtech.onbid.data.repository.OnBidRepository
import com.smtech.onbid.service.MemoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MemoServiceImpl(@Autowired val memoDAO: MemoDAO,private val onBidRepository: OnBidRepository): MemoService {

    /** bididx key로 메모 목록조회 */
    override fun findMemos(memo: MemoDTO): List<Memos> {
        val onBid = onBidRepository.findById(memo.bididx)
            .orElseThrow { IllegalArgumentException("Invalid bididx: ${memo.bididx}") }
        return memoDAO.findMemos(onBid)
    }

    /**
     * 메모 신규등록
     */
    override fun memoSave(memo: MemoDTO): Memos {

        val onBid = onBidRepository.findById(memo.bididx)
            .orElseThrow { IllegalArgumentException("Invalid bididx: ${memo.bididx}") }

        val param  = Memos( onMemo= onBid,memo_contents = memo.memo_contents)
        return memoDAO.memoSave(param)
    }

    /**
     * 메모 수정
     */
    override fun statusUpdate(memo: MemoDTO): Memos? {
        val param  = Memos(memo.idx, memo_contents = memo.memo_contents)
        return memoDAO.statusUpdate(param)
    }

    /**
     * 메모 삭제
     */
    override fun daleteMemos(memo: MemoDTO): Boolean {
        val param  = Memos(idx = memo.idx)
        return memoDAO.daleteMemos(param)
    }
}