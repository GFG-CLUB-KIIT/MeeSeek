package co.kit.gfg.chatapp

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.kit.gfg.chatapp.adapter.ChatListAdapter
import co.kit.gfg.chatapp.devices.BLDevice
import co.kit.gfg.chatapp.viewModel.ChatListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatListFragment : Fragment(R.layout.fragment_paired_device), ChatListAdapter.Interaction {

    private lateinit var chatListAdapter: ChatListAdapter
    private lateinit var emptyChatList: TextView
    private lateinit var recycler: RecyclerView
    private val viewMode: ChatListViewModel by viewModels()
    private var blDevices: List<BLDevice?>? = null
    private val REQUEST_ENABLE_BT = 3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emptyChatList = view.findViewById(R.id.txt_chatListFragment_emptyList)
        recycler = view.findViewById(R.id.recycler_chatListF_items)
        getConnectedDevices()
        initRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_list, container, false)
        /*emptyChatList = view.findViewById(R.id.txt_chatListFragment_emptyList)
        recycler = view.findViewById(R.id.recycler_chatListF_items)
        getConnectedDevices()
        initRecyclerView()*/
    }

    private fun initRecyclerView() {
        recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            chatListAdapter = ChatListAdapter(this@ChatListFragment)
            adapter = chatListAdapter
        }
    }

    private fun getConnectedDevices() {
        viewMode.getConnectedDevices().observe(viewLifecycleOwner) { device ->
            blDevices = device
            Log.d("TAG", "getConnectedDevices:$device")
            showConnectedDevices(device)
        }
    }

    private fun showConnectedDevices(devices: List<BLDevice?>) {
        if (devices.isNotEmpty())
            submitDevices(devices)
        else
            emptyChatList.visibility = View.VISIBLE
    }

    private fun submitDevices(devices: List<BLDevice?>) {
        chatListAdapter.submitList(devices)
    }

    private fun navigateToFragment(item: BLDevice) {
        val bundle = Bundle()
        bundle.putString(Constants.DEVICE_ADDRESS, item.deviceAddress)
        bundle.putString(Constants.DEVICE_NAME, item.deviceName)
        findNavController().navigate(R.id.action_chatListFragment_to_chatFragment, bundle)
    }

    override fun onItemSelected(position: Int, item: BLDevice) {
        navigateToFragment(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_ENABLE_BT -> {
                if (resultCode == Activity.RESULT_OK) {

                } else {
                    Toast.makeText(
                        requireContext(), R.string.bt_not_enabled_leaving,
                        Toast.LENGTH_SHORT
                    ).show()
                    activity?.finish()
                }
            }
        }
    }

}