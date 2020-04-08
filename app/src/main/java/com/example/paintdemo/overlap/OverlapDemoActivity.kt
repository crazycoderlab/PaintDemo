package com.example.paintdemo.overlap

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paintdemo.R
import com.example.paintdemo.overlap.view.BlendModeDemoView
import com.example.paintdemo.overlap.view.XfermodeDemoView

class OverlapDemoActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overlap)
        val recyclerView = findViewById<RecyclerView>(R.id.overlay_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = OverLayAdapter(this)
        recyclerView.postDelayed({
            recyclerView.postInvalidate()
        },200)
    }
}

data class ItemData(val blendMode: Int,val xfermode: Int,val blendName: String)

class OverLayAdapter constructor(val context: Context): RecyclerView.Adapter<OverLayAdapter.MyViewHolder>() {

    companion object{
        val DATA = listOf(
            ItemData(-1,-1,"设置BlendMode"),
            ItemData(0,-1,"CLEAR"),
            ItemData(1,-1,"SRC"),
            ItemData(2,-1,"DST"),
            ItemData(3,-1,"SRC_OVER"),
            ItemData(4,-1,"DST_OVER"),
            ItemData(5,-1,"SRC_IN"),
            ItemData(6,-1,"DST_IN"),
            ItemData(7,-1,"SRC_OUT"),
            ItemData(8,-1,"DST_OUT"),
            ItemData(9,-1,"SRC_ATOP"),
            ItemData(10,-1,"DST_ATOP"),
            ItemData(11,-1,"XOR"),
            ItemData(12,-1,"PLUS"),
            ItemData(13,-1,"MODULATE"),
            ItemData(14,-1,"SCREEN"),
            ItemData(15,-1,"OVERLAY"),
            ItemData(16,-1,"DARKEN"),
            ItemData(17,-1,"LIGHTEN"),
            ItemData(18,-1,"COLOR_DODGE"),
            ItemData(19,-1,"COLOR_BURN"),
            ItemData(20,-1,"HARD_LIGHT"),
            ItemData(21,-1,"SOFT_LIGHT"),
            ItemData(22,-1,"DIFFERENCE"),
            ItemData(23,-1,"EXCLUSION"),
            ItemData(24,-1,"MULTIPLY"),
            ItemData(25,-1,"HUE"),
            ItemData(26,-1,"SATURATION"),
            ItemData(27,-1,"COLOR"),
            ItemData(28,-1,"LUMINOSITY"),
            ItemData(-1,-1,"设置XferMode"),
            ItemData(-1,0,"CLEAR"),
            ItemData(-1,1,"SRC"),
            ItemData(-1,2,"DST"),
            ItemData(-1,3,"SRC_OVER"),
            ItemData(-1,4,"DST_OVER"),
            ItemData(-1,5,"SRC_IN"),
            ItemData(-1,6,"DST_IN"),
            ItemData(-1,7,"SRC_OUT"),
            ItemData(-1,8,"DST_OUT"),
            ItemData(-1,9,"SRC_ATOP"),
            ItemData(-1,10,"DST_ATOP"),
            ItemData(-1,11,"XOR"),
            ItemData(-1,12,"DARKEN"),
            ItemData(-1,13,"LIGHTEN"),
            ItemData(-1,14,"MULTIPLY"),
            ItemData(-1,15,"SCREEN"),
            ItemData(-1,16,"ADD"),
            ItemData(-1,17,"OVERLAY"))
    }

    class MyViewHolder(private var myView: View) : RecyclerView.ViewHolder(myView){
        val textName: TextView = itemView.findViewById(R.id.overlap_item_name)
        val textMode: TextView = itemView.findViewById(R.id.overlap_item_modeName)
        val blendModeDemoView: BlendModeDemoView = itemView.findViewById(R.id.overlap_item_blend)
        val xfermodeDemoView: XfermodeDemoView = itemView.findViewById(R.id.overlap_item_xfer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_overlap_item,parent,false))
    }

    override fun getItemCount(): Int {
        return DATA.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(DATA[position].blendMode == -1 && DATA[position].xfermode == -1){
            holder.textName.visibility = View.VISIBLE
            holder.textMode.visibility = View.GONE
            holder.blendModeDemoView.visibility = View.GONE
            holder.xfermodeDemoView.visibility = View.GONE
            holder.textName.text = DATA[position].blendName
        }else{
            holder.textName.visibility = View.GONE
            holder.textMode.visibility = View.VISIBLE
            holder.textMode.text = DATA[position].blendName
            if(DATA[position].blendMode >= 0) {
                holder.blendModeDemoView.visibility = View.VISIBLE
                holder.xfermodeDemoView.visibility = View.GONE
                holder.blendModeDemoView.mode = DATA[position].blendMode
            }else{
                holder.xfermodeDemoView.visibility = View.VISIBLE
                holder.blendModeDemoView.visibility = View.GONE
                holder.xfermodeDemoView.mode = DATA[position].xfermode
            }
        }
    }
}
