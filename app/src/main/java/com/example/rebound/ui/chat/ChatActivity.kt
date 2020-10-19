package com.example.rebound.ui.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rebound.R
import com.example.rebound.data.model.User
import com.example.rebound.util.Constants
import kotlinx.android.synthetic.main.layout_actionbar.*

class ChatActivity : AppCompatActivity() {

    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        if (intent.hasExtra(Constants.HOME_TO_CHAT_TAG))
            user = intent.getParcelableExtra(Constants.HOME_TO_CHAT_TAG)

        updateUI()
    }

    private fun updateUI() {
        tv_appbar.text = if (user != null) user?.username else R.string.app_name.toString()
        iv_appbar.setImageResource(R.drawable.ic_send)
    }
}