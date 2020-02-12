package logikcull.loadfiles.validator

import java.io.File


/**
 * Validates the path load file and the extracted contents of the archive
 */
class PathValidator {
    companion object {
        fun fileExists(path: String, fileName: String): Boolean {
            val updatedPath = if (path.last() != File.separatorChar) {
                path + File.separatorChar
            } else {
                path
            }
            val file =  File(updatedPath + fileName)
            return file.exists() && !file.isDirectory
        }
    }
}