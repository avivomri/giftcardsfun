package com.example.giftcardsfun.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.giftcardsfun.R
import com.example.giftcardsfun.databinding.GiftCardRowLayoutBinding
import com.example.giftcardsfun.db.entity.GiftCardEntity
import kotlinx.coroutines.Dispatchers

class GiftCardsAdapter(giftCards: MutableList<GiftCardEntity>,
                       private val lifeCycleOwner: LifecycleOwner, viewLifecycleOwner: LifecycleOwner,
                       observer: Observer<MovieExtendedDetailsResultJson>):
    RecyclerView.Adapter<GiftCardsAdapter.GiftCardRowHolder>() {
    private val giftCards: MutableList<GiftCardEntity>
    private val pickedGiftCardLiveData: MutableLiveData<GiftCardEntity>

    init {
        this.giftCards = ArrayList(giftCards)
        this.pickedGiftCardLiveData = MutableLiveData()
        this.pickedGiftCardLiveData.observe(viewLifecycleOwner, observer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftCardRowHolder {
        val binding: GiftCardRowLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
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
    inner class GiftCardRowHolder(val binding: GiftCardRowLayoutBinding, lifeCycleOwner: LifecycleOwner) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.lifecycleOwner = lifeCycleOwner
        }

        fun binding(giftCard: MovieRow, moviePickedLiveData: MutableLiveData<MovieExtendedDetailsResultJson>,
                    lifecycleScope: LifecycleCoroutineScope) {
            binding.model = giftCard
            binding.root.setOnClickListener {
                lifecycleScope.launch(Dispatchers.Main) {
                    val movieDetailsResult = MovieRepo.getMovieDetails(giftCard.serverMovieId)!!
                    moviePickedLiveData.value = movieDetailsResult
                }
            }
        }
    })
}