package com.myprog.colorswitch.fragment

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myprog.colorswitch.R
import com.myprog.colorswitch.model.Colors
import com.skydoves.expandablelayout.ExpandableLayout


class ColorSwitchFragment : Fragment() {

    private lateinit var colors: ArrayList<Colors>
    lateinit var green: Colors
    lateinit var red: Colors
    lateinit var yellow: Colors
    lateinit var blue: Colors
    lateinit var recyclerView: RecyclerView
    private var adapter: ColorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        colorInit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_color_switch, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ColorAdapter(colors)
        recyclerView.adapter = adapter
        return view
    }

    private fun colorInit() {
        green = Colors("Green", R.color.green, false)
        red = Colors("Red", R.color.red, false)
        yellow = Colors("Yellow", R.color.yellow,false)
        blue = Colors("Blue", R.color.blue,false)
        colors = arrayListOf(green, red, yellow, blue)
    }

    companion object {
        fun newInstance(): ColorSwitchFragment {
            return ColorSwitchFragment()
        }
    }

    private inner class ColorAdapter(var colors: ArrayList<Colors>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = layoutInflater.inflate(R.layout.color_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
           /*
            val bitmap = Bitmap.createBitmap(200,300, Bitmap.Config.ARGB_8888)
            bitmap.eraseColor(colors[position].colors)
            imageView.setImageBitmap(bitmap)*/

            val color: Colors = colors[position]
            val titleColor: TextView = holder.itemView.findViewById(R.id.title_color)
            val imageView: ImageView = holder.itemView.findViewById(R.id.colorView)
            titleColor.setText(color.title)



        }

        override fun getItemCount(): Int {
            return colors.size
        }

    }

    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {




        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {


        }

    }
}