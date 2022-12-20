package com.crisnavarro.fakestore.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.crisnavarro.fakestore.R
import com.crisnavarro.fakestore.core.hide
import com.crisnavarro.fakestore.core.show
import com.crisnavarro.fakestore.data.network.models.Address
import com.crisnavarro.fakestore.data.network.models.UserInformation
import com.crisnavarro.fakestore.data.network.request.CreateUserRequest
import com.crisnavarro.fakestore.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment(R.layout.fragment_signup) {

    private var binding: FragmentSignupBinding? = null
    private val viewModel: SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() = with(binding!!) {
        listOf(
            etName,
            etLastName,
            etCity,
            etNumber,
            etStreet,
            etZipCode,
            etEmail,
            etAlias,
            etPassword,
            etPasswordConfirm
        ).map { it.doOnTextChanged { _, _, _, _ -> enableSignupButton() } }
    }

    private fun initListeners() = with(binding!!) {
        iconBack.setOnClickListener { findNavController().popBackStack() }
        btnSignup.setOnClickListener { registerUser() }
    }

    private fun initObservers() = with(binding!!) {
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it)
                view?.show(animationView)
            else
                view?.hide(animationView)
        }
    }

    private fun registerUser() = with(binding!!) {

        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        val passwordConfirm = etPasswordConfirm.text.toString()

        val userInformation = UserInformation(
            firstname = etName.text.toString(),
            lastname = etLastName.text.toString()
        )

        val userAddress = Address(
            city = etCity.text.toString(),
            number = etNumber.text.toString().toInt(),
            street = etStreet.text.toString(),
            zipcode = etZipCode.text.toString().toInt()
        )

        viewModel.signup(
            CreateUserRequest(
                address = userAddress,
                email = email,
                name = userInformation,
                password = password,
                username = passwordConfirm
            )
        )
    }

    private fun enableSignupButton() = with(binding!!) {
        btnSignup.isEnabled =
            etName.text.toString().length >= 3
                    && etLastName.text.toString().length >= 3
                    && etCity.text.toString().length >= 3
                    && etNumber.text.toString().length >= 3
                    && etStreet.text.toString().length >= 3
                    && etZipCode.text.toString().length >= 3
                    && etEmail.text.toString().length >= 3
                    && etAlias.text.toString().length >= 3
                    && etPassword.text.toString().length >= 3
                    && etPasswordConfirm.text.toString().length >= 3
                    && etPassword.text.toString() == etPasswordConfirm.text.toString()

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}