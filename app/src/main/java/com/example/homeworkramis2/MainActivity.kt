package com.example.homeworkramis2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworkramis2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            sendBtn.setOnClickListener {
                val email = emailEtBox.editText?.text.toString()
                val subject = subjectEtBox.editText?.text.toString()
                val message = messageEtBox.editText?.text.toString()

                val addresses: Array<String> = email.split(",")
                    .toTypedArray()  //Не понял для чего эта строка кода нужна но без неё не работает

                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:")
                intent.putExtra(Intent.EXTRA_EMAIL, addresses)
                intent.putExtra(Intent.EXTRA_SUBJECT, subject)
                intent.putExtra(Intent.EXTRA_TEXT, message)

                try {
                    startActivity(Intent.createChooser(intent, "Send email"))
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, "Something is wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}