package ru.otus.basicarchitecture

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.otus.basicarchitecture.databinding.FragmentAddressBinding
import kotlin.getValue

@AndroidEntryPoint
class AddressFragment : Fragment() {

    private var _binding: FragmentAddressBinding? = null
    private val binding: FragmentAddressBinding
        get() = _binding ?: throw RuntimeException("FragmentAddressBinding == null")

    private val viewModel: AddressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            userName = it.getParcelable(EXTRA_USER_NAME, UserName::class.java) as UserName
//        }
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
    ): View? {
        _binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun observeViewModel(){
        viewModel.errorEmptyCountry.observe(viewLifecycleOwner) {
            with(binding) {
                if (it){
                    editTextCountry.error = String.format(
                        resources.getString(R.string.empty_field),
                        textInputCountry.hint.toString()
                    )
                } else {
                    editTextCountry.error = null
                }
            }

        }
        viewModel.errorEmptyCity.observe(viewLifecycleOwner) {
            with(binding) {
                if (it){
                    editTextCity.error = String.format(resources.getString(R.string.empty_field),
                        textInputCity.hint.toString()
                    )
                } else {
                    editTextCity.error = null
                }
            }

        }
        viewModel.errorEmptyAddress.observe(viewLifecycleOwner) {
            with(binding) {
                if (it){
                    editTextAddress.error = String.format(resources.getString(R.string.empty_field),
                        textInputAddress.hint.toString()
                    )
                } else {
                    editTextAddress.error = null
                }
            }
        }
        viewModel.canContinue.observe(viewLifecycleOwner) {
            if (it){
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContainer, InterestsFragment.newInstance())
                    .commit()
            }
        }
    }

    private fun addTextChangedListeners(){
        with(binding){
            editTextCountry.addTextChangedListener(object : TextWatcher{
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
                    viewModel.setCountry(editTextCountry.text.toString())
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
            editTextCity.addTextChangedListener(object : TextWatcher{
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
                    viewModel.setCity(editTextCity.text.toString())
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
            editTextAddress.addTextChangedListener(object : TextWatcher{
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
                    viewModel.setAddress(editTextAddress.text.toString())
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val EXTRA_USER_NAME = "user_name"

        fun newInstance() = AddressFragment()
//            AddressFragment().apply {
//                arguments = Bundle().apply {
//                    putParcelable(EXTRA_USER_NAME, userName)
//                }
//            }
    }
}