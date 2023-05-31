package com.example.mythirdparty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.example.mythirdparty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MainAdapter()

        initView()
    }

    private fun initView() {
        binding.etSearchMain.doOnTextChanged { text, start, before, count ->
            text.let {
                if (text.isNullOrEmpty()) {
                    initObserver("")
                } else {
                    initObserver(text.toString())
                }
            }
        }
    }

    private fun initObserver(query: String?) {

        viewModel.getListUser(query)

        viewModel.isLoading.observe(this) {
            binding.shimmerUserMain.isVisible = it
            binding.rvUserMain.isVisible = !it
        }

        viewModel.lottieState.observe(this) {
            binding.lottieEmptyBox.isVisible = it
            binding.rvUserMain.isVisible = !it
        }

        viewModel.listUser.observe(this) {
            it.let {
                adapter.submitList(it?.items)
                binding.rvUserMain.adapter = adapter
            }
        }
    }

}