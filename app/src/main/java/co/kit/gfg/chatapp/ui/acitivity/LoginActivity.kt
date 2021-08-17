package co.kit.gfg.chatapp.ui.acitivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.kit.gfg.chatapp.*
import co.kit.gfg.chatapp.OnSwipeTouchListener

import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    lateinit var handler: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_login)
        handler= DatabaseHelper(this)


        //Switching to Registration


        //Login click
        LoginButton.setOnClickListener{
            if(handler.userPresent(login_bluetooth_name.text.toString(),login_Username.text.toString(),login_password.text.toString()))
            {       /*Saving the Login Data*/

                UserInfo.saveDetails(
                    applicationContext,
                    login_bluetooth_name.text.toString(),
                    login_Username.text.toString(),
                    login_password.text.toString()
                )

                //start the activity
                val intent=Intent(this, MainActivity::class.java)
                  startActivity(intent)

            }
            else//Wrong password
            {
                Toast.makeText(this,"Invalid inputs", Toast.LENGTH_LONG).show()
            }
        }
        constraintLoginLayout.setOnTouchListener(object: OnSwipeTouchListener(this@LoginActivity)
        {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                val intent=Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intent)
            }
        })

    }


}




