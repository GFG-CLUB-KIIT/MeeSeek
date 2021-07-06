package co.kit.gfg.chatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.kit.gfg.chatapp.adapter.PairedDevicesAdapter
import co.kit.gfg.chatapp.devices.BLDevice

class PairedDeviceFragment(private val onClick: OnClick, private val blDevices: List<BLDevice>) :
    DialogFragment(R.layout.fragment_paired_device), PairedDevicesAdapter.Interaction {

    private lateinit var pairedDevicesAdapter: PairedDevicesAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_paired_device, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recycler_pairedDevice_models)
        initRecyclerView()
        pairedDevicesAdapter.submitList(blDevices)
    }

    private fun initRecyclerView() {
        recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            pairedDevicesAdapter = PairedDevicesAdapter(this@PairedDeviceFragment)
            adapter = pairedDevicesAdapter
        }

    }

    override fun onItemSelected(position: Int, item: BLDevice) {
        onClick.pairedDeviceSelected(position, item)
        dismiss()
    }

    interface OnClick {
        fun pairedDeviceSelected(position: Int, item: BLDevice)
    }
}