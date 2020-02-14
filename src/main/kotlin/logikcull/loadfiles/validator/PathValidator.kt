package logikcull.loadfiles.validator

import logikcull.loadfiles.LoadFileEntry
import logikcull.loadfiles.validator.LoadFileResultValidator
import logikcull.loadfiles.validator.ValidatorResult
import java.io.File

/**
 * Validates the path load file and the extracted contents of the archive
 */
class PathValidator: LoadFileResultValidator {
    override fun validate(loadFileEntry: LoadFileEntry): ValidatorResult {
        val file =  File(loadFileEntry.path)
        return if (file.exists() && !file.isDirectory) {
            ValidatorResult(true)
        } else {
            ValidatorResult(false, "The file ${file.path} doesn't exist or is a directory")
        }
    }
}