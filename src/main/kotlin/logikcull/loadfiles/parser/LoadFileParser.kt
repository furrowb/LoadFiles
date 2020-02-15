package logikcull.loadfiles.parser

import logikcull.loadfiles.LoadFileEntry
import logikcull.loadfiles.validator.LoadFileResultValidator

/**
 * The LoadFile is the base class that all file loaders should start from. It handles validation, parsing
 * and returning the resulting list of LoadFileEntry.
 *
 * @param validators The validators that validate any conditions needed from the resulting data of the load file
 */
abstract class LoadFileParser(private val validators: List<LoadFileResultValidator>): AutoCloseable {

    /**
     * Runs the parser for the load file and validates the results.
     *
     * @return the resulting entries from the load file
     */
    fun parse(): List<LoadFileEntry> {
        val results = parseLoad()
        validatePostResults(results)
        return results
    }

    /**
     * Parses the load file for each load file type and returns the results.  Each load file parser is expected to
     * override this method.
     *
     * @return the resulting entries from the load file
     */
    protected open fun parseLoad(): List<LoadFileEntry> {
        throw NotImplementedError("Method not implemented")
    }

    private fun validatePostResults(results: List<LoadFileEntry>) {
        results.forEach { loadFileEntry ->
            validators.forEach { validator ->
                val result = validator.validate(loadFileEntry)
                if (!result.isValid) {
                    // Can log instead of throwing an error if we'd like to continue with the rest of the results
                    throw IllegalArgumentException(result.errorMessage)
                }
            }
        }
    }
}