/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.petList
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.model.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PetViewModel : ViewModel() {

    val petLiveData: LiveData<UiState<List<Pet>>>
        get() = _petLiveData

    private val _petLiveData = MutableLiveData<UiState<List<Pet>>>()

    fun getPetList() {
        Log.e("xxx", "getPetList")
        viewModelScope.launch {
            _petLiveData.value = UiState(true, null, null)
            withContext(Dispatchers.IO) {
                delay(1000)
                _petLiveData.postValue(UiState(false, null, petList))
            }
        }
    }
}
