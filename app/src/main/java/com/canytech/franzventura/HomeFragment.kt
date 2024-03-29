package com.canytech.franzventura

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_fragment.view.*
import kotlinx.android.synthetic.main.item_theory_music.view.*

class HomeFragment : Fragment() {

    private lateinit var names: Array<String>
    private lateinit var imageResIds: IntArray

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)

        val activity = activity as Context
        val theoryRecyclerView = view.rv_music_theory
        val sheetRecyclerView = view.rv_sheet_music
        val classicRecyclerView = view.rv_classics

        theoryRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        theoryRecyclerView.adapter = TheoryListAdapter()

        sheetRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        sheetRecyclerView.adapter = TheoryListAdapter()

        classicRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        classicRecyclerView.adapter = TheoryListAdapter()

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val resources = context.resources
        names = resources.getStringArray(R.array.names)

        val typedArray = resources.obtainTypedArray(R.array.images)
        val imageCount = names.size
        imageResIds = IntArray(imageCount)
        for (i in 0 until imageCount) {
            imageResIds[i] = typedArray.getResourceId(i, 0)
        }
        typedArray.recycle()

    }

    internal inner class TheoryListAdapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
            ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_theory_music, viewGroup, false
                )
            )

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val theoryItem = TheoryItem(
                names[position],
                imageResIds[position]
            )
            viewHolder.bind(theoryItem)
        }

        override fun getItemCount() = names.size
    }

}

internal class ViewHolder constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(theoryItem: TheoryItem) {
        itemView.img_cover_theory.setImageResource(theoryItem.imageResId)
        itemView.title_class.text = theoryItem.name
    }
}

