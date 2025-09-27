
package com.example.turismoapp.feature.profile.domain.usecase
import com.example.turismoapp.feature.profile.domain.model.ProfileModel
import com.example.turismoapp.feature.profile.domain.repository.IProfileRepository
import com.example.turismoapp.feature.profile.domain.usecase.GetProfileUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetProfileUseCaseTest {

    // Mockea el repositorio, la única dependencia de tu caso de uso.
    private val mockRepository = mockk<IProfileRepository>()

    // Crea una instancia del caso de uso, inyectando el mock.
    private val getProfileUseCase = GetProfileUseCase(mockRepository)

    // --- Prueba de Caso de Éxito ---
    @Test
    fun `invoke should return success with profile data`() = runTest {
        // GIVEN: Un perfil de ejemplo que el repositorio debería devolver.
        val expectedProfile = ProfileModel(
            pathUrl = "https://example.com/profile.jpg",
            name = "Vivi Revollo",
            email = "vivi.omg@gmail.com",
            cellphone = "77777777",
            summary = "Developer"
        )
        // GIVEN: Configura el mock para que devuelva un resultado exitoso.
        coEvery { mockRepository.fetchData() } returns Result.success(expectedProfile)

        // WHEN: Llama al caso de uso.
        val result = getProfileUseCase.invoke()

        // THEN: Verifica que el resultado es exitoso y contiene el perfil esperado.
        assertTrue(result.isSuccess)
        assertEquals(expectedProfile, result.getOrNull())
    }

    // --- Prueba de Caso de Fallo ---
    @Test
    fun `invoke should return failure on repository error`() = runTest {
        // GIVEN: Un error de ejemplo que el repositorio podría lanzar.
        val expectedError = Exception("Network error")
        // GIVEN: Configura el mock para que devuelva un resultado de fallo.
        coEvery { mockRepository.fetchData() } returns Result.failure(expectedError)

        // WHEN: Llama al caso de uso.
        val result = getProfileUseCase.invoke()

        // THEN: Verifica que el resultado es un fallo y contiene el error esperado.
        assertTrue(result.isFailure)
        assertEquals(expectedError.message, result.exceptionOrNull()?.message)
    }
}