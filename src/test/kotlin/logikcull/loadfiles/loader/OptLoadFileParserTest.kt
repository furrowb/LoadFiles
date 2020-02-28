package logikcull.loadfiles.loader

import logikcull.loadfiles.parser.OptLoadFileParser
import logikcull.loadfiles.reader.CsvReader
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OptLoadFileParserTest {
    @Test
    fun testOptLoadFile() {
        val file = javaClass.getResource("/test.opt").path.removePrefix("file:")
        val subject = OptLoadFileParser(CsvReader(file)).use {  it.parse() }

        assertEquals(subject.size, 3)
        for (entry in subject) {
            assert(entry.controlNumber.startsWith("test-00000"))
            assertEquals(entry.volumeName, "Import Test 01")
            assert(entry.path.startsWith("IMAGES\\001\\test-00000"))
            assert(entry.path.endsWith(".tif"))
        }
    }

    @Test
    fun nonExistentFile() {
        assertFailsWith(java.nio.file.NoSuchFileException::class) {
            OptLoadFileParser(CsvReader("/file-that-doesnt-exist")).parse()
        }
    }
}
