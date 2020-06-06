package tjw.link_task.extentions

import tjw.go_plus_meeting.domain.network.Failure



enum class MeetingPriority{High,Middle,Low}
enum class RecyclerViewStatus { Loading, NetWorkFailure, ResponseFail, EmptyResponse ,Success,UnknownFail }
enum class Navigation { HomeMap, Notification, Profile }


fun String?.toMeetingPriority():MeetingPriority?
= when(this)
    {
        MeetingPriority.High.name -> MeetingPriority.High
        MeetingPriority.Low.name -> MeetingPriority.Low
        MeetingPriority.Middle.name -> MeetingPriority.Middle
        else ->null
    }

fun ArrayList<*>?.toRecyclerViewStatus()
{
 if (this.isNullOrEmpty())RecyclerViewStatus.EmptyResponse
 else RecyclerViewStatus.Success
}
fun Failure.toRecyclerViewStatus():RecyclerViewStatus
= when (this) {
        is Failure.NetworkConnection -> RecyclerViewStatus.NetWorkFailure
        is Failure.RequestError , is Failure.ServerError -> RecyclerViewStatus.ResponseFail
        else->RecyclerViewStatus.ResponseFail
    }