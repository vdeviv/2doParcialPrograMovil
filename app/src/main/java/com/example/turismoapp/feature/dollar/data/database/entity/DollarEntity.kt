package com.example.turismoapp.feature.dollar.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "dollars")
data class DollarEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,


    @ColumnInfo(name = "dollar_official_buy")
    var dolarOficialCompra: String? = null,

    @ColumnInfo(name = "dollar_official_sell")
    var dolarOficialVenta: String? = null,

    @ColumnInfo(name = "dollar_parallel_buy")
    var dolarParaleloCompra: String? = null,

    @ColumnInfo(name = "dollar_parallel_sell")
    var dolarParaleloVenta: String? = null,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long = System.currentTimeMillis()
)