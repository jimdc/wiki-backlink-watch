package com.kongzi.view

import android.content.Intent
import android.net.Uri
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

public class BacklinksAdapter(var blinks: List<Backlink>) : RecyclerView.Adapter<BacklinksAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var row1 = v.findViewById<TextView>(R.id.row1)
        var row2 = v.findViewById<TextView>(R.id.row2)
        var bEdit = v.findViewById<ImageView>(R.id.imgEditLink)
        init {
            bEdit.setOnClickListener {vv ->
                var url = "https://en.wikipedia.org/w/index.php?title=${row1.text}&action=edit"
                var intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
                if (intent.resolveActivity(v.context.packageManager) != null) {
                    vv.context.startActivity(intent)
                } else {
                    Log.d("BacklinksAdapter", "No webbrowser to open edit link!")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : BacklinksAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.backlinks_row_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        blinks[position].let { backlink ->
            val namespace = WikiNamespace.values().get(backlink.ns).name
            holder.row1.text = backlink.title
            holder.row2.text = "Row #${position}: ${namespace} #${backlink.pageid}"
        }
    }

    /**
     * @todo: something more efficient than this.
     */
    fun refresh(newItems: List<Backlink>) {
        blinks = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return blinks.size
    }
}