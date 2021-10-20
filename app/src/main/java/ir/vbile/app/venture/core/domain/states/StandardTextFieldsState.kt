package ir.vbile.app.venture.core.domain.states

import ir.vbile.app.venture.core.util.Error

data class StandardTextFieldsState(
    val text: String = "",
    val error: Error? = null
)
