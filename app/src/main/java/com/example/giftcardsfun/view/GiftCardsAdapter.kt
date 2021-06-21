package com.example.giftcardsfun.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.giftcardsfun.R
import com.example.giftcardsfun.db.entity.GiftCardEntity
import kotlinx.coroutines.Dispatchers

class RecyclerAdapter(context: Context, movies: MutableList<MovieRow>, 
                      private val lifeCycleOwner: LifecycleOwner, viewLifecycleOwner: LifecycleOwner, 
                      observer: Observer<MovieExtendedDetailsResultJson>): RecyclerView.Adapter<GiftCardsRecyclerViewAdapter.GiftCardRowHolder>() {
    private val giftCards: MutableList<MovieRow>
    private val pickedGiftCardLiveData: MutableLiveData<MovieExtendedDetailsResultJson>

    init {
        this.giftCards = ArrayList(movies)
        this.pickedGiftCardLiveData = MutableLiveData()
        this.pickedGiftCardLiveData.observe(viewLifecycleOwner, observer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftCardRowHolder {
        val binding: GiftCardLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.gift_card_row_layout, parent, false)
        return GiftCardRowHolder(binding, lifeCycleOwner)
    }

    override fun onBindViewHolder(holder: GiftCardRowHolder, position: Int) {
        val movie = giftCards[position]
        holder.binding(movie, pickedGiftCardLiveData, lifeCycleOwner.lifecycleScope)
    }

    override fun getItemCount(): Int {
        return giftCards.size
    }

    fun setGiftCards(movies: MutableList<GiftCardEntity>) {
        this.giftCards.clear()
        this.giftCards.addAll(movies)
        notifyDataSetChanged()
    }

    fun clear() {
        setGiftCards(mutableListOf())
    }

    //View Holder
    inner class GiftCardRowHolder(val binding: MovieRowLayoutBinding, lifeCycleOwner: LifecycleOwner) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.lifecycleOwner = lifeCycleOwner
        }

        fun binding(movie: MovieRow, moviePickedLiveData: MutableLiveData<MovieExtendedDetailsResultJson>, lifecycleScope: LifecycleCoroutineScope) {
            binding.model = movie
            binding.root.setOnClickListener {
                lifecycleScope.launch(Dispatchers.Main) {
                    val movieDetailsResult = MovieRepo.getMovieDetails(movie.serverMovieId)!!
                    moviePickedLiveData.value = movieDetailsResult
                }
            }
        }
    })
}