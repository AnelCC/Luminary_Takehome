package cvdevelopers.takehome.ui.userlist

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cvdevelopers.githubstalker.R
import cvdevelopers.githubstalker.databinding.FragmentUsersListBinding
import cvdevelopers.takehome.ui.base.ViewModelFactory
import cvdevelopers.takehome.utils.preferences.PreferencesHelper

class UserListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentUsersListBinding
    private lateinit var viewModel: UserListViewModel
    private lateinit var swipeLayout: SwipeRefreshLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users_list, container, false)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(requireActivity(), ViewModelFactory(activity as AppCompatActivity)).get(UserListViewModel::class.java)
        binding.setViewModel(viewModel);

        swipeLayout = binding.swipeLayout
        swipeLayout.setOnRefreshListener {
            viewModel.refreshDataFromWeb()
        }

        viewModel.userData.observe(this, Observer {
            swipeLayout.isRefreshing = false
            val position: Int? = context?.let { PreferencesHelper.getItem(it) }
            if (position!!.toInt() > 0){
                recyclerView.scrollToPosition(position)
            }
        })

        return  binding.root
    }

    override fun onPause() {
        super.onPause()
        context?.let { PreferencesHelper.setItem(it, binding.viewModel!!.userListAdapter.getPosition()) }
    }
}
