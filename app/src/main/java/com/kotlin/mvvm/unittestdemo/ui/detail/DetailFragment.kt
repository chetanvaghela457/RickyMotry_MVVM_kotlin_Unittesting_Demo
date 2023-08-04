package com.kotlin.mvvm.unittestdemo.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.kotlin.mvvm.unittestdemo.databinding.FragmentDetailBinding
import com.kotlin.mvvm.unittestdemo.ui.MainActivity
import com.kotlin.mvvm.unittestdemo.ui.home.CharacterHomeUiData
import com.kotlin.mvvm.unittestdemo.utility.loadImage

class DetailFragment : Fragment() {

    private lateinit var binding:FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        (activity as MainActivity).detailFragment(args.character.name)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi(args.character)
    }

    private fun setupUi(character: CharacterHomeUiData) {
        binding.apply {
            characterEpisodesTextView.text = character.episode
            characterCreatedAtTextView.text = character.created
            characterGenderTextView.text = character.gender
            characterLocationTextView.text = character.location
            characterOriginTextView.text = character.origin
            characterStatusTextView.text = character.status
            characterSpecyTextView.text = character.species
            characterImageView.loadImage(character.image)
        }
    }
}
