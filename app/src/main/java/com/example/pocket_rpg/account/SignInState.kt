package com.example.pocket_rpg.account

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
) {
}