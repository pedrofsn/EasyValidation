package br.com.redcode.easyfvalidation.sample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.redcode.easyvalidation.Validate
import br.com.redcode.easyvalidation.extensions.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun validate(view: View?) {
        if (Validate check editTextEmail) {
            editTextEmail.hideKeyboard()
            val text = editTextEmail.editableText.toString().trim()
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }
}
