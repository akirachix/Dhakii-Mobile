
import kotlinx.coroutines.flow.Flow

class CareGuideRepository(private val careGuideArticleDao: CareGuideArticleDao) {

    fun getArticlesByCategory(category: String): Flow<List<CareGuideArticle>> {
        return careGuideArticleDao.getArticlesByCategory(category)
    }

}

