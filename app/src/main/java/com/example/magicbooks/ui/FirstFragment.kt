package com.example.magicbooks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.magicbooks.R
import com.example.magicbooks.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    private val viewModel: BookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BookAdapter()

        with(binding) {
            rvView.adapter = adapter
            rvView.layoutManager = LinearLayoutManager(context)
        }

        viewModel.bookLiveDataFromDataBase.observe(viewLifecycleOwner, {
            it?.let {
                Log.d("LISTADO", "$it")
                adapter.update(it)
            }
        })


        adapter.selectedItem().observe(viewLifecycleOwner, {
            it.let {
                val bundle = Bundle()
                bundle.putString("id", it.id.toString())
                // findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        })
        /* view.findViewById<Button>(R.id.button_first).setOnClickListener {
             findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
         }*/
    }
}