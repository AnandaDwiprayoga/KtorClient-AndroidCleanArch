package com.pasukanlangit.id.cleanarch_ktorclient.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pasukanlangit.id.cleanarch_ktorclient.domain.model.LoginResponse
import com.pasukanlangit.id.cleanarch_ktorclient.domain.usecase.LoginUseCase
import com.pasukanlangit.id.cleanarch_ktorclient.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel(){

    private val _loginState = MutableStateFlow<Resource<LoginResponse>?>(null)
    val loginState: StateFlow<Resource<LoginResponse>?> = _loginState

    fun login(noHp: String, password: String) = viewModelScope.launch {
        loginUseCase(noHp, password)
            .collectLatest {
                _loginState.value = it
            }
    }
}