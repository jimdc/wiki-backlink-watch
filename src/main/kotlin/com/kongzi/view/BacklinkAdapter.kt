package com.kongzi.view

import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.kongzi.model.WikiApiService.BacklinkModel.Backlink
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.kongzi.R
import com.kongzi.model.WikiNamespace

public class BacklinkAdapter(var blinks: List<Backlink>) : RecyclerView.Adapter<BacklinkAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var row1 = v.findViewById<TextView>(R.id.row1)
        var row2 = v.findViewById<TextView>(R.id.row2)
        var bEdit = v.findViewById<ImageView>(R.id.imgEditLink)
        init {
            bEdit.setOnClickListener {
                var url = "https://en.wikipedia.org/w/index.php?title=${row1.text}&action=edit"
                var intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
                if (intent.resolveActivity(it.context.packageManager) != null) {
                    it.context.startActivity(intent)
                } else {
                    Log.d("BacklinkAdapter", "No webbrowser to open edit link!")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BacklinkAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.brow_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        blinks[position].let {
            val namespace = WikiNamespace.values().get(it.ns).name
            holder.row1?.text = it.title
            holder.row2?.text = "Row #${position}: ${namespace} #${it.pageid}"
        }
    }

    /**
     * @todo: something more efficient than this.
     */
    fun refresh(newitems: List<Backlink>) {
        blinks = newitems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return blinks.size
    }
}