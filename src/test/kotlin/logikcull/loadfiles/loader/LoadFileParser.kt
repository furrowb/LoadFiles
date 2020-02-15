package logikcull.loadfiles.loader

import logikcull.loadfiles.parser.LoadFileParser
import org.junit.Test
import kotlin.test.assertFailsWith

class LoadFileParserTest {
    @Test
    // Increase code coverage
    fun parseLoadNotOverridden() {
        val parser = object : LoadFileParser(emptyList()) {
            override fun close() {
            }
        }

        assertFailsWith<NotImplementedError> {
            parser.parse()
        }
    }
}