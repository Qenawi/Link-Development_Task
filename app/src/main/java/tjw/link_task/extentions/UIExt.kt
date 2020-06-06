package tjw.link_task.extentions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.*
import tjw.link_task.R
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun Activity.cHideSoftKeyboard() {
    val focusedView = currentFocus
    focusedView?.let { view ->
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
@BindingAdapter("loadImageFromUrl", "load_as_cover", requireAll = false)
fun cLoadImageFromUrl(view: ImageView, url: String?, cover: Boolean? = false)
{
    val requestOptions = RequestOptions().apply {
        if (cover == true) {
            //placeholder(R.color.carbon_white)
            error(R.color.colorAccent).placeholder(R.color.colorAccent)
        } else {
            //  this.placeholder(R.drawable.user)
            error(R.color.colorPrimary).placeholder(R.color.colorPrimary)
        }
    }
    Glide.with(view.context).applyDefaultRequestOptions(requestOptions).load("$url")
        .into(view)
}

fun View.cVisible(boolean: Boolean?) {
    boolean?.let {
        if (boolean) this.visibility = View.VISIBLE
        else this.visibility = View.INVISIBLE
    }
}

fun View.cEnable(boolean: Boolean?) =
    when (boolean) {
        true -> {
            this.isEnabled = true
        }
        false -> {
            this.isEnabled = false
        }
        null -> {
        }
    }

fun EditText.cTextExtractor(): String {
    return if (this.text.isNullOrEmpty()) ""
    else this.text.toString()
}
fun Spinner.cPopulate(arrayListID: Int) {
    this.context?.let { ctx ->
        val mArray = ctx.resources.getStringArray(arrayListID)
        val adb = ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, mArray)
        this.adapter = adb
    }
}
@BindingAdapter("lock_view")
fun View.mLock(load: Boolean?) = load?.let { bool -> this.mEnable(!bool) }
fun View.mEnable(boolean: Boolean?) =
    when (boolean) {
        true -> {
            this.isEnabled = true
        }
        false -> {
            this.isEnabled = false
        }
        null -> {
        }
    }

@BindingAdapter("progress_view")
fun View.mProgress(boolean: Boolean?) {
    cVisible(boolean)
}

@BindingAdapter(value =  ["safe_text","decimal_format"],requireAll = false)
fun TextView.mText(any: Any?, boolean: Boolean?) = any?.let {
        sText ->
    text = if(boolean==true)(sText as Int).safeDecimalFormat() else sText.toString()
}
fun Int?.safeDecimalFormat():String{
    val df = DecimalFormat("#,###")
    df.roundingMode = RoundingMode.CEILING
    return  this?.let {num->

        val ret=  df.format(num)
        ret
    }?: ""
}
fun delay250(block: (Any) -> Unit = {}) {
    GlobalScope.launch {
        delay(250)
        withContext(Dispatchers.Main)
        { block(Any()) }
    }
}


@SuppressLint("SimpleDateFormat")
@BindingAdapter("milliSecondData")
fun TextView.msData(msDate: String?)
        = msDate?.let {date->
    this.text= SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(date.toLong())
}
@BindingAdapter("go_back", "title", requireAll = false)
fun View.handle(data: MutableLiveData<Boolean>?, name: MutableLiveData<String>?) {
    when (name?.value) {
        getString_(R.string.app_name) ,getString_(R.string.app_name),getString_(R.string.app_name)-> this.cVisible(false)
        else -> this.cVisible(true)
    }
    this.setOnClickListener { data?.postValue(true) }
}
fun View.getString_(resource_id: Int): String = try {
    this.context.getString(resource_id)
} catch (e: Exception) {
    "-"
}