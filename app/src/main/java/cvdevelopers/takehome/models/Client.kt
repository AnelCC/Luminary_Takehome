package cvdevelopers.takehome.models

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.*

@Entity(tableName = "user")
data class Client(
    @NonNull @field:PrimaryKey
    val email: String,
    @Nullable
    @Embedded
    val id: Id?,
    @Nullable
    @Embedded
    val name: Name,
    @Nullable
    @Embedded
    val picture: Picture?
)