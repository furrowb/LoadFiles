package logikcull.loadfiles

import logikcull.loadfiles.parser.LfpLoadFileParser
import logikcull.loadfiles.parser.OptLoadFileParser
import logikcull.loadfiles.parser.XlfLoadFileParser
import java.io.File
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ParserFactoryTest {
    private lateinit var factory: ParserFactory

    @BeforeTest
    fun setup() {
        factory = ParserFactory()
    }

    @Test
    fun getOptLoadParser() {
        val temp = File.createTempFile("a-file", ".opt")
        val parser = factory.getParser(temp.absolutePath)
        assertTrue(parser is OptLoadFileParser)
    }
    @Test
    fun getXlfLoadParser() {
        val temp = File.createTempFile("a-file", ".xlf")
        val parser = factory.getParser(temp.absolutePath)
        assertTrue(parser is XlfLoadFileParser)
    }

    @Test
    fun getLfpLoadParser() {
        val temp = File.createTempFile("a-file", ".lfp")
        val parser = factory.getParser(temp.absolutePath)
        assertTrue(parser is LfpLoadFileParser)
    }

    @Test
    fun unknownFileExtension() {
        val temp = File.createTempFile("a-file", ".blah")
        assertFailsWith<IllegalArgumentException> {
            factory.getParser(temp.absolutePath)
        }
    }
}