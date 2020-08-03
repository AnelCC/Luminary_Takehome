package cvdevelopers.takehome.models

import androidx.annotation.Nullable

data class Picture(
    @Nullable
    val large: String?,
    @Nullable
    val medium: String?,
    @Nullable
    val thumbnail: String?
)