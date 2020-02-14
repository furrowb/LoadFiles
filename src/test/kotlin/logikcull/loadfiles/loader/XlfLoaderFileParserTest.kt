package logikcull.loadfiles.loader

import logikcull.loadfiles.LoadFileEntry
import logikcull.loadfiles.parser.XlfLoadFileParser
import logikcull.loadfiles.parser.XlfParseException
import org.junit.Test
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class XlfLoaderFileParserTest {

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
            assertEquals(expected[index], subject[index])
        }
    }

    @Test
    fun missingImageName() {
        val temp = File.createTempFile("bad-data", ".lfp")
        val writer = BufferedWriter(FileWriter(temp))

        writer.append("""
            <loadfile>
                <entries>
                    <entry control-number="test-000001">
                        <volume>Import Test 01</volume>
                        <image-path>IMAGES/001/</image-path>
                    </entry>
                </entries>
            </loadfile>
        """.trimIndent())
        writer.close()

        val parser = XlfLoadFileParser(temp.absolutePath)
        // No exception is thrown. Just no data is generated. Possible area of improvement
        assertEquals(0, parser.parse().size)
    }

    @Test
    fun nonExistentFile() {
        // Possibly improvement: throw NoSuchFileException
        assertFailsWith(XlfParseException::class) {
            XlfLoadFileParser("/path/to/nowhere").parse()
        }
    }
}