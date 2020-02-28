package logikcull.loadfiles

import logikcull.loadfiles.parser.LfpLoadFileParser
import logikcull.loadfiles.parser.LoadFileParser
import logikcull.loadfiles.parser.OptLoadFileParser
import logikcull.loadfiles.parser.XlfLoadFileParser
import logikcull.loadfiles.reader.CsvReader
import logikcull.loadfiles.reader.XmlReader
import logikcull.loadfiles.validator.ControlNumberValidator
import logikcull.loadfiles.validator.FieldPresentValidator
import logikcull.loadfiles.validator.PathValidator
import java.io.File

class ParserFactory {
    private val pathValidator = PathValidator()
    private val controlNumberValidator = ControlNumberValidator(Regex(".+-\\d{6}"))
    private val fieldPresentValidator = FieldPresentValidator()
    private val validators = listOf(fieldPresentValidator, pathValidator, controlNumberValidator)

    fun getParser(path: String): LoadFileParser {
        val file = File(path)
        val extension = file.name.removePrefix(file.nameWithoutExtension)
        return when {
            OptLoadFileParser.fileExtension().contains(extension.toLowerCase()) -> optLoadFile(path)
            XlfLoadFileParser.fileExtension().contains(extension.toLowerCase()) -> xlfLoadFile(path)
            LfpLoadFileParser.fileExtension().contains(extension.toLowerCase()) -> lfpLoadFile(path)
            else -> throw IllegalArgumentException("Unrecognized file extension for file $path")
        }
    }

    private fun optLoadFile(path: String): LoadFileParser = OptLoadFileParser(CsvReader(path), validators)
    private fun xlfLoadFile(path: String): LoadFileParser = XlfLoadFileParser(XmlReader(path), validators)
    private fun lfpLoadFile(path: String): LoadFileParser = LfpLoadFileParser(CsvReader(path), validators)
}