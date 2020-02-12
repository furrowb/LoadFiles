package logikcull.loadfiles.validator

import org.junit.Test
import java.io.File
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PathValidatorTest {

    @Test
    fun validatePathWithoutPathSeparator() {
        val filePath = getResourceFolderPath();
        assertTrue(PathValidator.fileExists(filePath, "test.opt"))
    }

    @Test
    fun validatePathWithPathSeparator() {
        val filePath = getResourceFolderPath() + File.separatorChar;
        assertTrue(PathValidator.fileExists(filePath, "test.opt"))
    }

    @Test
    fun pathDoesNotExist() {
        val filePath = getResourceFolderPath() + File.separatorChar;
        assertFalse(PathValidator.fileExists(filePath, "file-does-not-exist"))
    }

    @Test
    fun directoryIsNotAFile() {
        val filePath = getResourceFolderPath() + File.separatorChar;
        assertFalse(PathValidator.fileExists(filePath, ""))
    }

    private fun getResourceFolderPath(): String {
        val testFile = this.javaClass.getResource("/test.opt")
        val lastSeparatorIndex = testFile.path.lastIndexOf(File.separatorChar)
        return testFile.path.substring(0, lastSeparatorIndex)
    }
}