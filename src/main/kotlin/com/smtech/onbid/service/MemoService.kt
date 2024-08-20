package com.smtech.onbid.service

import com.smtech.onbid.data.dto.MemoDTO
import com.smtech.onbid.data.entity.Memos

interface MemoService {
    fun findMemos(memo: MemoDTO): List<Memos>
    fun memoSave(memo:MemoDTO): Memos
    fun statusUpdate(memo: MemoDTO): Memos?
    fun daleteMemos(memo: MemoDTO):Boolean;
}