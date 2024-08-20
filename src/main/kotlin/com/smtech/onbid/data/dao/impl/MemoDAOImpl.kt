package com.smtech.onbid.data.dao.impl

import com.smtech.onbid.data.dao.MemoDAO
import com.smtech.onbid.data.entity.Memos
import com.smtech.onbid.data.entity.OnBid
import com.smtech.onbid.data.repository.MemoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MemoDAOImpl(@Autowired val memoRepository: MemoRepository): MemoDAO {

    override fun findMemos(memo: OnBid): List<Memos> {
        return memoRepository.findByOnMemo(memo)
    }

    /** 신규 메모등록 */
    override fun memoSave(memo: Memos): Memos {
//        val result = memoRepository.save(memo)
        return memoRepository.save(memo)
    }


    /** 메모 수정 */
    override fun statusUpdate(memo: Memos): Memos? {

        var optionalEntity = memoRepository.findById(memo.idx!!)
        return if(optionalEntity.isPresent) {
            // 2. 엔티티 존재 시, 수정 작업 수행
            val attachCode = optionalEntity.get()
            attachCode.memo_contents = memo.memo_contents
            // 3. save 메서드로 엔티티 저장 (업데이트)
            memoRepository.save(attachCode)
        } else {
            null
        }
    }

    /** 메모 단건 삭제 */
    override fun daleteMemos(memo: Memos): Boolean {
        memoRepository.deleteById(memo.idx!!)
        return memoRepository.existsById(memo.idx!!)
    }
}