/*
 * Copyright 2018 Google LLC
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

package com.example.android.kotlincoroutines.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.kotlincoroutines.util.BACKGROUND
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * MainViewModel designed to store and manage UI-related data in a lifecycle conscious way. This
 * allows data to survive configuration changes such as screen rotations. In addition, background
 * work such as fetching network results can continue through configuration changes and deliver
 * results after the new Fragment or Activity is available.
 */
class MainViewModel : ViewModel() {

    /**
     * Request a snackbar to display a string.
     *
     * This variable is private because we don't want to expose MutableLiveData
     *
     * MutableLiveData allows anyone to set a value, and MainViewModel is the only
     * class that should be setting values.
     */
    private val _snackBar = MutableLiveData<String>()

    /**
     * Request a snackbar to display a string.
     */
    val snackbar: LiveData<String>
        get() = _snackBar

    /**
     * Wait one second then display a snackbar.
     *
     * 1.
     * viewModelScope.launch will start a coroutine in the viewModelScope.
     * This means when the job that we passed to viewModelScope gets canceled,
     * all coroutines in this job/scope will be cancelled. If the user left the Activity before
     * delay returned, this coroutine will automatically be cancelled when onCleared is called upon
     * destruction of the ViewModel.
     *
     * 2.
     * Since viewModelScope has a default dispatcher of Dispatchers.Main, this coroutine will be
     * launched in the main thread. We'll see later how to use different threads.
     *
     * 3.
     * The function delay is a suspend function. This is shown in Android Studio by the  icon in the
     * left gutter. Even though this coroutine runs on the main thread, delay won't block the thread
     * for one second. Instead, the dispatcher will schedule the coroutine to resume in one second
     * at the next statement.
     *
     */
    fun onMainViewClicked() {
//        BACKGROUND.submit {
//            Thread.sleep(1_000)
//            // use postValue since we're in a background thread
//            _snackBar.postValue("Hello, from threads!")
//        }
        // Dispatchers.Main is the default dispatcher if not declared where to launch.
        viewModelScope.launch {
            delay(1_000)
            _snackBar.value = "Hello, from coroutines!"
        }
    }

    /**
     * Called immediately after the UI shows the snackbar.
     */
    fun onSnackbarShown() {
        _snackBar.value = null
    }
}
