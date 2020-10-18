package com.example.rebound.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rebound.R
import com.example.rebound.data.model.Message
import com.example.rebound.data.model.User
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity(), UsersRVAdapter.OnItemClickListener {

    private val users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (users.isEmpty())
            fillUsers()

        rv_user_chat_boxes.adapter = UsersRVAdapter(users, this)
        rv_user_chat_boxes.layoutManager = LinearLayoutManager(this)
    }

    private fun fillUsers() {
        for (i in 1..18) {
            users.add(
                User(
                    i.toLong(),
                    "username$i",
                    Message(
                        i.toLong(),
                        "This message is numbered $i",
                        Date(2020 - 1900, 9, i),
                        i.toLong()
                    )
                )
            )
        }
        users.sortByDescending { it.lastMessage.date }
    }

    override fun setOnItemClick(user: User) {
        Toast.makeText(this, "You clicked ${user.username}", Toast.LENGTH_SHORT).show()
    }
}