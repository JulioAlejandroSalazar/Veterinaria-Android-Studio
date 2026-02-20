package com.example.primeraapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.primeraapp.ui.screens.RegistrarMascotaScreen
import com.example.primeraapp.ui.state.MascotaUiState
import com.example.primeraapp.viewmodel.MascotaViewModel
import io.mockk.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.flow.MutableStateFlow
import io.mockk.every
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.hasClickAction

@RunWith(AndroidJUnit4::class)
class RegistrarMascotaScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun registrarMascota_muestraErrores_siFormularioVacio() {

        val fakeState = MutableStateFlow(MascotaUiState())

        val fakeViewModel = mockk<MascotaViewModel>()
        every { fakeViewModel.uiState } returns fakeState

        composeTestRule.setContent {
            RegistrarMascotaScreen(
                navController = rememberNavController(),
                mascotaViewModel = fakeViewModel,
                mascotaId = null
            )
        }

        composeTestRule
            .onNode(
                hasText("Registrar Mascota") and hasClickAction()
            )
            .performClick()

        composeTestRule
            .onAllNodesWithText("Campo requerido")
            .assertCountEquals(4)
        composeTestRule.onNodeWithText("Edad inválida").assertExists()
        composeTestRule.onNodeWithText("Correo inválido").assertExists()
        composeTestRule.onNodeWithText("Fecha inválida").assertExists()
    }
}