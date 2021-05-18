package com.mfahmi.myjetpackprosubmission.utils

import androidx.paging.PagedList
import org.mockito.Mockito.*

@Suppress("UNCHECKED_CAST")
object PagedListUtil {
    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = mock(PagedList::class.java) as PagedList<T>
        `when`(pagedList[anyInt()]).then {
            val index = it.arguments.first() as Int
            list[index]
        }
        `when`(pagedList.size).thenReturn(list.size)
        return pagedList
    }
}