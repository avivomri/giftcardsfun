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
import com.example.giftcardsfun.R
import com.example.giftcardsfun.databinding.MainFragmentBinding
import com.example.giftcardsfun.db.entity.GiftCardEntity
import com.example.giftcardsfun.viewmodel.MainViewModel


class GiftCardsFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: GiftCardsAdapter

    sealed class State {
        data class Success(val list: List<GiftCardEntity>) : State()
        object Failure : State()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        adapter = GiftCardsAdapter(viewModel.getGiftCardModels())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStateLiveData().observe(viewLifecycleOwner, Observer{ state ->
            if (state is State.Success) {
                adapter.setGiftCards(state.list)
            } else if (state is State.Failure) {
                Toast.makeText(requireActivity(), "Failed to display gift card", Toast.LENGTH_SHORT).show()
            }
        })

        binding.buttonRefresh.setOnClickListener {
            viewModel.refresh()
        }
    }
}
