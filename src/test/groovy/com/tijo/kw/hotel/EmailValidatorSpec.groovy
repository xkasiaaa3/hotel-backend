package com.tijo.kw.hotel

import com.tijo.kw.hotel.security.auth.ValidateEmail
import spock.lang.Specification
import spock.lang.Unroll

class EmailValidatorSpec extends Specification {

    @Unroll
    def "Should check address email validation "() {
        when: "check address email"
        def validate = ValidateEmail.validateEmail(EMAIL_ADDRESS as String)
        then: "correct address email return true "
        validate == IS_CORRECT
        where:
        EMAIL_ADDRESS               | IS_CORRECT
        "correctEmail@gmail.com"    | true
        "correct1Email@gmail.com"   | true
        "email_incorrect_gmail_com" | false
        "@gmail.com"                | false
        "email.com"                 | false
        "email@com"                 | false
    }
}
