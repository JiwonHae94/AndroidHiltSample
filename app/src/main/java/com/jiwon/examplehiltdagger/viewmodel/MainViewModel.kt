package com.jiwon.examplehiltdagger.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiwon.examplehiltdagger.data.model.Account
import com.jiwon.examplehiltdagger.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {
    private val TAG = MainViewModel::class.java.simpleName
    private val list = mutableListOf<Account>()

    private val _itemList = MutableLiveData<List<Account>>()
    val itemList: LiveData<List<Account>> = _itemList

    init{
        _itemList.value = list
    }

    fun requestMembers(){
        viewModelScope.launch{
            userRepository.requestUsers().catch { e ->
                Log.e(TAG, e.stackTraceToString())
            }.collect{
                list.addAll(it)
                _itemList.value = list
            }
        }
    }
}