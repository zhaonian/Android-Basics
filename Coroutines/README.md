Need these:
private val viewModelJob = Job()

private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

override fun onCleared() {
    super.onCleared()
    viewModelJob.cancel()
}

You must pass CoroutineScope a Job in order to cancel all coroutines started in the scope. If you don't, the scope will run until your app is terminated. If that's not what you intended, you will be leaking memory.

Scopes created with the CoroutineScope constructor add an implicit job, which you can cancel using uiScope.coroutineContext.cancel()


with `dependencies {
  ...
  implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:x.x.x"
}`, you will not need the boilerplate code above, instead you can just use viewModelScope and the library will take care of setting up and clearing the scope for you, like this:

```
class MainViewModel : ViewModel() {
    // Make a network request without blocking the UI thread
    private fun makeNetworkRequest() {
       // launch a coroutine in viewModelScope 
        viewModelScope.launch(Dispatchers.IO) {
            // slowFetch()
        }
    }

    // No need to override onCleared()
}
```


Kotlin has the runBlocking function that blocks while it calls suspend functions. When runBlocking calls a suspend function, instead of suspending like normal it will block just like a function. You can think of it as a way to convert suspend functions into normal function calls.
Important: The function runBlocking will always block the caller, just like a regular function call. The coroutine will run synchronously on the same thread. You should avoid runBlocking in your application code and prefer launch which returns immediately.
runBlocking should only be used from APIs that expect blocking calls like tests.



Important: Even though we only use viewModelScope in this codelab, generally it's fine to add a scope anywhere that it makes sense. Don't forget to cancel it if it's no longer needed.

For example, you might declare one in a RecyclerView Adapter to do DiffUtil operations.

