package com.ord.rebound.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ord.rebound.R
import com.ord.rebound.data.model.User
import com.ord.rebound.util.Helper
import kotlinx.android.synthetic.main.item_user_chat_box.view.*
import java.util.*

class UsersRVAdapter(
    private val users: ArrayList<User>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<UsersRVAdapter.VH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.item_user_chat_box, parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(users[position], onItemClickListener)
    }

    override fun getItemCount() = users.size


    ///////////////////////////////////////////////////////////////////////////
    // VIEW HOLDER CLASS
    ///////////////////////////////////////////////////////////////////////////

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User, onItemClickListener: OnItemClickListener) {

            itemView.apply {
                tv_username.text = user.username

                tv_date.text = Helper.prettyPrintDate(Date(), user.lastMessage.date)
                tv_message_content.text = user.lastMessage.content
                iv_profile_image.setImageResource(R.mipmap.ic_launcher)

                setOnClickListener { onItemClickListener.setOnItemClick(user) }
            }

        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // On Item Click Listener
    ///////////////////////////////////////////////////////////////////////////

    interface OnItemClickListener {
        fun setOnItemClick(user: User)
    }
}