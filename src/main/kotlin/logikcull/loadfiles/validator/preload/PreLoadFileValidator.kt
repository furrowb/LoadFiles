package logikcull.loadfiles.validator.preload

import logikcull.loadfiles.validator.postload.ValidatorResult
import java.io.Reader

interface PreLoadFileValidator {
    fun validate(reader: Reader): ValidatorResult
}