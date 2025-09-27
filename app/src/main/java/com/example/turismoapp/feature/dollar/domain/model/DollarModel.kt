package com.example.turismoapp.feature.dollar.domain.model

data class DollarModel(
    var dolarOficialCompra: String? = null,
    var dolarOficialVenta: String? = null,
    var dolarParaleloCompra: String? = null,
    var dolarParaleloVenta: String? = null,
    var timestamp: Long = System.currentTimeMillis()
)