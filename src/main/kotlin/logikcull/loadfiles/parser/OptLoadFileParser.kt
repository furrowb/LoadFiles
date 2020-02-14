package logikcull.loadfiles.parser

import logikcull.loadfiles.LoadFileEntry
import logikcull.loadfiles.validator.postload.PathValidatorPost
import logikcull.loadfiles.validator.postload.PostLoadFileValidator
import java.nio.file.FileSystems
import java.nio.file.Files

class OptLoadFileParser(
        private val path: String,
        private val validators: List<PostLoadFileValidator> = emptyList()) : LoadFileParser(validators)
{
    private val bufferedReader = Files.newBufferedReader(FileSystems.getDefault().getPath(path))

    override fun parseLoad(): List<LoadFileEntry> {
        return bufferedReader.readLines().map { line ->
            val parts = line.split(",")
            LoadFileEntry(parts[0], parts[1], parts[2])
        }
    }

    override fun close() {
        bufferedReader.close()
    }

}
