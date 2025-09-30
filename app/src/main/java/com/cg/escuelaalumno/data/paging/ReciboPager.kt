package com.cg.escuelaalumno.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.cg.escuelaalumno.model.ReciboModel
import javax.inject.Inject

class ReciboPager {
    fun createPager(recibos: List<ReciboModel>): Pager<Int, ReciboModel> {
        return Pager(PagingConfig(pageSize = 10)) {
            ReciboPagingSource(recibos)
        }
    }
}