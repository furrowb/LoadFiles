package logikcull.loadfiles.reader

import java.io.File
import java.lang.Exception
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBException

class XmlParseException(message: String, exception: Exception? = null): Exception(message, exception)

class XmlReader(path: String) {
    val file = File(path)

    inline fun <reified T> parseXmlFile(): T {
        try {
            val jaxbContext = JAXBContext.newInstance(T::class.java)
            val unmarshaller = jaxbContext.createUnmarshaller()
            val rootElement = unmarshaller.unmarshal(file)
            if (rootElement is T) {
                return rootElement
            } else {
                throw XmlParseException("Unknown root element returned")
            }
        } catch (e: JAXBException) {
            throw XmlParseException("XLF parsing error: ${e.message}", e)
        }
    }
}
