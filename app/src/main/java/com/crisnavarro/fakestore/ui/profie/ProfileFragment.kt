package com.crisnavarro.fakestore.ui.profie

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
import com.crisnavarro.fakestore.data.network.response.ProfileResponse
import com.crisnavarro.fakestore.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() {
        viewModel.getProfile()
    }

    private fun initListeners() = with(binding!!) {
        ivClose.setOnClickListener { findNavController().popBackStack() }
    }

    private fun initObservers() = with(binding!!) {

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it)
                view.show(animationView)
            else
                view.hide(animationView)
        }
        viewModel.profile.observe(viewLifecycleOwner) {
            setData(it)
        }

    }

    private fun setData(profile: ProfileResponse) = with(binding!!) {
        etName.setText(profile[0].name.firstname)
        etLastName.setText(profile[0].name.lastname)
        etEmail.setText(profile[0].email)
        etAlias.setText(profile[0].username)
        etPassword.setText(profile[0].password)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}