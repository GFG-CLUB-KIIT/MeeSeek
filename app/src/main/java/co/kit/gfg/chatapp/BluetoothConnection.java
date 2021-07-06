package co.kit.gfg.chatapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.UUID;

import co.kit.gfg.chatapp.data.PairedDeviceData;
import co.kit.gfg.chatapp.data.UserInformation;

public class BluetoothConnection extends AppCompatActivity {
    BluetoothAdapter bluetoothAdapter;


    static  final int STATE_LISTENING=1;
    static  final int STATE_CONNECTING=2;
    static  final int STATE_CONNECTED=3;
    static  final int STATE_CONNECTION_FAILED=4;
    static  final int STATE_MESSAGE_RECEIVED=5;


    private  static final String APP_NAME="ABC";
    private  static final UUID MY_UUID=UUID.fromString("2caf8d1e-d240-11eb-b8bc-0242ac130003");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    /**
     * UPDATING STATUS OF THE CONNECTION
     */

    Handler handler=new Handler(new Handler.Callback() {
        @Override

        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case STATE_LISTENING:
       //             textView.setText("LISTENING");
                            break;
                case STATE_CONNECTING:
    //              textView.setText("CONNECTING");
                    break;
                case STATE_CONNECTED:
   //               textView.setText("CONNECTED");
                    break;
                case STATE_CONNECTION_FAILED:
//                   textView.setText("CONNECTION FAILED");
                    break;
                case STATE_MESSAGE_RECEIVED:
                // textView.setText=("");
                    break;
            }
            return true;
        }
    });
    /**
            Connecting as a Server

    */
      public class ServerClass extends Thread
    {
        private BluetoothServerSocket serverSocket;
        BluetoothAdapter bluetoothAdapter1=BluetoothAdapter.getDefaultAdapter();
        public ServerClass() {
            try {
                Log.d("ServerClass","WORKING");
                serverSocket= bluetoothAdapter1.listenUsingInsecureRfcommWithServiceRecord(APP_NAME,MY_UUID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void run()
        {
            BluetoothSocket socket=null;
            while(socket==null)
            {
                try {
                    Message message=Message.obtain();
                    message.what=STATE_CONNECTING;
                    handler.sendMessage(message);
                        Log.d("Connection","Server class CONNECTING");
                    socket=serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();

                    Message message=Message.obtain();
                    Log.d("Connection","Server class CONNECTION FAILED");
                    message.what=STATE_CONNECTION_FAILED;

                    UserInformation.connectionStatus="NOT CONNECTED";
                    handler.sendMessage(message);

                }
                if(socket!=null) /* BOTH DEVICES ARE CONNECTED */
                {

                    Message message=Message.obtain();
                    Log.d("Connection","Server class CONNECTED");
                    message.what=STATE_CONNECTED;
                    handler.sendMessage(message);
                    UserInformation.connectionStatus="CONNECTED";
                    //Launch the individual chat fragment or Audio fragment from here


                    break;
                }

            }
        }

    }

    /**
     Connecting as a Client

     */
    public class  ClientClass extends Thread
    {
        private  BluetoothDevice device;
        private  BluetoothSocket socket;
        public  ClientClass(BluetoothDevice device1)
        {
            device=device1;
            try {
                socket=device.createRfcommSocketToServiceRecord(MY_UUID);
                Log.d("CLIENTCLASS","WORKING");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        public  void run()
        {  /*If are dicovering the devices then add bluetoothAdapter.cancelDiscovery();*/
            try {
                socket.connect();
                Message message=Message.obtain();
                message.what=STATE_CONNECTED;
                UserInformation.connectionStatus="CONNECTED";
                handler.sendMessage(message);
                Log.d("Connection","CLIENT class CONNECTED");


            } catch (IOException e) {
                e.printStackTrace();

                Message message=Message.obtain();
                Log.d("Connection","CLIENT class CONNECTING FAILED");
                message.what=STATE_CONNECTION_FAILED;

                UserInformation.connectionStatus="NOT CONNECTED";
                handler.sendMessage(message);
            }
        }
    }
}
