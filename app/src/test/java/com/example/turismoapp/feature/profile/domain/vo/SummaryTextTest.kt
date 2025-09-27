import com.example.turismoapp.feature.profile.domain.vo.SummaryText
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class SummaryTextTest {

    @Test
    fun `of() should create SummaryText with valid text`() {
        // Given
        val rawSummary = "¡Ahora el ViewModel funciona!"
        // When
        val summaryText = SummaryText.of(rawSummary)
        // Then
        assertEquals("¡Ahora el ViewModel funciona!", summaryText.value)
    }

    @Test
    fun `of() should throw exception for summary shorter than 5 characters`() {
        // Given
        val rawSummary = "hola"
        // Then
        assertThrows(IllegalArgumentException::class.java) {
            // When
            SummaryText.of(rawSummary)
        }
    }

    @Test
    fun `of() should throw exception for summary with no letters`() {
        // Given
        val rawSummary = "1234567"
        // Then
        assertThrows(IllegalArgumentException::class.java) {
            // When
            SummaryText.of(rawSummary)
        }
    }
}