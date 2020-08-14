package cvdevelopers.takehome.models.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cvdevelopers.takehome.models.Client

@Dao
interface UserDao {
    @Query("SELECT * from user")
    fun getAll(): List<Client>

    @Insert
    suspend  fun insertUser(user: Client)

    @Insert
    fun insertUsers(users: List<Client>)

    @Query("DELETE from user")
    fun deleteAll()

    @Query("SELECT * FROM user WHERE email LIKE :expectedEmail")
    fun getUser(expectedEmail: String): Client
}