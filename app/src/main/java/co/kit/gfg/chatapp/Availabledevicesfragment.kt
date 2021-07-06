package co.kit.gfg.chatapp

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import co.kit.gfg.chatapp.data.PairedDeviceData


class Availabledevicesfragment() : Fragment() {
    var available_device_name = ArrayList<String>()
    var available_device_address = ArrayList<String>()
    lateinit var deviceDataAdapter: DeviceDataAdapter
    private val bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    lateinit var recycler_available_devices:RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewOfLayout =
            inflater.inflate(R.layout.fragment_availabledevicesfragment, container, false)
//         recycler_available_devices =
//            viewOfLayout.findViewById<RecyclerView>(R.id.recyler_available_devices)

//        scanList=viewOfLayout.findViewById<ListView>(R.id.deviceList)
//        bluetoothAdapter.startDiscovery()
//
//        val intentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
//        requireContext().registerReceiver(myReceiver, intentFilter)
//
//        Log.d("Hello", "After myReceiver")
//
//        recycler_available_devices.layoutManager = LinearLayoutManager(requireContext())

//        arrayAdapter=ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,available_device_name)
//        scanList.adapter(arrayAdapter)



//        if(bluetoothAdapter.isDiscovering)
//            bluetoothAdapter.cancelDiscovery()
//
//        bluetoothAdapter.startDiscovery()
//        val discoverDeviceIntent=IntentFilter(BluetoothDevice.ACTION_FOUND)
//        activity?.registerReceiver(broad,discoverDeviceIntent)
//
//        recycler_available_devices!!.layoutManager = LinearLayoutManager(context)
//        recycler_available_devices.adapter =DataclassAdapter(available_device_name, available_device_address)
//                Log.d("yyyy","${available_device_name.size}")

//        intentFilter.addAction(BluetoothDevice.ACTION_NAME_CHANGED)

        Log.d("Paired Devices","${PairedDeviceData.device.size}")

        var temp1 = BluetoothConnection()
        temp1.ServerClass().start()


        return viewOfLayout

    }


    private val myReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val actions= intent?.action

            if(BluetoothDevice.ACTION_FOUND == actions){
                val devices= intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                recycler_available_devices.adapter = deviceDataAdapter
                devices?.let {
                    available_device_name.add(it.name)
                    available_device_address.add(it.address)
                }
                deviceDataAdapter.notifyDataSetChanged()

            }


//            if (devices != null) {
//                Log.d("name",devices.name)
//                deviceDataAdapter = DeviceDataAdapter(available_device_name)

//                deviceDataAdapter.notifyDataSetChanged()
//            }
        }
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }
//    private val broad = object : BroadcastReceiver() {
//
//        override fun onReceive(context: Context?, intent: Intent?) {
//            Log.d("Hello","e")
//            val action: String? = intent?.action
//            val available_device_address = ArrayList<String>()
//            val available_device_name = ArrayList<String>()
//            val recycler_available_devices =
//                activity?.findViewById<RecyclerView>(R.id.recyler_available_devices)
//
//            if (BluetoothDevice.ACTION_FOUND == action) {
//                // Discovery has found a device. Get the BluetoothDevice
//                // object and its info from the Intent.
//                val device: BluetoothDevice? =
//                    intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
//
//                if (device != null) {
//                    Log.d("Ava", device.name)
//                    available_device_name.add(device.name) //name of device
//                    available_device_address.add(device.address) // MAC address
//                    Log.d("Hello","${device.name}")
//                }
//
//
//            }
//
//
//        }
//    }
//
//
//
//    override fun onDestroy() {
//
//        // Don't forget to unregister the ACTION_FOUND receiver.
//        activity?.unregisterReceiver(broad)
//        super.onDestroy()
//    }


}