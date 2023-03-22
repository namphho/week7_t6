package com.watasolutions.week7_t6.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.watasolutions.week7_t6.R
import com.watasolutions.week7_t6.app.MyApp
import com.watasolutions.week7_t6.app.ViewModelFactory
import com.watasolutions.week7_t6.data.db.AccountDatabase
import com.watasolutions.week7_t6.databinding.FragmentLoginBinding


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory((activity?.application as MyApp).prefs,
                AccountDatabase.getDatabase(activity?.application as MyApp))
        ).get(
            LoginViewModel::class.java
        )

//        /// old style
        viewModel = ViewModelProvider(
            this,
        ).get(
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
        registerErrorEvent()
        registerLoginSuccess()
        binding.btnLogin.setOnClickListener {
            val username = binding.tvUsername.editText?.text.toString().trim()
            val password = binding.tvPassword.editText?.text.toString().trim()
            viewModel.loginWithDatabase(username, password)
        }
        binding.btnSignUp.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_loginFragment_to_signUpFragment)
        }

    }

    private fun registerLoginSuccess() {
        viewModel.saveSuccessEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { isSuccess ->
                if (isSuccess) {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
            }
        }
    }

    private fun registerErrorEvent() {
        viewModel.loginFailedEvent.observe(viewLifecycleOwner) { errMsg ->
            if (viewModel.saveSuccessEvent.value?.peekContent() == false) {
                Toast.makeText(activity, errMsg, Toast.LENGTH_SHORT).show()
            }
        }
    }

}