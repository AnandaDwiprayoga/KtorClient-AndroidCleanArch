package com.pasukanlangit.id.cleanarch_ktorclient.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.pasukanlangit.id.cleanarch_ktorclient.R
import com.pasukanlangit.id.cleanarch_ktorclient.databinding.FragmentLoginBinding
import com.pasukanlangit.id.cleanarch_ktorclient.domain.utils.Resource
import com.pasukanlangit.id.cleanarch_ktorclient.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            btnLogin.setOnClickListener {
                val noHp = noHp.text.toString().trim()
                val password = password.text.toString().trim()

                viewModel.login(noHp, password)
            }
        }
        observeLogin()
    }

    private fun observeLogin() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loginState.collectLatest { state ->
                if(state != null){
                    binding.loading.isVisible = state is Resource.Loading
                    when(state){
                        is Resource.Error -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            goToHomePage()
                        }
                    }
                }
            }
        }
    }

    private fun goToHomePage() {
        val direction = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(direction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}