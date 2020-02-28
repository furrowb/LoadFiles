package logikcull.loadfiles.parser

import logikcull.loadfiles.LoadFileEntry
import logikcull.loadfiles.reader.CsvReader
import logikcull.loadfiles.validator.LoadFileResultValidator
import java.nio.file.FileSystems
import java.nio.file.Files

class OptLoadFileParser(
        private val csvReader: CsvReader,
        validators: List<LoadFileResultValidator> = emptyList()
) : LoadFileParser(validators)
{

    override fun parseLoad(): List<LoadFileEntry> {
        return csvReader.getLines().map { line ->
            val parts = line.split(",")
            LoadFileEntry(parts[0], parts[1], parts[2])
        }
    }

    override fun close() {
        csvReader.close()
    }

    companion object {
        fun fileExtension() = listOf(".opt")
    }

}
