package tjw.link_task.domain.base
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import tjw.link_task.extentions.cHideSoftKeyboard
import tjw.link_task.extentions.inTransaction
import tjw.link_task.extentions.observe

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    abstract fun layoutId(): Int
    lateinit var binding: B
    private val fragHolderId = 0
    val toolbarTitle = MutableLiveData<String>()
    val callBack = MutableLiveData<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId())
        binding.lifecycleOwner = this
        binding.root.layoutDirection = View.LAYOUT_DIRECTION_LOCALE
        this.observe(callBack) { click -> click?.let { onNavigation() } }
        cHideSoftKeyboard()

    }

    fun addFragment(fragment: Fragment,addToStack: Boolean?=null) {
        if(addToStack==true)
            addFragmentHelperWithBackStack(fragment)
        else addFragmentHelper(fragment)
    }
    private fun addFragmentHelperWithBackStack(fragment: Fragment) = supportFragmentManager.inTransaction {
        replace(
            fragHolderId, fragment
        )
    }
    private fun addFragmentHelper(fragment: Fragment) = supportFragmentManager.inTransaction {
        replace(
            fragHolderId, fragment
        )

    }

    abstract fun fragment(): BaseFragment
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            (supportFragmentManager.findFragmentById(fragHolderId) as BaseFragment).onBackPressed()
            super.onBackPressed()
        } else {
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        supportFragmentManager.fragments[supportFragmentManager.fragments.size - 1]?.onActivityResult(
            requestCode,
            resultCode,
            data
        )
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_HOME) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
    private fun onNavigation() {
        onBackPressed()
    }


}