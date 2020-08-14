package cvdevelopers.takehome.ui.userlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import cvdevelopers.githubstalker.R
import cvdevelopers.githubstalker.databinding.UserListItemBinding
import cvdevelopers.takehome.models.Client


class UserListAdapter (val itemListener: ItemListener) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private lateinit var userList:List<Client>
    private var position: Int = 0
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: UserListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_list_item, parent, false)
        this.context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
        this.position = position
        holder.itemView.setOnClickListener {
            //itemListener.onItemClick(userList.get(position))
            itemListener.onItemClick(userList.get(position).email)
        }
    }

    override fun getItemCount(): Int {
        return if(::userList.isInitialized) userList.size else 0
    }

    fun updateUserList(newList:List<Client>){
        this.userList = newList
        notifyDataSetChanged()
    }

    fun getPosition(): Int {
        return this.position
    }

    class ViewHolder(private val binding: UserListItemBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = UserViewModel()

        fun bind(user:Client){
            viewModel.bind(user)
            binding.itemViewModel = viewModel

          //  Picasso.with(binding.imgUserProfile.context).load(binding.itemViewModel!!.getImage().value).transform(CircleTransformation()).into(binding.imgUserProfile)
          //  ImageLoader(binding.itemViewModel!!.getApplicationContext() as Application  ).loadCircularImage(binding.itemViewModel!!.getImage().value!!,binding.imgUserProfile)
        }
    }

    interface ItemListener {
        fun onItemClick(monster: String)
    }
}