package ir.vbile.app.venture.core.presentation.util

import com.google.common.truth.Truth.assertThat
import ir.vbile.app.venture.feature_auth.presentation.util.AuthError
import org.junit.Test

class ValidationUtilTest {
    private val emptyField = ""
    private val validEmail = "garousiv@gmail.com"
    private val validPassword = "Zxc123Vbn"
    private val validConfirmPassword = "Zxc123Vbn"
    private val invalidEmail = "garousiv@gmail"
    private val invalidPassword = "zxca"
    private val invalidConfirmPassword = "zxcasdvsbs"
    private val weekPassword = "zxc"

    @Test
    fun `empty email, returns AuthError_FieldEmpty`() {
        val result = ValidationUtil.validateEmail(emptyField)
        assertThat(result).isEqualTo(AuthError.FieldEmpty)
    }

    @Test
    fun `invalid email, returns AuthError_InvalidEmail`() {
        val result = ValidationUtil.validateEmail(invalidEmail)
        assertThat(result).isEqualTo(AuthError.InvalidEmail)
    }

    @Test
    fun `empty password, returns AuthError_FieldEmpty`() {
        val result = ValidationUtil.validatePassword(emptyField)
        assertThat(result).isEqualTo(AuthError.FieldEmpty)
    }

    @Test
    fun `enter week password, returns AuthError_InputTooShort`() {
        val result = ValidationUtil.validatePassword(weekPassword)
        assertThat(result).isEqualTo(AuthError.InputTooShort)
    }

    @Test
    fun `invalid password, returns AuthError_InvalidPassword`() {
        val result = ValidationUtil.validatePassword(invalidEmail)
        assertThat(result).isEqualTo(AuthError.InvalidPassword)
    }

    @Test
    fun `empty confirm password, returns AuthError_FieldEmpty`() {
        val result = ValidationUtil.validateConfirmPassword(emptyField)
        assertThat(result).isEqualTo(AuthError.FieldEmpty)
    }

    @Test
    fun `enter week confirm password, returns AuthError_InputTooShort`() {
        val result = ValidationUtil.validateConfirmPassword(weekPassword)
        assertThat(result).isEqualTo(AuthError.InputTooShort)
    }

    @Test
    fun `invalid confirm password, returns AuthError_InvalidPassword`() {
        val result = ValidationUtil.validateConfirmPassword(invalidConfirmPassword)
        assertThat(result).isEqualTo(AuthError.InvalidConfirmPassword)
    }
}