package com.example.assignment5_quizapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private val quizList = ArrayList<Quiz>()

    private lateinit var radioOption1: RadioButton
    private lateinit var radioOption2: RadioButton
    private lateinit var radioOption3: RadioButton

    private lateinit var checkboxOption1: CheckBox
    private lateinit var checkboxOption2: CheckBox
    private lateinit var checkboxOption3: CheckBox

    private var ans1 = ""
    private var ans2 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initQuizQuestionsAnswers()
        initViews()
    }

    private fun initViews() {
        radioOption1 = findViewById(R.id.radio_opt1_q1)
        radioOption2 = findViewById(R.id.radio_opt2_q1)
        radioOption3 = findViewById(R.id.radio_opt3_q1)

        checkboxOption1 = findViewById(R.id.checkbox_option1_q2)
        checkboxOption2 = findViewById(R.id.checkbox_option2_q2)
        checkboxOption3 = findViewById(R.id.checkbox_option3_q2)
    }

    private fun initQuizQuestionsAnswers() {
        val quiz1 = Quiz(
            "B",
            1
        )

        val quiz2 = Quiz(
            "AC",
            2
        )

        quizList.add(0, quiz1)
        quizList.add(1, quiz2)
    }

    fun onResetClicked(view: View) {

        radioOption1.isChecked = false
        radioOption2.isChecked = false
        radioOption3.isChecked = false

        checkboxOption1.isChecked = false
        checkboxOption2.isChecked = false
        checkboxOption3.isChecked = false

        ans1 = ""
        ans2 = ""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSubmitClicked(view: View) {

        var score = 0

        val current = LocalDateTime.now()

        val formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = current.format(formatterDate)

        val formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss")
        val time = current.format(formatterTime)

        readAnswer2()

        if (validateAnswer1()) {
            score += 50
        }
        if (validateAnswer2()) {
            score += 50
        }

        val messageToDisplay =
            "Congratulations! You submitted on current Date: $date and Time: $time, You achieved $score %"

        displayDialog(messageToDisplay, "Your Quiz Score is here..")
    }

    private fun displayDialog(message: String, title: String) {
        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }

        builder.setMessage(message)!!.setTitle(title)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun validateAnswer1(): Boolean {
        val quiz = quizList[0] // first quiz
        if (quiz.answer == ans1) {
            return true
        }
        return false
    }

    private fun validateAnswer2(): Boolean {
        val quiz = quizList[1] // first quiz
        if (quiz.answer == ans2) {
            return true
        }
        return false
    }

    private fun readAnswer2() {
        ans2 = ""
        if (checkboxOption1.isChecked) {
            ans2 += "A"
        }
        if (checkboxOption2.isChecked) {
            ans2 += "B"
        }

        if (checkboxOption3.isChecked) {
            ans2 += "C"
        }

    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.radio_opt1_q1 ->
                    if (checked) {
                        ans1 = "A"
                    }
                R.id.radio_opt2_q1 ->
                    if (checked) {
                        ans1 = "B"
                    }
                R.id.radio_opt3_q1 ->
                    if (checked) {
                        ans1 = "C"
                    }
            }
        }
    }
}