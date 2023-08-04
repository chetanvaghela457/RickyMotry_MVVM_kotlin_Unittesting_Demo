package com.kotlin.mvvm.unittestdemo.ui.splash


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.kotlin.mvvm.unittestdemo.R
import com.kotlin.mvvm.unittestdemo.databinding.ActivitySplashBinding
import com.kotlin.mvvm.unittestdemo.ui.MainActivity
import com.kotlin.mvvm.unittestdemo.utility.observeOnce
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModels<SplashActivityViewModel> ()
    private lateinit var binding :ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeAppState()
        delay()
    }

    private fun observeAppState() {
        val dataObserver = Observer<Boolean> {
            if(it){
                binding.splashTextView.text = getString(R.string.welcome)
                viewModel.saveAppFirstOpenState(false)
            }else{
                binding.splashTextView.text= getString(R.string.hello)
            }
        }
        viewModel.readAppFirstOpenState()
        viewModel.readAppState.observeOnce(this,dataObserver)


    }

    private fun delay(){
        val timer= object : CountDownTimer(2000,50){
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }
        timer.start()

    }
}