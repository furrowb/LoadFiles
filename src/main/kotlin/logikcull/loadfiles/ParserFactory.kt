package logikcull.loadfiles

import logikcull.loadfiles.parser.LoadFile
import logikcull.loadfiles.parser.OptLoadFile
import logikcull.loadfiles.validator.postload.PathValidatorPost
import java.io.Reader

class ParserFactory {
    val pathValidator = PathValidatorPost()
    fun getParser(path: String, reader: Reader): LoadFile {
        return optLoadFile(reader)
    }
    private fun optLoadFile(reader: Reader): LoadFile = OptLoadFile(reader, emptyList(), listOf(pathValidator))
    private fun xlfLoadFile(reader: Reader): LoadFile = OptLoadFile(reader, emptyList(), listOf(pathValidator))
    private fun lfpLoadFile(reader: Reader): LoadFile = OptLoadFile(reader, emptyList(), listOf(pathValidator))
}