package com.hardik.vasundharatask.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hardik.vasundharatask.R
import com.hardik.vasundharatask.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            var data = "8"
            if(!binding.edtFirst.text.toString().isNullOrEmpty()){
                data = binding.edtFirst.text.toString()
            }
            val bundle = Bundle().apply {
                putString("someData", data) // Put your empty string data here
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}