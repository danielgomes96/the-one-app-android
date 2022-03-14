package com.devlabs.theoneapp

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.devlabs.core.extension.bind
import com.devlabs.core.extension.gone
import com.devlabs.core.extension.visible
import com.devlabs.movies.MoviesListActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val btnFingerprintAuth: Button by bind(R.id.activity_main_btn_login_fingerprint)
    private val btnPinAuth: Button by bind(R.id.activity_main_btn_login_pin)
    private val viewModel by viewModel<AuthenticationViewModel>()
    private var pinTitle: String = ""
    private val dialog: BottomSheetDialog by lazy {
        BottomSheetDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (hasBiometricCapability() == BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE) {
            btnFingerprintAuth.gone()
        } else {
            handleFingerprintAuthentication()
        }

        btnPinAuth.setOnClickListener {
            showPinAuthenticationDialog()
        }

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.apply {
            verifyHasPin()
            hasPinLiveData.observe(this@MainActivity) { hasPin ->
                pinTitle = if (hasPin) {
                    getString(R.string.button_pin_text_auth)
                } else {
                    getString(R.string.button_pin_text_auth_create)
                }
            }
            loggedInLiveData.observe(this@MainActivity) { hasLoggedIn ->
                if (hasLoggedIn) {
                    openMoviesList()
                    dialog.dismiss()
                } else {
                    dialog.findViewById<TextView>(R.id.bottom_sheet_pin_error_msg)?.visible()
                }
            }
        }
    }

    private fun openMoviesList() {
        val intent = Intent(this@MainActivity, MoviesListActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showPinAuthenticationDialog() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_pin_auth, null)
        dialog.setContentView(view)
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        val tvTitle = view.findViewById<TextView>(R.id.bottom_sheet_pin_title_text)
        tvTitle.text = pinTitle
        val etPin = view.findViewById<TextView>(R.id.bottom_sheet_pin_text)
        val btLogin = view.findViewById<TextView>(R.id.bottom_sheet_pin_enter)
        btLogin.setOnClickListener {
            viewModel.savePin(etPin.text.toString())
        }
        dialog.show()
    }

    private fun handleFingerprintAuthentication() {
        val executor = ContextCompat.getMainExecutor(this)
        val biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    if (errorCode == 13) {
                        showPinAuthenticationDialog()
                    }
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    openMoviesList()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.fingerprint_dialog_title))
            .setNegativeButtonText(getString(R.string.fingerprint_dialog_pin))
            .build()

        btnFingerprintAuth.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }

    private fun hasBiometricCapability(): Int {
        return BiometricManager.from(this).canAuthenticate()
    }
}