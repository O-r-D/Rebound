package com.ord.rebound.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.javafaker.Faker
import com.ord.rebound.R
import com.ord.rebound.data.model.Message
import com.ord.rebound.data.model.User
import com.ord.rebound.ui.chat.ChatActivity
import com.ord.rebound.util.Constants
import com.ord.rebound.util.Injector
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity(), UsersRVAdapter.OnItemClickListener {

    private var users = ArrayList<User>()
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel = Injector.provideUserVMFactory(this).let {
            ViewModelProvider(viewModelStore, it).get(UserViewModel::class.java)
        }

    }

    override fun onResume() {
        super.onResume()
        getUsers()
    }

    override fun onStop() {
        super.onStop()

        viewModel.insertUsers(users)
    }

    private fun updateUI() {
        users.sortByDescending { it.lastMessage.date }
        loadingPanel.visibility = View.GONE;
        rv_user_chat_boxes.adapter = UsersRVAdapter(users, this)
        rv_user_chat_boxes.layoutManager = LinearLayoutManager(this)
    }

    private fun getUsers() {

        viewModel.getUsers().observe(this, Observer {
            users = it as ArrayList<User>
            if (users.isNullOrEmpty())
                Runnable { fillUsers() }.run()
            updateUI()
        })


    }

    private fun fillUsers() {
        for (i in 1..200) {
            users.add(
                UUID.randomUUID().let {
                    User(
                        it.toString(),
                        Faker().funnyName().name(),
                        Message(UUID.randomUUID().toString(), "", null, it.toString())
                    )
                }
            )
        }
        updateUI()
    }

    override fun setOnItemClick(user: User) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra(Constants.HOME_TO_CHAT_TAG, user)
        startActivity(intent)
    }
}