package com.example.madventure

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SignInActivity : Activity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        email = findViewById(R.id.emailEditText)
        password = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val userEmail = email.text.toString().trim()
            val userPassword = password.text.toString()

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                showErrorDialog("Поля не могут быть пустыми!")
                return@setOnClickListener
            }

            if (!isValidEmail(userEmail)) {
                showErrorDialog("Неправильный формат электронной почты!")
                return@setOnClickListener
            }

            handleLogin(userEmail, userPassword)
        }
    }

    private fun handleLogin(userEmail: String, userPassword: String) {
        val savedEmail = sharedPreferences.getString("EMAIL", null)
        val savedPassword = sharedPreferences.getString("PASSWORD", null)

        if (savedEmail == null || savedPassword == null) {
            // Первый вход, сохраняем данные
            val editor = sharedPreferences.edit()
            editor.putString("EMAIL", userEmail)
            editor.putString("PASSWORD", userPassword)
            editor.apply()
            showSuccessDialog("Пользователь добавлен!")
            navigateToMainActivity()
        } else if (userEmail == savedEmail && userPassword == savedPassword) {
            navigateToMainActivity()
        } else {
            showErrorDialog("Неверные данные!")
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Ошибка")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()
            .show()
    }

    private fun showSuccessDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Успех")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()
            .show()
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
