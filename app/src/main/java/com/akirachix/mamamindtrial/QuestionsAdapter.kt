

package com.akirachix.mamamindtrial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.mamamindtrial.network.Question

class QuestionsAdapter(private var questions: List<Question>) : RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {


    private val scores: MutableMap<Int, Int> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    fun updateQuestions(newQuestions: List<Question>) {
        questions = newQuestions
        notifyDataSetChanged()
    }

    fun getTotalScore(): Int {
        return scores.values.sum()
    }

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val questionText: TextView = itemView.findViewById(R.id.text_question)
        private val radioGroup: RadioGroup = itemView.findViewById(R.id.radio_group)

        fun bind(question: Question) {
            questionText.text = question.question
            radioGroup.removeAllViews()

            val radioButton1 = addRadioButton(question.option_1, question.first_score)
            val radioButton2 = addRadioButton(question.option_2, question.second_score)
            val radioButton3 = addRadioButton(question.option_3, question.third_score)
            val radioButton4 = addRadioButton(question.option_4, question.forth_score)

            when (question.current_score) {
                question.first_score -> radioButton1.isChecked = true
                question.second_score -> radioButton2.isChecked = true
                question.third_score -> radioButton3.isChecked = true
                question.forth_score -> radioButton4.isChecked = true
            }

            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                val selectedScore = when (checkedId) {
                    radioButton1.id -> question.first_score
                    radioButton2.id -> question.second_score
                    radioButton3.id -> question.third_score
                    radioButton4.id -> question.forth_score
                    else -> 0
                }
                scores[question.id] = selectedScore
            }
        }

        private fun addRadioButton(option: String, score: Int): RadioButton {
            val radioButton = RadioButton(itemView.context)
            radioButton.text = option
            radioButton.id = View.generateViewId()
            radioButton.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            radioButton.setPadding(8, 8, 8, 8)
            radioButton.textSize = 16f
            radioGroup.addView(radioButton)
            return radioButton
        }
    }
}