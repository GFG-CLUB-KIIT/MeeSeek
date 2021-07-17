package co.kit.gfg.chatapp

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.kit.gfg.chatapp.data.PairedDeviceData
import co.kit.gfg.chatapp.data.UserInformation

import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    lateinit var handler: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_login)
        handler= DatabaseHelper(this)


        //Switching to Registration

        btnRegLogin.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }


        //Login click
        log_button.setOnClickListener{
            if(handler.userPresent(log_bluetooth_name.text.toString(),log_username.text.toString(),log_password.text.toString()))
            {       /*Saving the Login Data*/

                    UserInfo.saveDetails(applicationContext,log_bluetooth_name.text.toString(),log_username.text.toString())

                //start the activity
                val intent=Intent(this, MainActivity::class.java)
                  startActivity(intent)

            }
            else//Wrong password
            {
                Toast.makeText(this,"Invalid inputs", Toast.LENGTH_LONG).show()
            }
        }

    }


}




