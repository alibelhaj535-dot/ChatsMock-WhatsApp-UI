package com.playfake.chatsmock

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatActivity : AppCompatActivity() {
    private lateinit var adapter: MessageAdapter
    private val messages = mutableListOf<Message>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        
        adapter = MessageAdapter(messages)
        findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = this@ChatActivity.adapter
        }
        
        findViewById<ImageView>(R.id.send_button).setOnClickListener {
            val input = findViewById<EditText>(R.id.message_input).text.toString()
            if (input.isNotBlank()) {
                messages.add(Message(input, true))
                adapter.notifyItemInserted(messages.size - 1)
                findViewById<EditText>(R.id.message_input).text.clear()
                messages.add(Message("رد تلقائي: $input", false))
                adapter.notifyItemInserted(messages.size - 1)
            }
        }
    }
}
