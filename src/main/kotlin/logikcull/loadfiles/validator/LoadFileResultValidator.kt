package logikcull.loadfiles.validator

import logikcull.loadfiles.LoadFileEntry

// Instead of just returning true/false, allow validator to return error message
data class ValidatorResult(val isValid: Boolean, val errorMessage: String? = null)

interface LoadFileResultValidator {
    fun validate(loadFileEntry: LoadFileEntry): ValidatorResult
}
