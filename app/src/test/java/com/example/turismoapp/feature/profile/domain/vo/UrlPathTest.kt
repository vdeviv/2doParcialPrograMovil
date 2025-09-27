import com.example.turismoapp.feature.profile.domain.vo.UrlPath
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class UrlPathTest {

    @Test
    fun `of() should create UrlPath with valid HTTPS URL`() {
        // Given
        val rawUrl = "https://www.example.com"
        // When
        val urlPath = UrlPath.of(rawUrl)
        // Then
        assertEquals("https://www.example.com", urlPath.value)
    }

    @Test
    fun `of() should create UrlPath with valid HTTP URL`() {
        // Given
        val rawUrl = "http://www.example.com"
        // When
        val urlPath = UrlPath.of(rawUrl)
        // Then
        assertEquals("http://www.example.com", urlPath.value)
    }

    @Test
    fun `of() should throw exception for URL shorter than 5 characters`() {
        // Given
        val rawUrl = "http"
        // Then
        assertThrows(IllegalArgumentException::class.java) {
            // When
            UrlPath.of(rawUrl)
        }
    }

    @Test
    fun `of() should throw exception for invalid URL without protocol`() {
        // Given
        val rawUrl = "www.example.com"
        // Then
        assertThrows(IllegalArgumentException::class.java) {
            // When
            UrlPath.of(rawUrl)
        }
    }
}