package com.example.covidapp.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covidapp.R
import com.example.covidapp.model.ResponseItem

class ApiAdapter (val dataCovid: List<ResponseItem?>?): RecyclerView.Adapter<ApiAdapter.MyViewHolder>(){
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPositif =view.findViewById<TextView>(R.id.tv_positif)
        val tvSembuh =view.findViewById<TextView>(R.id.tv_sembuh)
        val tvMeninggal =view.findViewById<TextView>(R.id.tv_meninggal)
        val tvDirawat =view.findViewById<TextView>(R.id.tv_rawat)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_beranda, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvPositif.text = dataCovid?.get(position)?.positif
        holder.tvSembuh.text = dataCovid?.get(position)?.sembuh
        holder.tvMeninggal.text = dataCovid?.get(position)?.meninggal
        holder.tvDirawat.text = dataCovid?.get(position)?.dirawat
    }

    override fun getItemCount(): Int {
        if (dataCovid != null){
            return dataCovid.size
        }
        return 0
    }
}