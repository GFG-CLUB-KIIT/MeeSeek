package co.kit.gfg.chatapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import co.kit.gfg.chatapp.data.PairedDeviceData

class DeviceDataAdapter (
//     name: ArrayList<String>
//    val address: ArrayList<String>

): RecyclerView.Adapter<DeviceDataAdapter.dataViewholder>() {


   inner class dataViewholder(itemView:View):RecyclerView.ViewHolder(itemView){

        var mTextView1: TextView = itemView.findViewById(R.id.device_name)
       //var mTextView2: TextView = itemView.findViewById(R.id.device_address)


        init{

            itemView.setOnClickListener {v:View->
                val position:Int=adapterPosition

                Toast.makeText(itemView.context, "Connecting to Device ${PairedDeviceData.device[position].name}", Toast.LENGTH_SHORT).show()
                Log.d("Device Clicked","${PairedDeviceData.device[position].name}")


                /*On click the Client class is launched*/
                val bluetoothConnection= BluetoothConnection()
                bluetoothConnection.ClientClass(PairedDeviceData.device[position]).start()

               //status.text(connecting)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):dataViewholder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.device_info,parent,false)
        return dataViewholder(itemView)
    }

    override fun onBindViewHolder(holder: dataViewholder, position: Int) {
        
        holder.mTextView1.text=PairedDeviceData.deviceName[position]
      //  holder.mTextView2.text=PairedDeviceData.deviceName[position]


    }

    override fun getItemCount(): Int {
        return PairedDeviceData.deviceName.size
    }



}



