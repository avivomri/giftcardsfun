package com.example.giftcardsfun.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.giftcardsfun.R
import com.example.giftcardsfun.databinding.MainFragmentBinding
import com.example.giftcardsfun.viewmodel.GiftCardFragmentState
import com.example.giftcardsfun.viewmodel.MainViewModel


class GiftCardsFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: GiftCardsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        adapter = GiftCardsAdapter(viewModel.getGiftCardModels())

        binding.recyclerViewGiftCards.adapter = adapter
        binding.recyclerViewGiftCards.layoutManager = LinearLayoutManager(requireActivity())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Important fix: removeObservers and getViewLifecyclerOwner instead of activity to prevent multiple call to onChanged from unremoved observers.
        //More info: https://blog.usejournal.com/observe-livedata-from-viewmodel-in-fragment-fd7d14f9f5fb
        viewModel.getStateLiveData().removeObservers(viewLifecycleOwner)
        viewModel.getStateLiveData().observe(viewLifecycleOwner, Observer { state ->
            if (state is GiftCardFragmentState.Success) {
                adapter.setGiftCards(state.list)
            } else if (state is GiftCardFragmentState.Failure) {
                Toast.makeText(requireActivity(), "Failed to display gift card", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        binding.buttonRefresh.setOnClickListener {
            viewModel.refresh()
        }

        super.onViewCreated(view, savedInstanceState)
    }
}
