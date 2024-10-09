package com.akirachix.mamamindtrial

import CareGuideArticle
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.mamamindtrial.databinding.FragmentCareGuideListBinding
import com.akirachix.mamamindtrial.ui.ArticleDetailActivity

class CareGuideListFragment : Fragment() {

    private var _binding: FragmentCareGuideListBinding? = null
    private val binding get() = _binding!!

    private lateinit var category: String

    companion object {
        private const val ARG_CATEGORY = "category"

        fun newInstance(category: String): CareGuideListFragment {
            return CareGuideListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY, category)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(ARG_CATEGORY) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCareGuideListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        // Load articles manually based on the category
        val articles: List<CareGuideArticle> = loadArticles()

        val adapter = CareGuideAdapter(articles) { article ->
            val intent = Intent(requireContext(), ArticleDetailActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun loadArticles(): List<CareGuideArticle> {
        // Manually created articles based on the category
        return when (category) {
            "Mental Health" -> listOf(
                CareGuideArticle(
                    id = 1,
                    category = "Mental Health",
                    title = "Understanding Postpartum Mental Breakdown",
                    image = R.drawable.mental_health_ig, // Use actual drawable resource ID
                    subtitle = "How Postpartum Can Affect a Mother’s Mental Health",
                    content = " <h4>Mothers Need Assistance to Go Through This</h4>\n" +
                            "\n" +
                            "\n" +
                            "<p>After childbirth, many mothers experience significant emotional and mental challenges. This period, known as the postpartum period, can lead to mental breakdowns if proper care and attention are not given. Postpartum depression is one of the most common mental health issues affecting mothers. According to studies, about 10-15% of women experience postpartum depression in the first year following childbirth. This is a crucial time for mothers as they transition into their new roles, and many feel unprepared for the emotional toll. The constant demands of newborn care, combined with the sudden drop in hormones, can make new mothers feel overwhelmed, sad, or anxious.</p>\n" +
                            "\n" +
                            "<p>During this time, mothers may feel isolated, anxious, and uncertain about their ability to handle their new responsibilities. Without adequate support, these feelings can spiral into a full-blown mental breakdown. It is essential that mothers receive help early and that society creates an environment where their mental health is prioritized. Postpartum mental health care is not just about treating depression but ensuring that mothers have the tools they need to handle the stress that comes with childbirth.</p>\n" +
                            "\n" +
                            "<h4>Best Practices to Show Mothers We Are With Them</h4>\n" +
                            "\n" +
                            "<p>To prevent a mental breakdown, mothers are encouraged to seek professional help early, establish a strong support system, and engage in self-care practices such as adequate sleep, proper nutrition, and gentle physical activities. Understanding these changes and talking to loved ones can provide emotional relief. Early intervention is key in managing postpartum depression and preventing mental breakdowns. Moreover, creating a safe space for mothers to express their emotions without fear of judgment is critical. Communities should promote awareness of postpartum mental health to reduce stigma and ensure that mothers feel supported throughout their journey.</p>\n" +
                            "\n",
                    author = "Dr. Angela Mwangi"
                ),
                CareGuideArticle(
                    id = 2,
                    category = "Mental Health",
                    title = "Managing the Challenges of Motherhood",
                    image = R.drawable.mental_health_ig2, // Use same or different image
                    subtitle = "Coping with Emotional Strain and Maintaining Mental Health",
                    content = "<h4>The Emotional Strain Mothers Face</h4>\n" +
                            "\n" +
                            "<p>Motherhood is often depicted as a joyous and fulfilling experience, but it comes with a host of challenges that can strain a mother's mental health. Between the sleepless nights, constant feedings, and the unrelenting pressure to be the \"perfect\" mother, it is easy for mothers to feel overwhelmed. Many new mothers face the expectation of juggling their previous responsibilities with the added demands of caring for a newborn. This emotional strain can lead to increased anxiety, feelings of inadequacy, and depression if left unchecked. As mothers take on the role of nurturing their child, they may forget to nurture their own emotional and mental health.</p>\n" +
                            "\n" +
                            "<p>The emotional toll of motherhood is something that society often overlooks. Mothers are expected to be resilient and strong, yet they are also vulnerable to emotional exhaustion. The constant caregiving demands, coupled with the need to maintain other relationships and perhaps return to work, create an immense emotional burden. These challenges require careful management to prevent burnout and ensure a mother's well-being. Coping strategies that involve self-compassion, reaching out for help, and creating a routine can help alleviate the emotional strain that many mothers feel in the early stages of motherhood.</p>\n" +
                            "\n" +
                            "<h4>Practical Tips for Managing Emotional Strain</h4>\n" +
                            "\n" +
                            "<p>Managing the emotional strain of motherhood requires acknowledging that it is okay to ask for help. Mothers should build a strong support system, whether it is through family, friends, or professional resources. They should also create a routine that allows for breaks and self-care moments, even if they are brief. Prioritizing mental health by practicing mindfulness, journaling, or engaging in a hobby can provide mothers with a much-needed mental reset. It is also important for mothers to realize that perfection is not the goal. Parenting is an ongoing learning process, and mistakes are part of the journey.</p>\n" +
                            "\n",
                    author = "Dr. Mary Mutheu"
                ),
                CareGuideArticle(
                    id = 3,
                    category = "Mental Health",
                    title = "Mindfulness And Meditation For Postpartum Mothers",
                    image = R.drawable.mental_health_ig3, // Use actual drawable resource ID
                    subtitle = "Finding Mental Clarity Through Simple Practices",
                    content = "<h4>How Mindfulness Benefits New Mothers</h4>\n" +
                            "\n" +
                            "<p>The postpartum period is a time of significant emotional upheaval, and many mothers struggle with finding a moment of peace amidst the chaos. This is where mindfulness can play a transformative role in helping mothers regain mental clarity and focus. Mindfulness is the practice of being fully present and aware of the current moment, without judgment. For mothers, this means being able to focus on their emotions and thoughts without becoming overwhelmed by them. Meditation, often paired with mindfulness, helps in quieting the mind and reducing the stress and anxiety that often accompany motherhood. These practices provide a calm center amid the storm.</p>\n" +
                            "\n" +
                            "<p>Through mindfulness, mothers can reconnect with their bodies and emotions. It is particularly effective for mothers dealing with postpartum anxiety or depression as it encourages a non-judgmental approach to one’s thoughts and feelings. Practicing mindfulness doesn’t require long hours or complicated rituals. Just a few minutes of deep breathing or sitting in silence can make a noticeable difference in a mother's emotional state. By integrating mindfulness into their daily routines, mothers can develop a healthier relationship with their mental health and manage the stresses of motherhood more effectively.</p>\n" +
                            "\n" +
                            "<h4>Simple Meditation Techniques To Start With</h4>\n" +
                            "\n" +
                            "<p>Mothers who are new to meditation can start with simple breathing exercises. These exercises involve sitting quietly and focusing on each breath as it enters and leaves the body. Guided meditations are also helpful, particularly for beginners, as they provide structure and focus. Over time, mothers may choose to incorporate other mindfulness practices, such as body scanning, mindful walking, or even mindful eating. The key is consistency; even five minutes of meditation a day can have profound benefits for mental health. These practices help reduce stress, increase emotional awareness, and promote a sense of inner peace and balance.</p>\n" +
                            "\n",
                    author = "Dr. Fiona Kamau"
                ),
                CareGuideArticle(
                    id = 4,
                    category = "Mental Health",
                    title = "The Importance Of Sleep For Mental Wellness",
                    image = R.drawable.mental_health_ig4, // Use same or different image
                    subtitle = "Prioritizing Rest to Strengthen Mental Health",
                    content = "<h4>The Connection Between Sleep And Emotional Well-being</h4>\n" +
                            "\n" +
                            "<p>Sleep is one of the most critical, yet often neglected, aspects of mental wellness, especially for new mothers. The sleep disruptions that come with caring for a newborn can have a direct impact on emotional stability. When mothers do not get enough sleep, it affects their ability to manage stress, make decisions, and regulate emotions. Sleep deprivation can exacerbate symptoms of anxiety and depression, which are already common during the postpartum period. Without proper rest, the brain cannot function at its optimal level, leading to irritability, mood swings, and difficulty focusing. This makes it harder for mothers to cope with the demands of motherhood.</p>\n" +
                            "\n" +
                            "<p>The mental health benefits of sleep cannot be overstated. Sleep is when the brain processes emotions, restores energy, and repairs the body. For new mothers, prioritizing sleep is crucial to maintaining emotional balance and preventing burnout. Creating a healthy sleep environment and taking naps when the baby is resting are just a few strategies that can help mothers improve their sleep quality. It may be challenging to get uninterrupted sleep in the early stages of motherhood, but even small adjustments can make a significant difference. Sleep is not a luxury but a necessity for both mental and physical well-being.</p>\n" +
                            "\n" +
                            "<h4>Strategies To Improve Sleep Quality</h4>\n" +
                            "\n" +
                            "<p>Mothers can improve their sleep quality by creating a relaxing bedtime routine. This might involve turning off screens an hour before bed, dimming the lights, and engaging in calming activities such as reading or meditation. Using white noise machines or calming music can also create a peaceful sleep environment. It's also important for mothers to make their sleep environment as comfortable as possible by investing in supportive pillows and soft bedding. Finally, new mothers should try to sleep when their baby sleeps, even if it means taking short naps throughout the day. These strategies promote better sleep and, in turn, better mental health.</p>\n" +
                            "\n",
                    author = "Dr. Beatrice Odongo"
                )
            )

            "Nutrition" -> listOf(
                CareGuideArticle(
                    id = 5,
                    category = "Nutrition",
                    title = "Nutrition Tips for New Mothers",
                    image = R.drawable.nutrition_ig2, // Use same or different image
                    subtitle = "Maintaining Energy and Milk Supply Through Proper Diet",
                    content = "<h4>The Role of Hydration in Breastfeeding</h4>\n" +
                            "\n" +
                            "<p>Breastfeeding mothers have higher nutritional needs than those who are not breastfeeding, primarily because they are providing nourishment for both themselves and their babies. Hydration plays a crucial role in maintaining milk supply and energy levels. Water makes up a large portion of breast milk, so staying properly hydrated is essential for producing enough milk. Mothers are encouraged to drink plenty of water throughout the day, especially before and after breastfeeding sessions. Along with water, hydrating foods such as fruits and vegetables can contribute to overall hydration and help maintain milk production.</p>\n" +
                            "\n" +
                            "<p>Inadequate hydration can lead to reduced milk supply and fatigue. It is important for mothers to listen to their bodies and drink fluids regularly, especially when they feel thirsty. Drinking herbal teas, broths, and consuming water-rich foods such as cucumbers, watermelons, and oranges can also help meet hydration needs. Proper hydration not only supports milk production but also helps mothers maintain their energy levels, as dehydration can lead to feelings of lethargy and exhaustion. For optimal hydration, it’s recommended that mothers carry a water bottle and take small sips throughout the day.</p>\n" +
                            "\n" +
                            "<h4>Nutritional Guidelines for Breastfeeding Mothers</h4>\n" +
                            "\n" +
                            "<p>In addition to hydration, breastfeeding mothers need to maintain a balanced and nutrient-rich diet to support their own health and that of their baby. This includes consuming an additional 300-500 calories per day, as breastfeeding burns extra calories. However, these extra calories should come from nutrient-dense foods like whole grains, lean proteins, healthy fats, and plenty of fruits and vegetables. These foods provide the necessary vitamins and minerals for milk production and help mothers maintain their energy levels throughout the day.</p>\n" +
                            "\n" +
                            "<p>It is particularly important for breastfeeding mothers to consume foods high in protein, calcium, and omega-3 fatty acids. Protein is essential for tissue repair and muscle maintenance, while calcium supports bone health. Omega-3s, which are found in fatty fish, chia seeds, and flaxseeds, are crucial for both the mother's brain health and the baby's brain development. A varied diet that includes these key nutrients can support a mother’s physical and mental well-being while ensuring that her baby receives the necessary nutrients for healthy growth and development.</p>\n",
                    author = "Dr. Lydia Ndungu"
                ),
                CareGuideArticle(
                    id = 6,
                    category = "Nutrition",
                    title = "Essential Nutrients for Postpartum Recovery",
                    image = R.drawable.nutrition_ig3, // Use actual drawable resource ID
                    subtitle = "Key Vitamins and Minerals That Aid Healing",
                    content = "<h4>Understanding the Role of Key Nutrients in Recovery</h4>\n" +
                            "\n" +
                            "<p>Postpartum recovery is a critical period where the body needs specific nutrients to heal properly. New mothers should focus on consuming foods that are rich in key vitamins and minerals, such as iron, calcium, vitamin D, and omega-3 fatty acids. Iron is essential for rebuilding the blood supply after childbirth and for maintaining energy levels. Calcium is crucial for bone health, especially for breastfeeding mothers, who may lose some of their bone density during this period. Vitamin D helps in the absorption of calcium, supporting both maternal and infant bone health.</p>\n" +
                            "\n" +
                            "<p>Omega-3 fatty acids are another critical component of a postpartum diet. These healthy fats support brain function and can help reduce the risk of postpartum depression. Studies have shown that omega-3s, especially those found in fatty fish, play an important role in mood regulation and mental well-being. Additionally, consuming antioxidant-rich foods such as berries, nuts, and green vegetables can help reduce inflammation and boost the immune system, further aiding recovery. For mothers who are breastfeeding, these nutrients also support the development of the baby's brain, bones, and overall health.</p>\n" +
                            "\n" +
                            "<h4>Incorporating These Nutrients into Everyday Meals</h4>\n" +
                            "\n" +
                            "<p>Incorporating these essential nutrients into everyday meals can be simple and enjoyable. Starting the day with a nutrient-rich breakfast that includes whole grains, eggs, and fruits can provide the energy needed to tackle the day. For lunch and dinner, including lean proteins like chicken, fish, or legumes, alongside leafy greens and whole grains, ensures that meals are balanced and nutrient-dense. Adding seeds and nuts as snacks can provide a boost of omega-3 fatty acids, while dairy or fortified plant-based alternatives can ensure that calcium needs are met.</p>\n" +
                            "\n" +
                            "<p>Mothers should aim to eat a variety of foods to cover their nutritional bases. Diversity in meals ensures that they get a wide range of vitamins and minerals. If necessary, mothers can also consider taking supplements, especially for nutrients like vitamin D and omega-3s, which can be hard to obtain in sufficient quantities through diet alone. However, it’s important to consult with a healthcare provider before starting any supplementation. Ultimately, a well-rounded, nutrient-dense diet will support both the mother and baby’s health.</p>\n",
                    author = "Dr. Grace Kimathi"
                ),
                CareGuideArticle(
                    id = 7,
                    category = "Nutrition",
                    title = "The Importance Of Postpartum Nutrition",
                    image = R.drawable.nutrition_ig4, // Use same or different image
                    subtitle = "How Proper Nutrition Helps New Mothers Recover",
                    content = "<h4>Why Proper Nutrition is Essential for Recovery</h4>\n" +
                            "\n" +
                            "<p>After childbirth, a mother's body undergoes significant physical changes that require proper nutrition to recover fully. The process of giving birth can leave the body depleted of essential nutrients, making it important for new mothers to focus on replenishing these nutrients. Eating a balanced diet rich in vitamins, minerals, and healthy fats can aid in faster recovery and help mothers regain strength. Postpartum nutrition is not only important for the mother's recovery but also plays a role in supporting breastfeeding and the overall health of the newborn.</p>\n" +
                            "\n" +
                            "<p>A balanced postpartum diet should include a variety of nutrient-dense foods such as leafy greens, lean proteins, whole grains, and healthy fats. These foods provide the body with the necessary energy and nutrients to heal tissues, boost immune function, and produce quality breast milk. Proper hydration is also essential during this period, as it aids in digestion, circulation, and milk production. Incorporating foods high in iron, calcium, and omega-3 fatty acids can be especially beneficial for new mothers, as these nutrients support physical recovery and promote mental well-being.</p>\n" +
                            "\n" +
                            "<h4>Tips for Maintaining a Healthy Diet Postpartum</h4>\n" +
                            "\n" +
                            "<p>Maintaining a healthy diet postpartum can be challenging for many new mothers due to the demands of caring for a newborn. However, it is important to make nutrition a priority, even in small ways. Preparing simple, nutrient-dense meals ahead of time, or having family and friends assist with meal preparation, can ensure that mothers are getting the nutrition they need without feeling overwhelmed. Eating smaller, more frequent meals throughout the day can help maintain energy levels, and incorporating snacks like nuts, yogurt, and fresh fruit can provide an extra nutritional boost.</p>\n" +
                            "\n" +
                            "<p>Postpartum nutrition doesn’t need to be complicated. Small changes, such as adding more vegetables to meals or choosing whole grains over refined grains, can have a significant impact on a mother’s recovery. Focusing on nutrient-rich foods, staying hydrated, and avoiding processed and sugary foods will ensure that new mothers feel energized, healthy, and prepared to meet the demands of motherhood. Listening to one's body and eating intuitively can also help mothers meet their unique nutritional needs during this time.</p>\n",
                    author = "Dr. Mary Nyambura"
                ),
                CareGuideArticle(
                    id = 8,
                    category = "Nutrition",
                    title = "Nutritional Tips for Faster Postpartum Recovery",
                    image = R.drawable.nutrition_ig, // Use actual drawable resource ID
                    subtitle = "How A Healthy Diet Can Accelerate Healing After Birth",
                    content = "<h4>Focusing on Nutrient-Rich Foods</h4>\n" +
                            "\n" +
                            "<p>The postpartum period is a time for healing, and nutrition plays a critical role in this process. After giving birth, the body needs to repair tissues, replenish lost nutrients, and support breastfeeding, which increases nutritional demands. Nutrient-rich foods such as lean proteins, vegetables, whole grains, and healthy fats provide the building blocks necessary for recovery. These foods help repair tissues, boost energy, and improve immune function. Protein, in particular, is essential for healing wounds and maintaining muscle mass, while antioxidants found in fruits and vegetables support the immune system and reduce inflammation.</p>\n" +
                            "\n" +
                            "<p>Postpartum recovery requires a focus on high-quality foods that nourish the body from the inside out. For example, leafy greens such as spinach and kale are rich in iron and calcium, which support bone health and aid in the replenishment of lost blood. Whole grains like quinoa and brown rice provide energy and help regulate digestion, which is often slowed down after birth. Additionally, healthy fats found in avocados, nuts, and seeds help balance hormones and provide essential fatty acids that are important for both the mother and the baby's brain development.</p>\n" +
                            "\n" +
                            "<h4>Establishing a Balanced Postpartum Meal Plan</h4>\n" +
                            "\n" +
                            "<p>Establishing a balanced meal plan is one of the most effective ways to support postpartum recovery. Mothers should aim to eat small, frequent meals throughout the day to keep their energy levels stable and avoid blood sugar crashes. A typical meal could include a lean protein such as grilled chicken or fish, paired with a complex carbohydrate like sweet potatoes or whole grain bread, and a side of vegetables. Snacks such as Greek yogurt with berries or a handful of almonds can provide an extra boost of nutrition between meals.</p>\n" +
                            "\n" +
                            "<p>While meal planning might seem overwhelming with a newborn, preparing simple, nutritious meals ahead of time can help alleviate stress. Batch cooking and freezing meals can be a lifesaver during the postpartum period when time is limited. It's also important to avoid processed foods, which are often high in sugars and unhealthy fats that can lead to energy crashes and inflammation. Instead, mothers should focus on whole, natural foods that provide lasting energy and support overall well-being. This approach to nutrition not only aids in physical recovery but also promotes mental clarity and emotional stability.</p>\n",
                    author = "Dr. Samuel Kariuki"
                )
            )
            "Sleep" -> listOf(
                CareGuideArticle(
                    id = 9,
                    category = "Sleep",
                    title = "The Importance of Sleep for New Mothers",
                    image = R.drawable.sleep_ig, // Use actual drawable resource ID
                    subtitle = "How Proper Rest Affects Postpartum Recovery",
                    content = "<h4>Why Sleep is Crucial During the Postpartum Period</h4>\n" +
                            "\n" +
                            "<p>Sleep is one of the most important factors in postpartum recovery, yet it is often the most elusive for new mothers. The demands of caring for a newborn, combined with hormonal shifts, can lead to fragmented sleep, which in turn affects physical and mental well-being. Adequate sleep is essential for healing after childbirth, boosting the immune system, and maintaining cognitive function. Sleep also plays a critical role in emotional regulation, helping mothers manage stress, anxiety, and the emotional challenges that come with motherhood.</p>\n" +
                            "\n" +
                            "<p>According to sleep experts, mothers should aim for seven to nine hours of sleep each night, but this can be difficult in the early postpartum months when infants need to be fed every few hours. To compensate for lost nighttime sleep, mothers are encouraged to nap during the day when the baby sleeps. This can help reduce sleep debt and provide much-needed rest. Poor sleep quality has been linked to increased risks of postpartum depression, which makes finding ways to improve sleep an essential part of a mother’s self-care routine.</p>\n" +
                            "\n" +
                            "<h4>Practical Tips for Improving Sleep</h4>\n" +
                            "\n" +
                            "<p>New mothers can improve their sleep by establishing a calming bedtime routine that promotes relaxation. Simple practices such as dimming the lights, avoiding screens before bed, and practicing deep breathing or meditation can signal to the body that it is time to rest. Creating a comfortable sleep environment, with a supportive mattress, soft bedding, and a quiet space, can also make a significant difference. Mothers may also benefit from adjusting their expectations of sleep, focusing on the quality of rest rather than the quantity, and learning to let go of non-essential tasks to prioritize sleep whenever possible.</p>\n",
                    author = "Dr. Emily White"
                ),
                CareGuideArticle(
                    id = 10,
                    category = "Sleep",
                    title = "Sleep Challenges for New Mothers and How to Overcome Them",
                    image = R.drawable.sleep_ig2, // Use same or different image
                    subtitle = "Navigating the Complex Sleep Patterns in the First Year",
                    content = "<h4>Understanding Fragmented Sleep Patterns</h4>\n" +
                            "\n" +
                            "<p>New mothers often find themselves dealing with fragmented sleep due to the irregular sleep patterns of their newborns. Babies in the first few months tend to sleep for only two to three hours at a time, requiring frequent feedings, which interrupts a mother’s natural sleep cycle. This sleep deprivation can take a toll on both physical and mental health, leading to feelings of exhaustion, irritability, and difficulty concentrating. While this is a common experience for many new mothers, understanding the root causes of fragmented sleep can help mothers find effective solutions.</p>\n" +
                            "\n" +
                            "<p>One of the main reasons for sleep challenges is the newborn’s need for frequent feedings, especially during the night. This is because newborns have smaller stomachs and require regular nourishment. Additionally, newborns have not yet developed a circadian rhythm, meaning they don’t differentiate between day and night. Understanding that these sleep disruptions are temporary can give mothers peace of mind. In the meantime, new mothers should practice self-compassion and accept that the first few months will likely involve sleepless nights, but these challenges will gradually improve as the baby grows.</p>\n" +
                            "\n" +
                            "<h4>Strategies to Navigate Sleep Deprivation</h4>\n" +
                            "\n" +
                            "<p>To cope with sleep deprivation, mothers can implement strategies to optimize their sleep when the baby is resting. One effective approach is to sleep when the baby sleeps, even if it means taking naps throughout the day. Asking for support from partners, family members, or friends to take over baby duties for short periods can provide mothers with uninterrupted sleep sessions. Sleep training methods, such as establishing a bedtime routine for the baby and encouraging self-soothing techniques, can also help mothers get longer stretches of sleep as the baby develops.</p>\n" +
                            "\n" +
                            "<p>In addition to napping, new mothers should prioritize self-care practices that support better sleep. Reducing caffeine intake, engaging in light physical activity, and practicing mindfulness or relaxation techniques before bed can help mothers fall asleep more easily and improve the overall quality of their rest. Although sleep deprivation is a natural part of early motherhood, making sleep a priority can prevent it from significantly impacting a mother's health and well-being in the long run.</p>\n",
                    author = "Author: Dr. Hannah Lee"
                ),
                CareGuideArticle(
                    id = 11,
                    category = "Sleep",
                    title = "Co-Sleeping and Sleep Safety for New Mothers",
                    image = R.drawable.sleep_ig3, // Use actual drawable resource ID
                    subtitle = "Balancing Sleep for Both Mother and Baby",
                    content = "<h4>The Debate Around Co-Sleeping</h4>\n" +
                            "\n" +
                            "<p>Co-sleeping, where the baby sleeps in close proximity to the mother, is a common practice for many families worldwide. For some mothers, co-sleeping provides peace of mind and helps the baby feel more secure. It also allows for easier nighttime breastfeeding, reducing the need for mothers to fully wake up during feeding sessions. However, co-sleeping has been a topic of debate due to safety concerns, particularly regarding the risk of Sudden Infant Death Syndrome (SIDS). The American Academy of Pediatrics (AAP) recommends room-sharing without bed-sharing for at least the first six months, as this can significantly reduce the risk of SIDS.</p>\n" +
                            "\n" +
                            "<p>Mothers who choose to co-sleep with their babies should follow safety guidelines to minimize risks. These include ensuring that the baby is placed on their back to sleep, using a firm mattress, and avoiding loose bedding or pillows that could obstruct the baby’s breathing. Mothers should also avoid sleeping with their baby if they have consumed alcohol, taken medication, or are overly fatigued, as this could impair their ability to wake up if the baby is in danger. By following these safety practices, mothers can find a balance between maintaining close contact with their baby and ensuring a safe sleep environment.</p>\n" +
                            "\n" +
                            "<h4>Alternatives to Co-Sleeping for Better Rest</h4>\n" +
                            "\n" +
                            "<p>For mothers who prefer not to co-sleep but still want their baby close by, using a bassinet or crib in the same room can provide a sense of security for both the mother and baby while promoting safer sleep practices. Room-sharing allows mothers to attend to their baby’s needs during the night without the risks associated with bed-sharing. Another option is the use of a bedside sleeper, which attaches to the side of the bed, giving the mother easy access to her baby while maintaining a separate sleep space for the infant.</p>\n" +
                            "\n" +
                            "<p>Regardless of the sleep arrangement, new mothers should prioritize their own sleep by creating a consistent sleep routine for the baby and ensuring they have a comfortable sleep environment. Taking shifts with a partner or caregiver during nighttime duties can also help mothers get more rest, ultimately benefiting both their physical and mental health during the postpartum period.</p>\n",
                    author = "Dr. Sarah Johnson"
                ),
                CareGuideArticle(
                    id = 12,
                    category = "Sleep",
                    title = "Sleep Training Techniques for New Mothers",
                    image = R.drawable.sleep_ig4, // Use same or different image
                    subtitle = "Establishing Healthy Sleep Habits for Baby and Mother",
                    content = "<h4>The Basics of Sleep Training</h4>\n" +
                            "\n" +
                            "<p>Sleep training is the process of teaching a baby how to fall asleep independently and establish healthy sleep habits. For many mothers, the postpartum period is marked by disrupted sleep patterns as babies wake frequently during the night for feeding or comfort. While newborns naturally have irregular sleep cycles, around the age of four to six months, many babies are developmentally ready for sleep training. This process can help both the baby and mother achieve more consistent and restful sleep, leading to improved mood, energy levels, and overall well-being.</p>\n" +
                            "\n" +
                            "<p>There are several approaches to sleep training, including the “cry-it-out” method, where parents allow the baby to self-soothe without immediate intervention, and gentler methods such as “gradual retreat,” where parents slowly reduce their presence in the baby’s room until the baby learns to fall asleep independently. The goal of sleep training is to teach the baby to associate their crib or sleep space with falling asleep, rather than relying on being rocked or fed to sleep. Although sleep training can be challenging in the beginning, many mothers report significant improvements in their baby’s sleep patterns within a few weeks.</p>\n" +
                            "\n" +
                            "<h4>Customizing Sleep Training for Your Baby</h4>\n" +
                            "\n" +
                            "<p>Every baby is different, so sleep training techniques should be tailored to the child’s temperament and the family’s preferences. Some babies respond well to a structured bedtime routine, while others may need more flexibility. Establishing a consistent bedtime routine, including calming activities such as a bath, story, or lullaby, can signal to the baby that it is time to sleep. Mothers should also ensure that the sleep environment is conducive to rest by keeping the room dark, quiet, and cool.</p>\n" +
                            "\n" +
                            "<p>For mothers, it’s important to remain patient and flexible throughout the sleep training process. It may take time for the baby to adjust, and there may be setbacks along the way. However, with consistency and persistence, sleep training can help mothers achieve better sleep and reduce the overall stress associated with sleep deprivation. Additionally, mothers should seek support from partners, family members, or pediatricians to ensure that they are implementing a method that works best for their family.</p>\n",
                    author = "Dr. Aisha Kamau"
                )
            )
            "Exercises" -> listOf(
                CareGuideArticle(
                    id = 13,
                    category = "Exercises",
                    title = "The Benefits of Postpartum Exercise for Mental Health",
                    image = R.drawable.exercise_ig, // Use actual drawable resource ID
                    subtitle = "How Physical Activity Reduces the Risk of Postpartum Depression",
                    content = "<h4>Why Postpartum Exercise is Essential for Mental Health</h4>\n" +
                            "\n" +
                            "<p>Physical exercise is a powerful tool for improving mental health, especially for new mothers in their first year postpartum. Studies have shown that engaging in regular physical activity helps reduce the risk of postpartum depression, a condition that affects up to 15% of new mothers worldwide. Exercise releases endorphins, which are chemicals in the brain that act as natural painkillers and mood elevators. These endorphins help combat feelings of sadness, anxiety, and stress, which are common in the postpartum period. Additionally, exercise improves sleep quality, reduces fatigue, and boosts overall well-being.</p>\n" +
                            "\n" +
                            "<p>For mothers who are dealing with the emotional and physical demands of caring for a newborn, exercise can serve as a mental and emotional release. Whether it’s a gentle walk around the neighborhood, postnatal yoga, or a strength training session, incorporating exercise into a daily routine can provide significant mental health benefits. Even short bursts of exercise, such as 10 to 15 minutes, can help mothers feel more energized and in control of their mental health. The key is to start slow and gradually increase the intensity of workouts as the body recovers from childbirth.</p>\n" +
                            "\n" +
                            "<h4>Creating a Supportive Environment for Exercise</h4>\n" +
                            "\n" +
                            "<p>For new mothers, finding the time and motivation to exercise can be challenging. Support from family, friends, and even other new mothers can make a big difference. Mothers are encouraged to set small, achievable exercise goals, such as taking a short walk with the baby in a stroller or doing light stretching exercises at home. Group exercise classes specifically designed for postpartum women, such as mommy-and-me yoga, can provide social support while allowing mothers to engage in physical activity. Creating a supportive environment where mothers feel encouraged and motivated to exercise is key to reaping the mental health benefits of postpartum physical activity.</p>\n",
                    author = "Dr. Lisa Thompson"
                ),
                CareGuideArticle(
                    id = 14,
                    category = "Exercises",
                    title = "How Exercise Helps Mothers Cope with Postpartum Depression",
                    image = R.drawable.exercise_ig2, // Use same or different image
                    subtitle = "Using Physical Activity to Improve Emotional Well-Being",
                    content = "<h4>The Role of Exercise in Coping with Postpartum Depression</h4>\n" +
                            "\n" +
                            "<p>Postpartum depression can be a debilitating condition that affects a mother’s ability to care for herself and her baby. Exercise has been proven to be an effective way to alleviate the symptoms of depression by promoting the release of serotonin, a neurotransmitter associated with happiness and emotional well-being. Research shows that mothers who engage in regular physical activity during their first year postpartum are less likely to experience severe symptoms of depression. Exercise offers a healthy and natural way to cope with the stress and emotional changes that come with motherhood.</p>\n" +
                            "\n" +
                            "<p>Exercise does not have to be strenuous to be effective. Low-impact activities such as walking, swimming, and stretching can be particularly beneficial for new mothers as they adjust to the physical changes of postpartum life. These forms of exercise are gentle on the body, reduce stress levels, and allow mothers to take time for themselves. Moreover, being physically active can provide a sense of accomplishment and boost self-esteem, which is important for mothers dealing with the emotional ups and downs of postpartum depression.</p>\n" +
                            "\n" +
                            "<h4>Exercise as a Tool for Emotional Recovery</h4>\n" +
                            "\n" +
                            "<p>Mothers dealing with postpartum depression may feel overwhelmed by the demands of caring for a newborn, which can lead to feelings of helplessness or despair. Exercise provides an outlet for emotional recovery by giving mothers a sense of control over their health and well-being. Regular physical activity can create a routine, which helps mothers feel more grounded and in control of their day. Additionally, exercising outdoors can provide further benefits, as exposure to sunlight and fresh air helps improve mood and alleviate symptoms of depression.</p>\n",
                    author = "Dr. Rachel Kim"
                ),
                CareGuideArticle(
                    id = 15,
                    category = "Exercises",
                    title = "Safe Postpartum Exercises to Combat Depression",
                    image = R.drawable.exercise_ig3, // Use actual drawable resource ID
                    subtitle = "Simple Movements That Support Mental and Physical Health",
                    content = "<h4>Getting Started with Safe Postpartum Exercises</h4>\n" +
                            "\n" +
                            "<p>Exercising during the postpartum period is one of the safest and most effective ways to improve both mental and physical health. It’s important for new mothers to ease back into exercise with low-impact movements that support recovery while reducing the risk of postpartum depression. Pelvic floor exercises, gentle stretching, and low-intensity aerobic activities such as walking or swimming are great ways to start. These exercises not only help mothers regain strength and stamina but also promote emotional balance by reducing feelings of anxiety and depression.</p>\n" +
                            "\n" +
                            "<p>One of the key benefits of postpartum exercise is that it helps to reduce cortisol levels, the hormone associated with stress. Elevated cortisol levels are common in new mothers due to the physical and emotional demands of caring for a newborn. Engaging in regular physical activity, even for short periods, can help regulate these hormones and create a sense of calm. In addition, postpartum exercises that focus on core strength, such as modified planks and pelvic tilts, help mothers regain abdominal strength, which is important for overall stability and balance.</p>\n" +
                            "\n" +
                            "<h4>Incorporating Exercise into Daily Life</h4>\n" +
                            "\n" +
                            "<p>Many new mothers find it challenging to prioritize exercise while caring for a baby. However, incorporating small movements into daily routines can make a significant difference. For example, mothers can perform pelvic floor exercises while feeding their baby or go for a short walk with the stroller. Exercise does not have to be done in a gym setting to be effective; everyday activities such as climbing stairs, squatting, or carrying the baby can serve as functional fitness. The key is to find moments throughout the day to stay active and build consistency over time.</p>\n",
                    author = "Dr. Stephanie James"
                ),
                CareGuideArticle(
                    id = 16,
                    category = "Exercises",
                    title = "The Impact of Regular Exercise on Postpartum Mental Health",
                    image = R.drawable.exercise_ig4, // Use actual drawable resource ID
                    subtitle = "How Physical Activity Helps Reduce Postpartum Depression Symptoms",
                    content = "<h4>The Science Behind Exercise and Mental Health</h4>\n" +
                            "\n" +
                            "<p>Research has shown a strong link between regular physical activity and improved mental health, especially for new mothers. Exercise is known to increase the production of endorphins, which are natural mood boosters that help combat the symptoms of postpartum depression. By incorporating exercise into their daily routine, mothers can significantly reduce feelings of anxiety, stress, and sadness. In fact, some studies suggest that regular physical activity can be just as effective as medication for treating mild to moderate cases of depression.</p>\n" +
                            "\n" +
                            "<p>Postpartum depression is often triggered by a combination of hormonal changes, sleep deprivation, and the overwhelming responsibilities of caring for a newborn. Exercise provides an outlet for stress relief and offers a healthy way to cope with the challenges of early motherhood. Physical activity also promotes better sleep, which is crucial for emotional stability. Even something as simple as a 20-minute walk can have a positive impact on a mother’s mental health by reducing stress levels and improving her overall mood.</p>\n" +
                            "\n" +
                            "<h4>Fostering Mental Wellness Through Movement</h4>\n" +
                            "\n" +
                            "<p>For mothers who are struggling with postpartum depression, it can be difficult to find the motivation to exercise. However, starting with small, manageable goals can make the process less intimidating. Mothers can begin with low-impact exercises such as walking, swimming, or gentle yoga, which can be done at home or in the company of other new mothers. Group exercise classes designed for postpartum women can also provide social support and a sense of community, which is important for emotional well-being. As mothers build confidence and physical strength, they can gradually increase the intensity of their workouts.</p>\n" +
                            "\n" +
                            "<p>Ultimately, regular exercise fosters a sense of empowerment and control, which is essential for managing postpartum depression. By making physical activity a priority, mothers can improve their mental and emotional health, allowing them to better care for themselves and their baby.</p>\n",
                    author = "Dr. Maria Lopez"
                )

            )
            else -> emptyList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



