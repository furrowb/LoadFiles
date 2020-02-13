package logikcull.loadfiles.validator.postload

import logikcull.loadfiles.LoadFileEntry

// Instead of just returning true/false, allow validator to return error message
data class ValidatorResult(val isValid: Boolean, val errorMessage: String? = null)

interface PostLoadFileValidator {
    fun validate(loadFileEntry: LoadFileEntry): ValidatorResult
}
