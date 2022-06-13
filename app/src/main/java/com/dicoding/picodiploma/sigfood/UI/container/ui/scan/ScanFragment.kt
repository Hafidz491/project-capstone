package com.dicoding.picodiploma.sigfood.UI.container.ui.scan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.sigfood.UI.login.LoginActivity
import com.dicoding.picodiploma.sigfood.UI.loginfirst.LoginFirstActivity
import com.dicoding.picodiploma.sigfood.databinding.FragmentScanBinding

class ScanFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null
    private val scanViewModel by viewModels<ScanViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scanViewModel.checkToken()

        scanViewModel.token.observe(viewLifecycleOwner) {
            if(it != "tokenIni") {
                val intentToLogin = Intent(context, LoginFirstActivity::class.java)
                startActivity(intentToLogin)
                requireActivity().finish()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}