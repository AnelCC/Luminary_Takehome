package cvdevelopers.takehome.ui.userlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cvdevelopers.takehome.models.Client
import javax.inject.Inject


class UserViewModel @Inject constructor() : ViewModel() {

    private val name = MutableLiveData<String>()
    private val imageUrl = MutableLiveData<String>()

    init {
        imageUrl.value = ""
    }

    fun bind(user:  Client){
        name.value = user.name.first+" "+user.name.last
        imageUrl.value = if (user.picture?.thumbnail != null) user.picture.thumbnail else  ""
    }

    fun getFullName():MutableLiveData<String>{
        return name
    }

    fun getImage():MutableLiveData<String>{
        Log.d("UserViewModel", imageUrl.value)
        return imageUrl
    }
}