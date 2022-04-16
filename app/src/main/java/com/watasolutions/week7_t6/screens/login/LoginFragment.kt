package com.watasolutions.week7_t6.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.watasolutions.week7_t6.app.MyApp
import com.watasolutions.week7_t6.app.ViewModelFactory
import com.watasolutions.week7_t6.databinding.FragmentLoginBinding


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var model: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this, ViewModelFactory(activity?.application as MyApp)).get(
            LoginViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerLoadUserEvent()
        registerSaveSuccess()
        binding.btnSave.setOnClickListener {
            val username = binding.tvUsername.editText?.text.toString().trim()
            val password = binding.tvPassword.editText?.text.toString().trim()
            model.saveUser(username, password)
        }
        binding.btnLoad.setOnClickListener {
            model.loadUser()
        }

    }

    private fun registerSaveSuccess(){
        model.saveSuccessEvent.observe(this){
            when(it) {
                true -> {
                    binding.tvUsername.editText?.setText("")
                    binding.tvPassword.editText?.setText("")
                }
            }
        }
    }

    private fun registerLoadUserEvent(){
        model.loadSuccessEvent.observe(this){
            binding.tvReview.text = "${it.username} - ${it.password}"
        }
    }

}