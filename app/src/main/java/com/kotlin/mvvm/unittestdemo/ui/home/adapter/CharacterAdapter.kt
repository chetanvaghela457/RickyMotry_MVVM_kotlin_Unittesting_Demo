package com.kotlin.mvvm.unittestdemo.ui.home.adapter

import android.view.ViewGroup
import com.kotlin.mvvm.unittestdemo.ui.base.BaseRecyclerViewAdapter
import com.kotlin.mvvm.unittestdemo.ui.home.CharacterHomeUiData

class CharacterAdapter(private val onClick:(CharacterHomeUiData)->Unit) : BaseRecyclerViewAdapter<CharacterHomeUiData, CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.createForm(parent, onClick)
    }

}