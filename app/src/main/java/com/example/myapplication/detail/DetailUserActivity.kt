package com.example.myapplication.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.User

class DetailUserActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_USER = "extra_user"
        var githubUrl: String  = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val bgPhoto: ImageView = findViewById(R.id.imageView)
        val civAvatar: ImageView = findViewById(R.id.civ_avatar)
        val ivShare: ImageView = findViewById(R.id.iv_share)
        val tvName: TextView = findViewById(R.id.tv_name)
        val tvUsername: TextView = findViewById(R.id.tv_username)
        val tvLocationUser: TextView = findViewById(R.id.tv_location)
        val tvCompanyUser: TextView = findViewById(R.id.tv_company)
        val tvFollowing: TextView = findViewById(R.id.tv_following)
        val tvFollowers: TextView = findViewById(R.id.tv_followers)
        val tvRepository: TextView = findViewById(R.id.tv_repository)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        githubUrl = user.githubUrl
        Glide.with(this@DetailUserActivity)
            .load(this@DetailUserActivity.resources.getIdentifier(user.avatar,"drawable",this@DetailUserActivity.packageName))
            .circleCrop()
            .into(civAvatar)
        Glide.with(this@DetailUserActivity)
            .load(this@DetailUserActivity.resources.getIdentifier(user.avatar,"drawable",this@DetailUserActivity.packageName))
            .into(bgPhoto)

        tvName.text = user.name
        tvUsername.text = user.username
        tvLocationUser.text = user.location
        tvCompanyUser.text = user.company
        tvFollowing.text = user.following
        tvFollowers.text = user.followers
        tvRepository.text = user.repository

        ivShare.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.iv_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, githubUrl)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

            }
        }
    }
}