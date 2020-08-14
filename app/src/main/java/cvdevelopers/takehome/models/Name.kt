package cvdevelopers.takehome.models

import androidx.annotation.Nullable

data class Name(
    @Nullable
    val first: String,
    @Nullable
    val last: String,
    @Nullable
    val title: String
)