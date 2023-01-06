package com.kibzation.cvsnbatakehome.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kibzation.cvsnbatakehome.R
import com.kibzation.cvsnbatakehome.databinding.FragmentMainBinding
import com.kibzation.cvsnbatakehome.model.Team
import com.kibzation.cvsnbatakehome.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mainViewModel: MainViewModel
    private var fragmentMainBinding: FragmentMainBinding? = null
    private val binding get() = fragmentMainBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = activity?.run {
            ViewModelProvider(requireActivity())[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = mainViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.fetchCountries()
        observeTeamList()
        observeLoading()
        loadImage()
    }

    private fun observeTeamList() {
        mainViewModel.teams.observe(requireActivity()) {
            val arrayAdapter = TeamAdapter(it, requireContext(), R.layout.team)
            val autoCompleteView = binding.autoCompleteTextView
            autoCompleteView.setAdapter(arrayAdapter)
            autoCompleteView.setOnItemClickListener { _, _, position, _ ->
                mainViewModel.setupTeam(arrayAdapter.getItem(position) as Team)

            }
        }
    }

    private fun observeLoading() {
        mainViewModel.loading.observe(requireActivity()) {
            binding.progressBar.visibility =
                if (it) View.VISIBLE else View.GONE
        }
    }

    private fun loadImage(){
        Glide.with(requireContext())
            .load("https://www.balldontlie.io/images/cryingjordan.jpeg")
            .centerCrop()
            .into(binding.imageView)
    }

}