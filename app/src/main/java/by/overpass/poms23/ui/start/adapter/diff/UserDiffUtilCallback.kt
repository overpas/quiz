package by.overpass.poms23.ui.start.adapter.diff

import android.support.v7.util.DiffUtil
import by.overpass.poms23.data.model.pojo.User

class UserDiffUtilCallback(
        private val oldList: List<User>,
        private val newList: List<User>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].name == newList[newItemPosition].name

}