package logikcull.loadfiles.validator

import junit.framework.Assert.assertFalse
import logikcull.loadfiles.LoadFileEntry
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class ControlNumberValidatorTest {

    private val entry = LoadFileEntry("control-123", "volume", "path")

    @Test
    fun validate() {
        val validator = ControlNumberValidator(Regex("\\S{7}-\\d{3}"))
        assertTrue(validator.validate(entry).isValid)
    }

    @Test
    fun failRegexCheck() {
        val validator = ControlNumberValidator(Regex("not-there"))
        val results = validator.validate(entry)
        assertFalse(results.isValid)
        assertEquals("Control number control-123 does not match regex not-there", results.errorMessage)
    }
}