package co.kit.gfg.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


//        handler= DatabaseHelper(this)
            val handler= DatabaseHelper(this)




        RegisterButton.setOnClickListener{
            handler.insertUserData(register_bluetooth_name.text.toString(),register_Username.text.toString(),register_password.text.toString())
                Toast.makeText(this,"Values Inserted", Toast.LENGTH_LONG).show()
                onBackPressed()

        }
        constraintRegisterLayout.setOnTouchListener(object:OnSwipeTouchListener(this@RegisterActivity){
            override fun onSwipeRight() {
                super.onSwipeRight()
                val intent=Intent(applicationContext,LoginActivity::class.java)
                startActivity(intent)
            }

        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}
