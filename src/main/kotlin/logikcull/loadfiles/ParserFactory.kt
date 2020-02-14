package logikcull.loadfiles

import logikcull.loadfiles.parser.LoadFileParser
import logikcull.loadfiles.parser.OptLoadFileParser
import logikcull.loadfiles.parser.XlfLoadFileParser
import logikcull.loadfiles.validator.PathValidator

class ParserFactory {
    private val pathValidator = PathValidator()
    fun getParser(path: String): LoadFileParser {
        return optLoadFile(path)
    }
    private fun optLoadFile(path: String): LoadFileParser = OptLoadFileParser(path, listOf(pathValidator))
    private fun xlfLoadFile(path: String): LoadFileParser = XlfLoadFileParser(path, listOf(pathValidator))
    private fun lfpLoadFile(path: String): LoadFileParser = OptLoadFileParser(path, listOf(pathValidator))
}