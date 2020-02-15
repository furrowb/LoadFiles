package logikcull.loadfiles.validator

import logikcull.loadfiles.LoadFileEntry

class FieldPresentValidator: LoadFileResultValidator {
    override fun validate(loadFileEntry: LoadFileEntry): ValidatorResult {
        val issues = mutableListOf<String>()
        if (loadFileEntry.volumeName.isNullOrEmpty()) issues.add("Volume name is empty or null")
        if (loadFileEntry.path.isNullOrEmpty()) issues.add("Path is empty or null")
        if (loadFileEntry.controlNumber.isNullOrEmpty()) issues.add("Control number is empty or null")
        return if (issues.isNotEmpty()) {
            ValidatorResult(false, issues.joinToString("\n"))
        } else {
            ValidatorResult(true)
        }
    }
}