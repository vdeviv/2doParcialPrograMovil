package com.example.turismoapp.feature.dollar.data.mapper


import com.example.turismoapp.feature.dollar.data.database.entity.DollarEntity
import com.example.turismoapp.feature.dollar.domain.model.DollarModel


fun DollarEntity.toModel(): DollarModel {
    return DollarModel(
        dolarOficialCompra = dolarOficialCompra,
        dolarOficialVenta = dolarOficialVenta,
        dolarParaleloCompra = dolarParaleloCompra,
        dolarParaleloVenta = dolarParaleloVenta,
        timestamp = timestamp
    )
}

fun DollarModel.toEntity(): DollarEntity {
    return DollarEntity(
        dolarOficialCompra = dolarOficialCompra,
        dolarOficialVenta = dolarOficialVenta,
        dolarParaleloCompra = dolarParaleloCompra,
        dolarParaleloVenta = dolarParaleloVenta,
        timestamp = timestamp
    )
}
