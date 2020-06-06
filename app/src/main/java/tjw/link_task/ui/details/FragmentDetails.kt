package tjw.link_task.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import tjw.link_task.R
import tjw.link_task.databinding.FragmentDetailBinding
import tjw.link_task.domain.base.BaseFragment
import tjw.link_task.domain.data.Article

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
    ): View? {
        (binding as FragmentDetailBinding).data=data
        return  binding.root
    }
    override fun onResume()
    {
        super.onResume()
    }
    private fun navigate(){}
}