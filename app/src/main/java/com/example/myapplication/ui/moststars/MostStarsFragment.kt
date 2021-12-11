package com.example.myapplication.ui.moststars

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.CustomerReviewsItem
import com.example.myapplication.R
import com.example.myapplication.Restaurant
import com.example.myapplication.adapter.ReviewAdapter
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentMostStarsBinding

class MostStarsFragment : BaseFragment<FragmentMostStarsBinding>() {

    private lateinit var mostStarsViewModel: MostStarsViewModel

    override fun getViewBinding(): FragmentMostStarsBinding = FragmentMostStarsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the ViewModel
        mostStarsViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MostStarsViewModel::class.java)

        mostStarsViewModel.restaurant.observe(viewLifecycleOwner,  { restaurant -> setRestaurantData(restaurant)})

        val layoutManager = LinearLayoutManager(activity)
        binding.rvReview.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(activity, layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)

        mostStarsViewModel.listReview.observe(viewLifecycleOwner, {
            consumerReviews -> setReviewData(consumerReviews)
        })


            binding.btnSend.setOnClickListener {
//                val anotherText = "Pujay"
//                mostStarsViewModel.getName().setValue(anotherText)
                Toast.makeText(activity, "BOOM", Toast.LENGTH_SHORT).show()
            }


    }

    private fun setRestaurantData(restaurant: Restaurant) {
        binding.tvTitle.text = restaurant.name
    }

    private fun setReviewData(consumerReviews: List<CustomerReviewsItem>) {
        val listReview = consumerReviews.map {
            "${it.review}\n- ${it.name}"
        }
        val adapter = ReviewAdapter(listReview)
        binding.rvReview.adapter = adapter
        binding.edReview.setText("")
    }
}