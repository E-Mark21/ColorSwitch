package com.myprog.colorswitch.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myprog.colorswitch.R
import com.myprog.colorswitch.model.Colors


class ColorSwitchFragment : Fragment() {

    private lateinit var colors: ArrayList<Colors>
    private lateinit var green: Colors
    private lateinit var red: Colors
    private lateinit var yellow: Colors
    private lateinit var blue: Colors
    private lateinit var greenFromColorXml: Colors
    private lateinit var redFromColorXml: Colors
    private lateinit var yellowFromColorXml: Colors
    private lateinit var blueFromColorXml: Colors
    private lateinit var recyclerView: RecyclerView
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
        green = Colors("Green", Color.GREEN)
        red = Colors("Red", Color.RED)
        yellow = Colors("Yellow", Color.YELLOW)
        blue = Colors("Blue", Color.BLUE)
        greenFromColorXml = Colors("Green from XML", R.color.green)
        redFromColorXml = Colors("Red from XML", R.color.red)
        yellowFromColorXml = Colors("Yellow from XML", R.color.yellow)
        blueFromColorXml = Colors("Blue from XML", R.color.blue)
        colors = arrayListOf(green, greenFromColorXml, red, redFromColorXml, yellow, yellowFromColorXml, blue, blueFromColorXml)
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

        @SuppressLint("ResourceAsColor")
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            val color: Colors = colors[position]
            val titleColor: TextView = holder.itemView.findViewById(R.id.title_color)
            val titleColorExp : TextView = holder.itemView.findViewById(R.id.title_color_exp)
            val imageView: ImageView = holder.itemView.findViewById(R.id.colorView)
            val linearLayout: LinearLayout = holder.itemView.findViewById(R.id.linear_layout)
            val constraintLayout: ConstraintLayout = holder.itemView.findViewById(R.id.expandableLayout)
            val isExpanded = color.isVisible
            constraintLayout.visibility = if(isExpanded) {View.VISIBLE} else{View.GONE}

            linearLayout.setOnClickListener {
                color.isVisible = (!color.isVisible)
                notifyItemChanged(position)
            }
            constraintLayout.setOnClickListener {
                color.isVisible = (!color.isVisible)
                notifyItemChanged(position)
            }
            val bitmap: Bitmap = Bitmap.createBitmap(1000,300,Bitmap.Config.ARGB_8888)
            bitmap.eraseColor(color.color)
            titleColor.text = color.title
            titleColor.setTextColor(color.color)
            titleColorExp.text = color.title
            imageView.setImageBitmap(bitmap)
            titleColor.visibility = if (!isExpanded) {View.VISIBLE} else{View.GONE}

        }

        override fun getItemCount(): Int {
            return colors.size
        }

    }

    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}