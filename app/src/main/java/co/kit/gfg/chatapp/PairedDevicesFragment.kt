package co.kit.gfg.chatapp

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.kit.gfg.chatapp.data.PairedDeviceData
import kotlinx.android.synthetic.main.device_info.*
import java.util.*


class PairedDevicesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val viewOfLayout=inflater!!.inflate(R.layout.fragment_paired_devices, container, false)
        val recycler_paired_devices=viewOfLayout.findViewById<RecyclerView>(R.id.recyler_paired_devices)
        val bluetoothAdapter=BluetoothAdapter.getDefaultAdapter()
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter.bondedDevices
        val paired_names = ArrayList<String>(pairedDevices!!.size)
        val paired_address = ArrayList<String>(pairedDevices!!.size)
        if (pairedDevices.isNotEmpty()) {
            pairedDevices.forEach { device ->
                PairedDeviceData.deviceName.add(device.name)    //Device name
                PairedDeviceData.deviceAddress.add(device.address) // MAC address
                PairedDeviceData.device.add(device)

            }
            Log.d("Paired Device Size","${paired_names.size}")
            recycler_paired_devices.layoutManager = LinearLayoutManager(context)
            recycler_paired_devices.adapter =DeviceDataAdapter()
            Log.d("Paired Device Address",paired_address.toString())




        }



        return viewOfLayout
    }


}