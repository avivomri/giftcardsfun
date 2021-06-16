package com.example.giftcardsfun.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.giftcardsfun.viewmodel.MainViewModel
import com.example.giftcardsfun.R
import com.example.giftcardsfun.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    sealed class State {
        object success : State()
        object failure : State()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment, container, false
        )

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val view: View = binding.getRoot()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStateLiveData().(this, )

        binding.buttonRefresh.setOnClickListener{
            viewModel.refresh()
        }
    }
}
