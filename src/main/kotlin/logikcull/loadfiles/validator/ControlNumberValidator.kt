package logikcull.loadfiles.validator

import logikcull.loadfiles.LoadFileEntry

class ControlNumberValidator(private val regex: Regex): LoadFileResultValidator {
    override fun validate(loadFileEntry: LoadFileEntry): ValidatorResult {
        return if (regex matches loadFileEntry.controlNumber) {
            ValidatorResult(true)
        } else {
            ValidatorResult(false, "Control number ${loadFileEntry.controlNumber} does not match regex $regex")
        }
    }

}