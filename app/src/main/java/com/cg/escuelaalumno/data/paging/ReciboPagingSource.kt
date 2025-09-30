package com.cg.escuelaalumno.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cg.escuelaalumno.model.ReciboModel

class ReciboPagingSource(
    private val recibos: List<ReciboModel>
) : PagingSource<Int, ReciboModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReciboModel> {
        val page = params.key ?: 1 // ← aquí cambiamos de 0 a 1
        val pageSize = params.loadSize
        val fromIndex = (page - 1) * pageSize
        val toIndex = minOf(fromIndex + pageSize, recibos.size)

        return try {
            val pageData = recibos.subList(fromIndex, toIndex)
            LoadResult.Page(
                data = pageData,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (toIndex >= recibos.size) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, ReciboModel>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}