package logikcull.loadfiles.loader

import logikcull.loadfiles.parser.LfpLoadFileParser
import org.junit.Test
import kotlin.test.assertEquals

class LfpLoaderTest {

    @Test
    fun validResults() {
        val file = javaClass.getResource("/test.lfp").path.removePrefix("file:")
        val parser = LfpLoadFileParser(file)

        val results = parser.parse()
        assertEquals(3, results.size)
    }
}
