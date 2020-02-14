package logikcull.loadfiles.model

import javax.xml.bind.annotation.*

@XmlRootElement(name = "loadfile")
@XmlAccessorType(XmlAccessType.FIELD)
class XlfRootElement(@XmlElement(name = "entries") val entries: EntriesElement?) {
    constructor() : this(EntriesElement())
}

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
class EntriesElement() {
    @XmlElement(name = "entry")
    val entryElements: MutableCollection<EntryElement> = mutableListOf()
}

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
class EntryElement(
        @XmlAttribute(name = "control-number") val controlNumber: String?,
        volume: String?,
        imagePath: String?,
        imageName: String?) {
    // JAXB requires an empty constructor
    constructor(): this(null, null, null, null)

    // Deliberately moved these out of the constructor so the element "name" will be applied correctly
    @XmlElement(name = "volume") val volume: String? = volume
    @XmlElement(name = "image-path") val imagePath: String? = imagePath
    @XmlElement(name = "image-name") val imageName: String? = imageName
}