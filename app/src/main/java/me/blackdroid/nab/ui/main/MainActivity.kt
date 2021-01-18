package me.blackdroid.nab.ui.main

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import me.blackdroid.nab.R
import me.blackdroid.nab.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel : MainViewModel

    private val adapter by lazy {
        DailyAdapter( emptyList() ).apply {
            itemClickEvent.subscribe {
                
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java).apply {
            foreCast.observe(this@MainActivity, Observer {forecast ->
                forecast.list?.let {
                    adapter.data = it
                }
            })
        }

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).also {
            it.lifecycleOwner = this@MainActivity
            it.viewmodel = viewModel
            it.executePendingBindings()
            setUpRecyclerView()
        }

        btnLoad.setOnClickListener {
            viewModel.loadDaily()
        }

    }

    private fun setUpRecyclerView() {
        rvDaily.adapter = adapter
        rvDaily.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
    }
}