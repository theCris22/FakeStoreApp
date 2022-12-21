package com.crisnavarro.fakestore.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.crisnavarro.fakestore.R
import com.crisnavarro.fakestore.core.hide
import com.crisnavarro.fakestore.core.show
import com.crisnavarro.fakestore.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var binding: FragmentLoginBinding? = null
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() = with(binding!!) {
        etUserName.setText("mor_2314")
        etPassword.setText("83r5^_")
        etUserName.doOnTextChanged { text, _, _, _ -> enableButtonLogin(text.toString()) }
        etPassword.doOnTextChanged { text, _, _, _ -> enableButtonLogin(text.toString()) }
    }

    private fun enableButtonLogin(text: String) =
        with(binding!!) { btnLogin.isEnabled = text.length >= 3 }

    private fun initListeners() = with(binding!!) {
        btnSignup.setOnClickListener { findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment()) }
        btnLogin.setOnClickListener { login() }
    }

    private fun initObservers() = with(binding!!) {
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it)
                view?.show(animationView)
            else
                view?.hide(animationView)
        }
        viewModel.successLogin.observe(viewLifecycleOwner) {
            /*if (it)
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment2())*/
        }
        viewModel.failLogin.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank())
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()

        }
    }

    private fun login() = with(binding!!) {
        val userName = etUserName.text.toString()
        val password = etPassword.text.toString()

        viewModel.login(userName, password)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}