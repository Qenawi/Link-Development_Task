package tjw.link_task.domain.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import tjw.link_task.R

@Parcelize
data class MenuItem(
    var selected: Boolean? = false,
    var name: Int,
    var img: Int
) : Parcelable

fun getMenu() = arrayListOf<MenuItem>(
    MenuItem(name = R.string.explore, img = R.drawable.explore),
    MenuItem(name = R.string.live_chat, img = R.drawable.live),
    MenuItem(name = R.string.gallary, img = R.drawable.gallery),
    MenuItem(name = R.string.with_list, img = R.drawable.wishlist),
    MenuItem(name = R.string.menu_magazine, img = R.drawable.magazine)
)
