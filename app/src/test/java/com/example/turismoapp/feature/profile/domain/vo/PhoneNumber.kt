import com.example.turismoapp.feature.profile.domain.vo.PhoneNumber
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class PhoneNumberTest {

    @Test
    fun `of() should create PhoneNumber with valid number`() {
        // Given
        val rawNumber = "+777 7777"
        // When
        val phoneNumber = PhoneNumber.of(rawNumber)
        // Then
        assertEquals("+777 7777", phoneNumber.value)
    }

    @Test
    fun `of() should throw exception for a number with less than 5 digits`() {
        // Given
        val rawNumber = "1234"
        // Then
        assertThrows(IllegalArgumentException::class.java) {
            // When
            PhoneNumber.of(rawNumber)
        }
    }

    @Test
    fun `of() should create PhoneNumber even with non-digit characters`() {
        // Given
        val rawNumber = "+1 (555) 123-4567"
        // When
        val phoneNumber = PhoneNumber.of(rawNumber)
        // Then
        assertEquals("+1 (555) 123-4567", phoneNumber.value)
    }
}