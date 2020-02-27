package logikcull.loadfiles.validator

import logikcull.loadfiles.LoadFileEntry

class FieldPresentValidator: LoadFileResultValidator {
    override fun validate(loadFileEntry: LoadFileEntry): ValidatorResult {
        val issues = mutableListOf<String>()
        if (loadFileEntry.volumeName.isEmpty()) issues.add("Volume name is empty")
        if (loadFileEntry.path.isEmpty()) issues.add("Path is empty")
        if (loadFileEntry.controlNumber.isEmpty()) issues.add("Control number is empty")
        return if (issues.isNotEmpty()) {
            ValidatorResult(false, issues.joinToString("\n"))
        } else {
            ValidatorResult(true)
        }
    }
}