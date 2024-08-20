package com.smtech.onbid.data.dao

import com.smtech.onbid.data.entity.Memos
import com.smtech.onbid.data.entity.OnBid

interface MemoDAO {

    fun findMemos(memo: OnBid):List<Memos>
    fun memoSave(memo: Memos) : Memos
    fun statusUpdate(memo: Memos): Memos?
    fun daleteMemos(memo:Memos):Boolean;
}