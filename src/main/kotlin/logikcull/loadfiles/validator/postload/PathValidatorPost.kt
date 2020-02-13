package logikcull.loadfiles.validator.postload

import logikcull.loadfiles.LoadFileEntry
import java.io.File


/**
 * Validates the path load file and the extracted contents of the archive
 */
class PathValidatorPost: PostLoadFileValidator {
    override fun validate(loadFileEntry: LoadFileEntry): ValidatorResult {
        val file =  File(loadFileEntry.path)
        return if (file.exists() && !file.isDirectory) {
            ValidatorResult(true)
        } else {
            ValidatorResult(false, "The file doesn't exist or is a directory")
        }
    }
}