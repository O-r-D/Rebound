package com.ord.rebound.ui.chat

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.ord.rebound.R
import com.ord.rebound.data.model.Chat
import com.ord.rebound.data.model.Message
import com.ord.rebound.data.model.User
import com.ord.rebound.ui.home.UserViewModel
import com.ord.rebound.util.Constants
import com.ord.rebound.util.Helper
import com.ord.rebound.util.Injector
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.layout_actionbar.*
import java.util.*
import kotlin.collections.ArrayList

class ChatActivity : AppCompatActivity() {

    private var user: User? = null
    private var chat: Chat? = null
    private lateinit var chatViewModel: ChatViewModel
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        if (intent.hasExtra(Constants.HOME_TO_CHAT_TAG))
            user = intent.getParcelableExtra(Constants.HOME_TO_CHAT_TAG)

        chatViewModel = ViewModelProvider(
            viewModelStore,
            Injector.provideChatVMFactory(this)
        ).get(ChatViewModel::class.java)

        userViewModel = ViewModelProvider(
            viewModelStore,
            Injector.provideUserVMFactory(this)
        ).get(UserViewModel::class.java)


        checkChat()
        updateUI()
    }

    private fun checkChat() {
        if (user == null)
            Toast.makeText(this, "USER NOT FOUND", Toast.LENGTH_SHORT).show().also { finish() }

        chat = chatViewModel.getChat(Constants.DEFAULT_USER_ID, user!!.uid)


        if (chat == null || chat?.messages.isNullOrEmpty()) {
            chat = Chat(
                UUID.randomUUID().toString(),
                Constants.DEFAULT_USER_ID,
                user!!.uid,
                ArrayList()
            )
            return
        }

        for (message in chat!!.messages)

            when (message.userUID) {
                Constants.DEFAULT_USER_ID -> {
                    showSenderMessage(message.content)
                }
                user!!.uid -> {
                    showReceiverMessage(message.content)
                }
            }
    }

    private fun updateUI() {
        tv_appbar.text = if (user != null) user?.username else R.string.app_name.toString()

        iv_appbar.apply {
            setImageResource(R.drawable.ic_back)
            setOnClickListener { finish() }
        }

        et_message_input.addTextChangedListener(
            onTextChanged = { charSequence: CharSequence?, _: Int, _: Int, _: Int ->
                if (!charSequence.isNullOrBlank() && charSequence.length < 46)
                    charSequence.replace(Regex(" "), "").length.toFloat()
                        .let { iv_send.animate().rotation(it) }
                else if (charSequence.isNullOrBlank())
                    iv_send.animate().rotation(0f)
            })

        iv_send.setOnClickListener {
            if (et_message_input.text.isNullOrBlank())
                return@setOnClickListener



            chat?.messages?.add(                    // Add sent message to chat
                Message(
                    UUID.randomUUID().toString(),
                    et_message_input.text.toString(),
                    Date(),
                    Constants.DEFAULT_USER_ID
                )
            )


            user!!.lastMessage = Message(
                UUID.randomUUID().toString(),
                et_message_input.text.toString(),
                Date(),
                user!!.uid
            ).also {
                showSenderMessage(it.content)       // Show sender message

                Handler(mainLooper).postDelayed({
                    chat?.messages?.add(it)         // Add rebound message as last message of user
                    showReceiverMessage(it.content) // Show receiver message
                }, 500)

                Handler(mainLooper).postDelayed({
                    chat?.messages?.add(it)         // Add rebound message as last message of user
                    showReceiverMessage(it.content) // Show receiver message
                }, 1000)

            }

        }
    }

    private fun showSenderMessage(content: String?) =
        with(TextView(this), {
            background = ContextCompat.getDrawable(context, R.drawable.chat_message_box_sender)

            val padding = Helper.dpToPixels(18)
            setPadding(padding, padding, padding, padding)

            setTextColor(ContextCompat.getColor(context, R.color.black))
            text = content

            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, Helper.dpToPixels(10), Helper.dpToPixels(6), 0)
                gravity = Gravity.END
                weight = 6f
            }.let { layoutParams = it }


            ll_message_boxes.addView(this)

            et_message_input.setText("")

            sv_chat.post { sv_chat.fullScroll(ScrollView.FOCUS_DOWN) }
        })

    private fun showReceiverMessage(content: String?) =
        with(TextView(this), {
            background =
                ContextCompat.getDrawable(context, R.drawable.chat_message_box_receiver)

            val padding = Helper.dpToPixels(18)
            setPadding(padding, padding, padding, padding)

            setTextColor(ContextCompat.getColor(context, R.color.black))
            text = content

            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(Helper.dpToPixels(6), Helper.dpToPixels(10), 0, 0)
                gravity = Gravity.START
                weight = 6f
            }.let { layoutParams = it }

            ll_message_boxes.addView(this)
            sv_chat.post { sv_chat.fullScroll(ScrollView.FOCUS_DOWN) }
        })

    override fun onStop() {
        super.onStop()
        chat?.let { chatViewModel.insertChat(it) }
        userViewModel.insertUser(user!!)
    }
}