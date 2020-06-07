package tjw.link_task.domain.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import tjw.link_task.R
import tjw.link_task.extentions.Navigation

@Parcelize
data class MenuItem(
    var type: Navigation=Navigation.NONE,
    var name: Int,
    var img: Int
) : Parcelable

fun getMenu() = arrayListOf<MenuItem>(
    MenuItem(name = R.string.explore, img = R.drawable.explore,type = Navigation.Explore),
    MenuItem(name = R.string.live_chat, img = R.drawable.live,type = Navigation.LiveChat),
    MenuItem(name = R.string.gallary, img = R.drawable.gallery,type = Navigation.Gallary),
    MenuItem(name = R.string.with_list, img = R.drawable.wishlist,type = Navigation.WishList),
    MenuItem(name = R.string.menu_magazine, img = R.drawable.magazine,type = Navigation.Magazine)
)
