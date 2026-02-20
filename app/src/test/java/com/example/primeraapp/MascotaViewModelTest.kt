package com.example.primeraapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.model.Dueno
import com.example.domain.model.Mascota
import com.example.domain.repository.MascotaRepository
import com.example.primeraapp.viewmodel.MascotaViewModel
import io.mockk.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.*
import org.junit.*
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class MascotaViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: MascotaRepository
    private lateinit var viewModel: MascotaViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `cuando repository emite mascotas, uiState se actualiza`() = runTest {
        val mascotasFlow = MutableStateFlow(
            listOf(
                Mascota(
                    id = 1L,
                    nombre = "Firulais",
                    especie = "Perro",
                    edad = 3,
                    dueno = Dueno("Juan", "123456", "juan@mail.com", ""),
                    fechaUltimaVacuna = LocalDate.now()
                )
            )
        )

        coEvery { repository.getMascotasFlow() } returns mascotasFlow

        viewModel = MascotaViewModel(repository)

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value

        assertEquals(1, state.mascotas.size)
        assertFalse(state.isLoading)
        assertNull(state.errorMessage)
    }

    @Test
    fun `agregarMascota exitosa actualiza successMessage`() = runTest {
        coEvery { repository.getMascotasFlow() } returns MutableStateFlow(emptyList())
        coEvery { repository.add(any()) } just Runs

        viewModel = MascotaViewModel(repository)

        val mascota = Mascota(
            id = 2L,
            nombre = "Michi",
            especie = "Gato",
            edad = 2,
            dueno = Dueno("Ana", "999", "ana@mail.com", ""),
            fechaUltimaVacuna = LocalDate.now()
        )

        viewModel.agregarMascota(mascota)

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value

        coVerify { repository.add(mascota) }
        assertEquals("Mascota agregada correctamente", state.successMessage)
        assertFalse(state.isLoading)
    }

    @Test
    fun `agregarMascota con error actualiza errorMessage`() = runTest {
        coEvery { repository.getMascotasFlow() } returns MutableStateFlow(emptyList())
        coEvery { repository.add(any()) } throws Exception("Error DB")

        viewModel = MascotaViewModel(repository)

        val mascota = Mascota(
            id = 3L,
            nombre = "Rocky",
            especie = "Perro",
            edad = 5,
            dueno = Dueno("Luis", "111", "luis@mail.com", ""),
            fechaUltimaVacuna = LocalDate.now()
        )

        viewModel.agregarMascota(mascota)

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value

        assertEquals("Error DB", state.errorMessage)
        assertFalse(state.isLoading)
    }
}