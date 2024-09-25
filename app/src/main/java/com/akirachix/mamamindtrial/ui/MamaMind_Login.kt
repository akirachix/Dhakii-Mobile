package com.akirachix.mamamindtrial.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.akirachix.mamamindtrial.HomePage
import com.akirachix.mamamindtrial.NextSignUp
import com.akirachix.mamamindtrial.R
import com.akirachix.mamamindtrial.viewModel.SignInViewModel
import com.akirachix.mamamindtrial.databinding.ActivityMamaMindLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MamamindLogin : AppCompatActivity() {
    private lateinit var binding: ActivityMamaMindLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val signInViewModel: SignInViewModel by viewModels()

    companion object {
        private const val TAG = "MamamindLogin"
        private const val REQUEST_CODE_GOOGLE_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMamaMindLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()
        configureGoogleSignIn()
        observeLoginResult()
        setupClickListeners()

        binding.signUpText.setOnClickListener{
            val intent = Intent(this, NextSignUp::class.java)
            startActivity(intent)
        }
    }

    private fun configureGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun observeLoginResult() {
        signInViewModel.loginResult.observe(this, Observer { result ->
            result.onSuccess { navigateToMain() }
                .onFailure { showError(it.message ?: "Login failed") }
        })
    }

    private fun setupClickListeners() {
        with(binding) {
            signInButton.setOnClickListener { signInWithGoogle() }
            loginbtn.setOnClickListener { handleEmailLogin() }
            signUpText.setOnClickListener { navigateToSignUp() }
        }
    }

    private fun handleEmailLogin() {
        val username = binding.usernameInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()

        when {
            username.isEmpty() -> showError("Please enter username")
            password.isEmpty() -> showError("Please enter password")
            else -> signInViewModel.login(username, password)
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, REQUEST_CODE_GOOGLE_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_GOOGLE_SIGN_IN) {
            handleGoogleSignInResult(data)
        }
    }

    private fun handleGoogleSignInResult(data: Intent?) {
        try {
            val account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException::class.java)
            account.idToken?.let { firebaseAuthWithGoogle(it) }
        } catch (e: ApiException) {
            Log.w(TAG, "Google sign in failed", e)
            showError("Google Sign In failed")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnSuccessListener { navigateToMain() }
            .addOnFailureListener { e ->
                Log.e(TAG, "signInWithCredential:failure", e)
                showError("Authentication failed: ${e.message}")
            }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, HomePage::class.java))
        finish()
    }

    private fun navigateToSignUp() {
        // TODO: Implement navigation to sign-up screen
        showError("Sign up functionality not implemented yet")
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}