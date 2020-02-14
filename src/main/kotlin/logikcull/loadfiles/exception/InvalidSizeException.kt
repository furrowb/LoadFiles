package logikcull.loadfiles.exception

import java.lang.Exception

class InvalidSizeException(private val size: Int): Exception("Invalid size. Expected $size")