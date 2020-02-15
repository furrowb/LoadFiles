package logikcull.loadfiles.validator

import logikcull.loadfiles.LoadFileEntry
import org.junit.Test
import kotlin.test.assertEquals


class FieldPresentValidatorTest {
    @Test
    fun allFieldsPresent() {
        val entry = LoadFileEntry("control", "volume", "path")
        val result = FieldPresentValidator().validate(entry)
        assertEquals(true, result.isValid)
    }

    @Test
    fun missingControl() {
        val entry = LoadFileEntry("", "volume", "path")
        val result = FieldPresentValidator().validate(entry)
        assertEquals(false, result.isValid)
        assertEquals("Control number is empty or null", result.errorMessage)
    }

    @Test
    fun missingVolume() {
        val entry = LoadFileEntry("control", "", "path")
        val result = FieldPresentValidator().validate(entry)
        assertEquals(false, result.isValid)
        assertEquals("Volume name is empty or null", result.errorMessage)
    }

    @Test
    fun missingPath() {
        val entry = LoadFileEntry("control", "volume", "")
        val result = FieldPresentValidator().validate(entry)
        assertEquals(false, result.isValid)
        assertEquals("Path is empty or null", result.errorMessage)
    }

    @Test
    fun multipleFieldsMissing() {
        val entry = LoadFileEntry("control", "", "")
        val result = FieldPresentValidator().validate(entry)
        assertEquals(false, result.isValid)
        assertEquals("Volume name is empty or null\nPath is empty or null", result.errorMessage)
    }
}