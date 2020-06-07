package tjw.link_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tjw.link_task.databinding.ActivityWithToolBarBinding
import tjw.link_task.domain.base.BaseActivity
import tjw.link_task.domain.base.BaseFragment
import tjw.link_task.ui.home.FragmentHome

class MainActivity: BaseActivity<ActivityWithToolBarBinding>() {

    override fun layoutId()=R.layout.activity_with_tool_bar

    override fun fragment()=FragmentHome.newInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.appToolBar.callback = callBack
        binding.appToolBar.title = toolbarTitle
        addFragment(fragment(),true)
    }
}
