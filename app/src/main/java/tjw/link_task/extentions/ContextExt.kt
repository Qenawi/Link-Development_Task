package tjw.link_task.extentions

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import tjw.link_task.domain.base.BaseActivity

val Context.networkInfo: NetworkInfo?
    @SuppressLint("MissingPermission")
    get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

inline fun <reified T : Activity> mLaunchActivity(bnd: Bundle? = null, contex: Context?) {
    contex?.let { ctx ->
        val intent = Intent(ctx, T::class.java)
        bnd?.run { intent.putExtras(bnd) }
        ctx.startActivity(intent)
    }
}

inline fun <reified T : Activity> mLaunchActivityForResult(contex: Context?, result: Int) {
    contex?.let { ctx ->
        if (ctx is Activity) {
            ctx.startActivityForResult(Intent(ctx, T::class.java), result)
        } else if (ctx is Fragment) {
            ctx.startActivityForResult(Intent(ctx, T::class.java), result)
        }
    }
}

inline fun <reified T : Fragment> mAddFragment(addToBackStack: Boolean?=null,activity: Activity?, newInstance: () -> T) {
    activity?.let { act ->
        (act as BaseActivity<*>).addFragment(newInstance(),addToBackStack)
    }

}


inline fun <reified T : Application> AndroidViewModel.getString(int: Int): String =
    this.getApplication<T>().getString(int)


/*
@SuppressLint("HardwareIds")
fun  Context?.getDeviceUniqueFootPrint():String=try {
    Settings.Secure.getString(this?.contentResolver, Settings.Secure.ANDROID_ID)
}catch (e:Exception){ FirebaseInstanceId.getInstance().id}
*/
private var mainToast:Toast?=null
fun Context?.toast(st:String?)
        =
    this?.let {ctx->
        mainToast?.cancel()
        mainToast=Toast.makeText(ctx,st, Toast.LENGTH_SHORT)
        mainToast?.show()
    }
