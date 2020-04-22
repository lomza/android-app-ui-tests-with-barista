package com.lomza.uitestsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_groceries_list.*

class GroceriesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groceries_list)

        setSupportActionBar(vGroceriesListToolbar)
        setGroceriesList()
    }

    private fun setGroceriesList() {
        val listManager = LinearLayoutManager(this)
        val listAdapter = GroceriesListAdapter(getGroceriesArray()) { grocery ->
            goToGroceryDetails(grocery)
        }

        vGroceriesRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = listManager
            adapter = listAdapter
        }
    }

    private fun getGroceriesArray(): Array<String> {
        return arrayOf(
            "Bread", "Pasta", "Cereals", "Milk", "Cheese",
            "Eggs", "Butter", "Fruit", "Vegetables", "Honey"
        )
    }

    private fun goToGroceryDetails(grocery: String) {
        val groceryDetailsIntent = Intent(this, GroceryDetailsActivity::class.java)
        groceryDetailsIntent.putExtra(GroceryDetailsActivity.EXTRA_GROCERY_NAME, grocery)
        startActivity(groceryDetailsIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_groceries_list, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_help -> {
                showHelpWebPage()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showHelpWebPage() {
        val groceryListWebsite = getString(R.string.groceries_list_help_url)
        val groceryListIntent = Intent(Intent.ACTION_VIEW, Uri.parse(groceryListWebsite))
        startActivity(groceryListIntent)
    }
}