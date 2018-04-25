package es.aivm.mdexpansionpanel


import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import es.aivm.expansionpanel.ExpansionLayout
import es.aivm.expansionpanel.viewgroup.ExpansionLayoutCollection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.subtitle ="Material Design Kotlin"

        tabs.addTab(tabs.newTab().setText("TAB1"))
        tabs.addTab(tabs.newTab().setText("TAB2"))
        tabs.addTab(tabs.newTab().setText("TAB3"))

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        recycler_view.layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerAdapter()
        recycler_view.adapter = adapter

        //fill with empty objects
        val list = ArrayList<Any>()
        for (i in 0..29) {
            list.add(Any())
        }
        adapter.setItems(list)
    }

    class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {

        private val list = ArrayList<Any>()

        private val expansionsCollection = ExpansionLayoutCollection()

        init {
            expansionsCollection.openOnlyOne(true)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
            return RecyclerHolder.buildFor(parent)
        }

        override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
            holder.bind(list[position])

            expansionsCollection.add(holder.expansionLayout)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        fun setItems(items: List<Any>) {
            this.list.addAll(items)
            notifyDataSetChanged()
        }

        class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            companion object {

                private val LAYOUT = R.layout.recycler_cell

                fun buildFor(viewGroup: ViewGroup): RecyclerHolder {
                    return RecyclerHolder(LayoutInflater.from(viewGroup.context).inflate(LAYOUT, viewGroup, false))
                }
            }
            var expansionLayout: ExpansionLayout
                internal set

            init {
                expansionLayout = itemView.findViewById(R.id.expansionLayout)
            }

            fun bind(`object`: Any) {
                expansionLayout.collapse(false)
            }
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
