package logikcull.loadfiles.loader

import logikcull.loadfiles.LoadFileEntry
import logikcull.loadfiles.parser.XlfLoadFileParser
import org.junit.Test
import kotlin.test.assertEquals

class XlfLoaderTest {

    @Test
    fun validResults() {
        val file = javaClass.getResource("/test.xlf").path.removePrefix("file:")
        val subject = XlfLoadFileParser(file).parse()

        assertEquals(subject.size, 3)
        val expected = listOf(
                LoadFileEntry("test-000001", "Import Test 01", "IMAGES/001/test-000001.tif"),
                LoadFileEntry("test-000002", "Import Test 01", "IMAGES/001/test-000002.tif"),
                LoadFileEntry("test-000003", "Import Test 01", "IMAGES/001/test-000003.tif")
        )
        for (index in 0..2) {
            assertEquals(expected[index].path,subject[index].path)
            assertEquals(expected[index].controlNumber,subject[index].controlNumber)
            assertEquals(expected[index].volumeName,subject[index].volumeName)
        }
    }

}