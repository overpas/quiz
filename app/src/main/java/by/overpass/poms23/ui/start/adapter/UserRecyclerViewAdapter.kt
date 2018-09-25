package by.overpass.poms23.ui.start.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import by.overpass.poms23.data.model.pojo.User
import by.overpass.poms23.ui.start.adapter.diff.UserDiffUtilCallback

class UserRecyclerViewAdapter : RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder>() {

    var users: List<User> = emptyList()
        set(value) {
            val diffUtilCallback = UserDiffUtilCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        android.R.layout.simple_list_item_2,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.user = users[position]
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var user: User? = null
            set(value) {
                itemView.findViewById<TextView>(android.R.id.text1).text = value?.name
                itemView.findViewById<TextView>(android.R.id.text2).text = value?.bestScore.toString()
            }
    }
}