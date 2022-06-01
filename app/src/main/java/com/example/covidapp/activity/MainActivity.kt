package com.example.covidapp.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.covidapp.R
import com.example.covidapp.api.ApiConfig
import com.example.covidapp.databinding.ActivityMainBinding
import com.example.covidapp.fragment.BerandaFragment
import com.example.covidapp.fragment.InfoAppFragment
import com.example.covidapp.fragment.ProvinsiFragment
import com.example.covidapp.model.ResponseItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_beranda.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val fragHome: Fragment = BerandaFragment()
    val fragProvinsi: Fragment = ProvinsiFragment()
    val fragInfo: Fragment = InfoAppFragment()

    val fm: FragmentManager = supportFragmentManager
    var active: Fragment = fragHome


    private lateinit var menuItem: MenuItem
    private lateinit var menu: Menu
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showDataIndonesia()
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        setupBottomMenu()


    }

    private fun showDataIndonesia() {
        ApiConfig.instance.getIndonesia().enqueue(object : Callback<ArrayList<ResponseItem>> {
            override fun onResponse(
                call: Call<ArrayList<ResponseItem>>,
                response: Response<ArrayList<ResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val indonesiaResponse = response.body()!![0]
                    tv_sembuh.text = indonesiaResponse.sembuh
                    tv_meninggal.text = indonesiaResponse.meninggal
                    tv_positif.text = indonesiaResponse.positif
                    tv_rawat.text = indonesiaResponse.dirawat
                }
            }

            override fun onFailure(call: Call<ArrayList<ResponseItem>>, t: Throwable) {
                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }




//    private fun showDataIndonesia() {
//        ApiConfig.instance.getIndonesia().enqueue(object : Callback<ArrayList<ResponseCovid>>{
//            override fun onResponse(
//                call: Call<ArrayList<ResponseCovid>>,
//                response: Response<ArrayList<ResponseCovid>>
//            ) {
//                if (response.isSuccessful){
//                    val ResponseCorona = response.body()!![0]
//                    tv_sembuh.text = ResponseCorona.sembuh
//                    tv_meninggal.text = ResponseCorona.meninggal
//                    tv_positif.text = ResponseCorona.positif
//                    tv_rawat.text = ResponseCorona.dirawat
//
//                }
//            }
//
//            override fun onFailure(call: Call<ArrayList<ResponseCovid>>, t: Throwable) {
//                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }

    private fun setupBottomMenu() {
        fm.beginTransaction().add(R.id.nav_container, fragHome).show(fragHome).commit()
        fm.beginTransaction().add(R.id.nav_container, fragProvinsi).hide(fragProvinsi).commit()
        fm.beginTransaction().add(R.id.nav_container, fragInfo).hide(fragInfo).commit()

        menu = btn_nav_view.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        btn_nav_view.setOnNavigationItemSelectedListener { item->

            when(item.itemId){
                R.id.navigation_beranda -> {
                    callFrag(0,fragHome)
                }

                R.id.navigation_provinsi -> {
                    callFrag(1, fragProvinsi)
                }

                R.id.navigation_info_app -> {
                    callFrag(2, fragInfo)
                }
            }

            false

        }
    }

    private fun callFrag(i: Int, fragment: Fragment){
        menuItem = menu.getItem(i)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}