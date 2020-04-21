package com.lomza.uitestsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_grocery_details.*

class GroceryDetailsActivity : AppCompatActivity() {

    private lateinit var grocery: String

    private val deleteConfirmationDialog: AlertDialog by lazy {
        let { activity ->
            val builder = AlertDialog.Builder(activity)
            builder.apply {
                setMessage(R.string.grocery_details_note_delete_dialog_title)
                setPositiveButton(R.string.grocery_details_note_delete_dialog_yes) { _, _ ->
                    Toast.makeText(activity, getString(R.string.grocery_delete_toast_text), Toast.LENGTH_SHORT).show()
                }
                setNegativeButton(R.string.grocery_details_note_delete_dialog_no) { _, _ -> }
            }
            builder.create()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grocery_details)

        grocery = intent.getStringExtra(EXTRA_GROCERY_NAME) as String
        setupToolbar()
        setupView()
    }

    private fun setupToolbar() {
        vGroceryDetailsToolbar.title = grocery
        setSupportActionBar(vGroceryDetailsToolbar)
    }

    private fun setupView() {
        vGroceryDetailsClearButton.setOnClickListener {
            vGroceryDetailsNoteEdit.text.clear()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_grocery_details, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                deleteConfirmationDialog.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_GROCERY_NAME = "grocery_name"
    }
}