package br.com.dio.subscribersapp.ui.subscriberList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.dio.subscribersapp.R
import br.com.dio.subscribersapp.data.db.AppDatabase
import br.com.dio.subscribersapp.data.db.dao.SubscriberDao
import br.com.dio.subscribersapp.extension.navigateWithAnimations
import br.com.dio.subscribersapp.repository.DatabaseDataSource
import br.com.dio.subscribersapp.repository.SubscriberRepository
import kotlinx.android.synthetic.main.subscriber_list_fragment.*
import kotlinx.android.synthetic.main.subscriber_list_fragment.view.*

class SubscriberListFragment : Fragment(R.layout.subscriber_list_fragment) {

    private val viewModel: SubscriberListViewModel by viewModels {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDao = AppDatabase.getInstance(requireContext()).subscriberDAO
                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return SubscriberListViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelEvents()
        configureViewListeners()
    }

    private fun observeViewModelEvents() {

        viewModel.allSubscribersEvent.observe(viewLifecycleOwner) { allSubscribers ->
            val subscriberListAdapter = SubscriberListAdapter(allSubscribers).apply {
                onItemClick = { subscriber ->
                    val directions = SubscriberListFragmentDirections
                        .actionSubscriberListFragmentToSubscriberFragment(subscriber)
                    findNavController().navigateWithAnimations(directions)
                }
            }

            with(recycler_subscribers) {
                setHasFixedSize(true)
                adapter = subscriberListAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSubscribers()
    }

    private fun configureViewListeners() {
        fabAddSubscriber.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.action_subscriberListFragment_to_subscriberFragment)
        }
    }
}