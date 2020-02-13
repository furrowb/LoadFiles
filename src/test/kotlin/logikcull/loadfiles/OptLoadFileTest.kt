package logikcull.loadfiles

import logikcull.loadfiles.parser.OptLoadFile
import java.io.InputStreamReader
import kotlin.test.Test
import kotlin.test.assertEquals

class OptLoadFileTest {
    @Test
    fun testOptLoadfile() {
        val subject = OptLoadFile(InputStreamReader(javaClass.getResourceAsStream("/test.opt")), emptyList(), emptyList()).parse()

        assertEquals(subject.size, 3)
        for (entry in subject) {
            assert(entry.controlNumber.startsWith("test-00000"))
            assertEquals(entry.volumeName, "Import Test 01")
            assert(entry.path.startsWith("IMAGES\\001\\test-00000"))
            assert(entry.path.endsWith(".tif"))
        }
    }
}
