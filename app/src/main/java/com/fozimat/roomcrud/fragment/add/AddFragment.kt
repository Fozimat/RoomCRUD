package com.fozimat.roomcrud.fragment.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fozimat.roomcrud.R
import com.fozimat.roomcrud.data.User
import com.fozimat.roomcrud.data.UserViewModel
import com.fozimat.roomcrud.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding as FragmentAddBinding

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdd.setOnClickListener {
            insertData()
        }
    }

    private fun insertData() {
        binding.apply {
            val firstName = edtFirstName.text.toString().trim()
            val lastName = edtLastName.text.toString().trim()
            val age = edtAge.text.toString().trim()

            when {
                firstName.isEmpty() -> {
                    edtFirstName.error = "Field can't be blank"
                    return
                }
                lastName.isEmpty() -> {
                    edtLastName.error = "Field can't be blank"
                    return
                }
                age.isEmpty() -> {
                    edtAge.error = "Field can't be blank"
                    return
                }
                else -> {
                    val user = User(0, firstName, lastName, Integer.parseInt(age))
                    userViewModel.addUser(user)
                    Toast.makeText(requireContext(), "Data successfully added", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(R.id.action_addFragment_to_listFragment)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}