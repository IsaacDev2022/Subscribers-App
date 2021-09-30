package br.com.dio.subscribersapp.ui.subscriberList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dio.subscribersapp.data.db.entity.SubscriberEntity
import br.com.dio.subscribersapp.repository.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberListViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    private val _allSubscribersEvent = MutableLiveData<List<SubscriberEntity>>()
    val allSubscribersEvent: LiveData<List<SubscriberEntity>>
        get() = _allSubscribersEvent

    fun getSubscribers() = viewModelScope.launch {
        _allSubscribersEvent.postValue(repository.getAllSubscribers())
    }
}