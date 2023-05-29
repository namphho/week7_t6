package com.watasolutions.week7_t6.screens.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.watasolutions.week7_t6.app.MyApp
import com.watasolutions.week7_t6.app.ViewModelFactory
import com.watasolutions.week7_t6.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    lateinit var binding: FragmentSignUpBinding
    lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                (activity?.application as MyApp).prefs,
                (activity?.application as MyApp).db
            )
        ).get(
            SignUpViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            val username = binding.tvUsername.editText?.text.toString().trim()
            val password = binding.tvPassword.editText?.text.toString().trim()
            viewModel.registerUserWithDB(username, password)
        }

        viewModel.registerSuccessEvent.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                Toast.makeText(
                    activity, "Đăng ký thành công", Toast
                        .LENGTH_SHORT
                ).show()
            }
        }

        viewModel.registerErrorEvent.observe(viewLifecycleOwner) { isFailed ->
            if (isFailed) {
                Toast.makeText(
                    activity, "Đăng ký that bai", Toast
                        .LENGTH_SHORT
                ).show()
            }
        }
    }


}