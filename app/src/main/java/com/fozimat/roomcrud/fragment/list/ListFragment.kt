package com.fozimat.roomcrud.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fozimat.roomcrud.R
import com.fozimat.roomcrud.data.UserViewModel
import com.fozimat.roomcrud.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding as FragmentListBinding

    private val adapter =  ListAdapter()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        userViewModel.readAllData.observe(viewLifecycleOwner, { data ->
            if(data != null) {
                adapter.setData(data)
            }
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }

    private fun setRecyclerView() {
        val rvUser = binding.rvUser
        rvUser.layoutManager = LinearLayoutManager(requireContext())
        rvUser.setHasFixedSize(true)
        rvUser.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}