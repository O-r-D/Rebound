package com.ord.rebound.ui.chat

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.ord.rebound.R
import com.ord.rebound.data.model.Message
import com.ord.rebound.data.model.User
import com.ord.rebound.util.Constants
import com.ord.rebound.util.Helper
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.layout_actionbar.*
import java.util.*

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

        et_message_input.addTextChangedListener(
            onTextChanged = { charSequence: CharSequence?, start: Int, count: Int, after: Int ->
                if (!charSequence.isNullOrBlank() && charSequence.length < 46)
                    charSequence.replace(Regex(" "), "").length.toFloat()
                        .let { iv_send.animate().rotation(it) }
                else if (charSequence.isNullOrBlank())
                    iv_send.animate().rotation(0f)
            })

        iv_send.setOnClickListener {
            if (et_message_input.text.isNullOrBlank())
                return@setOnClickListener
            val message = Message(1, et_message_input.text.toString(), Date())
            user!!.lastMessage = message

            TextView(this).apply {
                background = ContextCompat.getDrawable(context, R.drawable.chat_message_box_sender)

                val padding = Helper.dpToPixels(18)
                setPadding(padding, padding, padding, padding)

                setTextColor(ContextCompat.getColor(context, R.color.black))
                text = message.content

                val layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(0, Helper.dpToPixels(10), Helper.dpToPixels(6), 0)
                layoutParams.gravity = Gravity.END
                layoutParams.weight = 6f
                setLayoutParams(layoutParams)


                ll_message_boxes.addView(this)

                et_message_input.setText("")

                sv_chat.post { sv_chat.fullScroll(ScrollView.FOCUS_DOWN) }
                rebound(message.content)
            }
        }

    }

    private fun rebound(content: String?) {
        Handler(mainLooper).postDelayed({
            with(TextView(this), {
                background =
                    ContextCompat.getDrawable(context, R.drawable.chat_message_box_receiver)

                val padding = Helper.dpToPixels(18)
                setPadding(padding, padding, padding, padding)

                setTextColor(ContextCompat.getColor(context, R.color.black))
                text = content

                val layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(Helper.dpToPixels(6), Helper.dpToPixels(10), 0, 0)
                layoutParams.gravity = Gravity.START
                layoutParams.weight = 6f
                setLayoutParams(layoutParams)


                ll_message_boxes.addView(this)
                sv_chat.post { sv_chat.fullScroll(ScrollView.FOCUS_DOWN) }
            })
        }, 700)
    }
}