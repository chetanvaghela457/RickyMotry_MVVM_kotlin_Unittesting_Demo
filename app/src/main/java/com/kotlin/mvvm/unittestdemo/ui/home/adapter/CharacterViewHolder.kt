package com.kotlin.mvvm.unittestdemo.ui.home.adapter

import android.view.ViewGroup
import com.kotlin.mvvm.unittestdemo.databinding.AdapterCharacterItemBinding
import com.kotlin.mvvm.unittestdemo.ui.base.BaseViewHolder
import com.kotlin.mvvm.unittestdemo.ui.home.CharacterHomeUiData
import com.kotlin.mvvm.unittestdemo.utility.inflateAdapterItem


class CharacterViewHolder(private val binding:AdapterCharacterItemBinding,val onClick:(CharacterHomeUiData)->Unit):BaseViewHolder<CharacterHomeUiData>(binding.root){

    private var viewData: CharacterHomeUiData? = null
    companion object{
        fun createForm(parent:ViewGroup,onClick: (CharacterHomeUiData) -> Unit)=
            CharacterViewHolder(parent.inflateAdapterItem(AdapterCharacterItemBinding::inflate),onClick)
    }
    init {
        itemView.setOnClickListener {
            viewData?.let { onClick(it) }
        }
    }
    override fun onBind(data: CharacterHomeUiData) {
        binding.characterComponent.setCharacterData(data)
        viewData = data
    }

}