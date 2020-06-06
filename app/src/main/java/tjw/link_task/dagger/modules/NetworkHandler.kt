package tjw.link_task.dagger.modules

import android.content.Context
import tjw.link_task.extentions.networkInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler @Inject constructor(private val context: Context)
{ val isConnected get() = context.networkInfo?.isConnected }