package com.kongzi.past

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
import com.kongzi.future.WikiNamespace

public class BacklinksAdapter(private var backlinks: List<Backlink>) : RecyclerView.Adapter<BacklinksAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var row1: TextView? = view.findViewById(R.id.row1)
        var row2: TextView? = view.findViewById(R.id.row2)
        private var btnEdit: ImageView? = view.findViewById(R.id.imgEditLink)
        init {
            btnEdit?.setOnClickListener { viewFromButton ->
                val url = "https://en.wikipedia.org/w/index.php?title=${row1?.text}&action=edit"
                val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
                if (intent.resolveActivity(view.context.packageManager) != null) {
                    viewFromButton.context.startActivity(intent)
                } else {
                    Log.d("BacklinksAdapter", "No webbrowser to open edit link!")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.backlinks_row_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        backlinks[position].let { backlink ->
            val namespace = WikiNamespace.values()[backlink.ns].name
            holder.row1?.text = backlink.title
            holder.row2?.text = "Row #$position: $namespace #${backlink.pageid}"
        }
    }

    /**
     * @todo: something more efficient than this.
     */
    fun refresh(newItems: List<Backlink>) {
        backlinks = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return backlinks.size
    }
}