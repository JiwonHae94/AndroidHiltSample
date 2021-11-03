package com.jiwon.examplehiltdagger.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jiwon.examplehiltdagger.databinding.ActivityMainBinding
import com.jiwon.examplehiltdagger.view.adapter.AccountAdapter
import com.jiwon.examplehiltdagger.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        setObservers()
    }

    private fun setUpUI() {
        binding.accRecyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = AccountAdapter()
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL))
        }

        binding.restBtn.setOnClickListener {
            mainViewModel.requestMembers()
        }
    }

    private fun setObservers(){
        mainViewModel.itemList.observe(this, { list ->
            list?.let { listReceived ->
                Log.d(MainActivity::class.java.simpleName, "acc received : ${listReceived.joinToString(" ")}")
                binding.accRecyclerView.apply{
                    with(adapter as AccountAdapter){
                        this.addAccounts(listReceived)
                        notifyDataSetChanged()
                    }
                }
            }
        })
    }
}