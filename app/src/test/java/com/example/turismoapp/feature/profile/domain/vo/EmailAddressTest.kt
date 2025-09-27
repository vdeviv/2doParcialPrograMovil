import com.example.turismoapp.feature.profile.domain.vo.EmailAddress
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class EmailAddressTest {

    @Test
    fun `of() should create EmailAddress with valid email`() {
        // GIVEN
        val rawEmail = "vivi.omg@gmail.com"
        // WHEN
        val emailAddress = EmailAddress.of(rawEmail)
        // THEN
        assertEquals("vivi.omg@gmail.com", emailAddress.value)
    }

    @Test
    fun `of() should throw exception for email shorter than 5 characters`() {
        // GIVEN
        val rawEmail = "a@a.c"
        // THEN
        assertThrows(IllegalArgumentException::class.java) {
            // WHEN
            EmailAddress.of(rawEmail)
        }
    }

    @Test
    fun `of() should throw exception for invalid email format`() {
        // GIVEN
        val rawEmail = "vivi.omg.gmail.com"
        // THEN
        assertThrows(IllegalArgumentException::class.java) {
            // WHEN
            EmailAddress.of(rawEmail)
        }
    }
}