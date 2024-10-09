import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CareGuideViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val repository: CareGuideRepository

    init {
        val dao = CareGuideDatabase.getDatabase(context).careGuideArticleDao()
        repository = CareGuideRepository(dao)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CareGuideViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CareGuideViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
