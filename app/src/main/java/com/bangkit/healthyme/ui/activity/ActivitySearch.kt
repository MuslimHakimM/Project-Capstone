package com.bangkit.healthyme.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.healthyme.data.model.adapter.MakananAdapter
import com.bangkit.healthyme.data.model.food.MakananItem
import com.bangkit.healthyme.databinding.ActivitySearchBinding
import com.bangkit.healthyme.ui.LoginPreference
import com.bangkit.healthyme.ui.viewmodel.SearchViewModel

class ActivitySearch : AppCompatActivity() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: ActivitySearchBinding
    private lateinit var mLoginPreference: LoginPreference
    private lateinit var adapter: MakananAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Home"

        mLoginPreference = LoginPreference(this)
        adapter = MakananAdapter()

        setRecyclerView()
        setupViewModel()
        validate()
    }

    override fun onResume() {
        super.onResume()
        setupViewModel()
    }

    private fun setRecyclerView() {
        binding.apply {
            rvMakanan.layoutManager = LinearLayoutManager(this@ActivitySearch)
            rvMakanan.setHasFixedSize(true)
            rvMakanan.adapter = adapter

        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        viewModel.getMakanan(mLoginPreference.getUser().accessToken)
        viewModel.listMakanan.observe(this) {
            if (it != null) {
                adapter.setMakanan(it as ArrayList<MakananItem>)
            }
        }
        viewModel.isLoading.observe(this) { showLoading(it) }
    }

    private fun validate() {
        if (!mLoginPreference.getUser().isLogin) {
            val login = mLoginPreference.getUser().isLogin
            Log.d("tag", login.toString())
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this@ActivitySearch as Activity).toBundle()
            )
            finish()
        }
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}
