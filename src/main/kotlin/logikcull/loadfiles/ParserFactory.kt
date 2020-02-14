package logikcull.loadfiles

import logikcull.loadfiles.parser.LoadFileParser
import logikcull.loadfiles.parser.OptLoadFileParser
import logikcull.loadfiles.parser.XlfLoadFileParser
import logikcull.loadfiles.validator.postload.PathValidatorPost

class ParserFactory {
    private val pathValidator = PathValidatorPost()
    fun getParser(path: String): LoadFileParser {
        return optLoadFile(path)
    }
    private fun optLoadFile(path: String): LoadFileParser = OptLoadFileParser(path)
    private fun xlfLoadFile(path: String): LoadFileParser = XlfLoadFileParser(path)
    private fun lfpLoadFile(path: String): LoadFileParser = OptLoadFileParser(path)
}