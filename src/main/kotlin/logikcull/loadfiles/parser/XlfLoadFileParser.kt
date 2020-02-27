package logikcull.loadfiles.parser

import logikcull.loadfiles.LoadFileEntry
import logikcull.loadfiles.model.XlfRootElement
import logikcull.loadfiles.validator.LoadFileResultValidator
import java.io.File
import java.lang.Exception
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBException

class XlfParseException(message: String, exception: Exception? = null): Exception(message, exception)

class XlfLoadFileParser(private val path: String, private val validators: List<LoadFileResultValidator> = emptyList()): LoadFileParser(validators) {
    private val file = File(path)

    override fun parseLoad(): List<LoadFileEntry> {
        try {
            val jaxbContext = JAXBContext.newInstance(XlfRootElement::class.java)
            val unmarshaller = jaxbContext.createUnmarshaller()
            val rootElement = unmarshaller.unmarshal(file)
            if (rootElement is XlfRootElement) {
                return createLoadFileEntries(rootElement)
            } else {
                throw XlfParseException("Unknown root element returned")
            }
        } catch (e: JAXBException) {
            throw XlfParseException("XLF parsing error: ${e.message}", e)
        }
    }

    private fun createLoadFileEntries(element: XlfRootElement): List<LoadFileEntry> {
        return element.entries?.entryElements?.mapNotNull { entry ->
            if (entry.controlNumber == null || entry.volume == null || entry.imageName == null || entry.imagePath == null) {
                // TODO Add logging to be aware of this condition
                null
            } else {
                LoadFileEntry(entry.controlNumber, entry.volume, entry.imagePath + entry.imageName)
            }
        } ?: emptyList()
    }

    override fun close() {
        //JAXB and File have nothing to close
    }

    companion object {
        fun fileExtension() = listOf(".xlf")
    }
}
