package com.felipe.jogodavelha
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var isPlayer1 = true
    var gameEnd = false

    private lateinit var top_left: ImageView
    private lateinit var top_center: ImageView
    private lateinit var top_right: ImageView

    private lateinit var center_left: ImageView
    private lateinit var center_center: ImageView
    private lateinit var center_right: ImageView

    private lateinit var bottom_left: ImageView
    private lateinit var bottom_center: ImageView
    private lateinit var bottom_right: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Removendo a barra do cabeçalho
        supportActionBar!!.hide()

        // Coletando os IDs
        top_left = findViewById(R.id.top_left)
        top_center = findViewById(R.id.top_center)
        top_right = findViewById(R.id.top_right)

        center_left = findViewById(R.id.center_left)
        center_center = findViewById(R.id.center_center)
        center_right = findViewById(R.id.center_right)

        bottom_left = findViewById(R.id.bottom_left)
        bottom_center = findViewById(R.id.bottom_center)
        bottom_right = findViewById(R.id.bottom_right)


        // Resetando o game
        val reset: Button = findViewById(R.id.bt_jogar)
        reset.setOnClickListener {
            reset(top_left)
            reset(top_center)
            reset(top_right)

            reset(center_left)
            reset(center_center)
            reset(center_right)

            reset(bottom_left)
            reset(bottom_center)
            reset(bottom_right)
        }

        configureBox(top_left)
        configureBox(top_center)
        configureBox(top_right)

        configureBox(center_left)
        configureBox(center_center)
        configureBox(center_right)

        configureBox(bottom_left)
        configureBox(bottom_center)
        configureBox(bottom_right)
    }

    // Resetando o game
    private fun reset(box: ImageView) {
        box.setImageDrawable(null)
        box.tag = null
        isPlayer1 = true
        gameEnd = false
    }

    // Definindo o vencedor
    private fun configureBox(box: ImageView) {
        box.setOnClickListener {
            if (box.tag == null && !gameEnd) {
                if (isPlayer1) {
                    box.setImageResource(R.drawable.ic_circle)
                    isPlayer1 = false
                    box.tag = 1
                } else {
                    box.setImageResource(R.drawable.ic_x)
                    isPlayer1 = true
                    box.tag = 2
                }
                if (playerWin(1)) {
                    Toast.makeText(this@MainActivity, "Player 1 venceu!", Toast.LENGTH_SHORT).show()
                    gameEnd = true
                }else if (playerWin(2)) {
                    Toast.makeText(this@MainActivity, "Player 2 venceu!", Toast.LENGTH_SHORT).show()
                    gameEnd = true
                }
            }
        }
    }

        // Lógica do ganhador
    private fun playerWin(value: Int): Boolean {
        if ((top_center.tag == value && center_center.tag == value && bottom_center.tag == value) ||
            (top_left.tag == value && center_left.tag == value && bottom_left.tag == value) ||
            (top_right.tag == value && center_right.tag == value && bottom_right.tag == value) ||

            (top_left.tag == value && top_center.tag == value && top_right.tag == value) ||
            (center_left.tag == value && center_center.tag == value && center_right.tag == value) ||
            (bottom_left.tag == value && bottom_center.tag == value && bottom_right.tag == value) ||

            (top_left.tag == value && center_center.tag == value && bottom_right.tag == value) ||
            (top_right.tag == value && center_center.tag == value && bottom_left.tag == value)
            ) {
            return true
        }
            return false
    }



}