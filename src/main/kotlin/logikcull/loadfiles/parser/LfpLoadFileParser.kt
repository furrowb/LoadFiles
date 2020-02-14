package logikcull.loadfiles.parser

import logikcull.loadfiles.LoadFileEntry
import logikcull.loadfiles.exception.InvalidSizeException
import logikcull.loadfiles.validator.LoadFileResultValidator
import java.nio.file.FileSystems
import java.nio.file.Files

enum class LfpField(val index: Int) {
    DOCUMENT_TYPE(0),
    CONTROL_NUMBER(1),
    VOLUME_AND_FILE(4)
}

enum class VolumeAndFileField(val index: Int){
    VOLUME(0),
    DIRECTORY(1),
    FILE_NAME(2),
    PAGE_COUNT(3)
}

class LfpLoadFileParser(private val path: String, private val validators: List<LoadFileResultValidator> = emptyList()): LoadFileParser(validators) {
    private val bufferedReader = Files.newBufferedReader(FileSystems.getDefault().getPath(path))

    override fun parseLoad(): List<LoadFileEntry> {
        return bufferedReader.readLines().map { line ->
            val parts = line.split(",")
            if (parts.size != 6) {
                throw InvalidSizeException(6)
            }
            val controlNumber = parts[LfpField.CONTROL_NUMBER.index]
            val volumeAndFile = parseVolumeAndFile(parts[LfpField.VOLUME_AND_FILE.index])
            LoadFileEntry(controlNumber, volumeAndFile.first, volumeAndFile.second)
        }
    }

    private fun parseVolumeAndFile(field: String): Pair<String, String> {
        val parts = field.split(";")
        if (parts.size != 4) {
            throw InvalidSizeException(4)
        }
        val volume = parts[VolumeAndFileField.VOLUME.index].removePrefix("@")
        val directory = parts[VolumeAndFileField.DIRECTORY.index]
        val fileName = parts[VolumeAndFileField.FILE_NAME.index]

        return Pair(volume, directory + fileName)
    }

    override fun close() {
        bufferedReader.close()
    }
}