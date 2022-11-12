package com.example.kotlinprojectbmb

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.kotlinprojectbmb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val QUIZ_COUNT = 5

    private val quizData = mutableListOf(
        mutableListOf("What is DET 260?", "Game Engines", "3D Modeling", "2D Animation", "Advanced DET"),
        mutableListOf("Which language is NOT taught in CS 315?", "GDScript", "Java", "Kotlin", "XML"),
        mutableListOf("Who taught ITC 110 in 2019 with Professor Tanner?", "Dr. Prather", "Dr. Homer", "Dr. Burton", "Dr. Reeves"),
        mutableListOf("What did Benjamin put on his bookshelf in a 3D modeling assignment?", "A red ball", "A Lego set", "A picture", "Another Bookshelf"),
        mutableListOf("Which anime character did Lauren feature in her 3D modeling final?", "Inosuke", "Naruto", "Sailor Moon", "Edward Elric"),
        mutableListOf("What game did Ethan take inspiration from for his Game Engines Final?", "Half-Life", "Portal", "Super Mario", "Pikmin"),
        mutableListOf("What was Benjamin's brick texture referred to as in Game Materials?", "Ghost Bricks", "Groutless", "I am Grout", "Floaty Bois"),
        mutableListOf("In 2021, who's Meet Mat won the student vote?", "Tanner Webb", "Jared Wright", "Joel Bell", "Camila Rodriguez"),
        mutableListOf("What is Alissa's favorite mythical creature?", "Dragon", "Centaur", "Lochness Monster", "Ent"),
        mutableListOf("Who took over Game Textures class in 2021?", "Professor Tanner still taught the class in 2021", "Dr. Burton", "Dr. Darby", "Dr. Reeves"),
        mutableListOf("What game engine has Benjamin started advocating for?", "Godot", "Game Maker", "Construct", "Game Salad"),
        mutableListOf("What game is Lauren basing her Game Engines final on?", "Pikmin", "Factorio", "Half-Life", "Her ITC 110 Project")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        quizData.shuffle()

        showNextQuiz()
    }

    fun showNextQuiz()
    {
        binding.countLabel.text = getString(R.string.count_label, quizCount)

        val quiz = quizData[0]

        binding.questionLabel.text = quiz[0]
        rightAnswer = quiz[1]

        quiz.removeAt(0)
        quiz.shuffle()

        binding.answerButton1.text = quiz[0]
        binding.answerButton2.text = quiz[1]
        binding.answerButton3.text = quiz[2]
        binding.answerButton4.text = quiz[3]

        quizData.removeAt(0)
    }

    fun checkAnswer(view: View)
    {
        val answerButton: Button = findViewById(view.id)
        val buttonText = answerButton.text.toString()

        val alertTitle: String
        if(buttonText == rightAnswer)
        {
            alertTitle = "Correct!"
            rightAnswerCount++
        }
        else
        {
            alertTitle = "Wrong..."
        }

        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("Answer: $rightAnswer")
            .setPositiveButton("OK") {dialogInterface, i ->
                checkQuizCount()
            }
            .setCancelable(false)
            .show()
    }

    fun checkQuizCount()
    {
        if(quizCount == QUIZ_COUNT)
        {
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount)
            startActivity(intent)
        }
        else
        {
            quizCount++
            showNextQuiz()
        }
    }

}