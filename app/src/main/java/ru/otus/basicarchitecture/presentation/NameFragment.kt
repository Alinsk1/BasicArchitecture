package ru.otus.basicarchitecture.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.otus.basicarchitecture.R
import ru.otus.basicarchitecture.databinding.FragmentNameBinding

@AndroidEntryPoint
class NameFragment : Fragment() {

    private var _binding: FragmentNameBinding? = null
    private val binding: FragmentNameBinding
        get() = _binding ?: throw RuntimeException("FragmentNameBinding == null")

    private val viewModel: NameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        addTextChangedListeners()
        binding.buttonNext.setOnClickListener {
            viewModel.validateData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNameBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun observeViewModel(){
        viewModel.errorEmptyName.observe(viewLifecycleOwner) {
            with(binding) {
                if (it){
                    editTextName.error = String.format(resources.getString(R.string.empty_field),
                        textInputName.hint.toString()
                    )
                } else {
                    editTextName.error = null
                }
            }
        }
        viewModel.errorEmptySurname.observe(viewLifecycleOwner) {
            with(binding) {
                if (it){
                    editTextSurname.error = String.format(resources.getString(R.string.empty_field),
                        textInputSurname.hint.toString()
                    )
                } else {
                    editTextSurname.error = null
                }
            }
        }
        viewModel.errorEmptyBirthday.observe(viewLifecycleOwner) {
            with(binding) {
                if (it){
                    editTextBirthday.error = String.format(resources.getString(R.string.empty_field),
                        textInputBirthday.hint.toString()
                    )
                } else {
                    editTextBirthday.error = null
                }
            }
        }
        viewModel.errorAge.observe(viewLifecycleOwner) {
            with(binding) {
                if (it){
                    editTextBirthday.error = resources.getString(R.string.you_are_under_18)
                } else {
                    editTextBirthday.error = null
                }
            }

        }
        viewModel.errorBirthday.observe(viewLifecycleOwner) {
            with(binding) {
                if (it){
                    editTextBirthday.error = resources.getString(R.string.incorrectly_birthday)
                } else {
                    editTextBirthday.error = null
                }
            }

        }
        viewModel.canContinue.observe(viewLifecycleOwner) {
            if (it){
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContainer, AddressFragment.Companion.newInstance())
                    .commit()
            }
        }
    }

    private fun addTextChangedListeners(){
        with(binding){
            editTextName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    viewModel.setName(editTextName.text.toString())
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
            editTextSurname.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    viewModel.setSurname(editTextSurname.text.toString())
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
            editTextBirthday.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    viewModel.setBirthday(editTextBirthday.text.toString())
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    companion object {
    }
}