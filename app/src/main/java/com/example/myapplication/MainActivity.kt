package com.example.myapplication

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.*
import com.example.myapplication.adapter.ListUserAdapter
import com.example.myapplication.detail.DetailUserActivity
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var rvUsers: RecyclerView
    private val list = ArrayList<User>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvUsers = findViewById(R.id.rv_users);
        rvUsers.setHasFixedSize(true)
        list.addAll(listUsers)
        showRecyclerUser()
    }

    private val listUsers: ArrayList<User>
    get() {
        val dataUsername = resources.getStringArray(R.array.username)
        val dataName = resources.getStringArray(R.array.name)
        val dataLocation = resources.getStringArray(R.array.location)
        val dataRepository = resources.getStringArray(R.array.repository)
        val dataCompany = resources.getStringArray(R.array.company)
        val dataFollowers = resources.getStringArray(R.array.followers)
        val dataFollowing = resources.getStringArray(R.array.following)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataGithubUrl = resources.getStringArray(R.array.data_github_url)

        val listUser = ArrayList<User>()
        for (i in dataUsername.indices) {
            val user = User(dataUsername[i], dataName[i], dataLocation[i], dataRepository[i],
                    dataCompany[i], dataFollowers[i], dataFollowing[i], dataPhoto[i], dataGithubUrl[i])
            listUser.add(user)
        }
        return listUser
    }


    private fun showRecyclerUser() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvUsers.layoutManager = GridLayoutManager(this, 2)

        } else {
            rvUsers.layoutManager = LinearLayoutManager(this)
            rvUsers.itemAnimator = DefaultItemAnimator()
            rvUsers.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        }
        val listUserAdapter = ListUserAdapter(list)
        rvUsers.adapter = listUserAdapter
        listUserAdapter.notifyDataSetChanged()
        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }

        })
    }

    private fun showSelectedUser(data: User) {

        val moveWithObjectIntent = Intent(this@MainActivity, DetailUserActivity::class.java)
        moveWithObjectIntent.putExtra(DetailUserActivity.EXTRA_USER, data)
        startActivity(moveWithObjectIntent)
    }


}