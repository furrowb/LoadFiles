package logikcull.loadfiles.validator

import logikcull.loadfiles.LoadFileEntry
import logikcull.loadfiles.validator.postload.PathValidatorPost
import org.junit.Test
import java.io.File
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PathValidatorTest {

    @Test
    fun validPath() {
        val filePath = "${getResourceFolderPath()}${File.separatorChar}test.opt";
        val loadFileEntry = LoadFileEntry("1", "volume 1", filePath)
        assertTrue(PathValidatorPost().validate(loadFileEntry).isValid)
    }

    @Test
    fun pathDoesNotExist() {
        val filePath = "${getResourceFolderPath()}${File.separatorChar}file-does-not-exist"
        val loadFileEntry = LoadFileEntry("1", "non-existent", filePath)
        assertFalse(PathValidatorPost().validate(loadFileEntry).isValid)
    }

    @Test
    fun directoryIsNotAFile() {
        val filePath = "${getResourceFolderPath()}${File.separatorChar}"
        val loadFileEntry = LoadFileEntry("1", "lacking a file", filePath)
        assertFalse(PathValidatorPost().validate(loadFileEntry).isValid)
    }

    private fun getResourceFolderPath(): String {
        val testFile = this.javaClass.getResource("/test.opt")
        val lastSeparatorIndex = testFile.path.lastIndexOf(File.separatorChar)
        return testFile.path.substring(0, lastSeparatorIndex)
    }
}