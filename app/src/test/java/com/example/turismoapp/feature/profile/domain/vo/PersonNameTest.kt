import com.example.turismoapp.feature.profile.domain.vo.PersonName
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class PersonNameTest {

    @Test
    fun `of() should create PersonName with valid name`() {
        // GIVEN
        val rawName = "Vivi Revollo"
        // WHEN
        val personName = PersonName.of(rawName)
        // THEN
        assertEquals("Vivi Revollo", personName.value)
    }

    @Test
    fun `of() should throw exception for name shorter than 5 characters`() {
        // GIVEN
        val rawName = "Vivi"
        // THEN
        assertThrows(IllegalArgumentException::class.java) {
            // WHEN
            PersonName.of(rawName)
        }
    }

    @Test
    fun `of() should throw exception for name with no letters`() {
        // GIVEN
        val rawName = "12345"
        // THEN
        assertThrows(IllegalArgumentException::class.java) {
            // WHEN
            PersonName.of(rawName)
        }
    }
}