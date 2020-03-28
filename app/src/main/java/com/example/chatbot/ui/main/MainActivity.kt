package com.example.chatbot.ui.main

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.chatbot.R
import com.example.chatbot.base.BaseActivity
import com.example.chatbot.base.BaseAdapter
import com.example.chatbot.databinding.ActivityMainBinding
import com.example.chatbot.ui.main.recyclerview.MessageAdapter
import timber.log.Timber
import kotlin.random.Random

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    companion object {
        private const val NEXT_LONG_UNTIL: Long = 5000
        private const val NEXT_LONG_MIN: Long = 1000
    }

    private val itemClicked: BaseAdapter.OnItemClickListener = object :
        BaseAdapter.OnItemClickListener {
        override fun onItemClick(v: View, position: Int) {
            Toast.makeText(
                this@MainActivity,
                messageAdapter.getItemAt(position)?.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private val generateItem: Runnable = object : Runnable {
        override fun run() {
            val item = viewModel.getMessageItem()
            Timber.e(item.toString())
            messageAdapter.addNewItem(item)
            handler.postDelayed(this, random())
            with(binding.recyclerView) {
                layoutManager?.smoothScrollToPosition(this, null, 0)
            }
        }
    }
    private val messageAdapter = MessageAdapter(onItemClickListener = itemClicked)
    private val handler = Handler()

    override fun layoutRes() = R.layout.activity_main

    override fun viewModelClass() = MainViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        addItemDynamically()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_main_pause -> {
                stopAddItem()
                true
            }
            R.id.menu_main_resume -> {
                resumeAddItem()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        }
    }

    private fun stopAddItem() {
        handler.removeCallbacksAndMessages(null)
    }

    private fun resumeAddItem() {
        addItemDynamically(NEXT_LONG_MIN)
    }

    private fun setupRecyclerView() {
        messageAdapter.setDataList(viewModel.getMessageList())
        binding.recyclerView.adapter = messageAdapter
    }

    private fun random() = Random.nextLong(NEXT_LONG_UNTIL) + NEXT_LONG_MIN

    private fun addItemDynamically(delay: Long = random()) {
        handler.postDelayed(generateItem, delay)
    }

}
