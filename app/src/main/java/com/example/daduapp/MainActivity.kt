package com.example.daduapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.daduapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: DiceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateDiceImages(viewModel.dice1, viewModel.dice2)

        binding.rollbuton.setOnClickListener {
            viewModel.dice1 = Dice(6).roll()
            viewModel.dice2 = Dice(6).roll()

            updateDiceImages(viewModel.dice1, viewModel.dice2)
        }
    }

    private fun updateDiceImages(dice1: Int, dice2: Int) {
        binding.diceImage1.setImageResource(getDiceDrawable(dice1))
        binding.diceImage2.setImageResource(getDiceDrawable(dice2))

        if (dice1 == dice2 && dice1 != 0) {
            Toast.makeText(this, "Selamat anda dapat dadu double!", Toast.LENGTH_SHORT).show()
        } else if (dice1 != 0 && dice2 != 0) {
            Toast.makeText(this, "Anda belum beruntung!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDiceDrawable(roll: Int): Int {
        return when (roll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_0
        }
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int = (1..numSides).random()
}
