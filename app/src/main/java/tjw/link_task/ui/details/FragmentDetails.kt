package tjw.link_task.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_detail.*
import tjw.link_task.R
import tjw.link_task.databinding.FragmentDetailBinding
import tjw.link_task.domain.base.BaseActivity
import tjw.link_task.domain.base.BaseFragment
import tjw.link_task.domain.data.Article
import tjw.link_task.extentions.delay150
import kotlin.math.min


class FragmentDetails : BaseFragment() {
    override fun layoutId()= R.layout.fragment_detail
    override fun view_life_cycle_owner()=viewLifecycleOwner
    private  var data:Article?=null
    private fun readBundle(){data=arguments?.getParcelable(detailsKey)}
    companion object{
        const val detailsKey="FragmentDetailsKey"
        fun newInstance(data:Article?):FragmentDetails
        {
            val args = Bundle().apply {this.putParcelable(detailsKey,data)}
            val fragment = FragmentDetails()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readBundle()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        super.onCreateView(inflater, container, savedInstanceState)
        (binding as FragmentDetailBinding).data=data
        return  binding.root
    }
    override fun onResume()
    {
        super.onResume()
        (activity as BaseActivity<*>?)?.toolbarTitle?.postValue(data?.title?.substring(0, min(data?.title?.length?:0,20))?:getString(R.string.details))

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        materialButton.setOnClickListener {
          delay150{
            navigate()
          }
        }
    }
    private fun navigate()
    {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data?.url)))
    }   catch (e:Exception){}
    }
}