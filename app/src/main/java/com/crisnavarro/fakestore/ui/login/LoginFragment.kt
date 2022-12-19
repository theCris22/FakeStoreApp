package com.crisnavarro.fakestore.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        initListeners()
        initObservers()
    }

    private fun initListeners() = with(binding!!) {
        btnSignup.setOnClickListener { findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment()) }
        btnLogin.setOnClickListener { login() }
    }

    private fun initObservers() = with(binding!!) {
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it)
                animationView.show()
            else
                animationView.hide()
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