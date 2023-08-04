package com.kotlin.mvvm.unittestdemo.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.kotlin.mvvm.unittestdemo.databinding.LayoutCharacterBinding
import com.kotlin.mvvm.unittestdemo.ui.home.CharacterHomeUiData
import com.kotlin.mvvm.unittestdemo.utility.loadImage

class CharacterUiComponent @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet?=null,
    defStyleAttr:Int=0
    ) : FrameLayout(context,attributeSet,defStyleAttr){

    private val binding = LayoutCharacterBinding.inflate(LayoutInflater.from(context),this,false)

    init {
        addView(binding.root)
    }

    fun setCharacterData(characterHomeUiData: CharacterHomeUiData){
        binding.characterImageView.loadImage(characterHomeUiData.image)
        binding.characterTextView.text = characterHomeUiData.name
        setVisibilityOfGender(characterHomeUiData.gender)
    }

    private fun setVisibilityOfGender(gender: String) {
        when(gender){
            "Male" -> {
                binding.maleImageView.visibility = View.VISIBLE
                binding.femaleImageView.visibility = View.INVISIBLE
                binding.unknownImageView.visibility = View.INVISIBLE
            }
            "Female" -> {
                binding.femaleImageView.visibility = View.VISIBLE
                binding.maleImageView.visibility = View.INVISIBLE
                binding.unknownImageView.visibility = View.INVISIBLE
            }
            "unknown" -> {
                binding.unknownImageView.visibility = View.VISIBLE
                binding.femaleImageView.visibility = View.INVISIBLE
                binding.maleImageView.visibility = View.INVISIBLE
            }
        }
    }


}