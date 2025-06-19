package ru.otus.basicarchitecture

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import ru.otus.basicarchitecture.databinding.FragmentResultBinding
import kotlin.getValue

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding: FragmentResultBinding
        get() = _binding ?: throw RuntimeException("FragmentResultBinding == null")

    private val viewModel: ResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.loadUserInfo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun observeViewModel(){
        viewModel.userName.observe(viewLifecycleOwner) {
            with(binding){
                textViewName.text = it.name
                textViewSurname.text = it.surname
                textViewBirthday.text = it.birthday
            }
        }
        viewModel.userAddress.observe(viewLifecycleOwner) {
            with(binding){
                textViewAddress.text = String.format(
                    resources.getString(R.string.address_mask),
                    it.country,
                    it.city,
                    it.address
                )
            }
        }
        viewModel.interests.observe(viewLifecycleOwner) {
            val defaultColor = ContextCompat.getColor(requireContext(), R.color.gray_light)
            with(binding){
                it.forEach { interest ->
                    val chip = Chip(requireContext()).apply {
                        text = interest
                        chipBackgroundColor = ColorStateList.valueOf(defaultColor)
                    }
                    binding.chipGroupInterests.addView(chip)
                }
            }
        }
    }

    companion object {
        fun newInstance() = ResultFragment()
    }
}