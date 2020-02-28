package logikcull.loadfiles.parser

import logikcull.loadfiles.LoadFileEntry
import logikcull.loadfiles.model.XlfRootElement
import logikcull.loadfiles.reader.XmlReader
import logikcull.loadfiles.validator.LoadFileResultValidator


class XlfLoadFileParser(private val xmlReader: XmlReader, validators: List<LoadFileResultValidator> = emptyList()): LoadFileParser(validators) {
    override fun parseLoad(): List<LoadFileEntry> {
        val rootElement = xmlReader.parseXmlFile<XlfRootElement>()
        return createLoadFileEntries(rootElement)
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
