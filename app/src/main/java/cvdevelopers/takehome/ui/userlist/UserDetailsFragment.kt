package cvdevelopers.takehome.ui.userlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation

import cvdevelopers.githubstalker.R
import cvdevelopers.githubstalker.databinding.FragmentUserDetailsBinding
import cvdevelopers.takehome.ui.base.ViewModelFactory

/**
 * A simple [Fragment] subclass.
 */

const val LOG_TAG = "UserDetailsFragment"

class UserDetailsFragment : Fragment() {
    private lateinit var viewModel: UserListViewModel
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host)
        viewModel = ViewModelProvider(requireActivity()).get(UserListViewModel::class.java)
        Log.i(LOG_TAG, "DetailFragment viewModel ${viewModel.selectedUser.value}")


        val binding = FragmentUserDetailsBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return  binding.root
    }

}
