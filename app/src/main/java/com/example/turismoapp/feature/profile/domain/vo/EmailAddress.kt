package com.example.turismoapp.feature.profile.domain.vo


@JvmInline
value class EmailAddress(val value: String) {
    init {
        // CAMBIO AQUI: La longitud mínima debe ser mayor para evitar errores.
        require(value.trim().length > 5) { "Email muy corto" }
        require(Regex("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$").matches(value)) { "Email inválido" }
    }
    override fun toString() = value
    companion object { fun of(raw: String) = EmailAddress(raw.trim()) }
}
