package logikcull.loadfiles.parser

import logikcull.loadfiles.LoadFileEntry
import logikcull.loadfiles.validator.postload.PostLoadFileValidator
import logikcull.loadfiles.validator.preload.PreLoadFileValidator
import java.io.IOException
import java.io.Reader

/**
 * The LoadFile is the base class that all file loaders should start from. It handles validation, parsing
 * and returning the resulting list of LoadFileEntry.
 *
 * @param reader The Reader that allows for processing the load file
 * @param preValidators The validators that validate any conditions needed of the Reader
 * @param postValidators The validators that validate any conditions needed from the resulting data of the load file
 */
abstract class LoadFile(private val reader: Reader, private val preValidators: List<PreLoadFileValidator>, private val postValidators: List<PostLoadFileValidator>): AutoCloseable {

    fun parse(): List<LoadFileEntry> {
        validatePreParsing()
        val results = parseLoad()
        validatePostResults(results)
        return results
    }

    protected open fun parseLoad(): List<LoadFileEntry> {
        throw NotImplementedError("Method not implemented")
    }

    private fun validatePreParsing() {
        preValidators.forEach { validator ->
            val result = validator.validate(reader)
            if (!result.isValid) {
                throw IOException(result.errorMessage)
            }
        }
    }

    private fun validatePostResults(results: List<LoadFileEntry>) {
        results.forEach { loadFileEntry ->
            postValidators.forEach { validator ->
                val result = validator.validate(loadFileEntry)
                if (!result.isValid) {
                    throw IllegalArgumentException(result.errorMessage)
                }
            }
        }
    }

    override fun close() {
        reader.close()
    }

}