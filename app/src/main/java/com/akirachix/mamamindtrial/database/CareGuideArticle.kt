

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "care_guide_articles")
data class CareGuideArticle(
    @PrimaryKey val id: Int,
    val category: String,
    val title: String,
    val image: Int, // Use Int for drawable resource
    val subtitle: String,
    val content: String,
    val author: String
) : Parcelable


