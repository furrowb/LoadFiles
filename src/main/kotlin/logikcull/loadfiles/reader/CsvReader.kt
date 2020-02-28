package logikcull.loadfiles.reader

import java.nio.file.FileSystems
import java.nio.file.Files

class CsvReader(private val path: String): AutoCloseable {
    private val bufferedReader = Files.newBufferedReader(FileSystems.getDefault().getPath(path))

    fun getLines(): List<String> = bufferedReader.readLines()

    override fun close() {
        bufferedReader.close()
    }
}