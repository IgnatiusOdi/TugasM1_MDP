package com.example.tugas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var tvScore: TextView
    lateinit var btRandom: Button
    var buttons : MutableList<Button> = mutableListOf()
    lateinit var etNama: EditText
    lateinit var btPlay: Button

    private var nama = ""
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvScore = findViewById(R.id.tvScore)
        btRandom = findViewById(R.id.btRandom)
        buttons.add(findViewById(R.id.btNorth))
        buttons.add(findViewById(R.id.btNorthEast))
        buttons.add(findViewById(R.id.btEast))
        buttons.add(findViewById(R.id.btSouthEast))
        buttons.add(findViewById(R.id.btSouth))
        buttons.add(findViewById(R.id.btSouthWest))
        buttons.add(findViewById(R.id.btWest))
        buttons.add(findViewById(R.id.btNorthWest))
        etNama = findViewById(R.id.etNama)
        btPlay = findViewById(R.id.btPlay)

        btRandom.setOnClickListener {
            randomAngka()
        }

        btPlay.setOnClickListener {
            if (btPlay.text == "PLAY") {
                // PLAY
                if (etNama.text.isNotBlank()) {
                    score = 0
                    tvScore.text = score.toString()
                    btRandom.isEnabled = true
                    btRandom.setBackgroundColor(resources.getColor(R.color.purple_500))
                    btRandom.text = ""
                    randomAngka()
                    for (i in 0 until buttons.size) {
                        buttons[i].isEnabled = true
                        buttons[i].setBackgroundColor(resources.getColor(R.color.gray))
                        buttons[i].setOnClickListener {
                            if (buttons[i].text.isBlank()) {
                                if (btRandom.text == "1" || btRandom.text == "2") {
                                    score += btRandom.text.toString().toInt()
                                    tvScore.text = score.toString()
                                }
                                buttons[i].text = btRandom.text
                                buttons[i].setBackgroundColor(resources.getColor(R.color.purple_500))
                                randomAngka()
                            } else {
                                Toast.makeText(this@MainActivity, "BUTTON TIDAK KOSONG!", Toast.LENGTH_SHORT).show()
                            }
                            if (isGameOver()) {
                                Toast.makeText(this@MainActivity, "GAME OVER!\n$nama - $score", Toast.LENGTH_SHORT).show()
                                gameOver()
                            }
                        }
                    }
                    nama = etNama.text.toString()
                    etNama.isEnabled = false
                    btPlay.text = "RESET"
                } else {
                    Toast.makeText(this@MainActivity, "NAMA TIDAK BOLEH KOSONG!", Toast.LENGTH_SHORT).show()
                }
            } else {
                // RESET
                gameOver()
            }
        }
    }

    private fun randomAngka() {
        var rnd = (1..4).random()
        if (rnd <= 2) {
            btRandom.text = "1"
        } else if (rnd == 3) {
            btRandom.text = "2"
        } else {
            btRandom.text = "+"
        }
    }

    private fun isGameOver(): Boolean {
        for (i in 0 until buttons.size) {
            if (buttons[i].text.isBlank()) {
                return false
            }
        }
        return true
    }

    private fun gameOver() {
        btPlay.text = "PLAY"
        score = 0
        tvScore.text = score.toString()
        btRandom.isEnabled = false
        btRandom.setBackgroundColor(resources.getColor(R.color.gray))
        btRandom.text = ""
        for (i in 0 until buttons.size) {
            buttons[i].isEnabled = false
            buttons[i].setBackgroundColor(resources.getColor(R.color.gray))
            buttons[i].text = ""
        }
        etNama.isEnabled = true
        etNama.text.clear()
    }
}
