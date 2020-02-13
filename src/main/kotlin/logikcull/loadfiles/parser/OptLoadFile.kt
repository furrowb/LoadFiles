package logikcull.loadfiles.parser

import logikcull.loadfiles.LoadFileEntry
import logikcull.loadfiles.validator.postload.PostLoadFileValidator
import logikcull.loadfiles.validator.preload.PreLoadFileValidator
import java.io.BufferedReader
import java.io.Reader

class OptLoadFile(private val reader: Reader, private val preLoadFileValidators: List<PreLoadFileValidator>, private val postLoadFileValidators: List<PostLoadFileValidator>) : LoadFile(
        reader,
        preLoadFileValidators,
        postLoadFileValidators
    )
{

    override fun parseLoad(): List<LoadFileEntry> {
        val bufferedReader = BufferedReader(reader)
        return bufferedReader.readLines().map { line ->
            val parts = line.split(",")
            LoadFileEntry(parts[0], parts[1], parts[2])
        }
    }

}
